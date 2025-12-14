package org.dromara.workflow.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.workflow.domain.DcExpenseReimbursement;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 报销申请业务对象 dc_expense_reimbursement
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcExpenseReimbursement.class, reverseConvertGenerate = false)
public class DcExpenseReimbursementBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 填报日期
     */
    @NotNull(message = "填报日期不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date applyDate;

    /**
     * 报销人ID
     */
    @NotNull(message = "报销人ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long applicantId;

    /**
     * 报销人姓名
     */
    @NotBlank(message = "报销人姓名不能为空", groups = {AddGroup.class, EditGroup.class})
    private String applicantName;

    /**
     * 所属部门ID
     */
    @NotNull(message = "所属部门ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long departmentId;

    /**
     * 所属部门名称
     */
    @NotBlank(message = "所属部门名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String departmentName;

    /**
     * 岗位/职务
     */
    private String position;

    /**
     * 报销事由
     */
    @NotBlank(message = "报销事由不能为空", groups = {AddGroup.class, EditGroup.class})
    private String expenseReason;

    /**
     * 费用日期
     */
    @NotNull(message = "费用日期不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date expenseDate;

    /**
     * 费用类型（travel:差旅费, entertainment:招待费, office:办公费, transportation:交通费, etc）
     */
    @NotBlank(message = "费用类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private String expenseType;

    /**
     * 费用明细描述
     */
    @NotBlank(message = "费用明细描述不能为空", groups = {AddGroup.class, EditGroup.class})
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
}
