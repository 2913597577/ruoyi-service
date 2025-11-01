package org.dromara.workflow.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.math.BigDecimal;

/**
 * 审批对象 test_approve
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("test_approve")
public class TestApprove extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 审批类型
     */
    private String applyType;

    /**
     * 金额
     */
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
