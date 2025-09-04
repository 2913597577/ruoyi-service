package org.dromara.customertotal.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 全部客户对象 dc_customer
 *
 * @author chang
 * @date 2025-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_customer")
public class DcCustomer extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 意向客户ID（dc_intention_customer.id）
     */
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
     * 删除标志（0存在 1删除）
     */
    @TableLogic
    private String delFlag;

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
