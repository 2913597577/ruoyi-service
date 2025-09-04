package org.dromara.customerflow.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.customerflow.domain.DcCustomerFlow;
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
 * 待提交流转单视图对象 dc_customer_flow
 *
 * @author leo
 * @date 2025-07-08
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCustomerFlow.class)
public class DcCustomerFlowVo implements Serializable {

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
     * 意向客户ID（dc_intention_customer.id）
     */
    @ExcelProperty(value = "意向客户ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "d=c_intention_customer.id")
    private Long intentionId;

    /**
     * 流程实例ID（flow_instance.id）
     */
    @ExcelProperty(value = "流程实例ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "f=low_instance.id")
    private Long flowInstanceId;

    /**
     * 流转状态（PENDING待提交/CONFIRM待确认/ARCHIVED已归档/REJECTED已退回/COMPLETED已完成）
     */
    @ExcelProperty(value = "流转状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "P=ENDING待提交/CONFIRM待确认/ARCHIVED已归档/REJECTED已退回/COMPLETED已完成")
    private String flowStatus;

    /**
     * 流转类型（TRANSFER转交/ARCHIVE归档/RETURN退回等）
     */
    @ExcelProperty(value = "流转类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "T=RANSFER转交/ARCHIVE归档/RETURN退回等")
    private String flowType;

    /**
     * 提交人ID（sys_user.user_id）
     */
    @ExcelProperty(value = "提交人ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "s=ys_user.user_id")
    private Long submitBy;

    /**
     * 提交时间
     */
    @ExcelProperty(value = "提交时间")
    private Date submitTime;

    /**
     * 确认人ID（sys_user.user_id）
     */
    @ExcelProperty(value = "确认人ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "s=ys_user.user_id")
    private Long confirmBy;

    /**
     * 确认时间
     */
    @ExcelProperty(value = "确认时间")
    private Date confirmTime;

    /**
     * 归档人ID（sys_user.user_id）
     */
    @ExcelProperty(value = "归档人ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "s=ys_user.user_id")
    private Long archiveBy;

    /**
     * 归档时间
     */
    @ExcelProperty(value = "归档时间")
    private Date archiveTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
