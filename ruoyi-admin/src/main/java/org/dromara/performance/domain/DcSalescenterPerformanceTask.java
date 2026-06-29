package org.dromara.performance.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.math.BigDecimal;

/**
 * 业绩任务对象 dc_performance_task
 *
 * @author Lion Li
 * @date 2025-10-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_salescenter_performance_task")
public class DcSalescenterPerformanceTask extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 销售中心id
     */
    private Long salesCenterId;

    /**
     * 销售中心姓名
     */
    private String salesCenterName;

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
