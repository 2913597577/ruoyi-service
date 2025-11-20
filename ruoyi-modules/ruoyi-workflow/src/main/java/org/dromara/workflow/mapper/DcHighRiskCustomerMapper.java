package org.dromara.workflow.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.workflow.domain.DcHighRiskCustomer;
import org.dromara.workflow.domain.vo.DcHighRiskCustomerVo;

public interface DcHighRiskCustomerMapper extends BaseMapperPlus<DcHighRiskCustomer, DcHighRiskCustomerVo> {

    void updateRiskTypeById(@Param("id") Long id, @Param("isHighRisk") Integer isHighRisk);
}
