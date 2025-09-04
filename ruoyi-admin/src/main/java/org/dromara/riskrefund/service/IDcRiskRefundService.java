package org.dromara.riskrefund.service;

import org.dromara.riskrefund.domain.vo.DcRiskRefundVo;
import org.dromara.riskrefund.domain.bo.DcRiskRefundBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 风险退费客户Service接口
 *
 * @author Lion Li
 * @date 2025-07-09
 */
public interface IDcRiskRefundService {

    /**
     * 查询风险退费客户
     *
     * @param id 主键
     * @return 风险退费客户
     */
    DcRiskRefundVo queryById(Long id);

    /**
     * 分页查询风险退费客户列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 风险退费客户分页列表
     */
    TableDataInfo<DcRiskRefundVo> queryPageList(DcRiskRefundBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的风险退费客户列表
     *
     * @param bo 查询条件
     * @return 风险退费客户列表
     */
    List<DcRiskRefundVo> queryList(DcRiskRefundBo bo);

    /**
     * 新增风险退费客户
     *
     * @param bo 风险退费客户
     * @return 是否新增成功
     */
    Boolean insertByBo(DcRiskRefundBo bo);

    /**
     * 修改风险退费客户
     *
     * @param bo 风险退费客户
     * @return 是否修改成功
     */
    Boolean updateByBo(DcRiskRefundBo bo);

    /**
     * 校验并批量删除风险退费客户信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
