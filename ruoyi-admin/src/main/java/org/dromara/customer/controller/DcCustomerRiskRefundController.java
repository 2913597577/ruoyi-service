package org.dromara.customer.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.customer.domain.bo.DcCustomerInformationBo;
import org.dromara.customer.domain.bo.DcCustomerRiskRefundBo;
import org.dromara.customer.domain.vo.DcCustomerInformationVo;
import org.dromara.customer.domain.vo.DcCustomerRiskRefundVo;
import org.dromara.customer.service.IDcCustomerInformationService;
import org.dromara.customer.service.IDcCustomerRiskRefundService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private final IDcCustomerInformationService dcCustomerInformationService;

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
        Long customerId = bo.getCustomerId();
        if (customerId == null) {
            return R.warn("客户ID不能为空");
        }
        DcCustomerInformationVo customerInformation = dcCustomerInformationService.queryListByTransferId(customerId);
        if (customerInformation == null) {
            return R.warn("客户信息不存在");
        }
        if (bo.getCustomerType() == 1) {
            if (customerInformation.getIsRisk() == 1) {
                return R.warn("该客户信息已录入风险客户表");
            }
            if (!dcCustomerRiskRefundService.insertByBo(bo)) {
                return R.warn("客户转为风险客户失败");
            }
            customerInformation.setIsRisk(1);
            DcCustomerInformationBo update = MapstructUtils.convert(customerInformation, DcCustomerInformationBo.class);
            dcCustomerInformationService.updateByBo(update);
        }
        if (bo.getCustomerType() == 2) {
            if (customerInformation.getIsRefund() == 1) {
                return R.warn("该客户信息已录入退费客户表");
            }
            if (!dcCustomerRiskRefundService.insertByBo(bo)) {
                return R.warn("客户转为退费客户失败");
            }
            customerInformation.setIsRefund(1);
            DcCustomerInformationBo update = MapstructUtils.convert(customerInformation, DcCustomerInformationBo.class);
            dcCustomerInformationService.updateByBo(update);
        }
        return R.ok();
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
