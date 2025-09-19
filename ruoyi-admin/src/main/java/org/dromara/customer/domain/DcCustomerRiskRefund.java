package org.dromara.customer.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户风险/退费对象 dc_customer_risk_refund
 *
 * @author Lion Li
 * @date 2025-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_customer_risk_refund")
public class DcCustomerRiskRefund extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 法务支持（律师id）
     */
    private Long lawyerId;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户对接人
     */
    private String principal;

    /**
     * 客户对接人联系方式
     */
    private String principalPhone;

    /**
     * 大成负责人id
     */
    private Long inviterId;

    /**
     * 签约日期
     */
    private Date signDate;

    /**
     * 到期时间
     */
    private Date expireDate;

    /**
     * 签单金额
     */
    private BigDecimal contractAmount;

    /**
     * 服务时长
     */
    private String serviceHours;

    /**
     * 1-风险 2-退费
     */
    private Integer customerType;

    /**
     * 风险/退费原因
     */
    private String reasons;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 备注
     */
    private String remark1;

    /**
     * 备注
     */
    private String remark2;

    /**
     * 备注
     */
    private String remark3;

    /**
     * 删除标志 0存在 1删除
     */
    @TableLogic
    private String delFlag;


}
