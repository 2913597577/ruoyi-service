package org.dromara.customer.domain.bo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.customer.domain.DcCustomerIntentionTracking;

import java.util.Date;

/**
 * 意向客户跟踪记录业务对象 dc_customer_intention_tracking
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomerIntentionTracking.class, reverseConvertGenerate = false)
public class DcCustomerIntentionTrackingBo extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 意向客户表id
     */
    @NotNull(message = "意向客户表id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long intentionId;

    /**
     * 介绍客户id
     */
    private Long customerId;

    /**
     * 介绍客户名称
     */
    private String customerName;

    /**
     * 备注
     */
    private String customerRemark;

    /**
     * 跟踪时间
     */
    private Date trackingDate;

    /**
     * 下次跟踪时间
     */
    private Date nextTrackingDate;

    /**
     * 备注3
     */
    private String remark3;

    /**
     * 删除标志 0存在 1删除
     */
    @TableLogic
    private String delFlag;

    /**
     * 介绍客户名称
     */
    private String intentionName;

    /**
     * 意向客户所属城市
     */
    private String remark1;

    /**
     * 备注2
     */
    private String remark2;

    /**
     * 法务支持id
     */
    private Long legalSupportId;

    /**
     * 法务支持名称
     */
    private String legalSupportName;


}
