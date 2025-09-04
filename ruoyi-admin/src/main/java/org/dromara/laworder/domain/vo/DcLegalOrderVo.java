package org.dromara.laworder.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.laworder.domain.DcLegalOrder;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 法务接单记录视图对象 dc_legal_order
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcLegalOrder.class)
public class DcLegalOrderVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 客户ID（dc_customer.id）
     */
    @ExcelProperty(value = "客户ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "d=c_customer.id")
    private Long customerId;

    /**
     * 法务人员ID（dc_legal_support.id）
     */
    @ExcelProperty(value = "法务人员ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "d=c_legal_support.id")
    private Long legalSupportId;

    /**
     * 接单时间
     */
    @ExcelProperty(value = "接单时间")
    private Date acceptTime;

    /**
     * 服务截止时间
     */
    @ExcelProperty(value = "服务截止时间")
    private Date deadline;

    /**
     * 完成时间
     */
    @ExcelProperty(value = "完成时间")
    private Date completeTime;

    /**
     * 实际服务时长（单位：分钟）
     */
    @ExcelProperty(value = "实际服务时长", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "单=位：分钟")
    private Long serviceDuration;

    /**
     * 状态（PENDING待处理/PROCESSING处理中/COMPLETED已完成）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "P=ENDING待处理/PROCESSING处理中/COMPLETED已完成")
    private String orderStatus;


}
