package org.dromara.customer.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 客户意向登记对象 dc_customer_intention
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_customer_intention")
public class DcCustomerIntention extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 提报日期
     */
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
     * 意向客户id
     */
    private Long intendedCustomerId;

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

    /**
     * 删除标志（0存在 1删除）
     */
    @TableLogic
    private String delFlag;


}
