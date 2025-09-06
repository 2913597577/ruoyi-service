package org.dromara.myCustomer.domain.vo;

import java.util.Date;

import org.dromara.myCustomer.domain.DcCustomerTracking;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 客户跟踪视图对象 dc_customer_tracking
 *
 * @author Lion Li
 * @date 2025-09-06
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCustomerTracking.class)
public class DcCustomerTrackingVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 客户id
     */
    @ExcelProperty(value = "客户id")
    private Long customerId;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String customerRemark;

    /**
     * 跟踪状态
     */
    @ExcelProperty(value = "跟踪状态")
    private Long cumtomerStatus;

    /**
     * 跟踪时间
     */
    @ExcelProperty(value = "跟踪时间")
    private Date trackingTime;

    /**
     * 下次跟踪时间
     */
    @ExcelProperty(value = "下次跟踪时间")
    private Date nextTime;


}
