package org.dromara.financial.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.web.core.BaseController;
import org.dromara.financial.domain.FinancialStatisticsQuery;
import org.dromara.financial.domain.bo.DcFinancialStatisticsBo;
import org.dromara.financial.domain.vo.DcFinancialStatisticsVo;
import org.dromara.financial.service.IDcFinancialStatisticsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 财务统计
 *
 * @author Lion Li
 * @date 2025-12-26
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/financialStatistics/financialStatistics")
public class DcFinancialStatisticsController extends BaseController {

    private final IDcFinancialStatisticsService dcFinancialStatisticsService;

    /**
     * 查询财务统计列表
     */
    @SaCheckPermission("financialStatistics:financialStatistics:list")
    @GetMapping("/list")
    public TableDataInfo<DcFinancialStatisticsVo> list(DcFinancialStatisticsBo bo, PageQuery pageQuery) {
        return dcFinancialStatisticsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出财务统计列表
     */
    @SaCheckPermission("financialStatistics:financialStatistics:export")
    @Log(title = "财务统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcFinancialStatisticsBo bo, HttpServletResponse response) {
        List<DcFinancialStatisticsVo> list = dcFinancialStatisticsService.queryList(bo);
        ExcelUtil.exportExcel(list, "财务统计", DcFinancialStatisticsVo.class, response);
    }

    /**
     * 获取财务统计详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("financialStatistics:financialStatistics:query")
    @GetMapping("/{id}")
    public R<DcFinancialStatisticsVo> getInfo(@NotNull(message = "主键不能为空")
                                              @PathVariable Long id) {
        return R.ok(dcFinancialStatisticsService.queryById(id));
    }

    /**
     * 新增财务统计
     */
    @SaCheckPermission("financialStatistics:financialStatistics:add")
    @Log(title = "财务统计", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcFinancialStatisticsBo bo) {
        bo.setCreaterName(LoginHelper.getUsername());
        //bo.setSourceType("hand_record");
        return toAjax(dcFinancialStatisticsService.insertByBo(bo));
    }

    /**
     * 修改财务统计
     */
    @SaCheckPermission("financialStatistics:financialStatistics:edit")
    @Log(title = "财务统计", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcFinancialStatisticsBo bo) {
        return toAjax(dcFinancialStatisticsService.updateByBo(bo));
    }

    /**
     * 删除财务统计
     *
     * @param ids 主键串
     */
    @SaCheckPermission("financialStatistics:financialStatistics:remove")
    @Log(title = "财务统计", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcFinancialStatisticsService.deleteWithValidByIds(List.of(ids), true));
    }

    // Controller
    @GetMapping("/statistics")
    public R<JSONObject> getStatistics(FinancialStatisticsQuery query) {
        JSONObject result = dcFinancialStatisticsService.getStatistics(query);
        return R.ok(result);
    }

}
