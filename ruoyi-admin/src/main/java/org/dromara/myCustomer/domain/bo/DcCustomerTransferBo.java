package org.dromara.myCustomer.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.myCustomer.domain.DcCustomerTransfer;
import org.dromara.performance.domain.bo.DcCustomerPerformanceBo;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 客户信息录入业务对象 dc_customer_transfer
 *
 * @author Lion Li
 * @date 2025-09-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomerTransfer.class, reverseConvertGenerate = false)
public class DcCustomerTransferBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String companyName;

    /**
     * 公司对接人
     */
    @NotBlank(message = "公司对接人不能为空", groups = {AddGroup.class, EditGroup.class})
    private String contactPerson;

    /**
     * 公司对接人联系方式
     */
    private String contactInfo;

    /**
     * 对接人职务
     */
    private String contactPosition;

    /**
     * 对接人年龄
     */
    private Integer contactAge;

    /**
     * 附赠自然人
     */
    private String additionalPerson;

    /**
     * 附赠自然人联系方式
     */
    private String additionalContact;

    /**
     * 附赠自然人职务
     */
    private String additionalPosition;

    /**
     * 附赠自然人年龄
     */
    private Long additionalAge;

    /**
     * 公司所属行业
     */
    private String companyIndustry;

    /**
     * 公司地址
     */
    private String companyAddress;

    /**
     * 员工人数
     */
    private Integer employeeCount;

    /**
     * 是否有代账公司(1:是,0:否)
     */
    private Integer accountingCompany;

    /**
     * 客户性格及工作习惯描述
     */
    private String customerDescription;

    /**
     * 实付金额
     */
    private BigDecimal actualPayment;

    /**
     * 尾款情况
     */
    private BigDecimal balanceStatus;

    /**
     * 签约类型(1-常法 2-单项 3-律师费 4-其他)
     */
    private Integer contractType;

    /**
     * 常法签约(1-升级版 2-标准版 3-其他)
     */
    private Integer serviceType;

    /**
     * 服务周期开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date serviceStart;

    /**
     * 服务周期结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date serviceEnd;

    /**
     * 律师咨询情况
     */
    private String lawyerConsultation;

    /**
     * 其他费用沟通
     */
    private String otherFee;

    /**
     * 财务是否确认(1:是,0:否)
     */
    private Integer financeConfirmed;

    /**
     * 财务签名
     */
    private String financeSignature;

    /**
     * 以前是否有过公司法务(1:是,0:否)
     */
    private Integer preLegal;

    /**
     * 以前合作公司名称
     */
    private String preCompany;

    /**
     * 以前不合作原因
     */
    private String preReason;

    /**
     * 公司以前出现过的纠纷及解决方式
     */
    private String preDiscuss;

    /**
     * 待处理事项登记(1-劳资纠纷2-合同纠纷3-借贷纠纷4-承揽纠纷5-财税问题6-执行案件7-其他)
     */
    private Integer pendingMatters;

    /**
     * 待处理事项备注
     */
    private String pendingRemark;

    /**
     * 欠款问题详细登记(1- 相关主体2-已知债务人信息3-标的额4-证据情况5-案件处理要求6-其他)
     */
    private Integer debtDetails;

    /**
     * 欠款问题备注
     */
    private String debtRemark;

    /**
     * 邀约人id
     */
    private Long inviterId;
    /**
     * 客户经理id
     */
    private Long accountManagerId;
    /**
     * 合同金额
     */
    private BigDecimal contractAmount;
    /**
     * 服务时长
     */
    private String serviceDuration;
    /**
     * 备注
     */
    private String remark;
    /**
     * 尾款支付条件
     */
    private String balancePayType;
    /**
     * 合同编号
     */
    private String contractCode;
    /**
     * 合同ossId
     */
    private String contractOssId;
    /**
     * 客户服务城市
     */
    private String customerCity;
    /**
     * 审核人名称
     */
    private String auditUserName;
    /**
     * 审核人id
     */
    private Long auditUserId;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 发票要求
     */
    private String invoiceRequirements;

    /**
     * 发票内容
     */
    private String invoiceContent;

    /**
     * 发票状态(0-待开票 1-已开票 )
     */
    private Integer invoiceStatus;

    /**
     * 决策人
     */
    private String decisionMaker;
    /**
     * 决策人联系方式
     */

    private String decisionMakerContact;
    /**
     * 决策人职务
     */
    private String decisionMakerPosition;
    /**
     * 决策人年龄
     */
    private Integer decisionMakerAge;
    /**
     * 二次开发类型
     * 0-续费、1-尾款、2-咨询费、3-升级常法、4-律师函、5-合同定审、6-立案、7-诉讼文书、8-法务其他、9-财税代账、10-财税其他
     */
    private Integer secondDevelopmentType;
    /**
     * 是否二次收费
     */
    private Integer isSecondaryCharge;
    /**
     * 债务人
     */
    private String debtor;
    /**
     * 欠款金额
     */
    private BigDecimal debtAmount;
    /**
     * 债务人联系方式
     */
    private String debtorContact;
    /**
     * 证据备注
     */
    private String evidenceRemark;
    /**
     * 客户来源
     * 0-销售外呼、1-地推引流、2-客户转介绍、3-电商媒体、4-上门/来电、5-员工资源、6-其他来源
     */
    private String customerSource;
    /**
     * 推荐人id（客户id）
     */
    private Long referrerId;
    /**
     * 推荐人名称
     */
    private String referrer;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区
     */
    private String district;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 签单日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signDate;

    private List<Long> deptIds;

    /**
     * 签约月份(格式: yyyy-MM)
     */
    private String signDateMonth;

    /**
     * 二次收费录入表中数量（用于筛选）
     */
    private Long logCount;

    private List<DcCustomerPerformanceBo> performanceInfo;




}
