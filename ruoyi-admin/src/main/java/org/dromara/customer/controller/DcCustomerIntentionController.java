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
import org.dromara.customer.domain.vo.DcCustomerIntentionVo;
import org.dromara.customer.domain.bo.DcCustomerIntentionBo;
import org.dromara.customer.service.IDcCustomerIntentionService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 客户意向登记
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customerIntention/customerIntention")
public class DcCustomerIntentionController extends BaseController {

    private final IDcCustomerIntentionService dcCustomerIntentionService;

    /**
     * 查询客户意向登记列表
     */
    @SaCheckPermission("customerIntention:customerIntention:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerIntentionVo> list(DcCustomerIntentionBo bo, PageQuery pageQuery) {
        return dcCustomerIntentionService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户意向登记列表
     */
    @SaCheckPermission("customerIntention:customerIntention:export")
    @Log(title = "客户意向登记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerIntentionBo bo, HttpServletResponse response) {
        List<DcCustomerIntentionVo> list = dcCustomerIntentionService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户意向登记", DcCustomerIntentionVo.class, response);
    }

    /**
     * 获取客户意向登记详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customerIntention:customerIntention:query")
    @GetMapping("/{id}")
    public R<DcCustomerIntentionVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcCustomerIntentionService.queryById(id));
    }

    /**
     * 新增客户意向登记
     */
    @SaCheckPermission("customerIntention:customerIntention:add")
    @Log(title = "客户意向登记", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerIntentionBo bo) {
        return toAjax(dcCustomerIntentionService.insertByBo(bo));
    }

    /**
     * 修改客户意向登记
     */
    @SaCheckPermission("customerIntention:customerIntention:edit")
    @Log(title = "客户意向登记", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerIntentionBo bo) {
        return toAjax(dcCustomerIntentionService.updateByBo(bo));
    }

    /**
     * 删除客户意向登记
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customerIntention:customerIntention:remove")
    @Log(title = "客户意向登记", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerIntentionService.deleteWithValidByIds(List.of(ids), true));
    }
}
