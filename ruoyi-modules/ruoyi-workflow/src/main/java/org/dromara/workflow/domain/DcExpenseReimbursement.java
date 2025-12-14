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
 * 报销申请对象 dc_expense_reimbursement
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_expense_reimbursement")
public class DcExpenseReimbursement extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 填报日期
     */
    private Date applyDate;

    /**
     * 报销人ID
     */
    private Long applicantId;

    /**
     * 报销人姓名
     */
    private String applicantName;

    /**
     * 所属部门ID
     */
    private Long departmentId;

    /**
     * 所属部门名称
     */
    private String departmentName;

    /**
     * 岗位/职务
     */
    private String position;

    /**
     * 报销事由
     */
    private String expenseReason;

    /**
     * 费用日期
     */
    private Date expenseDate;

    /**
     * 费用类型（travel:差旅费, entertainment:招待费, office:办公费, transportation:交通费, etc）
     */
    private String expenseType;

    /**
     * 费用明细描述
     */
    private String expenseDescription;

    /**
     * 票据张数
     */
    private Integer invoiceCount;

    /**
     * 单据金额
     */
    private BigDecimal invoiceAmount;

    /**
     * 报销金额
     */
    private BigDecimal reimbursementAmount;

    /**
     * 金额差异原因说明
     */
    private String differenceReason;

    /**
     * 发票信息附件路径
     */
    private String attachmentPath;

    /**
     * 已预支金额
     */
    private BigDecimal advancedAmount;

    /**
     * 应退/应补金额（正数：应补，负数：应退）
     */
    private BigDecimal refundOrSupplement;

    /**
     * 状态
     */
    private String status;

    /**
     * 支付方式（cash:现金, bank_transfer:银行转账, alipay:支付宝, wechat:微信）
     */
    private String paymentMethod;

    /**
     * 收款账户银行账号
     */
    private String receiverBankAccount;

    /**
     * 收款账户开户行
     */
    private String receiverBankName;

    /**
     * 收款人
     */
    private String receiverName;

    /**
     * 财务记账凭证号
     */
    private String financeVoucherNo;

    /**
     * 支付日期
     */
    private Date paymentDate;

    /**
     * 支付人ID
     */
    private Long financePayerId;

    /**
     * 支付人姓名
     */
    private String financePayerName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标志 0存在 1删除
     */
    @TableLogic
    private String delFlag;
}
