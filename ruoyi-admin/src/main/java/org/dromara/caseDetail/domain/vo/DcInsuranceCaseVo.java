package org.dromara.caseDetail.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.caseDetail.domain.DcInsuranceCase;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 保险记录表视图对象 dc_insurance_case
 *
 * @author Lion Li
 * @date 2025-09-29
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcInsuranceCase.class)
public class DcInsuranceCaseVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @ExcelProperty(value = "自增主键")
    private Long id;

    /**
     * 客户id(客户编号)
     */
    @ExcelProperty(value = "客户id(客户编号)")
    private Long customerId;

    /**
     * 下单日期
     */
    @ExcelProperty(value = "下单日期")
    private Date orderDate;

    /**
     * 工单号
     */
    @ExcelProperty(value = "工单号")
    private String insuranceNumber;

    /**
     * 法务支持id
     */
    @ExcelProperty(value = "法务支持id")
    private Long legalSupportId;

    /**
     * 法务支持姓名
     */
    @ExcelProperty(value = "法务支持姓名")
    private String legalSupportName;

    /**
     * 原告方
     */
    @ExcelProperty(value = "原告方")
    private String plaintiff;

    /**
     * 被告方
     */
    @ExcelProperty(value = "被告方")
    private String defendant;

    /**
     * 标的额
     */
    @ExcelProperty(value = "标的额")
    private Long subjectAmount;

    /**
     * 案由
     */
    @ExcelProperty(value = "案由")
    private String caseReason;

    /**
     * 管辖权法院
     */
    @ExcelProperty(value = "管辖权法院")
    private String jurisdictionCourt;

    /**
     * 保费
     */
    @ExcelProperty(value = "保费")
    private Long premium;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
