package org.dromara.customertotal.domain.bo;

import org.dromara.customertotal.domain.DcCustomer;
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
 * 全部客户业务对象 dc_customer
 *
 * @author chang
 * @date 2025-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomer.class, reverseConvertGenerate = false)
public class DcCustomerBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 意向客户ID（dc_intention_customer.id）
     */
    @NotNull(message = "意向客户ID（dc_intention_customer.id）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long intentionId;

    /**
     * 客户类型（EXCELLENT优秀/RISK风险/INACTIVE未维护）
     */
    private String customerType;

    /**
     * 最后跟进时间
     */
    private Date lastFollowTime;

    /**
     * 法务状态（PENDING待分配/PROCESSING处理中/COMPLETED已完成）
     */
    private String legalStatus;

    /**
     * 合同存储路径（sys_oss.url）
     */
    private String contractUrl;

    /**
     * 归档时间
     */
    private Date archiveTime;

    /**
     * 归档人（sys_user.user_id）
     */
    private Long archiveBy;

    /**
     * 归档/审批意见
     */
    private String archiveRemark;

    /**
     * 归档结果
     */
    private String archiveResult;

    /**
     * 归档人岗位ID
     */
    private Long archivePost;

    /**
     * 归档人角色ID
     */
    private Long archiveRole;

    /**
     * 归档来源
     */
    private String archiveSource;


}
