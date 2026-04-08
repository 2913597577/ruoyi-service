package org.dromara.customer.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.customer.domain.DcCustomerIntention;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 客户意向登记视图对象 dc_customer_intention
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCustomerIntention.class)
public class DcCustomerIntentionVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 提报日期
     */
    @ExcelProperty(value = "提报日期")
    private Date submissionDate;

    /**
     * 法务支持
     */
    @ExcelProperty(value = "法务支持")
    private String legalSupport;

    /**
     * 法务支持id
     */
    private String legalSupportId;

    /**
     * 意向客户
     */
    @ExcelProperty(value = "意向客户")
    private String intendedCustomer;

    /**
     * 介绍人id
     */
    private Long introducerId;

    /**
     * 类型
     */
    @ExcelProperty(value = "类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "intention_type")
    private Integer type;

    /**
     * 来源
     */
    @ExcelProperty(value = "来源")
    private String source;

    /**
     * 预计金额
     */
    @ExcelProperty(value = "预计金额")
    private Long expectedAmount;

    /**
     * 介绍人
     */
    @ExcelProperty(value = "介绍人")
    private String introducer;

    /**
     * 跟进结果
     */
    @ExcelProperty(value = "跟进结果")
    private Integer followUpResult;

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
