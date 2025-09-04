package org.dromara.lawperson.domain.bo;

import org.dromara.lawperson.domain.DcLegalSupport;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 法务支持人员业务对象 dc_legal_support
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcLegalSupport.class, reverseConvertGenerate = false)
public class DcLegalSupportBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 用户ID（sys_user.user_id）
     */
    @NotNull(message = "用户ID（sys_user.user_id）不能为空", groups = { AddGroup.class, EditGroup.class })
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


}
