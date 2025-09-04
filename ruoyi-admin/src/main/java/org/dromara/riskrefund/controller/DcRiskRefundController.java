package org.dromara.riskrefund.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.riskrefund.domain.vo.DcRiskRefundVo;
import org.dromara.riskrefund.domain.bo.DcRiskRefundBo;
import org.dromara.riskrefund.service.IDcRiskRefundService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 风险退费客户
 *
 * @author Lion Li
 * @date 2025-07-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/riskrefund/riskRefund")
public class DcRiskRefundController extends BaseController {

    private final IDcRiskRefundService dcRiskRefundService;

    /**
     * 查询风险退费客户列表
     */
    @SaCheckPermission("riskrefund:riskRefund:list")
    @GetMapping("/list")
    public TableDataInfo<DcRiskRefundVo> list(DcRiskRefundBo bo, PageQuery pageQuery) {
        return dcRiskRefundService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出风险退费客户列表
     */
    @SaCheckPermission("riskrefund:riskRefund:export")
    @Log(title = "风险退费客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcRiskRefundBo bo, HttpServletResponse response) {
        List<DcRiskRefundVo> list = dcRiskRefundService.queryList(bo);
        ExcelUtil.exportExcel(list, "风险退费客户", DcRiskRefundVo.class, response);
    }

    /**
     * 获取风险退费客户详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("riskrefund:riskRefund:query")
    @GetMapping("/{id}")
    public R<DcRiskRefundVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcRiskRefundService.queryById(id));
    }

    /**
     * 新增风险退费客户
     */
    @SaCheckPermission("riskrefund:riskRefund:add")
    @Log(title = "风险退费客户", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcRiskRefundBo bo) {
        return toAjax(dcRiskRefundService.insertByBo(bo));
    }

    /**
     * 修改风险退费客户
     */
    @SaCheckPermission("riskrefund:riskRefund:edit")
    @Log(title = "风险退费客户", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcRiskRefundBo bo) {
        return toAjax(dcRiskRefundService.updateByBo(bo));
    }

    /**
     * 删除风险退费客户
     *
     * @param ids 主键串
     */
    @SaCheckPermission("riskrefund:riskRefund:remove")
    @Log(title = "风险退费客户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcRiskRefundService.deleteWithValidByIds(List.of(ids), true));
    }
}
