package org.dromara.myCustomer.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.myCustomer.domain.DcCustomerTracking;
import org.dromara.myCustomer.domain.vo.DcCustomerTrackingVo;

import java.util.List;
import java.util.Map;

/**
 * 客户跟踪Mapper接口
 *
 * @author Lion Li
 * @date 2025-09-21
 */
public interface DcCustomerTrackingMapper extends BaseMapperPlus<DcCustomerTracking, DcCustomerTrackingVo> {

    List<Map<String, Object>> selectByYearMonth(Integer year, Integer month, Integer day, Integer trackingType,
                                                Integer customerStatus, Long userId, Integer isReturn, boolean isGroup);

    List<Map<String, Object>> selectMonthlyTrackingByLegalSupport(@Param("legalSupportId") Long legalSupportId, @Param("city") String city);

    List<Map<String, Object>> selectTodayFollowUpByLegalSupport(@Param("legalSupportId") Long legalSupportId, @Param("city") String city);

}
