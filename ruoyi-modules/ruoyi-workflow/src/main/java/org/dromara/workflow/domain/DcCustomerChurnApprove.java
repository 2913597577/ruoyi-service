package org.dromara.workflow.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 客户流失审批对象 dc_customer_churn_approve
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_customer_churn_approve")
public class DcCustomerChurnApprove extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 租户编号
     */
    private String tenantId;

    /**
     * 审批类型
     */
    private String applyType;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 原因
     */
    private String remark;

    /**
     * 状态
     */
    private String status;
}
