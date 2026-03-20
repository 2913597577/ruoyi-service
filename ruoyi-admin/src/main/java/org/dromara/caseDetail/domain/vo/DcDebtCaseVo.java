package org.dromara.caseDetail.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.caseDetail.domain.DcDebtCase;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 欠款案件表视图对象 dc_debt_case
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcDebtCase.class)
public class DcDebtCaseVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @ExcelProperty(value = "自增主键")
    private Long id;

    /**
     * 客户id(客户编号)
     */
    @ExcelProperty(value = "客户id(客户编号)")
    private Long customerId;

    /**
     * 欠款人
     */
    @ExcelProperty(value = "欠款人")
    private String debtorName;

    /**
     * 欠款金额
     */
    @ExcelProperty(value = "欠款金额")
    private Long debtAmount;

    /**
     * 剩余欠款
     */
    @ExcelProperty(value = "剩余欠款")
    private Long remainingAmount;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String contactPhone;

    /**
     * 身份证号
     */
    @ExcelProperty(value = "身份证号")
    private String idCard;

    /**
     * 需求接收时间
     */
    @ExcelProperty(value = "需求接收时间")
    private Date requestReceiveTime;

    /**
     * 备注（证据情况等）
     */
    @ExcelProperty(value = "备注", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "证=据情况等")
    private String evidenceNotes;

    /**
     * 立案系统账号
     */
    @ExcelProperty(value = "立案系统账号")
    private String filingSystemAccount;

    /**
     * 立案密码
     */
    @ExcelProperty(value = "立案密码")
    private String filingPassword;

    /**
     * 立案日期
     */
    @ExcelProperty(value = "立案日期")
    private Date filingDate;

    /**
     * 下次联系时间
     */
    @ExcelProperty(value = "下次联系时间")
    private Date nextContactTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 0-未处理 1-推进中 2-无法推进 3 -已办结
     */
    @ExcelProperty(value = "0-未处理 1-推进中 2-无法推进 3 -已办结")
    private Integer caseStatus;

    /**
     * 法官
     */
    @ExcelProperty(value = "法官")
    private String judgeName;

    /**
     * 法官电话
     */
    @ExcelProperty(value = "法官电话")
    private String judgePhone;


    /**
     * 法务支持id
     */
    private Long legalSupportId;

    /**
     * 法务支持姓名
     */
    @ExcelProperty(value = "法务支持姓名")
    private String legalSupportName;

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


}
