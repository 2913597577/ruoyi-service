package org.dromara.caseDetail.domain;

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
 * 欠款案件表对象 dc_debt_case
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_debt_case")
public class DcDebtCase extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 客户id(客户编号)
     */
    private Long customerId;

    /**
     * 欠款人
     */
    private String debtorName;

    /**
     * 欠款金额
     */
    private BigDecimal debtAmount;

    /**
     * 剩余欠款
     */
    private BigDecimal remainingAmount;

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
    private Date filingDate;

    /**
     * 下次联系时间
     */
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

    /**
     * 删除标志 0存在 1删除
     */
    @TableLogic
    private String delFlag;

    /**
     * 法务支持id
     */
    private Long legalSupportId;

    /**
     * 法务支持姓名
     */
    private String legalSupportName;

}
