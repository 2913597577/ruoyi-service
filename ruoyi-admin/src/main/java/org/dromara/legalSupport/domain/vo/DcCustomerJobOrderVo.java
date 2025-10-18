package org.dromara.legalSupport.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.legalSupport.domain.DcCustomerJobOrder;
import org.dromara.legalSupport.domain.bo.DcCustomerJobOrderBo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 工单管理视图对象 dc_customer_job_order
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Data
@ExcelIgnoreUnannotated
@AutoMappers({
    @AutoMapper(target = DcCustomerJobOrder.class),
    @AutoMapper(target = DcCustomerJobOrderBo.class)
})
public class DcCustomerJobOrderVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 法务支持
     */
    @ExcelProperty(value = "法务支持")
    private String legalSupport;

    /**
     * 法务支持id
     */
    @ExcelProperty(value = "法务支持id")
    private Long legalSupportId;

    /**
     * 源合同地址
     */
    @ExcelProperty(value = "源合同地址")
    private Long preContractAddress;

    /**
     * 源合同文件名
     */
    @ExcelProperty(value = "源合同文件名")
    private String preContractName;

    /**
     * 新合同地址
     */
    @ExcelProperty(value = "新合同地址")
    private Long newContractAddress;

    /**
     * 新合同文件名
     */
    @ExcelProperty(value = "新合同文件名")
    private String newContractName;

    /**
     * 客户要求
     */
    @ExcelProperty(value = "客户要求")
    private String customerRequirements;

    /**
     * 交付时间
     */
    @ExcelProperty(value = "交付时间")
    private Date deliveryTime;

    /**
     * 跟踪记录id
     */
    @ExcelProperty(value = "跟踪记录id")
    private Long trackingId;

    /**
     * 处理人id
     */
    @ExcelProperty(value = "处理人id")
    private Long contractHandler;

    /**
     * 处理人
     */
    @ExcelProperty(value = "处理人")
    private String contractHandlerName;

    /**
     * 工单处理状态
     */
    @ExcelProperty(value = "工单处理状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "processing_status")
    private Integer processingStatus;

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

    private Long customerId;

    /**
     * 客户姓名
     */
    @ExcelProperty(value = "客户姓名")
    private String customerName;

}
