package org.dromara.workflow.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.workflow.domain.bo.DcCustomerRefundBo;
import org.dromara.workflow.domain.vo.DcCustomerRefundVo;

import java.util.List;

/**
 * 客户风险/退费记录Service接口
 */
public interface IDcCustomerRefundService {

    /**
     * 查询客户风险/退费记录
     */
    DcCustomerRefundVo queryById(Long id);

    /**
     * 分页查询客户风险/退费记录列表
     */
    TableDataInfo<DcCustomerRefundVo> queryPageList(DcCustomerRefundBo bo, PageQuery pageQuery);

    /**
     * 查询客户风险/退费记录列表
     */
    List<DcCustomerRefundVo> queryList(DcCustomerRefundBo bo);

    /**
     * 新增客户风险/退费记录
     */
    DcCustomerRefundVo insertByBo(DcCustomerRefundBo bo);

    /**
     * 修改客户风险/退费记录
     */
    DcCustomerRefundVo updateByBo(DcCustomerRefundBo bo);

    /**
     * 批量删除客户风险/退费记录
     */
    Boolean deleteWithValidByIds(List<Long> ids);
}
