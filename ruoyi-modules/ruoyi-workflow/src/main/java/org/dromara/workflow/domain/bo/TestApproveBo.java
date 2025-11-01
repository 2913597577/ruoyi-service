package org.dromara.workflow.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.workflow.domain.TestApprove;

import java.math.BigDecimal;

/**
 * 审批申请业务对象 test_approve
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TestApprove.class, reverseConvertGenerate = false)
public class TestApproveBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 审批类型
     */
    @NotBlank(message = "审批类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private String applyType;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal balance;

    /**
     * 申请原因
     */
    private String remark;

    /**
     * 状态
     */
    private String status;

}
