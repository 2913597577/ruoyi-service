package org.dromara.customertotal.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.customertotal.domain.DcCustomer;
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
 * 全部客户视图对象 dc_customer
 *
 * @author chang
 * @date 2025-07-08
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCustomer.class)
public class DcCustomerVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 意向客户ID（dc_intention_customer.id）
     */
    @ExcelProperty(value = "意向客户ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "d=c_intention_customer.id")
    private Long intentionId;

    /**
     * 客户类型（EXCELLENT优秀/RISK风险/INACTIVE未维护）
     */
    @ExcelProperty(value = "客户类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "E=XCELLENT优秀/RISK风险/INACTIVE未维护")
    private String customerType;

    /**
     * 最后跟进时间
     */
    @ExcelProperty(value = "最后跟进时间")
    private Date lastFollowTime;

    /**
     * 法务状态（PENDING待分配/PROCESSING处理中/COMPLETED已完成）
     */
    @ExcelProperty(value = "法务状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "P=ENDING待分配/PROCESSING处理中/COMPLETED已完成")
    private String legalStatus;

    /**
     * 合同存储路径（sys_oss.url）
     */
    @ExcelProperty(value = "合同存储路径", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "s=ys_oss.url")
    private String contractUrl;

    /**
     * 归档时间
     */
    @ExcelProperty(value = "归档时间")
    private Date archiveTime;

    /**
     * 归档人（sys_user.user_id）
     */
    @ExcelProperty(value = "归档人", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "s=ys_user.user_id")
    private Long archiveBy;

    /**
     * 归档/审批意见
     */
    @ExcelProperty(value = "归档/审批意见")
    private String archiveRemark;

    /**
     * 归档结果
     */
    @ExcelProperty(value = "归档结果")
    private String archiveResult;

    /**
     * 归档人岗位ID
     */
    @ExcelProperty(value = "归档人岗位ID")
    private Long archivePost;

    /**
     * 归档人角色ID
     */
    @ExcelProperty(value = "归档人角色ID")
    private Long archiveRole;

    /**
     * 归档来源
     */
    @ExcelProperty(value = "归档来源")
    private String archiveSource;


}
