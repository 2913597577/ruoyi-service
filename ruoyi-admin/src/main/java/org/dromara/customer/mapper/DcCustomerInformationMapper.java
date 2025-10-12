package org.dromara.customer.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.customer.domain.DcCustomerInformation;
import org.dromara.customer.domain.vo.DcCustomerInformationVo;

import java.util.List;
import java.util.Map;

/**
 * 客户总表Mapper接口
 *
 * @author Lion Li
 * @date 2025-09-06
 */
public interface DcCustomerInformationMapper extends BaseMapperPlus<DcCustomerInformation, DcCustomerInformationVo> {

    List<Map<String, Object>> selectCustomerCountByType();

    List<Map<String, Object>> selectByYearMonth(Integer year, Integer month, Integer day, Long userId);
}
