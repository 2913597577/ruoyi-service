package org.dromara.workflow.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.workflow.domain.DcCustomerChurnApprove;
import org.dromara.workflow.domain.vo.DcCustomerChurnApproveVo;

public interface DcCustomerChurnApproveMapper extends BaseMapperPlus<DcCustomerChurnApprove, DcCustomerChurnApproveVo> {

    void updateCustomerTypeById(@Param("id") Long id, @Param("customerType") Integer customerType);

}
