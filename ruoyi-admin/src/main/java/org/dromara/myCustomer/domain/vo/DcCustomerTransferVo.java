package org.dromara.myCustomer.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.myCustomer.domain.DcCustomerTransfer;
import org.dromara.performance.domain.vo.DcCustomerPerformanceVo;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


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
    private BigDecimal actualPayment;

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

    /**
     * 邀约人id
     */
    @ExcelProperty(value = "邀约人")
    private Long inviterId;
    /**
     * 客户经理id
     */
    @ExcelProperty(value = "客户经理")
    private Long accountManagerId;
    /**
     * 合同金额
     */
    @ExcelProperty(value = "合同金额")
    private BigDecimal contractAmount;
    /**
     * 服务时长
     */
    @ExcelProperty(value = "服务时长")
    private String serviceDuration;
    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 交易时间
     */
    @ExcelProperty(value = "交易时间")
    private Date createTime;

    /**
     * 尾款支付条件
     */
    @ExcelProperty(value = "尾款支付条件")
    private String balancePayType;
    /**
     * 合同编号
     */
    @ExcelProperty(value = "合同编号")
    private String contractCode;
    /**
     * 合同ossId
     */
    private String contractOssId;

    /**
     * 客户地点照片Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "contractOssId")
    private String contractUrl;
    /**
     * 客户服务城市
     */
    @ExcelProperty(value = "客户服务城市")
    private String customerCity;
    /**
     * 审核人名称
     */
    @ExcelProperty(value = "审核人名称")
    private String auditUserName;
    /**
     * 审核人id
     */
    private Long auditUserId;
    /**
     * 审核时间
     */
    @ExcelProperty(value = "审核时间")
    private Date auditTime;

    /**
     * 发票要求
     */
    @ExcelProperty(value = "发票要求")
    private String invoiceRequirements;

    /**
     * 发票内容
     */
    @ExcelProperty(value = "发票内容")
    private String invoiceContent;

    /**
     * 发票状态
     */
    @ExcelProperty(value = "发票状态")
    private Integer invoiceStatus;

    /**
     * 决策人
     */
    @ExcelProperty(value = "决策人")
    private String decisionMaker;
    /**
     * 决策人联系方式
     */
    @ExcelProperty(value = "决策人联系方式")
    private String decisionMakerContact;
    /**
     * 决策人职务
     */
    @ExcelProperty(value = "决策人职务")
    private String decisionMakerPosition;
    /**
     * 决策人年龄
     */
    @ExcelProperty(value = "决策人年龄")
    private Integer decisionMakerAge;
    /**
     * 二次开发类型
     * 0-续费、1-尾款、2-咨询费、3-升级常法、4-律师函、5-合同定审、6-立案、7-诉讼文书、8-法务其他、9-财税代账、10-财税其他
     */
    @ExcelProperty(value = "二次开发类型")
    private Integer secondDevelopmentType;
    /**
     * 是否二次收费
     */
    @ExcelProperty(value = "是否二次收费")
    private Integer isSecondaryCharge;
    /**
     * 债务人
     */
    @ExcelProperty(value = "债务人")
    private String debtor;
    /**
     * 欠款金额
     */
    @ExcelProperty(value = "欠款金额")
    private BigDecimal debtAmount;
    /**
     * 债务人联系方式
     */
    @ExcelProperty(value = "债务人联系方式")
    private String debtorContact;
    /**
     * 证据备注
     */
    @ExcelProperty(value = "证据备注")
    private String evidenceRemark;
    /**
     * 客户来源
     * 0-销售外呼、1-地推引流、2-客户转介绍、3-电商媒体、4-上门/来电、5-员工资源、6-其他来源
     */
    @ExcelProperty(value = "客户来源")
    private String customerSource;
    /**
     * 推荐人id（客户id）
     */
    @ExcelProperty(value = "推荐人id")
    private Long referrerId;
    /**
     * 推荐人名称
     */
    @ExcelProperty(value = "推荐人名称")
    private String referrer;
    /**
     * 省份
     */
    @ExcelProperty(value = "省份")
    private String province;
    /**
     * 城市
     */
    @ExcelProperty(value = "城市")
    private String city;
    /**
     * 区
     */
    @ExcelProperty(value = "区")
    private String district;
    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 业绩信息
     */
    private List<DcCustomerPerformanceVo> performanceInfo;

    /**
     * 法务支持
     */
    private String legalSupport;

    /**
     * 签单时间
     */
    @ExcelProperty(value = "签单时间")
    private Date signDate;

    /**
     * 二次收费录入表数量
     */
    @ExcelProperty(value = "二次收费录入表数量")
    private Long logCount;

}
