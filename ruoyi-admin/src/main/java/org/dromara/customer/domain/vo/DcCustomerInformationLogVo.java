package org.dromara.customer.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.customer.domain.DcCustomerInformationLog;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 客户信息记录视图对象 dc_customer_information_log
 *
 * @author Lion Li
 * @date 2025-11-16
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCustomerInformationLog.class)
public class DcCustomerInformationLogVo implements Serializable {

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
    @ExcelProperty(value = "套餐类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "combo_type")
    private Integer packageType;

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

    /**
     * 到期时间
     */
    @ExcelProperty(value = "到期时间")
    private Date expireDate;

    /**
     * 是否转为意向客户
     */
    @ExcelProperty(value = "是否转为意向客户", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "dc_false_true")
    private Integer isIntention;

    /**
     * 是否转为风险客户
     */
    @ExcelProperty(value = "是否转为风险客户", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "dc_false_true")
    private Integer isRisk;

    /**
     * 是否转为退费客户
     */
    @ExcelProperty(value = "是否转为退费客户", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "dc_false_true")
    private Integer isRefund;

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

    /**
     * 客户总表id
     */
    @ExcelProperty(value = "客户总表id")
    private Long customerInfoId;

    /**
     * 客户总表创建时间
     */
    @ExcelProperty(value = "客户总表创建时间")
    private Date infoCreateTime;

    /**
     * 客户总表更新时间
     */
    @ExcelProperty(value = "客户总表更新时间")
    private Date infoUpdateTime;

    /**
     * 客户经理id
     */
    @ExcelProperty(value = "客户经理id")
    private Long accountManagerId;

    /**
     * 邀约人id
     */
    @ExcelProperty(value = "邀约人id")
    private Long inviterId;

    /**
     * 服务时长
     */
    @ExcelProperty(value = "服务时长")
    private String serviceDuration;

    /**
     * 合同金额
     */
    @ExcelProperty(value = "合同金额")
    private BigDecimal contractAmount;

    /**
     * 客户类型  A-0 B-1 C-2 D-3
     */
    @ExcelProperty(value = "客户类型  A-0 B-1 C-2 D-3", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "dc_customer_type")
    private Integer customerType;

    /**
     * 立案账号
     */
    @ExcelProperty(value = "立案账号")
    private String caseFillingAccount;

    /**
     * 立案密码
     */
    @ExcelProperty(value = "立案密码")
    private String caseFillingPwd;

    /**
     * 客户服务城市
     */
    @ExcelProperty(value = "客户服务城市")
    private String customerCity;

    /**
     * 是否分配法务支持 0-未分配 1-已分配
     */
    @ExcelProperty(value = "是否分配法务支持 0-未分配 1-已分配", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "dc_true_or_false")
    private Integer isAssigned;

    /**
     * 是否高风险客户 0-否 1-是
     */
    private Integer isHighRisk;

    /**
     * 备注1
     */
    @ExcelProperty(value = "备注1")
    private String remark1;

    /**
     * 备注2
     */
    @ExcelProperty(value = "备注2")
    private String remark2;

    /**
     * 备注3
     */
    @ExcelProperty(value = "备注3")
    private String remark3;

    /**
     * 服务开始时间
     */
    @ExcelProperty(value = "服务开始时间")
    private Date startDate;


}
