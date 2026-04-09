package org.dromara.legalSupport.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.domain.dto.RoleDTO;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.service.CommonService;
import org.dromara.common.web.core.BaseController;
import org.dromara.legalSupport.domain.bo.DcCustomerJobOrderBo;
import org.dromara.legalSupport.domain.vo.DcCustomerJobOrderVo;
import org.dromara.legalSupport.service.IDcCustomerJobOrderService;
import org.dromara.myCustomer.service.IDcCustomerTrackingService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工单管理
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customerJobOrder/customerJobOrder")
public class DcCustomerJobOrderController extends BaseController {

    private final IDcCustomerJobOrderService dcCustomerJobOrderService;

    private final IDcCustomerTrackingService dcCustomerTrackingService;
    private final CommonService commonService;


    /**
     * 查询工单管理列表
     */
    @SaCheckPermission("customerJobOrder:customerJobOrder:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerJobOrderVo> list(DcCustomerJobOrderBo bo, PageQuery pageQuery) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return null;
        }
        String deptCategory = loginUser.getDeptCategory();
        String city = null;
        if (StringUtils.isNotBlank(deptCategory) && !("ADMIN").equals(deptCategory)) {
            city = deptCategory.substring(0, deptCategory.indexOf('_'));
            //List<Long> customerIds = commonService.getCustomerIdsByCity(city);
            //bo.setCustomerIds(customerIds);
            bo.setRemark2(city);
        }
        List<RoleDTO> roles = loginUser.getRoles();
        if (roles != null && !roles.isEmpty()) {
            RoleDTO role = roles.get(0);
            if (role.getRoleKey().equals("LegalSupport_Employee")) {
                bo.setLegalSupportId(loginUser.getUserId());
            }
            if (role.getRoleKey().equals("LegalSupport_Manager")) {
                bo.setCreateDept(loginUser.getDeptId());
            }
        }
        return dcCustomerJobOrderService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出工单管理列表
     */
    @SaCheckPermission("customerJobOrder:customerJobOrder:export")
    @Log(title = "工单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerJobOrderBo bo, HttpServletResponse response) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return;
        }
        List<RoleDTO> roles = loginUser.getRoles();
        if (roles != null && !roles.isEmpty()) {
            RoleDTO role = roles.get(0);
            if (role.getRoleKey().equals("LegalSupport_Employee")) {
                bo.setLegalSupportId(loginUser.getUserId());
            }
        }
        List<DcCustomerJobOrderVo> list = dcCustomerJobOrderService.queryList(bo);
        ExcelUtil.exportExcel(list, "工单管理", DcCustomerJobOrderVo.class, response);
    }

    /**
     * 获取工单管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customerJobOrder:customerJobOrder:query")
    @GetMapping("/{id}")
    public R<DcCustomerJobOrderVo> getInfo(@NotNull(message = "主键不能为空")
                                           @PathVariable Long id) {
        return R.ok(dcCustomerJobOrderService.queryById(id));
    }

    /**
     * 新增工单管理
     */
    @SaCheckPermission("customerJobOrder:customerJobOrder:add")
    @Log(title = "工单管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerJobOrderBo bo) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("请登录");
        }
        return toAjax(dcCustomerJobOrderService.insertByBo(bo));
    }

    /**
     * 修改工单管理
     */
    @SaCheckPermission("customerJobOrder:customerJobOrder:edit")
    @Log(title = "工单管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerJobOrderBo bo) {
        return toAjax(dcCustomerJobOrderService.updateByBo(bo));
    }

    /**
     * 删除工单管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customerJobOrder:customerJobOrder:remove")
    @Log(title = "工单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerJobOrderService.deleteWithValidByIds(List.of(ids), true));
    }

    /**
     * 接工单
     */
    @GetMapping("/accept")
    public R<Void> accept(@RequestParam String id) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("请登录");
        }
        if (loginUser.getDeptId() != 1945465788862627841L) {
            return R.warn("非法务中心员工不可接工单");
        }
        DcCustomerJobOrderVo vo = dcCustomerJobOrderService.queryById(Long.valueOf(id));
        if (vo == null) {
            return R.warn("工单不存在");
        }
        if (vo.getContractHandler() != null) {
            return R.warn("工单已处理");
        }
        vo.setContractHandler(loginUser.getUserId());
        vo.setContractHandlerName(loginUser.getUsername());
        vo.setProcessingStatus(1);
        DcCustomerJobOrderBo update = new DcCustomerJobOrderBo();
        MapstructUtils.convert(vo, update);
        return toAjax(dcCustomerJobOrderService.updateByBo(update));
    }
}
