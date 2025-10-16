package org.dromara.caseDetail.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.caseDetail.domain.DcCaseTracking;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 案件进展表视图对象 dc_case_tracking
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCaseTracking.class)
public class DcCaseTrackingVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 案件id
     */
    @ExcelProperty(value = "案件id")
    private Long caseId;

    /**
     * 案件类型
     */
    @ExcelProperty(value = "案件类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "customer_case_type")
    private String caseType;

    /**
     * 客户id
     */
    @ExcelProperty(value = "客户id")
    private Long customerId;

    /**
     * 客户姓名
     */
    @ExcelProperty(value = "客户姓名")
    private Long customerName;

    /**
     * 案件进展
     */
    @ExcelProperty(value = "案件进展")
    private String caseProgress;

    /**
     * 跟进时间
     */
    @ExcelProperty(value = "跟进时间")
    private Date trackingTime;
    /**
     * 下次跟进时间
     */
    @ExcelProperty(value = "下次跟进时间")
    private Date nextTrackingTime;

}
