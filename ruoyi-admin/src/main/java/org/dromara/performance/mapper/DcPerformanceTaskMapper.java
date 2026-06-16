package org.dromara.performance.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.performance.domain.DcPerformanceTask;
import org.dromara.performance.domain.vo.DcPerformanceTaskVo;

import java.util.List;
import java.util.Map;

/**
 * 业绩任务Mapper接口
 *
 * @author Lion Li
 * @date 2025-10-26
 */
public interface DcPerformanceTaskMapper extends BaseMapperPlus<DcPerformanceTask, DcPerformanceTaskVo> {

    List<Map<String, Object>> selectPerformanceTaskByLegalSupportAndMonth(
        @Param("legalSupportId") Long legalSupportId,
        @Param("taskMonth") String taskMonth,
        @Param("city") String city
    );

    List<Map<String, Object>> selectPerformanceTaskByLegalSupportAndYear(
        @Param("legalSupportId") Long legalSupportId,
        @Param("taskYear") Integer taskYear
    );

}
