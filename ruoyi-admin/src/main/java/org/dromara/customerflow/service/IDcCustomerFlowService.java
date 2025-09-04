package org.dromara.customerflow.service;

import org.dromara.customerflow.domain.vo.DcCustomerFlowVo;
import org.dromara.customerflow.domain.bo.DcCustomerFlowBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 待提交流转单Service接口
 *
 * @author leo
 * @date 2025-07-08
 */
public interface IDcCustomerFlowService {

    /**
     * 查询待提交流转单
     *
     * @param id 主键
     * @return 待提交流转单
     */
    DcCustomerFlowVo queryById(Long id);

    /**
     * 分页查询待提交流转单列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 待提交流转单分页列表
     */
    TableDataInfo<DcCustomerFlowVo> queryPageList(DcCustomerFlowBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的待提交流转单列表
     *
     * @param bo 查询条件
     * @return 待提交流转单列表
     */
    List<DcCustomerFlowVo> queryList(DcCustomerFlowBo bo);

    /**
     * 新增待提交流转单
     *
     * @param bo 待提交流转单
     * @return 是否新增成功
     */
    Boolean insertByBo(DcCustomerFlowBo bo);

    /**
     * 修改待提交流转单
     *
     * @param bo 待提交流转单
     * @return 是否修改成功
     */
    Boolean updateByBo(DcCustomerFlowBo bo);

    /**
     * 校验并批量删除待提交流转单信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
