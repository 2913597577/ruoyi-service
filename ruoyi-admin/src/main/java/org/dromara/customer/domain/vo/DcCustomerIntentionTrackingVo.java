package org.dromara.customer.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.customer.domain.DcCustomerIntentionTracking;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 意向客户跟踪记录视图对象 dc_customer_intention_tracking
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCustomerIntentionTracking.class)
public class DcCustomerIntentionTrackingVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 意向客户表id
     */
    @ExcelProperty(value = "意向客户id")
    private Long intentionId;

    /**
     * 介绍客户id
     */
    private Long customerId;

    /**
     * 介绍客户名称
     */
    @ExcelProperty(value = "介绍客户名称")
    private String customerName;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String customerRemark;

    /**
     * 跟踪时间
     */
    @ExcelProperty(value = "跟踪时间")
    private Date trackingDate;

    /**
     * 下次跟踪时间
     */
    @ExcelProperty(value = "下次跟踪时间")
    private Date nextTrackingDate;

    /**
     * 备注3
     */
    @ExcelProperty(value = "备注3")
    private String remark3;

    /**
     * 删除标志 0存在 1删除
     */
    @ExcelProperty(value = "删除标志")
    private String delFlag;

    /**
     * 介绍客户名称
     */
    @ExcelProperty(value = "意向客户名称")
    private String intentionName;

    /**
     * 意向客户所属城市
     */
    @ExcelProperty(value = "意向客户所属城市")
    private String remark1;

    /**
     * 备注2
     */
    @ExcelProperty(value = "备注2")
    private String remark2;

    /**
     * 法务支持id
     */
    @ExcelProperty(value = "法务支持id")
    private Long legalSupportId;

    /**
     * 法务支持名称
     */
    @ExcelProperty(value = "法务支持名称")
    private String legalSupportName;

}
