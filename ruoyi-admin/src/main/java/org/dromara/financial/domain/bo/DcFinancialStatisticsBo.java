package org.dromara.financial.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.financial.domain.DcFinancialStatistics;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 财务统计业务对象 dc_financial_statistics
 *
 * @author Lion Li
 * @date 2025-12-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcFinancialStatistics.class, reverseConvertGenerate = false)
public class DcFinancialStatisticsBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal balance;

    /**
     * 财务类型
     */
    @NotNull(message = "财务类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long financialType;

    /**
     * 来源类型
     */
    @NotBlank(message = "来源类型不能为空", groups = {AddGroup.class, EditGroup.class})
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
    @NotNull(message = "流水时间（财务进账/支出具体时间）不能为空", groups = {AddGroup.class, EditGroup.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date flowTime;

    /**
     * 项目名称
     */
    private String companyName;


    /**
     * 录入人id
     */
    private Long operatorId;


}
