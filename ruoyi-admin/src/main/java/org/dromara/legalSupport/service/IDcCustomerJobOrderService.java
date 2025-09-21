package org.dromara.legalSupport.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.legalSupport.domain.bo.DcCustomerJobOrderBo;
import org.dromara.legalSupport.domain.vo.DcCustomerJobOrderVo;

import java.util.Collection;
import java.util.List;

/**
 * 工单管理Service接口
 *
 * @author Lion Li
 * @date 2025-09-21
 */
public interface IDcCustomerJobOrderService {

    /**
     * 查询工单管理
     *
     * @param id 主键
     * @return 工单管理
     */
    DcCustomerJobOrderVo queryById(Long id);

    /**
     * 分页查询工单管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 工单管理分页列表
     */
    TableDataInfo<DcCustomerJobOrderVo> queryPageList(DcCustomerJobOrderBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的工单管理列表
     *
     * @param bo 查询条件
     * @return 工单管理列表
     */
    List<DcCustomerJobOrderVo> queryList(DcCustomerJobOrderBo bo);

    /**
     * 新增工单管理
     *
     * @param bo 工单管理
     * @return 是否新增成功
     */
    Boolean insertByBo(DcCustomerJobOrderBo bo);

    /**
     * 修改工单管理
     *
     * @param bo 工单管理
     * @return 是否修改成功
     */
    Boolean updateByBo(DcCustomerJobOrderBo bo);

    /**
     * 校验并批量删除工单管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    DcCustomerJobOrderVo queryByTrackingId(Long trackingId);
}
