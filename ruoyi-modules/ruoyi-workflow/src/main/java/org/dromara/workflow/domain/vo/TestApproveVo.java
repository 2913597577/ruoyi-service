package org.dromara.workflow.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.workflow.domain.TestApprove;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 审批视图对象 test_approve
 *
 * @author may
 * @date 2025-11-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TestApprove.class)
public class TestApproveVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 审批类型
     */
    @ExcelProperty(value = "审批类型")
    private String applyType;

    /**
     * 金额
     */
    @ExcelProperty(value = "金额")
    private BigDecimal balance;

    /**
     * 申请原因
     */
    @ExcelProperty(value = "申请原因")
    private String remark;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 申请时间
     */
    @ExcelProperty(value = "申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
