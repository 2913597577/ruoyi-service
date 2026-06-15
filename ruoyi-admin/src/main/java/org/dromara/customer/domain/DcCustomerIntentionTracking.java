package org.dromara.customer.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 意向客户跟踪记录对象 dc_customer_intention_tracking
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_customer_intention_tracking")
public class DcCustomerIntentionTracking extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 意向客户表id
     */
    private Long intentionId;

    /**
     * 介绍客户id
     */
    private Long customerId;

    /**
     * 介绍客户名称
     */
    private String customerName;

    /**
     * 备注
     */
    private String customerRemark;

    /**
     * 跟踪时间
     */
    private Date trackingDate;

    /**
     * 下次跟踪时间
     */
    private Date nextTrackingDate;

    /**
     * 备注3
     */
    private String remark3;

    /**
     * 删除标志 0存在 1删除
     */
    @TableLogic
    private String delFlag;

    /**
     * 介绍客户名称
     */
    private String intentionName;

    /**
     * 意向客户所属城市
     */
    private String remark1;

    /**
     * 备注2
     */
    private String remark2;

    /**
     * 法务支持id
     */
    private Long legalSupportId;

    /**
     * 法务支持名称
     */
    private String legalSupportName;
}
