package org.dromara.customerflow.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 待提交流转单对象 dc_customer_flow
 *
 * @author leo
 * @date 2025-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_customer_flow")
public class DcCustomerFlow extends BaseEntity {

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
     * 意向客户ID（dc_intention_customer.id）
     */
    private Long intentionId;

    /**
     * 流程实例ID（flow_instance.id）
     */
    private Long flowInstanceId;

    /**
     * 流转状态（PENDING待提交/CONFIRM待确认/ARCHIVED已归档/REJECTED已退回/COMPLETED已完成）
     */
    private String flowStatus;

    /**
     * 流转类型（TRANSFER转交/ARCHIVE归档/RETURN退回等）
     */
    private String flowType;

    /**
     * 提交人ID（sys_user.user_id）
     */
    private Long submitBy;

    /**
     * 提交时间
     */
    private Date submitTime;

    /**
     * 确认人ID（sys_user.user_id）
     */
    private Long confirmBy;

    /**
     * 确认时间
     */
    private Date confirmTime;

    /**
     * 归档人ID（sys_user.user_id）
     */
    private Long archiveBy;

    /**
     * 归档时间
     */
    private Date archiveTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标志（0存在 1删除）
     */
    @TableLogic
    private String delFlag;


}
