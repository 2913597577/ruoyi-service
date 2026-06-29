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
import org.dromara.performance.domain.bo.DcSalescenterPerformanceTaskBo;
import org.dromara.performance.domain.vo.DcSalescenterPerformanceTaskVo;

import org.dromara.performance.service.IDcSalescenterPerformanceTaskService;
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
@RequestMapping("/salescenterPerformanceTask/salescenterPerformanceTask")
public class DcSalescenterPerformanceTaskController extends BaseController {

    private final IDcSalescenterPerformanceTaskService dcSalescenterPerformanceTaskService;
    private final ISysUserService sysUserService;

    /**
     * 查询业绩任务列表
     */
    @SaCheckPermission("salescenterPerformanceTask:salescenterPerformanceTask:list")
    @GetMapping("/list")
    public TableDataInfo<DcSalescenterPerformanceTaskVo> list(DcSalescenterPerformanceTaskBo bo, PageQuery pageQuery) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return null;
        }
        List<RoleDTO> roles = loginUser.getRoles();
        if (roles != null && !roles.isEmpty()) {
            RoleDTO role = roles.get(0);
            if (role.getRoleKey().equals("SalesCenter_Employee")) {
                bo.setSalesCenterId(loginUser.getUserId());
            }
            if (role.getRoleKey().equals("SalesCenter_Manager")) {
                List<SysUserVo> userList = sysUserService.selectUserListByDept(loginUser.getDeptId());
                List<Long> userIdList = userList.stream().map(SysUserVo::getUserId).toList();
                bo.setSalesCenterIds(userIdList);
            }
        }
        return dcSalescenterPerformanceTaskService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出业绩任务列表
     */
    @SaCheckPermission("salescenterPerformanceTask:salescenterPerformanceTask:export")
    @Log(title = "业绩任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcSalescenterPerformanceTaskBo bo, HttpServletResponse response) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return;
        }
        List<RoleDTO> roles = loginUser.getRoles();
        if (roles != null && !roles.isEmpty()) {
            RoleDTO role = roles.get(0);
            if (role.getRoleKey().equals("SalesCenter_Employee")) {
                bo.setSalesCenterId(loginUser.getUserId());

            }
            if (role.getRoleKey().equals("SalesCenter_Manager")) {
                List<SysUserVo> userList = sysUserService.selectUserListByDept(loginUser.getDeptId());
                List<Long> userIdList = userList.stream().map(SysUserVo::getUserId).toList();
                bo.setSalesCenterIds(userIdList);
            }
        }
        List<DcSalescenterPerformanceTaskVo> list = dcSalescenterPerformanceTaskService.queryList(bo);
        ExcelUtil.exportExcel(list, "业绩任务", DcSalescenterPerformanceTaskVo.class, response);
    }

    /**
     * 获取业绩任务详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("salescenterPerformanceTask:salescenterPerformanceTask:query")
    @GetMapping("/{id}")
    public R<DcSalescenterPerformanceTaskVo> getInfo(@NotNull(message = "主键不能为空")
                                          @PathVariable Long id) {
        return R.ok(dcSalescenterPerformanceTaskService.queryById(id));
    }

    /**
     * 新增业绩任务
     */
    @SaCheckPermission("salescenterPerformanceTask:salescenterPerformanceTask:add")
    @Log(title = "业绩任务", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcSalescenterPerformanceTaskBo bo) {
        return toAjax(dcSalescenterPerformanceTaskService.insertByBo(bo));
    }

    /**
     * 修改业绩任务
     */
    @SaCheckPermission("salescenterPerformanceTask:salescenterPerformanceTask:edit")
    @Log(title = "业绩任务", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcSalescenterPerformanceTaskBo bo) {
        return toAjax(dcSalescenterPerformanceTaskService.updateByBo(bo));
    }

    /**
     * 删除业绩任务
     *
     * @param ids 主键串
     */
    @SaCheckPermission("salescenterPerformanceTask:salescenterPerformanceTask:remove")
    @Log(title = "业绩任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcSalescenterPerformanceTaskService.deleteWithValidByIds(List.of(ids), true));
    }
}
