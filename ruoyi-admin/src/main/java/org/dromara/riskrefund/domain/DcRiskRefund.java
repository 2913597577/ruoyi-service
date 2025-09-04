package org.dromara.riskrefund.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 风险退费客户对象 dc_risk_refund
 *
 * @author Lion Li
 * @date 2025-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_risk_refund")
public class DcRiskRefund extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 客户ID（dc_customer.id）
     */
    private Long customerId;

    /**
     * 退费金额
     */
    private Long refundAmount;

    /**
     * 退费原因
     */
    private String refundReason;

    /**
     * 退费时间
     */
    private Date refundTime;

    /**
     * 审批人（sys_user.user_id）
     */
    private Long approveBy;

    /**
     * 删除标志（0存在 1删除）
     */
    @TableLogic
    private String delFlag;


}
