package org.dromara.performance.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.performance.domain.DcCustomerPerformance;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 业绩归属登记视图对象 dc_customer_performance
 *
 * @author Lion Li
 * @date 2025-10-21
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCustomerPerformance.class)
public class DcCustomerPerformanceVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 流转单id
     */
    @ExcelProperty(value = "流转单id")
    private Long transferId;

    /**
     * 业绩所属用户id
     */
    @ExcelProperty(value = "业绩所属用户id")
    private Long userId;

    /**
     * 业绩所属用户名字
     */
    @ExcelProperty(value = "业绩所属用户名字")
    private String userName;

    /**
     * 业绩所属金额
     */
    @ExcelProperty(value = "业绩所属金额")
    private BigDecimal balance;

    /**
     * 业绩所属城市
     */
    @ExcelProperty(value = "业绩所属城市")
    private String city;

    private Long createBy;

    private Long createrId;

    @ExcelProperty(value = "分配人")
    private String createrName;


}
