package org.dromara.lawperson.domain.vo;

import org.dromara.lawperson.domain.DcLegalSupport;
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
 * 法务支持人员视图对象 dc_legal_support
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcLegalSupport.class)
public class DcLegalSupportVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 用户ID（sys_user.user_id）
     */
    @ExcelProperty(value = "用户ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "s=ys_user.user_id")
    private Long userId;

    /**
     * 接单优先级（数字越小优先级越高）
     */
    @ExcelProperty(value = "接单优先级", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "数=字越小优先级越高")
    private Long supportLevel;

    /**
     * 最大同时处理案件数
     */
    @ExcelProperty(value = "最大同时处理案件数")
    private Long maxCaseCount;

    /**
     * 当前处理案件数
     */
    @ExcelProperty(value = "当前处理案件数")
    private Long currentCaseCount;

    /**
     * 状态（0可用 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=可用,1=停用")
    private String status;


}
