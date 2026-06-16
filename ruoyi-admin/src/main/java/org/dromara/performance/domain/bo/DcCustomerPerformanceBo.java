package org.dromara.performance.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.performance.domain.DcCustomerPerformance;

import java.math.BigDecimal;

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
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 流转单id
     */
    @NotNull(message = "流转单id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long transferId;

    /**
     * 业绩所属用户id
     */
    @NotNull(message = "业绩所属用户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long userId;

    /**
     * 业绩所属用户名字
     */
    @NotNull(message = "业绩所属用户名字不能为空", groups = {AddGroup.class, EditGroup.class})
    private String userName;

    /**
     * 业绩所属金额
     */
    @NotNull(message = "业绩所属金额不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal balance;

    /**
     * 业绩所属城市
     */
    @NotBlank(message = "业绩所属城市不能为空", groups = {AddGroup.class, EditGroup.class})
    private String city;

    private Long createBy;

    private Long createrId;

    private String createrName;

    private Integer transferServiceType;

    private Integer secondServiceType;

    private String remark1;

    private String remark2;

    private String remark3;

    /**
     * 更新月份(格式: yyyy-MM)
     */
    private String updateTimeMonth;


}
