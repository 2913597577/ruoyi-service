package org.dromara.performance.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.performance.domain.DcCustomerPerformance;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


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

    @ExcelProperty(value = "签约类型(1-常法 2-单项 3-律师费 4-其他)")
    private Integer transferServiceType;

    @ExcelProperty(value = "二开类型(0-续费、1-尾款、2-咨询费、3-升级常法、4-律师函、5-合同定审、6-立案、7-诉讼文书、8-法务其他、9-财税代账、10-财税其他)")
    private Integer secondServiceType;

    @ExcelProperty(value = "备注1")
    private String remark1;

    @ExcelProperty(value = "备注2")
    private String remark2;

    @ExcelProperty(value = "备注3")
    private String remark3;

    /**
     * 签单日期
     */
    @ExcelProperty(value = "签单日期")
    private Date signDate;

}
