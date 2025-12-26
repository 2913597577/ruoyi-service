package org.dromara.financial.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.translation.annotation.Translation;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.common.translation.constant.TransConstant;

import java.io.Serial;

/**
 * 财务统计对象 dc_financial_statistics
 *
 * @author Lion Li
 * @date 2025-12-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_financial_statistics")
public class DcFinancialStatistics extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 金额
     */
    private Long balance;

    /**
     * 财务类型
     */
    private Long financialType;

    /**
     * 来源类型
     */
    private String sourceType;

    /**
     * 发票凭证
     */
    private String contractNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 业绩归属城市
     */
    private String city;

    /**
     * 创建人姓名
     */
    private String createrName;

    /**
     * 备注1
     */
    private String remark1;

    /**
     * 备注2
     */
    private String remark2;

    /**
     * 备注3
     */
    private String remark3;

    /**
     * 流水时间（财务进账/支出具体时间）
     */
    private Date flowTime;


}
