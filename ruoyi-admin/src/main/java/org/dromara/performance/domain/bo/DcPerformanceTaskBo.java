package org.dromara.performance.domain.bo;

import org.dromara.performance.domain.DcPerformanceTask;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 业绩任务业务对象 dc_performance_task
 *
 * @author Lion Li
 * @date 2025-10-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcPerformanceTask.class, reverseConvertGenerate = false)
public class DcPerformanceTaskBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 法务支持id
     */
    @NotNull(message = "法务支持id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long legalSupportId;

    /**
     * 法务支持姓名
     */
    private String legalSupportName;

    /**
     * 任务月份（格式：YYYYMM，如202310）
     */
    @NotBlank(message = "任务月份（格式：YYYYMM，如202310）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String taskMonth;

    /**
     * 月度业绩目标
     */
    private BigDecimal performanceGoal;

    /**
     * 月度出访目标
     */
    private Long visitGoal;

    /**
     * 已完成的月度业绩目标
     */
    private BigDecimal achievedPerformanceGoal;

    /**
     * 已完成的月度出访目标
     */
    private Long achievedVisitGoal;


}
