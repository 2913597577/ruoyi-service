package org.dromara.customer.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.customer.domain.DcCustomerIntentionTracking;

/**
 * 意向客户跟踪记录业务对象 dc_customer_intention_tracking
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomerIntentionTracking.class, reverseConvertGenerate = false)
public class DcCustomerIntentionTrackingBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 意向客户表id
     */
    @NotNull(message = "意向客户表id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long inentionId;

    /**
     * 意向客户id
     */
    @NotNull(message = "意向客户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long customerId;

    /**
     * 意向客户
     */
    @NotNull(message = "意向客户不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long customerName;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = {AddGroup.class, EditGroup.class})
    private String customerRemark;


}
