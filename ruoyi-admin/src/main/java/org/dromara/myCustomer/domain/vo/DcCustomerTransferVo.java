package org.dromara.myCustomer.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.myCustomer.domain.DcCustomerTransfer;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 客户信息录入视图对象 dc_customer_transfer
 *
 * @author Lion Li
 * @date 2025-09-06
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCustomerTransfer.class)
public class DcCustomerTransferVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 公司名称
     */
    @ExcelProperty(value = "公司名称")
    private String companyName;

    /**
     * 公司对接人
     */
    @ExcelProperty(value = "公司对接人")
    private String contactPerson;

    /**
     * 公司对接人联系方式
     */
    @ExcelProperty(value = "公司对接人联系方式")
    private String contactInfo;

    /**
     * 对接人职务
     */
    @ExcelProperty(value = "对接人职务")
    private String contactPosition;

    /**
     * 对接人年龄
     */
    @ExcelProperty(value = "对接人年龄")
    private Integer contactAge;

    /**
     * 附赠自然人
     */
    @ExcelProperty(value = "附赠自然人")
    private String additionalPerson;

    /**
     * 附赠自然人联系方式
     */
    @ExcelProperty(value = "附赠自然人联系方式")
    private String additionalContact;

    /**
     * 附赠自然人职务
     */
    @ExcelProperty(value = "附赠自然人职务")
    private String additionalPosition;

    /**
     * 附赠自然人年龄
     */
    @ExcelProperty(value = "附赠自然人年龄")
    private Long additionalAge;

    /**
     * 公司所属行业
     */
    @ExcelProperty(value = "公司所属行业")
    private String companyIndustry;

    /**
     * 公司地址
     */
    @ExcelProperty(value = "公司地址")
    private String companyAddress;

    /**
     * 员工人数
     */
    @ExcelProperty(value = "员工人数")
    private Integer employeeCount;

    /**
     * 是否有代账公司(1:是,0:否)
     */
    @ExcelProperty(value = "是否有代账公司(1:是,0:否)")
    private Integer accountingCompany;

    /**
     * 客户性格及工作习惯描述
     */
    @ExcelProperty(value = "客户性格及工作习惯描述")
    private String customerDescription;

    /**
     * 实付金额
     */
    @ExcelProperty(value = "实付金额")
    private Long actualPayment;

    /**
     * 尾款情况
     */
    @ExcelProperty(value = "尾款情况")
    private Long balanceStatus;

    /**
     * 签约类型(1-常法 2-单项 3-律师费 4-其他)
     */
    @ExcelProperty(value = "签约类型(1-常法 2-单项 3-律师费 4-其他)")
    private Integer contractType;

    /**
     * 常法签约(1-升级版 2-标准版 3-其他)
     */
    @ExcelProperty(value = "常法签约(1-升级版 2-标准版 3-其他)")
    private Integer serviceType;

    /**
     * 服务周期开始时间
     */
    @ExcelProperty(value = "服务周期开始时间")
    private Date serviceStart;

    /**
     * 服务周期结束时间
     */
    @ExcelProperty(value = "服务周期结束时间")
    private Date serviceEnd;

    /**
     * 律师咨询情况
     */
    @ExcelProperty(value = "律师咨询情况")
    private String lawyerConsultation;

    /**
     * 其他费用沟通
     */
    @ExcelProperty(value = "其他费用沟通")
    private String otherFee;

    /**
     * 财务是否确认(1:是,0:否)
     */
    @ExcelProperty(value = "财务审核状态(0:待审核 1:审核通过 2:审核不通过")
    private Integer financeConfirmed;

    /**
     * 以前是否有过公司法务(1:是,0:否)
     */
    @ExcelProperty(value = "以前是否有过公司法务(1:是,0:否)")
    private Integer preLegal;

    /**
     * 以前合作公司名称
     */
    @ExcelProperty(value = "以前合作公司名称")
    private String preCompany;

    /**
     * 以前不合作原因
     */
    @ExcelProperty(value = "以前不合作原因")
    private String preReason;

    /**
     * 公司以前出现过的纠纷及解决方式
     */
    @ExcelProperty(value = "公司以前出现过的纠纷及解决方式")
    private String preDiscuss;

    /**
     * 待处理事项登记(1-劳资纠纷2-合同纠纷3-借贷纠纷4-承揽纠纷5-财税问题6-执行案件7-其他)
     */
    @ExcelProperty(value = "待处理事项登记(1-劳资纠纷2-合同纠纷3-借贷纠纷4-承揽纠纷5-财税问题6-执行案件7-其他)")
    private Integer pendingMatters;

    /**
     * 待处理事项备注
     */
    @ExcelProperty(value = "待处理事项备注")
    private String pendingRemark;

    /**
     * 欠款问题详细登记(1- 相关主体2-已知债务人信息3-标的额4-证据情况5-案件处理要求6-其他)
     */
    @ExcelProperty(value = "欠款问题详细登记(1- 相关主体2-已知债务人信息3-标的额4-证据情况5-案件处理要求6-其他)")
    private Integer debtDetails;

    /**
     * 欠款问题备注
     */
    @ExcelProperty(value = "欠款问题备注")
    private String debtRemark;


}
