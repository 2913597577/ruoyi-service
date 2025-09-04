package org.dromara.customertrace.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.customertrace.domain.DcCustomerFollow;
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
 * 客户跟进记录视图对象 dc_customer_follow
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCustomerFollow.class)
public class DcCustomerFollowVo implements Serializable {

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
     * 跟进类型（PHONE电话/VISIT拜访/EMAIL邮件）
     */
    @ExcelProperty(value = "跟进类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "P=HONE电话/VISIT拜访/EMAIL邮件")
    private String followType;

    /**
     * 跟进内容
     */
    @ExcelProperty(value = "跟进内容")
    private String followContent;

    /**
     * 下次跟进时间
     */
    @ExcelProperty(value = "下次跟进时间")
    private Date nextFollowTime;

    /**
     * 跟进人（sys_user.user_id）
     */
    @ExcelProperty(value = "跟进人", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "s=ys_user.user_id")
    private Long followBy;


}
