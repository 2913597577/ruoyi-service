package org.dromara.caseDetail.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.caseDetail.domain.DcInsuranceCase;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 保险记录表业务对象 dc_insurance_case
 *
 * @author Lion Li
 * @date 2025-09-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcInsuranceCase.class, reverseConvertGenerate = false)
public class DcInsuranceCaseBo extends BaseEntity {

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
     * 下单日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    private BigDecimal subjectAmount;

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
    private BigDecimal premium;

    /**
     * 备注
     */
    private String remark;

    private List<Long> customerIds;

    /**
     * 下单月份(格式: yyyy-MM)
     */
    private String orderMonth;

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
