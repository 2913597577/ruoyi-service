package org.dromara.financial.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.financial.domain.DcFinancialStatistics;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 财务统计视图对象 dc_financial_statistics
 *
 * @author Lion Li
 * @date 2025-12-26
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcFinancialStatistics.class)
public class DcFinancialStatisticsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 金额
     */
    @ExcelProperty(value = "金额")
    private BigDecimal balance;

    /**
     * 财务类型
     */
    @ExcelProperty(value = "财务类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "dc_financial_type")
    private Long financialType;

    /**
     * 来源类型
     */
    @ExcelProperty(value = "来源类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "dc_financial_source_type")
    private String sourceType;

    /**
     * 发票凭证
     */
    @ExcelProperty(value = "发票凭证")
    private String contractNo;

    /**
     * 发票凭证Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "contractNo")
    private String contractNoUrl;
    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 业绩归属城市
     */
    @ExcelProperty(value = "业绩归属城市", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "dc_sercive_city")
    private String city;

    /**
     * 创建人姓名
     */
    @ExcelProperty(value = "创建人姓名")
    private String createrName;

    /**
     * 备注1
     */
    @ExcelProperty(value = "备注1")
    private String remark1;

    /**
     * 备注2
     */
    @ExcelProperty(value = "备注2")
    private String remark2;

    /**
     * 备注3
     */
    @ExcelProperty(value = "备注3")
    private String remark3;

    /**
     * 流水时间（财务进账/支出具体时间）
     */
    @ExcelProperty(value = "流水时间", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "财务进账/支出具体时间")
    private Date flowTime;


}
