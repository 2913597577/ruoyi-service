package org.dromara.caseDetail.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 保险记录表对象 dc_insurance_case
 *
 * @author Lion Li
 * @date 2025-09-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_insurance_case")
public class DcInsuranceCase extends TenantEntity {

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
     * 下单日期
     */
    private Date orderDate;

    /**
     * 工单号
     */
    private String insuranceNumber;

    /**
     * 法务支持id
     */
    private Long legalSupportId;

    /**
     * 法务支持姓名
     */
    private String legalSupportName;

    /**
     * 原告方
     */
    private String plaintiff;

    /**
     * 被告方
     */
    private String defendant;

    /**
     * 标的额
     */
    private Long subjectAmount;

    /**
     * 案由
     */
    private String caseReason;

    /**
     * 管辖权法院
     */
    private String jurisdictionCourt;

    /**
     * 保费
     */
    private Long premium;

    /**
     * 备注
     */
    private String remark;

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


}
