package org.dromara.caseDetail.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.caseDetail.domain.DcCaseTracking;
import org.dromara.caseDetail.domain.vo.DcCaseTrackingVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

import java.util.List;
import java.util.Map;

/**
 * 案件进展表Mapper接口
 *
 * @author Lion Li
 * @date 2025-09-27
 */
public interface DcCaseTrackingMapper extends BaseMapperPlus<DcCaseTracking, DcCaseTrackingVo> {

    List<Map<String, Object>> selectMonthlyCaseTracking(@Param("legalSupportId") Long legalSupportId, @Param("city") String city);

    List<Map<String, Object>> selectTodayFollowUpByLegalSupport(@Param("legalSupportId") Long legalSupportId, @Param("city") String city);

}
