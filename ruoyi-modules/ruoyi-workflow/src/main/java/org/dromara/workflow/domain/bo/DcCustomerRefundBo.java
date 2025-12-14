package org.dromara.workflow.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.workflow.domain.DcCustomerRefund;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户风险/退费业务对象 dc_customer_risk_refund
 *
 * @author Lion Li
 * @date 2025-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomerRefund.class, reverseConvertGenerate = false)
public class DcCustomerRefundBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 法务支持（律师id）
     */
    private Long lawyerId;

    /**
     * 客户id
     */
    @NotNull(message = "客户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long customerId;

    /**
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String customerName;

    /**
     * 客户对接人
     */
    @NotBlank(message = "客户对接人不能为空", groups = {AddGroup.class, EditGroup.class})
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
    @NotNull(message = "签约日期不能为空", groups = {AddGroup.class, EditGroup.class})
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
     * 风险/退费原因
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

}
