package org.dromara.riskrefund.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.riskrefund.domain.DcRiskRefund;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 风险退费客户视图对象 dc_risk_refund
 *
 * @author Lion Li
 * @date 2025-07-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcRiskRefund.class)
public class DcRiskRefundVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 客户ID（dc_customer.id）
     */
    @ExcelProperty(value = "客户ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "d=c_customer.id")
    private Long customerId;

    /**
     * 退费金额
     */
    @ExcelProperty(value = "退费金额")
    private Long refundAmount;

    /**
     * 退费原因
     */
    @ExcelProperty(value = "退费原因")
    private String refundReason;

    /**
     * 退费时间
     */
    @ExcelProperty(value = "退费时间")
    private Date refundTime;

    /**
     * 审批人（sys_user.user_id）
     */
    @ExcelProperty(value = "审批人", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "s=ys_user.user_id")
    private Long approveBy;


}
