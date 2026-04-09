package org.dromara.myCustomer.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.domain.dto.RoleDTO;
import org.dromara.common.core.domain.model.LoginUser;
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
import org.dromara.myCustomer.domain.bo.DcCustomerTrackingBo;
import org.dromara.myCustomer.domain.vo.DcCustomerTrackingVo;
import org.dromara.myCustomer.service.IDcCustomerTrackingService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户跟踪
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/myCustomer/customerTracking")
public class DcCustomerTrackingController extends BaseController {

    private final IDcCustomerTrackingService dcCustomerTrackingService;
    private final CommonService commonService;

    /**
     * 查询客户跟踪列表
     */
    @SaCheckPermission("myCustomer:customerTracking:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerTrackingVo> list(DcCustomerTrackingBo bo, PageQuery pageQuery) {
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
        return dcCustomerTrackingService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户跟踪列表
     */
    @SaCheckPermission("myCustomer:customerTracking:export")
    @Log(title = "客户跟踪", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerTrackingBo bo, HttpServletResponse response) {
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
        List<DcCustomerTrackingVo> list = dcCustomerTrackingService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户跟踪", DcCustomerTrackingVo.class, response);
    }

    /**
     * 获取客户跟踪详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("myCustomer:customerTracking:query")
    @GetMapping("/{id}")
    public R<DcCustomerTrackingVo> getInfo(@NotNull(message = "主键不能为空")
                                           @PathVariable Long id) {
        return R.ok(dcCustomerTrackingService.queryById(id));
    }

    /**
     * 新增客户跟踪
     */
    @SaCheckPermission("myCustomer:customerTracking:add")
    @Log(title = "客户跟踪", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerTrackingBo bo) {
        return toAjax(dcCustomerTrackingService.insertByBo(bo));
    }

    /**
     * 修改客户跟踪
     */
    @SaCheckPermission("myCustomer:customerTracking:edit")
    @Log(title = "客户跟踪", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerTrackingBo bo) {
        return toAjax(dcCustomerTrackingService.updateByBo(bo));
    }

    /**
     * 删除客户跟踪
     *
     * @param ids 主键串
     */
    @SaCheckPermission("myCustomer:customerTracking:remove")
    @Log(title = "客户跟踪", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerTrackingService.deleteWithValidByIds(List.of(ids), true));
    }
}
