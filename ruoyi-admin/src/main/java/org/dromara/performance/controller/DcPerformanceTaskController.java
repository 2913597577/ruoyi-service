package org.dromara.performance.controller;

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
import org.dromara.performance.domain.bo.DcPerformanceTaskBo;
import org.dromara.performance.domain.vo.DcPerformanceTaskVo;
import org.dromara.performance.service.IDcPerformanceTaskService;
import org.dromara.system.domain.vo.SysUserVo;
import org.dromara.system.service.ISysUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 业绩任务
 *
 * @author Lion Li
 * @date 2025-10-26
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/performanceTask/performanceTask")
public class DcPerformanceTaskController extends BaseController {

    private final IDcPerformanceTaskService dcPerformanceTaskService;
    private final ISysUserService sysUserService;

    /**
     * 查询业绩任务列表
     */
    @SaCheckPermission("performanceTask:performanceTask:list")
    @GetMapping("/list")
    public TableDataInfo<DcPerformanceTaskVo> list(DcPerformanceTaskBo bo, PageQuery pageQuery) {
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
        return dcPerformanceTaskService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出业绩任务列表
     */
    @SaCheckPermission("performanceTask:performanceTask:export")
    @Log(title = "业绩任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcPerformanceTaskBo bo, HttpServletResponse response) {
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
        List<DcPerformanceTaskVo> list = dcPerformanceTaskService.queryList(bo);
        ExcelUtil.exportExcel(list, "业绩任务", DcPerformanceTaskVo.class, response);
    }

    /**
     * 获取业绩任务详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("performanceTask:performanceTask:query")
    @GetMapping("/{id}")
    public R<DcPerformanceTaskVo> getInfo(@NotNull(message = "主键不能为空")
                                          @PathVariable Long id) {
        return R.ok(dcPerformanceTaskService.queryById(id));
    }

    /**
     * 新增业绩任务
     */
    @SaCheckPermission("performanceTask:performanceTask:add")
    @Log(title = "业绩任务", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcPerformanceTaskBo bo) {
        return toAjax(dcPerformanceTaskService.insertByBo(bo));
    }

    /**
     * 修改业绩任务
     */
    @SaCheckPermission("performanceTask:performanceTask:edit")
    @Log(title = "业绩任务", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcPerformanceTaskBo bo) {
        return toAjax(dcPerformanceTaskService.updateByBo(bo));
    }

    /**
     * 删除业绩任务
     *
     * @param ids 主键串
     */
    @SaCheckPermission("performanceTask:performanceTask:remove")
    @Log(title = "业绩任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcPerformanceTaskService.deleteWithValidByIds(List.of(ids), true));
    }
}
