package org.dromara.caseDetail.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.caseDetail.domain.DcDebtCase;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 欠款案件表业务对象 dc_debt_case
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcDebtCase.class, reverseConvertGenerate = false)
public class DcDebtCaseBo extends BaseEntity {

    /**
     * 自增主键
     */
    @NotNull(message = "自增主键不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 客户id(客户编号)
     */
    @NotNull(message = "客户id(客户编号)不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long customerId;

    /**
     * 欠款人
     */
    private String debtorName;

    /**
     * 欠款金额
     */
    private Long debtAmount;

    /**
     * 剩余欠款
     */
    private Long remainingAmount;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 需求接收时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date requestReceiveTime;

    /**
     * 备注（证据情况等）
     */
    private String evidenceNotes;

    /**
     * 立案系统账号
     */
    private String filingSystemAccount;

    /**
     * 立案密码
     */
    private String filingPassword;

    /**
     * 立案日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date filingDate;

    /**
     * 下次联系时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nextContactTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0-未处理 1-推进中 2-无法推进 3 -已办结
     */
    private Integer caseStatus;

    /**
     * 法官
     */
    private String judgeName;

    /**
     * 法官电话
     */
    private String judgePhone;


    /**
     * 法务支持id
     */
    @NotNull(message = "法务支持不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long legalSupportId;

    /**
     * 法务支持姓名
     */
    private String legalSupportName;

    /**
     * 备注1
     */
    private String remark1;

    /**
     * 备注2
     */
    private String remark2;

    /**
     * 备注3
     */
    private String remark3;


}
