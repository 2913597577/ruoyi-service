package org.dromara.financial.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.financial.domain.DcFinancialStatistics;
import org.dromara.financial.domain.FinancialStatisticsQuery;
import org.dromara.financial.domain.vo.DcFinancialStatisticsVo;

import java.util.List;
import java.util.Map;

/**
 * 财务统计Mapper接口
 *
 * @author Lion Li
 * @date 2025-12-26
 */
public interface DcFinancialStatisticsMapper extends BaseMapperPlus<DcFinancialStatistics, DcFinancialStatisticsVo> {

    /**
     * 按城市统计收支情况
     */
    List<Map<String, Object>> selectCityStatistics(FinancialStatisticsQuery query);

    /**
     * 按来源类型统计
     */
    List<Map<String, Object>> selectSourceTypeStatistics(FinancialStatisticsQuery query);

    /**
     * 按财务类型统计
     */
    List<Map<String, Object>> selectFinancialTypeStatistics(FinancialStatisticsQuery query);
}
