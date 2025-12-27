package org.dromara.common.controller;

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
import org.dromara.workflow.domain.bo.DcExpenseReimbursementBo;
import org.dromara.workflow.domain.vo.DcExpenseReimbursementVo;
import org.dromara.workflow.service.IDcExpenseReimbursementService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 报销申请
 */
@ConditionalOnEnable
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/expense/reimbursement")
public class DcExpenseReimbursementController extends BaseController {

    private final IDcExpenseReimbursementService dcExpenseReimbursementService;

    /**
     * 查询报销申请列表
     */
    @SaCheckPermission("workflow:expenseReimbursement:list")
    @GetMapping("/list")
    public TableDataInfo<DcExpenseReimbursementVo> list(DcExpenseReimbursementBo bo, PageQuery pageQuery) {
        return dcExpenseReimbursementService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出报销申请列表
     */
    @SaCheckPermission("workflow:expenseReimbursement:export")
    @Log(title = "报销申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcExpenseReimbursementBo bo, HttpServletResponse response) {
        List<DcExpenseReimbursementVo> list = dcExpenseReimbursementService.queryList(bo);
        ExcelUtil.exportExcel(list, "报销申请", DcExpenseReimbursementVo.class, response);
    }

    /**
     * 获取报销申请详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:expenseReimbursement:query")
    @GetMapping("/{id}")
    public R<DcExpenseReimbursementVo> getInfo(@NotNull(message = "主键不能为空")
                                               @PathVariable Long id) {
        return R.ok(dcExpenseReimbursementService.queryById(id));
    }

    /**
     * 新增报销申请
     */
    @SaCheckPermission("workflow:expenseReimbursement:add")
    @Log(title = "报销申请", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<DcExpenseReimbursementVo> add(@Validated(AddGroup.class) @RequestBody DcExpenseReimbursementBo bo) {
        return R.ok(dcExpenseReimbursementService.insertByBo(bo));
    }

    /**
     * 修改报销申请
     */
    @SaCheckPermission("workflow:expenseReimbursement:edit")
    @Log(title = "报销申请", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<DcExpenseReimbursementVo> edit(@Validated(EditGroup.class) @RequestBody DcExpenseReimbursementBo bo) {
        return R.ok(dcExpenseReimbursementService.updateByBo(bo));
    }

    /**
     * 删除报销申请
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:expenseReimbursement:remove")
    @Log(title = "报销申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcExpenseReimbursementService.deleteWithValidByIds(List.of(ids)));
    }
}
