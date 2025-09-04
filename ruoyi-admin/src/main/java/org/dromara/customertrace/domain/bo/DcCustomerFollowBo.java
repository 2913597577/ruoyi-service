package org.dromara.customertrace.domain.bo;

import org.dromara.customertrace.domain.DcCustomerFollow;
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
 * 客户跟进记录业务对象 dc_customer_follow
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomerFollow.class, reverseConvertGenerate = false)
public class DcCustomerFollowBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 客户ID（dc_customer.id）
     */
    @NotNull(message = "客户ID（dc_customer.id）不能为空", groups = { AddGroup.class, EditGroup.class })
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
