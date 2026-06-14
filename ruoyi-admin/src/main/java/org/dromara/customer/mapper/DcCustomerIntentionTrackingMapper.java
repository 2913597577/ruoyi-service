package org.dromara.customer.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.customer.domain.DcCustomerIntentionTracking;
import org.dromara.customer.domain.vo.DcCustomerIntentionTrackingVo;

import java.util.List;
import java.util.Map;

/**
 * 意向客户跟踪记录Mapper接口
 *
 * @author Lion Li
 * @date 2025-09-21
 */
public interface DcCustomerIntentionTrackingMapper extends BaseMapperPlus<DcCustomerIntentionTracking, DcCustomerIntentionTrackingVo> {

    List<Map<String, Object>> selectTodayFollowUpByLegalSupport(@Param("legalSupportId") Long legalSupportId, @Param("city") String city);

}
