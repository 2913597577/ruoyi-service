package org.dromara.myCustomer.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 客户跟踪对象 dc_customer_tracking
 *
 * @author Lion Li
 * @date 2025-09-21
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
     * 跟踪记录
     */
    private String customerRemark;

    /**
     * 跟踪类型
     */
    private Integer trackingType;

    /**
     * 跟踪状态
     */
    private Integer cumtomerStatus;

    /**
     * 跟踪时间
     */
    private Date trackingTime;

    /**
     * 提交状态
     */
    private Integer submitStatus;

    /**
     * 下次跟踪时间
     */
    private Date nextTime;

    /**
     * 备注1
     */
    private String remark1;

    /**
     * 备注2
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

    /**
     * 是否是回访记录
     */
    private Integer isReturn;


    private Integer interCount;


}
