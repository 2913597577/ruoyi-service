package org.dromara.performance.domain.bo;

import org.dromara.performance.domain.DcCustomerPerformance;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 业绩归属登记业务对象 dc_customer_performance
 *
 * @author Lion Li
 * @date 2025-10-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomerPerformance.class, reverseConvertGenerate = false)
public class DcCustomerPerformanceBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 流转单id
     */
    @NotNull(message = "流转单id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long transferId;

    /**
     * 业绩所属用户id
     */
    @NotNull(message = "业绩所属用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 业绩所属用户名字
     */
    @NotNull(message = "业绩所属用户名字不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userName;

    /**
     * 业绩所属金额
     */
    @NotNull(message = "业绩所属金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long balance;

    /**
     * 业绩所属城市
     */
    @NotBlank(message = "业绩所属城市不能为空", groups = { AddGroup.class, EditGroup.class })
    private String city;


}
