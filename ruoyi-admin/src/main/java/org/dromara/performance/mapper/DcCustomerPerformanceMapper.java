package org.dromara.performance.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.performance.domain.DcCustomerPerformance;
import org.dromara.performance.domain.vo.DcCustomerPerformanceVo;

import java.util.Date;
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

    /**
     * 分页查询客户业绩列表
     */
    List<Map<String, Object>> selectListByPage(@Param("userId") List<Long> userId,
                                               @Param("transferId") List<Long> transferId,
                                               @Param("city") List<String> city,
                                               @Param("serviceCity") List<String> serviceCity,
                                               @Param("inviterId") List<Long> inviterId,
                                               @Param("serviceType") List<Integer> serviceType,
                                               @Param("secondDevelopmentType") List<Integer> secondDevelopmentType,
                                               @Param("serviceStart") Date serviceStart,
                                               @Param("serviceEnd") Date serviceEnd,
                                               @Param("companyName") List<String> companyName,
                                               @Param("page") Integer page,
                                               @Param("pageSize") Integer pageSize);

    /**
     * 统计客户业绩数量
     */
    int selectListByPageCount(@Param("userId") List<Long> userId,
                              @Param("transferId") List<Long> transferId,
                              @Param("city") List<String> city,
                              @Param("serviceCity") List<String> serviceCity,
                              @Param("inviterId") List<Long> inviterId,
                              @Param("serviceType") List<Integer> serviceType,
                              @Param("secondDevelopmentType") List<Integer> secondDevelopmentType,
                              @Param("serviceStart") Date serviceStart,
                              @Param("serviceEnd") Date serviceEnd,
                              @Param("companyName") List<String> companyName);


}
