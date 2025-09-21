package org.dromara.myCustomer.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.myCustomer.domain.DcCustomerTracking;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 客户跟踪视图对象 dc_customer_tracking
 *
 * @author Lion Li
 * @date 2025-09-21
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
     * 跟踪记录
     */
    @ExcelProperty(value = "跟踪记录")
    private String customerRemark;

    /**
     * 跟踪类型
     */
    @ExcelProperty(value = "跟踪类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "customer_tracking_type")
    private Integer trackingType;

    /**
     * 跟踪状态
     */
    @ExcelProperty(value = "跟踪状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "cumtomer_status")
    private Integer cumtomerStatus;

    /**
     * 跟踪时间
     */
    @ExcelProperty(value = "跟踪时间")
    private Date trackingTime;

    /**
     * 提交状态
     */
    @ExcelProperty(value = "提交状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "submit_status")
    private Integer submitStatus;

    /**
     * 下次跟踪时间
     */
    @ExcelProperty(value = "下次跟踪时间")
    private Date nextTime;

    /**
     * 备注1
     */
    @ExcelProperty(value = "备注1")
    private String remark1;

    /**
     * 备注2
     */
    @ExcelProperty(value = "备注2")
    private String remark2;

    /**
     * 备注3
     */
    @ExcelProperty(value = "备注3")
    private String remark3;


}
