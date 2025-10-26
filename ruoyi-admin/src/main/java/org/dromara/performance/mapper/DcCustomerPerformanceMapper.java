package org.dromara.performance.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.performance.domain.DcCustomerPerformance;
import org.dromara.performance.domain.vo.DcCustomerPerformanceVo;

import java.util.List;
import java.util.Map;

/**
 * 业绩归属登记Mapper接口
 *
 * @author Lion Li
 * @date 2025-10-21
 */
public interface DcCustomerPerformanceMapper extends BaseMapperPlus<DcCustomerPerformance, DcCustomerPerformanceVo> {

    List<Map<String, Object>> selectUserPerformanceRank(@Param("year") Integer year, @Param("month") Integer month,
                                                        @Param("userId") Long userId);

}
