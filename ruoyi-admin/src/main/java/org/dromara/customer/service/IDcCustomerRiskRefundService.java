package org.dromara.customer.service;

import org.dromara.customer.domain.vo.DcCustomerRiskRefundVo;
import org.dromara.customer.domain.bo.DcCustomerRiskRefundBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 客户风险/退费Service接口
 *
 * @author Lion Li
 * @date 2025-09-19
 */
public interface IDcCustomerRiskRefundService {

    /**
     * 查询客户风险/退费
     *
     * @param id 主键
     * @return 客户风险/退费
     */
    DcCustomerRiskRefundVo queryById(Long id);

    /**
     * 分页查询客户风险/退费列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户风险/退费分页列表
     */
    TableDataInfo<DcCustomerRiskRefundVo> queryPageList(DcCustomerRiskRefundBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的客户风险/退费列表
     *
     * @param bo 查询条件
     * @return 客户风险/退费列表
     */
    List<DcCustomerRiskRefundVo> queryList(DcCustomerRiskRefundBo bo);

    /**
     * 新增客户风险/退费
     *
     * @param bo 客户风险/退费
     * @return 是否新增成功
     */
    Boolean insertByBo(DcCustomerRiskRefundBo bo);

    /**
     * 修改客户风险/退费
     *
     * @param bo 客户风险/退费
     * @return 是否修改成功
     */
    Boolean updateByBo(DcCustomerRiskRefundBo bo);

    /**
     * 校验并批量删除客户风险/退费信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
