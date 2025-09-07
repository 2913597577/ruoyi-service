package org.dromara.customer.domain.bo;

import org.dromara.customer.domain.DcCustomerInformation;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户总表业务对象 dc_customer_information
 *
 * @author Lion Li
 * @date 2025-09-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomerInformation.class, reverseConvertGenerate = false)
public class DcCustomerInformationBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 签约日期
     */
    @NotNull(message = "签约日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date signDate;

    /**
     * 编号
     */
    @NotBlank(message = "编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contractNo;

    /**
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerName;

    /**
     * 负责人
     */
    @NotBlank(message = "负责人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String principal;

    /**
     * 负责人电话
     */
    private String principalPhone;

    /**
     * 法务法务支持（律师id）
     */
    private Long lawyerId;

    /**
     * 甩单人
     */
    private String transferPerson;

    /**
     * 杀单手
     */
    private String closer;

    /**
     * 签约类型
     */
    @NotNull(message = "签约类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer contractType;

    /**
     * 套餐类型
     */
    private Integer packageType;

    /**
     * 实收金额
     */
    private BigDecimal actualReceipt;

    /**
     * 尾款金额
     */
    private BigDecimal balance;

    /**
     * 到期时间
     */
    private Date expireDate;

    /**
     * 合同编号
     */
    private String contractCode;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 续费/尾款动作(1-续费 2-付尾款 3-其他)
     */
    private Integer actionType;

    /**
     * 客户id（流转单id）
     */
    private Long transferId;


}
