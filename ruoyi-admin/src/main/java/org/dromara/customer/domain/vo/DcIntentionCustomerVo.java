package org.dromara.customer.domain.vo;

import org.dromara.customer.domain.DcIntentionCustomer;
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
 * 客户意向登记视图对象 dc_intention_customer
 *
 * @author leo
 * @date 2025-07-08
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcIntentionCustomer.class)
public class DcIntentionCustomerVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 客户名称
     */
    @ExcelProperty(value = "客户名称")
    private String customerName;

    /**
     * 联系方式
     */
    @ExcelProperty(value = "联系方式")
    private String contactPhone;

    /**
     * 意向签约类型
     */
    @ExcelProperty(value = "意向签约类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "intention_type")
    private String intentionType;

    /**
     * 预计金额
     */
    @ExcelProperty(value = "预计金额")
    private Long expectedAmount;

    /**
     * 城市
     */
    @ExcelProperty(value = "城市")
    private String city;

    /**
     * 转介绍来源
     */
    @ExcelProperty(value = "转介绍来源")
    private String referralSource;

    /**
     * 备注信息
     */
    @ExcelProperty(value = "备注信息")
    private String remark;

    /**
     * 流程实例ID（flow_instance.id）
     */
    @ExcelProperty(value = "流程实例ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "f=low_instance.id")
    private Long flowInstanceId;

    /**
     * 创建者（sys_user.user_id）
     */
    @ExcelProperty(value = "创建者", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "s=ys_user.user_id")
    private Long createBy;


}
