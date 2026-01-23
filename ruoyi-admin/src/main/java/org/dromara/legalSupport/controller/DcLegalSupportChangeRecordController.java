package org.dromara.legalSupport.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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
import org.dromara.legalSupport.domain.bo.DcLegalSupportChangeRecordBo;
import org.dromara.legalSupport.domain.vo.DcLegalSupportChangeRecordVo;
import org.dromara.legalSupport.service.IDcLegalSupportChangeRecordService;
import org.dromara.system.domain.vo.SysUserVo;
import org.dromara.system.service.ISysUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 法务支持变更
 *
 * @author Lion Li
 * @date 2025-11-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/legalSupportChange/legalSupportChange")
public class DcLegalSupportChangeRecordController extends BaseController {

    private final IDcLegalSupportChangeRecordService dcLegalSupportChangeRecordService;
    private final ISysUserService sysUserService;

    /**
     * 查询法务支持变更列表
     */
    @SaCheckPermission("legalSupportChange:legalSupportChange:list")
    @GetMapping("/list")
    public TableDataInfo<DcLegalSupportChangeRecordVo> list(DcLegalSupportChangeRecordBo bo, PageQuery pageQuery) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return null;
        }
        List<RoleDTO> roles = loginUser.getRoles();
        if (roles != null && !roles.isEmpty()) {
            RoleDTO role = roles.get(0);
            if (role.getRoleKey().equals("LegalSupport_Employee")) {
                bo.setLegalSupportId(loginUser.getUserId());
            }
            if (role.getRoleKey().equals("LegalSupport_Manager")) {
                List<SysUserVo> userList = sysUserService.selectUserListByDept(loginUser.getDeptId());
                List<Long> userIdList = userList.stream().map(SysUserVo::getUserId).toList();
                bo.setLegalSupportIds(userIdList);
            }
        }
        return dcLegalSupportChangeRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出法务支持变更列表
     */
    @SaCheckPermission("legalSupportChange:legalSupportChange:export")
    @Log(title = "法务支持变更", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcLegalSupportChangeRecordBo bo, HttpServletResponse response) {
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
            if (role.getRoleKey().equals("LegalSupport_Manager")) {
                List<SysUserVo> userList = sysUserService.selectUserListByDept(loginUser.getDeptId());
                List<Long> userIdList = userList.stream().map(SysUserVo::getUserId).toList();
                bo.setLegalSupportIds(userIdList);
            }
        }
        List<DcLegalSupportChangeRecordVo> list = dcLegalSupportChangeRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "法务支持变更", DcLegalSupportChangeRecordVo.class, response);
    }

    /**
     * 获取法务支持变更详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("legalSupportChange:legalSupportChange:query")
    @GetMapping("/{id}")
    public R<DcLegalSupportChangeRecordVo> getInfo(@NotNull(message = "主键不能为空")
                                                   @PathVariable Long id) {
        return R.ok(dcLegalSupportChangeRecordService.queryById(id));
    }

    /**
     * 新增法务支持变更
     */
    @SaCheckPermission("legalSupportChange:legalSupportChange:add")
    @Log(title = "法务支持变更", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcLegalSupportChangeRecordBo bo) {
        return toAjax(dcLegalSupportChangeRecordService.insertByBo(bo));
    }

    /**
     * 修改法务支持变更
     */
    @SaCheckPermission("legalSupportChange:legalSupportChange:edit")
    @Log(title = "法务支持变更", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcLegalSupportChangeRecordBo bo) {
        return toAjax(dcLegalSupportChangeRecordService.updateByBo(bo));
    }

    /**
     * 删除法务支持变更
     *
     * @param ids 主键串
     */
    @SaCheckPermission("legalSupportChange:legalSupportChange:remove")
    @Log(title = "法务支持变更", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcLegalSupportChangeRecordService.deleteWithValidByIds(List.of(ids), true));
    }
}
