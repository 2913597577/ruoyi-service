package org.dromara.workflow.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户退费对象 dc_customer_risk_refund
 *
 * @author Lion Li
 * @date 2025-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_customer_refund")
public class DcCustomerRefund extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 法务支持（律师id）
     */
    private Long lawyerId;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户对接人
     */
    private String principal;

    /**
     * 客户对接人联系方式
     */
    private String principalPhone;

    /**
     * 大成负责人id
     */
    private Long inviterId;

    /**
     * 签约日期
     */
    private Date signDate;

    /**
     * 到期时间
     */
    private Date expireDate;

    /**
     * 签单金额
     */
    private BigDecimal contractAmount;

    /**
     * 服务时长
     */
    private String serviceHours;

    /**
     * 退费原因
     */
    private String reasons;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 备注
     */
    private String remark1;

    /**
     * 备注
     */
    private String remark2;

    /**
     * 备注
     */
    private String remark3;

    /**
     * 状态
     */
    private String status;

    /**
     * 原合同号
     */
    private String contractNo;

    /**
     * 退款方式
     */
    private String refundMethod;

    /**
     * 退款方开户行
     */
    private String refundBank;

    /**
     * 退款方开户行账号
     */
    private String bankNumber;

    /**
     * 退款方开户名
     */
    private String refundAccountName;

    /**
     * 退款日期
     */
    private Date refundDate;

    /**
     * 退款凭证号
     */
    private String refundVoucherNo;

    /**
     * 财务支付人
     */
    private String financePayer;

    /**
     * 记账凭证号
     */
    private String accountingVoucherNo;

    /**
     * 实收金额
     */
    private BigDecimal actualReceipt;

    /**
     * 尾款金额
     */
    private BigDecimal balance;

    /**
     * 删除标志 0存在 1删除
     */
    @TableLogic
    private String delFlag;


}
