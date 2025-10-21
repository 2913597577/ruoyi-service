package org.dromara.performance.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 业绩归属登记对象 dc_customer_performance
 *
 * @author Lion Li
 * @date 2025-10-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_customer_performance")
public class DcCustomerPerformance extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 流转单id
     */
    private Long transferId;

    /**
     * 业绩所属用户id
     */
    private Long userId;

    /**
     * 业绩所属用户名字
     */
    private Long userName;

    /**
     * 业绩所属金额
     */
    private Long balance;

    /**
     * 业绩所属城市
     */
    private String city;

    /**
     * 删除标志 0存在 1删除
     */
    @TableLogic
    private String delFlag;


}
