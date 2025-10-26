package org.dromara.caseDetail.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.caseDetail.domain.DcDebtCase;
import org.dromara.caseDetail.domain.vo.DcDebtCaseVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

import java.util.List;
import java.util.Map;

/**
 * 欠款案件表Mapper接口
 *
 * @author Lion Li
 * @date 2025-09-27
 */
public interface DcDebtCaseMapper extends BaseMapperPlus<DcDebtCase, DcDebtCaseVo> {

    List<Map<String, Object>> selectMonthlyDebtCase(@Param("legalSupportId") Long legalSupportId);

}
