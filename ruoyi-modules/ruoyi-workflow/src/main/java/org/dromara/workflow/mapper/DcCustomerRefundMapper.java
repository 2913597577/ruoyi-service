package org.dromara.workflow.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.workflow.domain.DcCustomerRefund;
import org.dromara.workflow.domain.vo.DcCustomerRefundVo;

import java.util.Map;

public interface DcCustomerRefundMapper extends BaseMapperPlus<DcCustomerRefund, DcCustomerRefundVo> {

    void updateRefundTypeById(@Param("id") Long id);

    /**
     * 新增财务统计记录
     *
     * @param paramMap 参数Map集合
     * @return 影响行数
     */
    int insertFinancialStatistics(@Param("financialStatistics") Map<String, Object> paramMap);

}
