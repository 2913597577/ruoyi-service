package org.dromara.legalSupport.domain.bo;

import org.dromara.legalSupport.domain.DcCustomerJobOrder;
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
 * 工单管理业务对象 dc_customer_job_order
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomerJobOrder.class, reverseConvertGenerate = false)
public class DcCustomerJobOrderBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 法务支持
     */
    private String legalSupport;

    /**
     * 法务支持id
     */
    private Long legalSupportId;

    /**
     * 源合同地址
     */
    private Long preContractAddress;

    /**
     * 源合同文件名
     */
    private String preContractName;

    /**
     * 新合同地址
     */
    private Long newContractAddress;

    /**
     * 新合同文件名
     */
    private String newContractName;

    /**
     * 客户要求
     */
    private String customerRequirements;

    /**
     * 交付时间
     */
    @NotNull(message = "交付时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date deliveryTime;

    /**
     * 跟踪记录id
     */
    private Long trackingId;

    /**
     * 处理人id
     */
    private Long contractHandler;

    /**
     * 处理人
     */
    private String contractHandlerName;

    /**
     * 工单处理状态
     */
    private Integer processingStatus;

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
