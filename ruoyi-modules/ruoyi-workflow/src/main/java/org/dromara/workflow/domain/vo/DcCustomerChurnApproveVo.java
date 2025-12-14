package org.dromara.workflow.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.workflow.domain.DcCustomerChurnApprove;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 客户流失审批视图对象 dc_customer_churn_approve
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCustomerChurnApprove.class)
public class DcCustomerChurnApproveVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 租户编号
     */
    private String tenantId;

    /**
     * 审批类型
     */
    @ExcelProperty(value = "审批类型")
    private String applyType;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 客户姓名
     */
    @ExcelProperty(value = "客户姓名")
    private String customerName;

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
