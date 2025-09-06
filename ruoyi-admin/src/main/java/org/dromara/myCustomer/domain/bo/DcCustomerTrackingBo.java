package org.dromara.myCustomer.domain.bo;

import org.dromara.myCustomer.domain.DcCustomerTracking;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * 客户跟踪业务对象 dc_customer_tracking
 *
 * @author Lion Li
 * @date 2025-09-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomerTracking.class, reverseConvertGenerate = false)
public class DcCustomerTrackingBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 客户id
     */
    @NotNull(message = "客户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long customerId;

    /**
     * 备注
     */
    private String customerRemark;

    /**
     * 跟踪状态
     */
    private Long cumtomerStatus;

    /**
     * 跟踪时间
     */
    private Date trackingTime;

    /**
     * 下次跟踪时间
     */
    private Date nextTime;


}
