package org.dromara.legalSupport.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.legalSupport.domain.DcLegalSupportChangeRecord;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 法务支持变更视图对象 dc_legal_support_change_record
 *
 * @author Lion Li
 * @date 2025-11-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcLegalSupportChangeRecord.class)
public class DcLegalSupportChangeRecordVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 客户名称
     */
    @ExcelProperty(value = "客户名称")
    private String customerName;

    /**
     * 客户id（流转单id）
     */
    @ExcelProperty(value = "客户id", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "流=转单id")
    private Long customerId;

    /**
     * 法务支持
     */
    @ExcelProperty(value = "法务支持")
    private String legalSupportName;

    /**
     * 法务支持ID
     */
    @ExcelProperty(value = "法务支持ID")
    private Long legalSupportId;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    private Long createBy;

    @ExcelProperty(value = "创建人")
    private String remark1;


}
