package org.dromara.customer.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.customer.domain.DcCustomerIntention;
import org.dromara.customer.domain.vo.DcCustomerIntentionVo;

import java.util.List;
import java.util.Map;

/**
 * 客户意向登记Mapper接口
 *
 * @author Lion Li
 * @date 2025-09-21
 */
public interface DcCustomerIntentionMapper extends BaseMapperPlus<DcCustomerIntention, DcCustomerIntentionVo> {

    List<Map<String, Object>> selectByYearMonth(Integer year, Integer month, Integer day, Integer type, Long userId);
}
