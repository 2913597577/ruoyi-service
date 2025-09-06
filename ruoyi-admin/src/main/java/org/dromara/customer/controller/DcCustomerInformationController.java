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
import org.dromara.customer.domain.vo.DcCustomerInformationVo;
import org.dromara.customer.domain.bo.DcCustomerInformationBo;
import org.dromara.customer.service.IDcCustomerInformationService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 客户总表
 *
 * @author Lion Li
 * @date 2025-09-06
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customerInfo/customerInfo")
public class DcCustomerInformationController extends BaseController {

    private final IDcCustomerInformationService dcCustomerInformationService;

    /**
     * 查询客户总表列表
     */
    @SaCheckPermission("customerInfo:customerInfo:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerInformationVo> list(DcCustomerInformationBo bo, PageQuery pageQuery) {
        return dcCustomerInformationService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户总表列表
     */
    @SaCheckPermission("customerInfo:customerInfo:export")
    @Log(title = "客户总表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerInformationBo bo, HttpServletResponse response) {
        List<DcCustomerInformationVo> list = dcCustomerInformationService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户总表", DcCustomerInformationVo.class, response);
    }

    /**
     * 获取客户总表详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customerInfo:customerInfo:query")
    @GetMapping("/{id}")
    public R<DcCustomerInformationVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcCustomerInformationService.queryById(id));
    }

    /**
     * 新增客户总表
     */
    @SaCheckPermission("customerInfo:customerInfo:add")
    @Log(title = "客户总表", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerInformationBo bo) {
        return toAjax(dcCustomerInformationService.insertByBo(bo));
    }

    /**
     * 修改客户总表
     */
    @SaCheckPermission("customerInfo:customerInfo:edit")
    @Log(title = "客户总表", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerInformationBo bo) {
        return toAjax(dcCustomerInformationService.updateByBo(bo));
    }

    /**
     * 删除客户总表
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customerInfo:customerInfo:remove")
    @Log(title = "客户总表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerInformationService.deleteWithValidByIds(List.of(ids), true));
    }
}
