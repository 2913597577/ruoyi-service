package org.dromara.legalSupport.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.legalSupport.domain.DcCustomerOutVisit;
import org.dromara.legalSupport.domain.vo.DcCustomerOutVisitVo;

import java.util.List;
import java.util.Map;

/**
 * 客户出访记录Mapper接口
 *
 * @author Lion Li
 * @date 2025-10-16
 */
public interface DcCustomerOutVisitMapper extends BaseMapperPlus<DcCustomerOutVisit, DcCustomerOutVisitVo> {

    List<Map<String, Object>> selectMonthlyOutVisit(@Param("legalSupportId") Long legalSupportId);

    List<Map<String, Object>> selectTodayFollowUpByLegalSupport(@Param("legalSupportId") Long legalSupportId);

}
