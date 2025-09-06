package org.dromara.customer.domain.vo;

import java.util.Date;

import org.dromara.customer.domain.DcCustomerInformation;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 客户总表视图对象 dc_customer_information
 *
 * @author Lion Li
 * @date 2025-09-06
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCustomerInformation.class)
public class DcCustomerInformationVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 签约日期
     */
    @ExcelProperty(value = "签约日期")
    private Date signDate;

    /**
     * 编号
     */
    @ExcelProperty(value = "编号")
    private String contractNo;

    /**
     * 客户名称
     */
    @ExcelProperty(value = "客户名称")
    private String customerName;

    /**
     * 负责人
     */
    @ExcelProperty(value = "负责人")
    private String principal;

    /**
     * 负责人电话
     */
    @ExcelProperty(value = "负责人电话")
    private String principalPhone;

    /**
     * 法务法务支持（律师id）
     */
    @ExcelProperty(value = "法务法务支持", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "律=师id")
    private Long lawyerId;

    /**
     * 甩单人
     */
    @ExcelProperty(value = "甩单人")
    private String transferPerson;

    /**
     * 杀单手
     */
    @ExcelProperty(value = "杀单手")
    private String closer;

    /**
     * 签约类型
     */
    @ExcelProperty(value = "签约类型")
    private Integer contractType;

    /**
     * 套餐类型
     */
    @ExcelProperty(value = "套餐类型")
    private Integer packageType;

    /**
     * 实收金额
     */
    @ExcelProperty(value = "实收金额")
    private Long actualReceipt;

    /**
     * 尾款金额
     */
    @ExcelProperty(value = "尾款金额")
    private Long balance;

    /**
     * 到期时间
     */
    @ExcelProperty(value = "到期时间")
    private Date expireDate;

    /**
     * 合同编号
     */
    @ExcelProperty(value = "合同编号")
    private String contractCode;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remarks;

    /**
     * 续费/尾款动作(1-续费 2-付尾款 3-其他)
     */
    @ExcelProperty(value = "续费/尾款动作(1-续费 2-付尾款 3-其他)")
    private Integer actionType;

    /**
     * 客户id（流转单id）
     */
    @ExcelProperty(value = "客户id", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "流=转单id")
    private Long transferId;


}
