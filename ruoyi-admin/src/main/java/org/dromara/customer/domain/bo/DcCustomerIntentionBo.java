package org.dromara.customer.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.customer.domain.DcCustomerIntention;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 客户意向登记业务对象 dc_customer_intention
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomerIntention.class, reverseConvertGenerate = false)
public class DcCustomerIntentionBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 提报日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date submissionDate;

    /**
     * 法务支持
     */
    private String legalSupport;

    /**
     * 法务支持ID
     */
    private Long legalSupportId;

    /**
     * 意向客户
     */
    private String intendedCustomer;

    /**
     * 介绍人id
     */
    private Long introducerId;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 来源
     */
    private String source;

    /**
     * 预计金额
     */
    private Long expectedAmount;

    /**
     * 介绍人
     */
    private String introducer;

    /**
     * 跟进结果
     */
    private Integer followUpResult;


}
