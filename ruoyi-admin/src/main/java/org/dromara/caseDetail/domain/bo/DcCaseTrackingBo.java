package org.dromara.caseDetail.domain.bo;

import org.dromara.caseDetail.domain.DcCaseTracking;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 案件进展表业务对象 dc_case_tracking
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCaseTracking.class, reverseConvertGenerate = false)
public class DcCaseTrackingBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 案件id
     */
    @NotNull(message = "案件id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long caseId;

    /**
     * 案件类型
     */
    @NotBlank(message = "案件类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String caseType;

    /**
     * 客户id
     */
    @NotNull(message = "客户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long customerId;

    /**
     * 客户姓名
     */
    @NotNull(message = "客户姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long customerName;

    /**
     * 案件进展
     */
    private String caseProgress;


}
