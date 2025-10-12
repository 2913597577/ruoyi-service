package org.dromara.customer.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.customer.domain.DcCustomerRiskRefund;
import org.dromara.customer.domain.vo.DcCustomerRiskRefundVo;

import java.util.List;
import java.util.Map;

/**
 * 客户风险/退费Mapper接口
 *
 * @author Lion Li
 * @date 2025-09-19
 */
public interface DcCustomerRiskRefundMapper extends BaseMapperPlus<DcCustomerRiskRefund, DcCustomerRiskRefundVo> {

    List<Map<String, Object>> selectByYearMonth(Integer year, Integer month, Integer day, Integer customerType, Long userId);

    Map<String, Object> selectRefundCount(Integer year, Integer month, Integer day, Integer customerType, Long userId);

}
