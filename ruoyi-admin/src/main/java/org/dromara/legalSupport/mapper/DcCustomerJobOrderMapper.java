package org.dromara.legalSupport.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.legalSupport.domain.DcCustomerJobOrder;
import org.dromara.legalSupport.domain.vo.DcCustomerJobOrderVo;

import java.util.List;
import java.util.Map;

/**
 * 工单管理Mapper接口
 *
 * @author Lion Li
 * @date 2025-09-21
 */
public interface DcCustomerJobOrderMapper extends BaseMapperPlus<DcCustomerJobOrder, DcCustomerJobOrderVo> {

    List<Map<String, Object>> selectMonthlyJobOrder(@Param("legalSupportId") Long legalSupportId, @Param("city") String city);

    List<Map<String, Object>> selectTodayFollowUpByLegalSupport(@Param("legalSupportId") Long legalSupportId, @Param("city") String city);

}
