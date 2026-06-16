package org.dromara.performance.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;

/**
 * 业绩任务对象 dc_performance_task
 *
 * @author Lion Li
 * @date 2025-10-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_performance_task")
public class DcPerformanceTask extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 法务支持id
     */
    private Long legalSupportId;

    /**
     * 法务支持姓名
     */
    private String legalSupportName;

    /**
     * 任务月份（格式：YYYYMM，如202310）
     */
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

    /**
     * 备注1(归属城市)
     */
    private String remark1;

    /**
     * 备注2（可记录完成情况说明等）
     */
    private String remark2;

    /**
     * 备注3
     */
    private String remark3;

    /**
     * 删除标志 0存在 1删除
     */
    @TableLogic
    private String delFlag;


}
