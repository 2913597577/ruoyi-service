package org.dromara.workflow.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.workflow.domain.DcHighRiskCustomer;

import java.time.LocalDate;

/**
 * 高风险客户记录业务对象 dc_high_risk_customer
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcHighRiskCustomer.class, reverseConvertGenerate = false)
public class DcHighRiskCustomerBo extends BaseEntity {

    /**
     * 自增主键ID
     */
    @NotNull(message = "自增主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 客户id
     */
    @NotNull(message = "客户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long customerId;

    /**
     * 客户姓名
     */
    @NotBlank(message = "客户姓名不能为空", groups = {AddGroup.class, EditGroup.class})
    private String customerName;

    /**
     * 证据情况
     */
    private String evidenceText;

    /**
     * 风险发现日期
     */
    @NotNull(message = "风险发现日期不能为空", groups = {AddGroup.class, EditGroup.class})
    private LocalDate riskDiscoveryDate;

    /**
     * 是否提及退费（1:是，0:否）
     */
    private Integer isRefundMentioned;

    /**
     * 风险判定（可多选）
     */
    private String riskDetermination;

    /**
     * 合规问题（可多选）
     */
    private String complianceIssues;

    /**
     * 原因
     */
    private String remark;

    /**
     * 状态
     */
    private String status;
}
