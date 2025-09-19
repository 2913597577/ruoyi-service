package org.dromara.customer.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.customer.domain.DcCustomerRiskRefund;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户风险/退费业务对象 dc_customer_risk_refund
 *
 * @author Lion Li
 * @date 2025-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomerRiskRefund.class, reverseConvertGenerate = false)
public class DcCustomerRiskRefundBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 法务支持（律师id）
     */
    private Long lawyerId;

    /**
     * 客户id
     */
    @NotNull(message = "客户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long customerId;

    /**
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String customerName;

    /**
     * 客户对接人
     */
    @NotBlank(message = "客户对接人不能为空", groups = {AddGroup.class, EditGroup.class})
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
    @NotNull(message = "签约日期不能为空", groups = {AddGroup.class, EditGroup.class})
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


}
