package org.dromara.myCustomer.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import java.io.Serial;

/**
 * 客户跟踪对象 dc_customer_tracking
 *
 * @author Lion Li
 * @date 2025-09-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_customer_tracking")
public class DcCustomerTracking extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 备注
     */
    private String customerRemark;

    /**
     * 跟踪状态
     */
    private Long cumtomerStatus;

    /**
     * 跟踪时间
     */
    private Date trackingTime;

    /**
     * 下次跟踪时间
     */
    private Date nextTime;

    /**
     * 删除标志 0存在 1删除
     */
    @TableLogic
    private String delFlag;


}
