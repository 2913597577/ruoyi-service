package org.dromara.customertrace.controller;

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
import org.dromara.customertrace.domain.vo.DcCustomerFollowVo;
import org.dromara.customertrace.domain.bo.DcCustomerFollowBo;
import org.dromara.customertrace.service.IDcCustomerFollowService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 客户跟进记录
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customtrace/customerTrace")
public class DcCustomerFollowController extends BaseController {

    private final IDcCustomerFollowService dcCustomerFollowService;

    /**
     * 查询客户跟进记录列表
     */
    @SaCheckPermission("customtrace:customerTrace:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerFollowVo> list(DcCustomerFollowBo bo, PageQuery pageQuery) {
        return dcCustomerFollowService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户跟进记录列表
     */
    @SaCheckPermission("customtrace:customerTrace:export")
    @Log(title = "客户跟进记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerFollowBo bo, HttpServletResponse response) {
        List<DcCustomerFollowVo> list = dcCustomerFollowService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户跟进记录", DcCustomerFollowVo.class, response);
    }

    /**
     * 获取客户跟进记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customtrace:customerTrace:query")
    @GetMapping("/{id}")
    public R<DcCustomerFollowVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcCustomerFollowService.queryById(id));
    }

    /**
     * 新增客户跟进记录
     */
    @SaCheckPermission("customtrace:customerTrace:add")
    @Log(title = "客户跟进记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerFollowBo bo) {
        return toAjax(dcCustomerFollowService.insertByBo(bo));
    }

    /**
     * 修改客户跟进记录
     */
    @SaCheckPermission("customtrace:customerTrace:edit")
    @Log(title = "客户跟进记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerFollowBo bo) {
        return toAjax(dcCustomerFollowService.updateByBo(bo));
    }

    /**
     * 删除客户跟进记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customtrace:customerTrace:remove")
    @Log(title = "客户跟进记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerFollowService.deleteWithValidByIds(List.of(ids), true));
    }
}
