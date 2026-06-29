package org.dromara.performance.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.performance.domain.DcPerformanceTask;
import org.dromara.performance.domain.DcSalescenterPerformanceTask;
import org.dromara.performance.domain.vo.DcPerformanceTaskVo;
import org.dromara.performance.domain.vo.DcSalescenterPerformanceTaskVo;

import java.util.List;
import java.util.Map;

/**
 * 业绩任务Mapper接口
 *
 * @author Lion Li
 * @date 2025-10-26
 */
public interface DcSalescenterPerformanceTaskMapper extends BaseMapperPlus<DcSalescenterPerformanceTask, DcSalescenterPerformanceTaskVo> {

    List<Map<String, Object>> selectPerformanceTaskBySalesCenterAndMonth(
        @Param("salesCenterId") Long salesCenterId,
        @Param("taskMonth") String taskMonth,
        @Param("city") String city
    );

    List<Map<String, Object>> selectPerformanceTaskBySalesCenterAndYear(
        @Param("salesCenterId") Long salesCenterId,
        @Param("taskYear") Integer taskYear,
        @Param("city") String city
    );

}
