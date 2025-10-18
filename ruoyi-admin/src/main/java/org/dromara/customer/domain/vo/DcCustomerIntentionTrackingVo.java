package org.dromara.customer.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.customer.domain.DcCustomerIntentionTracking;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 意向客户跟踪记录视图对象 dc_customer_intention_tracking
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCustomerIntentionTracking.class)
public class DcCustomerIntentionTrackingVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 意向客户表id
     */
    @ExcelProperty(value = "意向客户id")
    private Long intentionId;

    /**
     * 意向客户id
     */
    private Long customerId;

    /**
     * 意向客户
     */
    @ExcelProperty(value = "意向客户")
    private String customerName;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String customerRemark;

    /**
     * 跟踪时间
     */
    @ExcelProperty(value = "跟踪时间")
    private Date trackingDate;

    /**
     * 下次跟踪时间
     */
    @ExcelProperty(value = "下次跟踪时间")
    private Date nextTrackingDate;


}
