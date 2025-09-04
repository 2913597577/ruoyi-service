package org.dromara.laworder.domain.bo;

import org.dromara.laworder.domain.DcLegalOrder;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 法务接单记录业务对象 dc_legal_order
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcLegalOrder.class, reverseConvertGenerate = false)
public class DcLegalOrderBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 客户ID（dc_customer.id）
     */
    @NotNull(message = "客户ID（dc_customer.id）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long customerId;

    /**
     * 法务人员ID（dc_legal_support.id）
     */
    @NotNull(message = "法务人员ID（dc_legal_support.id）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long legalSupportId;

    /**
     * 接单时间
     */
    private Date acceptTime;

    /**
     * 服务截止时间
     */
    private Date deadline;

    /**
     * 完成时间
     */
    private Date completeTime;

    /**
     * 实际服务时长（单位：分钟）
     */
    private Long serviceDuration;

    /**
     * 状态（PENDING待处理/PROCESSING处理中/COMPLETED已完成）
     */
    private String orderStatus;


}
