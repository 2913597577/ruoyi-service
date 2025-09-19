package org.dromara.customer.controller;

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
import org.dromara.customer.domain.vo.DcCustomerRiskRefundVo;
import org.dromara.customer.domain.bo.DcCustomerRiskRefundBo;
import org.dromara.customer.service.IDcCustomerRiskRefundService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 客户风险/退费
 *
 * @author Lion Li
 * @date 2025-09-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customerRiskRefund/customerRiskRefund")
public class DcCustomerRiskRefundController extends BaseController {

    private final IDcCustomerRiskRefundService dcCustomerRiskRefundService;

    /**
     * 查询客户风险/退费列表
     */
    @SaCheckPermission("customerRiskRefund:customerRiskRefund:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerRiskRefundVo> list(DcCustomerRiskRefundBo bo, PageQuery pageQuery) {
        return dcCustomerRiskRefundService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户风险/退费列表
     */
    @SaCheckPermission("customerRiskRefund:customerRiskRefund:export")
    @Log(title = "客户风险/退费", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerRiskRefundBo bo, HttpServletResponse response) {
        List<DcCustomerRiskRefundVo> list = dcCustomerRiskRefundService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户风险/退费", DcCustomerRiskRefundVo.class, response);
    }

    /**
     * 获取客户风险/退费详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customerRiskRefund:customerRiskRefund:query")
    @GetMapping("/{id}")
    public R<DcCustomerRiskRefundVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcCustomerRiskRefundService.queryById(id));
    }

    /**
     * 新增客户风险/退费
     */
    @SaCheckPermission("customerRiskRefund:customerRiskRefund:add")
    @Log(title = "客户风险/退费", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerRiskRefundBo bo) {
        return toAjax(dcCustomerRiskRefundService.insertByBo(bo));
    }

    /**
     * 修改客户风险/退费
     */
    @SaCheckPermission("customerRiskRefund:customerRiskRefund:edit")
    @Log(title = "客户风险/退费", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerRiskRefundBo bo) {
        return toAjax(dcCustomerRiskRefundService.updateByBo(bo));
    }

    /**
     * 删除客户风险/退费
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customerRiskRefund:customerRiskRefund:remove")
    @Log(title = "客户风险/退费", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerRiskRefundService.deleteWithValidByIds(List.of(ids), true));
    }
}
