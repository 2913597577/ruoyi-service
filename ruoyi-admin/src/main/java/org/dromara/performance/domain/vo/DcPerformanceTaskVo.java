package org.dromara.performance.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.performance.domain.DcPerformanceTask;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 业绩任务视图对象 dc_performance_task
 *
 * @author Lion Li
 * @date 2025-10-26
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcPerformanceTask.class)
public class DcPerformanceTaskVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 法务支持id
     */
    @ExcelProperty(value = "法务支持id")
    private Long legalSupportId;

    /**
     * 法务支持姓名
     */
    @ExcelProperty(value = "法务支持姓名")
    private String legalSupportName;

    /**
     * 任务月份（格式：YYYYMM，如202310）
     */
    @ExcelProperty(value = "任务月份", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "格=式：YYYYMM，如202310")
    private String taskMonth;

    /**
     * 月度业绩目标
     */
    @ExcelProperty(value = "月度业绩目标")
    private BigDecimal performanceGoal;

    /**
     * 月度出访目标
     */
    @ExcelProperty(value = "月度出访目标")
    private Long visitGoal;

    /**
     * 已完成的月度业绩目标
     */
    @ExcelProperty(value = "已完成的月度业绩目标")
    private BigDecimal achievedPerformanceGoal;

    /**
     * 已完成的月度出访目标
     */
    @ExcelProperty(value = "已完成的月度出访目标")
    private Long achievedVisitGoal;

    /**
     * 备注1(归属城市)
     */
    @ExcelProperty(value = "归属城市")
    private String remark1;

    /**
     * 备注2（可记录完成情况说明等）
     */
    @ExcelProperty(value = "备注2（可记录完成情况说明等）")
    private String remark2;

    /**
     * 备注3
     */
    @ExcelProperty(value = "备注3")
    private String remark3;
    /**
     * 删除标志 0存在 1删除
     */
    @ExcelProperty(value = "删除标志 0存在 1删除")
    private String delFlag;

}
