package org.dromara.workflow.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.workflow.common.ConditionalOnEnable;
import org.dromara.workflow.domain.bo.TestApproveBo;
import org.dromara.workflow.domain.vo.TestApproveVo;
import org.dromara.workflow.service.ITestApproveService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 审批申请
 *
 * @date 2025-11-01
 */
@ConditionalOnEnable
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/approve")
public class TestApproveController extends BaseController {

    private final ITestApproveService testApproveService;

    /**
     * 查询审批申请列表
     */
    @SaCheckPermission("workflow:approve:list")
    @GetMapping("/list")
    public TableDataInfo<TestApproveVo> list(TestApproveBo bo, PageQuery pageQuery) {
        return testApproveService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出审批申请列表
     */
    @SaCheckPermission("workflow:approve:export")
    @Log(title = "审批申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TestApproveBo bo, HttpServletResponse response) {
        List<TestApproveVo> list = testApproveService.queryList(bo);
        ExcelUtil.exportExcel(list, "审批申请", TestApproveVo.class, response);
    }

    /**
     * 获取审批申请详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:approve:query")
    @GetMapping("/{id}")
    public R<TestApproveVo> getInfo(@NotNull(message = "主键不能为空")
                                    @PathVariable Long id) {
        return R.ok(testApproveService.queryById(id));
    }

    /**
     * 新增审批申请
     */
    @SaCheckPermission("workflow:approve:add")
    @Log(title = "审批申请", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<TestApproveVo> add(@Validated(AddGroup.class) @RequestBody TestApproveBo bo) {
        return R.ok(testApproveService.insertByBo(bo));
    }

    /**
     * 修改审批申请
     */
    @SaCheckPermission("workflow:approve:edit")
    @Log(title = "审批申请", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<TestApproveVo> edit(@Validated(EditGroup.class) @RequestBody TestApproveBo bo) {
        return R.ok(testApproveService.updateByBo(bo));
    }

    /**
     * 删除审批申请
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:approve:remove")
    @Log(title = "审批申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(testApproveService.deleteWithValidByIds(List.of(ids)));
    }
}
