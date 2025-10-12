package org.dromara.caseDetail.mapper;

import org.dromara.caseDetail.domain.DcInsuranceCase;
import org.dromara.caseDetail.domain.vo.DcInsuranceCaseVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

import java.util.List;
import java.util.Map;

/**
 * 保险记录表Mapper接口
 *
 * @author Lion Li
 * @date 2025-09-29
 */
public interface DcInsuranceCaseMapper extends BaseMapperPlus<DcInsuranceCase, DcInsuranceCaseVo> {

    List<Map<String, Object>> selectByYearMonth(Integer year, Integer month, Integer day, Long userId);

}
