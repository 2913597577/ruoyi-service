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
import org.dromara.customer.domain.vo.DcIntentionCustomerVo;
import org.dromara.customer.domain.bo.DcIntentionCustomerBo;
import org.dromara.customer.service.IDcIntentionCustomerService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 客户意向登记
 *
 * @author leo
 * @date 2025-07-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/intentioncustomer/intentionCustomer")
public class DcIntentionCustomerController extends BaseController {

    private final IDcIntentionCustomerService dcIntentionCustomerService;

    /**
     * 查询客户意向登记列表
     */
    @SaCheckPermission("intentioncustomer:intentionCustomer:list")
    @GetMapping("/list")
    public TableDataInfo<DcIntentionCustomerVo> list(DcIntentionCustomerBo bo, PageQuery pageQuery) {
        return dcIntentionCustomerService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户意向登记列表
     */
    @SaCheckPermission("intentioncustomer:intentionCustomer:export")
    @Log(title = "客户意向登记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcIntentionCustomerBo bo, HttpServletResponse response) {
        List<DcIntentionCustomerVo> list = dcIntentionCustomerService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户意向登记", DcIntentionCustomerVo.class, response);
    }

    /**
     * 获取客户意向登记详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("intentioncustomer:intentionCustomer:query")
    @GetMapping("/{id}")
    public R<DcIntentionCustomerVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcIntentionCustomerService.queryById(id));
    }

    /**
     * 新增客户意向登记
     */
    @SaCheckPermission("intentioncustomer:intentionCustomer:add")
    @Log(title = "客户意向登记", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcIntentionCustomerBo bo) {
        return toAjax(dcIntentionCustomerService.insertByBo(bo));
    }

    /**
     * 修改客户意向登记
     */
    @SaCheckPermission("intentioncustomer:intentionCustomer:edit")
    @Log(title = "客户意向登记", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcIntentionCustomerBo bo) {
        return toAjax(dcIntentionCustomerService.updateByBo(bo));
    }

    /**
     * 删除客户意向登记
     *
     * @param ids 主键串
     */
    @SaCheckPermission("intentioncustomer:intentionCustomer:remove")
    @Log(title = "客户意向登记", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcIntentionCustomerService.deleteWithValidByIds(List.of(ids), true));
    }
}
