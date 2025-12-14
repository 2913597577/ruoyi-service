package org.dromara.workflow.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.workflow.domain.DcCustomerRefund;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 客户风险/退费视图对象 dc_customer_risk_refund
 *
 * @author Lion Li
 * @date 2025-09-19
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCustomerRefund.class)
public class DcCustomerRefundVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 法务支持（律师id）
     */
    @ExcelProperty(value = "法务支持", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "律=师id")
    private Long lawyerId;

    /**
     * 客户id
     */
    @ExcelProperty(value = "客户id")
    private Long customerId;

    /**
     * 客户名称
     */
    @ExcelProperty(value = "客户名称")
    private String customerName;

    /**
     * 客户对接人
     */
    @ExcelProperty(value = "客户对接人")
    private String principal;

    /**
     * 客户对接人联系方式
     */
    @ExcelProperty(value = "客户对接人联系方式")
    private String principalPhone;

    /**
     * 大成负责人id
     */
    @ExcelProperty(value = "大成负责人id")
    private Long inviterId;

    /**
     * 签约日期
     */
    @ExcelProperty(value = "签约日期")
    private Date signDate;

    /**
     * 到期时间
     */
    @ExcelProperty(value = "到期时间")
    private Date expireDate;

    /**
     * 签单金额
     */
    @ExcelProperty(value = "签单金额")
    private BigDecimal contractAmount;

    /**
     * 服务时长
     */
    @ExcelProperty(value = "服务时长")
    private String serviceHours;

    /**
     * 风险/退费原因
     */
    @ExcelProperty(value = "退费原因")
    private String reasons;

    /**
     * 退款金额
     */
    @ExcelProperty(value = "退款金额")
    private BigDecimal refundAmount;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark1;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark2;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark3;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 原合同号
     */
    @ExcelProperty(value = "原合同号")
    private String contractNo;

    /**
     * 退款方式
     */
    @ExcelProperty(value = "退款方式")
    private String refundMethod;

    /**
     * 退款方开户行
     */
    @ExcelProperty(value = "退款方开户行")
    private String refundBank;

    /**
     * 退款方开户行账号
     */
    @ExcelProperty(value = "退款方开户行账号")
    private String bankNumber;

    /**
     * 退款方开户名
     */
    @ExcelProperty(value = "退款方开户名")
    private String refundAccountName;

    /**
     * 退款日期
     */
    @ExcelProperty(value = "退款日期")
    private Date refundDate;

    /**
     * 退款凭证号
     */
    @ExcelProperty(value = "退款凭证号")
    private String refundVoucherNo;

    /**
     * 财务支付人
     */
    @ExcelProperty(value = "财务支付人")
    private String financePayer;

    /**
     * 记账凭证号
     */
    @ExcelProperty(value = "记账凭证号")
    private String accountingVoucherNo;

    /**
     * 实收金额
     */
    @ExcelProperty(value = "实收金额")
    private BigDecimal actualReceipt;

    /**
     * 尾款金额
     */
    @ExcelProperty(value = "尾款金额")
    private BigDecimal balance;

}
