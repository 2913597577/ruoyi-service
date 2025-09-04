package org.dromara.customerflow.controller;

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
import org.dromara.customerflow.domain.vo.DcCustomerFlowVo;
import org.dromara.customerflow.domain.bo.DcCustomerFlowBo;
import org.dromara.customerflow.service.IDcCustomerFlowService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 待提交流转单
 *
 * @author leo
 * @date 2025-07-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customerflow/customerFlow")
public class DcCustomerFlowController extends BaseController {

    private final IDcCustomerFlowService dcCustomerFlowService;

    /**
     * 查询待提交流转单列表
     */
    @SaCheckPermission("customerflow:customerFlow:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerFlowVo> list(DcCustomerFlowBo bo, PageQuery pageQuery) {
        return dcCustomerFlowService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出待提交流转单列表
     */
    @SaCheckPermission("customerflow:customerFlow:export")
    @Log(title = "待提交流转单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerFlowBo bo, HttpServletResponse response) {
        List<DcCustomerFlowVo> list = dcCustomerFlowService.queryList(bo);
        ExcelUtil.exportExcel(list, "待提交流转单", DcCustomerFlowVo.class, response);
    }

    /**
     * 获取待提交流转单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customerflow:customerFlow:query")
    @GetMapping("/{id}")
    public R<DcCustomerFlowVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcCustomerFlowService.queryById(id));
    }

    /**
     * 新增待提交流转单
     */
    @SaCheckPermission("customerflow:customerFlow:add")
    @Log(title = "待提交流转单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerFlowBo bo) {
        return toAjax(dcCustomerFlowService.insertByBo(bo));
    }

    /**
     * 修改待提交流转单
     */
    @SaCheckPermission("customerflow:customerFlow:edit")
    @Log(title = "待提交流转单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerFlowBo bo) {
        return toAjax(dcCustomerFlowService.updateByBo(bo));
    }

    /**
     * 删除待提交流转单
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customerflow:customerFlow:remove")
    @Log(title = "待提交流转单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerFlowService.deleteWithValidByIds(List.of(ids), true));
    }
}
