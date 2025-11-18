package org.dromara.workflow.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.time.LocalDate;

/**
 * 高风险客户记录对象 dc_high_risk_customer
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_high_risk_customer")
public class DcHighRiskCustomer extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 证据情况
     */
    private String evidenceText;

    /**
     * 风险发现日期
     */
    private LocalDate riskDiscoveryDate;

    /**
     * 是否提及退费（1:是，0:否）
     */
    private Integer isRefundMentioned;

    /**
     * 风险判定（可多选）
     */
    private String riskDetermination;

    /**
     * 合规问题（可多选）
     */
    private String complianceIssues;

    /**
     * 原因
     */
    private String remark;

    /**
     * 状态
     */
    private String status;
}
