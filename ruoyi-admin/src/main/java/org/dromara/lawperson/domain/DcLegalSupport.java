package org.dromara.lawperson.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 法务支持人员对象 dc_legal_support
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_legal_support")
public class DcLegalSupport extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户ID（sys_user.user_id）
     */
    private Long userId;

    /**
     * 接单优先级（数字越小优先级越高）
     */
    private Long supportLevel;

    /**
     * 最大同时处理案件数
     */
    private Long maxCaseCount;

    /**
     * 当前处理案件数
     */
    private Long currentCaseCount;

    /**
     * 状态（0可用 1停用）
     */
    private String status;

    /**
     * 删除标志（0存在 1删除）
     */
    @TableLogic
    private String delFlag;


}
