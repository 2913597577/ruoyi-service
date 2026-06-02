package org.dromara.myCustomer.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.myCustomer.domain.DcCustomerTracking;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 客户跟踪业务对象 dc_customer_tracking
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomerTracking.class, reverseConvertGenerate = false)
public class DcCustomerTrackingBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 客户id
     */
    @NotNull(message = "客户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long customerId;

    /**
     * 跟踪记录
     */
    private String customerRemark;

    /**
     * 跟踪类型
     */
    private Integer trackingType;

    /**
     * 跟踪状态
     */
    private Integer cumtomerStatus;

    /**
     * 跟踪时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date trackingTime;

    /**
     * 提交状态
     */
    private Integer submitStatus;

    /**
     * 下次跟踪时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nextTime;

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
     * 是否是回访记录
     */
    private Integer isReturn;

    /**
     * 内勤计数
     */
    @NotNull(message = "内勤项数计数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer interCount;

    /**
     * 法务支持id
     */
    @NotNull(message = "法务支持id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long legalSupportId;

    /**
     * 法务支持姓名
     */
    private String legalSupportName;

    private List<Long> customerIds;

    /**
     * 跟踪月份(格式: yyyy-MM)
     */
    private String trackingMonth;

}
