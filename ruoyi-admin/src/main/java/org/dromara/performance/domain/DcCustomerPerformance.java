package org.dromara.performance.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 业绩归属登记对象 dc_customer_performance
 *
 * @author Lion Li
 * @date 2025-10-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_customer_performance")
public class DcCustomerPerformance extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 流转单id
     */
    private Long transferId;

    /**
     * 业绩所属用户id
     */
    private Long userId;

    /**
     * 业绩所属用户名字
     */
    private String userName;

    /**
     * 业绩所属金额
     */
    private BigDecimal balance;

    /**
     * 业绩所属城市
     */
    private String city;

    /**
     * 删除标志 0存在 1删除
     */
    @TableLogic
    private String delFlag;

    private Long createBy;

    private Long createrId;

    private String createrName;

    /**
     * 签约类型(1-常法 2-单项 3-律师费 4-其他)
     */
    private Integer transferServiceType;

    /**
     * 二开类型(0-续费、1-尾款、2-咨询费、3-升级常法、4-律师函、5-合同定审、6-立案、7-诉讼文书、8-法务其他、9-财税代账、10-财税其他)
     */
    private Integer secondServiceType;

    private String remark1;

    private String remark2;

    private String remark3;

    /**
     * 签单日期
     */
    private Date signDate;

}
