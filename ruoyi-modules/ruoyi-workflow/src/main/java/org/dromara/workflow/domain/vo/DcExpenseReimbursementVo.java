package org.dromara.workflow.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.workflow.domain.DcExpenseReimbursement;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 报销申请视图对象 dc_expense_reimbursement
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcExpenseReimbursement.class)
public class DcExpenseReimbursementVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 填报日期
     */
    @ExcelProperty(value = "填报日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyDate;

    /**
     * 报销人ID
     */
    private Long applicantId;

    /**
     * 报销人姓名
     */
    @ExcelProperty(value = "报销人姓名")
    private String applicantName;

    /**
     * 所属部门ID
     */
    private Long departmentId;

    /**
     * 所属部门名称
     */
    @ExcelProperty(value = "所属部门名称")
    private String departmentName;

    /**
     * 岗位/职务
     */
    @ExcelProperty(value = "岗位/职务")
    private String position;

    /**
     * 报销事由
     */
    @ExcelProperty(value = "报销事由")
    private String expenseReason;

    /**
     * 费用日期
     */
    @ExcelProperty(value = "费用日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expenseDate;

    /**
     * 费用类型（travel:差旅费, entertainment:招待费, office:办公费, transportation:交通费, etc）
     */
    @ExcelProperty(value = "费用类型")
    private String expenseType;

    /**
     * 费用明细描述
     */
    @ExcelProperty(value = "费用明细描述")
    private String expenseDescription;

    /**
     * 票据张数
     */
    @ExcelProperty(value = "票据张数")
    private Integer invoiceCount;

    /**
     * 单据金额
     */
    @ExcelProperty(value = "单据金额")
    private BigDecimal invoiceAmount;

    /**
     * 报销金额
     */
    @ExcelProperty(value = "报销金额")
    private BigDecimal reimbursementAmount;

    /**
     * 金额差异原因说明
     */
    @ExcelProperty(value = "金额差异原因说明")
    private String differenceReason;

    /**
     * 发票信息附件路径
     */
    @ExcelProperty(value = "发票信息附件路径")
    private String attachmentPath;

    /**
     * 已预支金额
     */
    @ExcelProperty(value = "已预支金额")
    private BigDecimal advancedAmount;

    /**
     * 应退/应补金额（正数：应补，负数：应退）
     */
    @ExcelProperty(value = "应退/应补金额")
    private BigDecimal refundOrSupplement;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 支付方式（cash:现金, bank_transfer:银行转账, alipay:支付宝, wechat:微信）
     */
    @ExcelProperty(value = "支付方式")
    private String paymentMethod;

    /**
     * 收款账户银行账号
     */
    @ExcelProperty(value = "收款账户银行账号")
    private String receiverBankAccount;

    /**
     * 收款账户开户行
     */
    @ExcelProperty(value = "收款账户开户行")
    private String receiverBankName;

    /**
     * 收款人
     */
    @ExcelProperty(value = "收款人")
    private String receiverName;

    /**
     * 财务记账凭证号
     */
    @ExcelProperty(value = "财务记账凭证号")
    private String financeVoucherNo;

    /**
     * 支付日期
     */
    @ExcelProperty(value = "支付日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paymentDate;

    /**
     * 支付人ID
     */
    @ExcelProperty(value = "支付人ID")
    private Long financePayerId;

    /**
     * 支付人姓名
     */
    @ExcelProperty(value = "支付人姓名")
    private String financePayerName;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
