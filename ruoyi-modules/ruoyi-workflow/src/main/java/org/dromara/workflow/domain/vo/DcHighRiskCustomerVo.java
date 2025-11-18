package org.dromara.workflow.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.workflow.domain.DcHighRiskCustomer;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * 高风险客户记录视图对象 dc_high_risk_customer
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcHighRiskCustomer.class)
public class DcHighRiskCustomerVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键ID
     */
    @ExcelProperty(value = "自增主键ID")
    private Long id;

    /**
     * 客户id
     */
    @ExcelProperty(value = "客户id")
    private Long customerId;

    /**
     * 客户姓名
     */
    @ExcelProperty(value = "客户姓名")
    private String customerName;

    /**
     * 证据情况
     */
    @ExcelProperty(value = "证据情况")
    private String evidenceText;

    /**
     * 风险发现日期
     */
    @ExcelProperty(value = "风险发现日期")
    private LocalDate riskDiscoveryDate;

    /**
     * 是否提及退费（1:是，0:否）
     */
    @ExcelProperty(value = "是否提及退费")
    private Integer isRefundMentioned;

    /**
     * 风险判定（可多选）
     */
    @ExcelProperty(value = "风险判定")
    private String riskDetermination;

    /**
     * 合规问题（可多选）
     */
    @ExcelProperty(value = "合规问题")
    private String complianceIssues;

    /**
     * 原因
     */
    @ExcelProperty(value = "原因")
    private String remark;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
