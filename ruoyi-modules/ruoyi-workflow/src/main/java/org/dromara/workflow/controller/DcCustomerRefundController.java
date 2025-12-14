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
import org.dromara.workflow.domain.bo.DcCustomerRefundBo;
import org.dromara.workflow.domain.vo.DcCustomerRefundVo;
import org.dromara.workflow.service.IDcCustomerRefundService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户风险/退费记录
 */
@ConditionalOnEnable
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/customer/refund")
public class DcCustomerRefundController extends BaseController {

    private final IDcCustomerRefundService dcCustomerRiskRefundService;

    /**
     * 查询客户风险/退费记录列表
     */
    @SaCheckPermission("workflow:refundCustomer:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerRefundVo> list(DcCustomerRefundBo bo, PageQuery pageQuery) {
        return dcCustomerRiskRefundService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户风险/退费记录列表
     */
    @SaCheckPermission("workflow:refundCustomer:export")
    @Log(title = "客户退费记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerRefundBo bo, HttpServletResponse response) {
        List<DcCustomerRefundVo> list = dcCustomerRiskRefundService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户退费记录", DcCustomerRefundVo.class, response);
    }

    /**
     * 获取客户风险/退费记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:refundCustomer:query")
    @GetMapping("/{id}")
    public R<DcCustomerRefundVo> getInfo(@NotNull(message = "主键不能为空")
                                         @PathVariable Long id) {
        return R.ok(dcCustomerRiskRefundService.queryById(id));
    }

    /**
     * 新增客户风险/退费记录
     */
    @SaCheckPermission("workflow:refundCustomer:add")
    @Log(title = "客户退费记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<DcCustomerRefundVo> add(@Validated(AddGroup.class) @RequestBody DcCustomerRefundBo bo) {
        return R.ok(dcCustomerRiskRefundService.insertByBo(bo));
    }

    /**
     * 修改客户风险/退费记录
     */
    @SaCheckPermission("workflow:refundCustomer:edit")
    @Log(title = "客户退费记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<DcCustomerRefundVo> edit(@Validated(EditGroup.class) @RequestBody DcCustomerRefundBo bo) {
        return R.ok(dcCustomerRiskRefundService.updateByBo(bo));
    }

    /**
     * 删除客户风险/退费记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:refundCustomer:remove")
    @Log(title = "客户退费记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerRiskRefundService.deleteWithValidByIds(List.of(ids)));
    }
}
