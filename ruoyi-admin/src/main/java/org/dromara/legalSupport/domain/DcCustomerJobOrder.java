package org.dromara.legalSupport.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 工单管理对象 dc_customer_job_order
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_customer_job_order")
public class DcCustomerJobOrder extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
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

    /**
     * 删除标志 0存在 1删除
     */
    @TableLogic
    private String delFlag;

    private long customerId;

    private String customerName;


}
