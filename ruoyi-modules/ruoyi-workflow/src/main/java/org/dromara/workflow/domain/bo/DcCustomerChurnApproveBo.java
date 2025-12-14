package org.dromara.workflow.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.workflow.domain.DcCustomerChurnApprove;

/**
 * 客户流失审批业务对象 dc_customer_churn_approve
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomerChurnApprove.class, reverseConvertGenerate = false)
public class DcCustomerChurnApproveBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 租户编号
     */
    private String tenantId;

    /**
     * 审批类型
     */
    @NotBlank(message = "审批类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private String applyType;

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
     * 原因
     */
    private String remark;

    /**
     * 状态
     */
    private String status;
}
