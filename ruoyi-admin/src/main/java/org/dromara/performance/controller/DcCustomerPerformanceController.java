package org.dromara.performance.controller;

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
import org.dromara.performance.domain.vo.DcCustomerPerformanceVo;
import org.dromara.performance.domain.bo.DcCustomerPerformanceBo;
import org.dromara.performance.service.IDcCustomerPerformanceService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 业绩归属登记
 *
 * @author Lion Li
 * @date 2025-10-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customerPerformance/customerPerformance")
public class DcCustomerPerformanceController extends BaseController {

    private final IDcCustomerPerformanceService dcCustomerPerformanceService;

    /**
     * 查询业绩归属登记列表
     */
    @SaCheckPermission("customerPerformance:customerPerformance:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerPerformanceVo> list(DcCustomerPerformanceBo bo, PageQuery pageQuery) {
        return dcCustomerPerformanceService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出业绩归属登记列表
     */
    @SaCheckPermission("customerPerformance:customerPerformance:export")
    @Log(title = "业绩归属登记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerPerformanceBo bo, HttpServletResponse response) {
        List<DcCustomerPerformanceVo> list = dcCustomerPerformanceService.queryList(bo);
        ExcelUtil.exportExcel(list, "业绩归属登记", DcCustomerPerformanceVo.class, response);
    }

    /**
     * 获取业绩归属登记详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customerPerformance:customerPerformance:query")
    @GetMapping("/{id}")
    public R<DcCustomerPerformanceVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcCustomerPerformanceService.queryById(id));
    }

    /**
     * 新增业绩归属登记
     */
    @SaCheckPermission("customerPerformance:customerPerformance:add")
    @Log(title = "业绩归属登记", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerPerformanceBo bo) {
        return toAjax(dcCustomerPerformanceService.insertByBo(bo));
    }

    /**
     * 修改业绩归属登记
     */
    @SaCheckPermission("customerPerformance:customerPerformance:edit")
    @Log(title = "业绩归属登记", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerPerformanceBo bo) {
        return toAjax(dcCustomerPerformanceService.updateByBo(bo));
    }

    /**
     * 删除业绩归属登记
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customerPerformance:customerPerformance:remove")
    @Log(title = "业绩归属登记", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerPerformanceService.deleteWithValidByIds(List.of(ids), true));
    }
}
