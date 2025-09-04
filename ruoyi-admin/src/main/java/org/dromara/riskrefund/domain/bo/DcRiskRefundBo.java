package org.dromara.riskrefund.domain.bo;

import org.dromara.riskrefund.domain.DcRiskRefund;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 风险退费客户业务对象 dc_risk_refund
 *
 * @author Lion Li
 * @date 2025-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcRiskRefund.class, reverseConvertGenerate = false)
public class DcRiskRefundBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 客户ID（dc_customer.id）
     */
    @NotNull(message = "客户ID（dc_customer.id）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long customerId;

    /**
     * 退费金额
     */
    @NotNull(message = "退费金额不能为空", groups = { AddGroup.class, EditGroup.class })
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


}
