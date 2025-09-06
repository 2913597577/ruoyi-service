package org.dromara.myCustomer.controller;

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
import org.dromara.myCustomer.domain.vo.DcCustomerTransferVo;
import org.dromara.myCustomer.domain.bo.DcCustomerTransferBo;
import org.dromara.myCustomer.service.IDcCustomerTransferService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 客户信息录入
 *
 * @author Lion Li
 * @date 2025-09-06
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/myCustomer/customerTransfer")
public class DcCustomerTransferController extends BaseController {

    private final IDcCustomerTransferService dcCustomerTransferService;

    /**
     * 查询客户信息录入列表
     */
    @SaCheckPermission("myCustomer:customerTransfer:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerTransferVo> list(DcCustomerTransferBo bo, PageQuery pageQuery) {
        return dcCustomerTransferService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户信息录入列表
     */
    @SaCheckPermission("myCustomer:customerTransfer:export")
    @Log(title = "客户信息录入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerTransferBo bo, HttpServletResponse response) {
        List<DcCustomerTransferVo> list = dcCustomerTransferService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户信息录入", DcCustomerTransferVo.class, response);
    }

    /**
     * 获取客户信息录入详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("myCustomer:customerTransfer:query")
    @GetMapping("/{id}")
    public R<DcCustomerTransferVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcCustomerTransferService.queryById(id));
    }

    /**
     * 新增客户信息录入
     */
    @SaCheckPermission("myCustomer:customerTransfer:add")
    @Log(title = "客户信息录入", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerTransferBo bo) {
        return toAjax(dcCustomerTransferService.insertByBo(bo));
    }

    /**
     * 修改客户信息录入
     */
    @SaCheckPermission("myCustomer:customerTransfer:edit")
    @Log(title = "客户信息录入", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerTransferBo bo) {
        return toAjax(dcCustomerTransferService.updateByBo(bo));
    }

    /**
     * 删除客户信息录入
     *
     * @param ids 主键串
     */
    @SaCheckPermission("myCustomer:customerTransfer:remove")
    @Log(title = "客户信息录入", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerTransferService.deleteWithValidByIds(List.of(ids), true));
    }
}
