package org.dromara.customer.domain.bo;

import org.dromara.customer.domain.DcIntentionCustomer;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 客户意向登记业务对象 dc_intention_customer
 *
 * @author leo
 * @date 2025-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcIntentionCustomer.class, reverseConvertGenerate = false)
public class DcIntentionCustomerBo extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerName;

    /**
     * 联系方式
     */
    @NotBlank(message = "联系方式不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contactPhone;

    /**
     * 意向签约类型
     */
    private String intentionType;

    /**
     * 预计金额
     */
    private Long expectedAmount;

    /**
     * 城市
     */
    private String city;

    /**
     * 转介绍来源
     */
    private String referralSource;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 流程实例ID（flow_instance.id）
     */
    private Long flowInstanceId;


}
