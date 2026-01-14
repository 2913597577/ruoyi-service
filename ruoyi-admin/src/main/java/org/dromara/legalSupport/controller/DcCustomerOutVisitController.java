package org.dromara.legalSupport.controller;

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
import org.dromara.legalSupport.domain.bo.DcCustomerOutVisitBo;
import org.dromara.legalSupport.domain.vo.DcCustomerOutVisitVo;
import org.dromara.legalSupport.service.IDcCustomerOutVisitService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户出访记录
 *
 * @author Lion Li
 * @date 2025-10-16
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customerOutVisit/customerOutVisit")
public class DcCustomerOutVisitController extends BaseController {

    private final IDcCustomerOutVisitService dcCustomerOutVisitService;
    private final CommonService commonService;

    /**
     * 查询客户出访记录列表
     */
    @SaCheckPermission("customerOutVisit:customerOutVisit:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerOutVisitVo> list(DcCustomerOutVisitBo bo, PageQuery pageQuery) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return null;
        }
        String deptCategory = loginUser.getDeptCategory();
        String city = null;
        if (StringUtils.isNotBlank(deptCategory) && !("ADMIN").equals(deptCategory)) {
            city = deptCategory.substring(0, deptCategory.indexOf('_'));
            List<Long> customerIds = commonService.getCustomerIdsByCity(city);
            bo.setCustomerIds(customerIds);
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
        return dcCustomerOutVisitService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户出访记录列表
     */
    @SaCheckPermission("customerOutVisit:customerOutVisit:export")
    @Log(title = "客户出访记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerOutVisitBo bo, HttpServletResponse response) {
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
        List<DcCustomerOutVisitVo> list = dcCustomerOutVisitService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户出访记录", DcCustomerOutVisitVo.class, response);
    }

    /**
     * 获取客户出访记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customerOutVisit:customerOutVisit:query")
    @GetMapping("/{id}")
    public R<DcCustomerOutVisitVo> getInfo(@NotNull(message = "主键不能为空")
                                           @PathVariable Long id) {
        return R.ok(dcCustomerOutVisitService.queryById(id));
    }

    /**
     * 新增客户出访记录
     */
    @SaCheckPermission("customerOutVisit:customerOutVisit:add")
    @Log(title = "客户出访记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerOutVisitBo bo) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.fail("请先登录");
        }
        return toAjax(dcCustomerOutVisitService.insertByBo(bo));
    }

    /**
     * 修改客户出访记录
     */
    @SaCheckPermission("customerOutVisit:customerOutVisit:edit")
    @Log(title = "客户出访记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerOutVisitBo bo) {
        return toAjax(dcCustomerOutVisitService.updateByBo(bo));
    }

    /**
     * 删除客户出访记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customerOutVisit:customerOutVisit:remove")
    @Log(title = "客户出访记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerOutVisitService.deleteWithValidByIds(List.of(ids), true));
    }
}
