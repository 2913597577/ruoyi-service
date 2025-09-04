package org.dromara.customertrace.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 客户跟进记录对象 dc_customer_follow
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_customer_follow")
public class DcCustomerFollow extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 客户ID（dc_customer.id）
     */
    private Long customerId;

    /**
     * 跟进类型（PHONE电话/VISIT拜访/EMAIL邮件）
     */
    private String followType;

    /**
     * 跟进内容
     */
    private String followContent;

    /**
     * 下次跟进时间
     */
    private Date nextFollowTime;

    /**
     * 跟进人（sys_user.user_id）
     */
    private Long followBy;


}
