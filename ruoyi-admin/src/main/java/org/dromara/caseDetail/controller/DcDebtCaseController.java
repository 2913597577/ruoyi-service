package org.dromara.caseDetail.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.caseDetail.domain.bo.DcDebtCaseBo;
import org.dromara.caseDetail.domain.vo.DcDebtCaseVo;
import org.dromara.caseDetail.service.IDcDebtCaseService;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.domain.dto.RoleDTO;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 欠款案件表
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/caseDetail/caseDetail")
public class DcDebtCaseController extends BaseController {

    private final IDcDebtCaseService dcDebtCaseService;

    /**
     * 查询欠款案件表列表
     */
    @SaCheckPermission("caseDetail:caseDetail:list")
    @GetMapping("/list")
    public TableDataInfo<DcDebtCaseVo> list(DcDebtCaseBo bo, PageQuery pageQuery) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return null;
        }
        List<RoleDTO> roles = loginUser.getRoles();
        if (roles != null && !roles.isEmpty()) {
            RoleDTO role = roles.get(0);
            if (role.getRoleKey().contains("Employee")) {
                bo.setLegalSupportId(loginUser.getUserId());
            }
            if (role.getRoleKey().contains("Manager")) {
                bo.setCreateDept(loginUser.getDeptId());
            }
        }
        return dcDebtCaseService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出欠款案件表列表
     */
    @SaCheckPermission("caseDetail:caseDetail:export")
    @Log(title = "欠款案件表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcDebtCaseBo bo, HttpServletResponse response) {
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
        List<DcDebtCaseVo> list = dcDebtCaseService.queryList(bo);
        ExcelUtil.exportExcel(list, "欠款案件表", DcDebtCaseVo.class, response);
    }

    /**
     * 获取欠款案件表详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("caseDetail:caseDetail:query")
    @GetMapping("/{id}")
    public R<DcDebtCaseVo> getInfo(@NotNull(message = "主键不能为空")
                                   @PathVariable Long id) {
        return R.ok(dcDebtCaseService.queryById(id));
    }

    /**
     * 新增欠款案件表
     */
    @SaCheckPermission("caseDetail:caseDetail:add")
    @Log(title = "欠款案件表", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcDebtCaseBo bo) {
        return toAjax(dcDebtCaseService.insertByBo(bo));
    }

    /**
     * 修改欠款案件表
     */
    @SaCheckPermission("caseDetail:caseDetail:edit")
    @Log(title = "欠款案件表", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcDebtCaseBo bo) {
        return toAjax(dcDebtCaseService.updateByBo(bo));
    }

    /**
     * 删除欠款案件表
     *
     * @param ids 主键串
     */
    @SaCheckPermission("caseDetail:caseDetail:remove")
    @Log(title = "欠款案件表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcDebtCaseService.deleteWithValidByIds(List.of(ids), true));
    }
}
