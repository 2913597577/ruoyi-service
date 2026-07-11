package org.dromara.financial.domain;

import lombok.Data;

import java.util.Map;

@Data
public class FinancialStatisticsQuery {
    /**
     * 财务类型
     */
    private String financialType;

    /**
     * 来源类型
     */
    private String sourceType;

    /**
     * 归属城市
     */
    private String city;

    /**
     * 录入人姓名
     */
    private String createrName;

    /**
     * 流水时间 - 开始时间
     */
    private String startTime;

    /**
     * 流水时间 - 结束时间
     */
    private String endTime;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页大小
     */
    private Integer pageSize = 10;

    /**
     * 额外参数
     */
    private Map<String, Object> params;

    /**
     * 项目名称
     */
    private String companyName;


    /**
     * 录入人id
     */
    private Long operatorId;

}
