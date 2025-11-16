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
import org.dromara.customer.domain.vo.DcCustomerInformationLogVo;
import org.dromara.customer.domain.bo.DcCustomerInformationLogBo;
import org.dromara.customer.service.IDcCustomerInformationLogService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 客户信息记录
 *
 * @author Lion Li
 * @date 2025-11-16
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customerInformationLog/customerInformationLog")
public class DcCustomerInformationLogController extends BaseController {

    private final IDcCustomerInformationLogService dcCustomerInformationLogService;

    /**
     * 查询客户信息记录列表
     */
    @SaCheckPermission("customerInformationLog:customerInformationLog:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerInformationLogVo> list(DcCustomerInformationLogBo bo, PageQuery pageQuery) {
        return dcCustomerInformationLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户信息记录列表
     */
    @SaCheckPermission("customerInformationLog:customerInformationLog:export")
    @Log(title = "客户信息记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerInformationLogBo bo, HttpServletResponse response) {
        List<DcCustomerInformationLogVo> list = dcCustomerInformationLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户信息记录", DcCustomerInformationLogVo.class, response);
    }

    /**
     * 获取客户信息记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customerInformationLog:customerInformationLog:query")
    @GetMapping("/{id}")
    public R<DcCustomerInformationLogVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcCustomerInformationLogService.queryById(id));
    }

    /**
     * 新增客户信息记录
     */
    @SaCheckPermission("customerInformationLog:customerInformationLog:add")
    @Log(title = "客户信息记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerInformationLogBo bo) {
        return toAjax(dcCustomerInformationLogService.insertByBo(bo));
    }

    /**
     * 修改客户信息记录
     */
    @SaCheckPermission("customerInformationLog:customerInformationLog:edit")
    @Log(title = "客户信息记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerInformationLogBo bo) {
        return toAjax(dcCustomerInformationLogService.updateByBo(bo));
    }

    /**
     * 删除客户信息记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customerInformationLog:customerInformationLog:remove")
    @Log(title = "客户信息记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerInformationLogService.deleteWithValidByIds(List.of(ids), true));
    }
}
