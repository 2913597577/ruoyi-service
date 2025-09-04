package org.dromara.laworder.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 法务接单记录对象 dc_legal_order
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_legal_order")
public class DcLegalOrder extends TenantEntity {

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
     * 法务人员ID（dc_legal_support.id）
     */
    private Long legalSupportId;

    /**
     * 接单时间
     */
    private Date acceptTime;

    /**
     * 服务截止时间
     */
    private Date deadline;

    /**
     * 完成时间
     */
    private Date completeTime;

    /**
     * 实际服务时长（单位：分钟）
     */
    private Long serviceDuration;

    /**
     * 状态（PENDING待处理/PROCESSING处理中/COMPLETED已完成）
     */
    private String orderStatus;

    /**
     * 删除标志（0存在 1删除）
     */
    @TableLogic
    private String delFlag;


}
