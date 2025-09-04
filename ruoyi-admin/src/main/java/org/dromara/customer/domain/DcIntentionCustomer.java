package org.dromara.customer.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 客户意向登记对象 dc_intention_customer
 *
 * @author leo
 * @date 2025-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_intention_customer")
public class DcIntentionCustomer extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 联系方式
     */
    private String contactPhone;

    /**
     * 意向签约类型
     */
    private String intentionType;

    /**
     * 预计金额
     */
    private Long expectedAmount;

    /**
     * 城市
     */
    private String city;

    /**
     * 转介绍来源
     */
    private String referralSource;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 流程实例ID（flow_instance.id）
     */
    private Long flowInstanceId;

    /**
     * 删除标志（0存在 1删除）
     */
    @TableLogic
    private String delFlag;


}
