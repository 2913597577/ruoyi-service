package org.dromara.myCustomer.service;

import org.dromara.myCustomer.domain.vo.DcCustomerTrackingVo;
import org.dromara.myCustomer.domain.bo.DcCustomerTrackingBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 客户跟踪Service接口
 *
 * @author Lion Li
 * @date 2025-09-06
 */
public interface IDcCustomerTrackingService {

    /**
     * 查询客户跟踪
     *
     * @param id 主键
     * @return 客户跟踪
     */
    DcCustomerTrackingVo queryById(Long id);

    /**
     * 分页查询客户跟踪列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户跟踪分页列表
     */
    TableDataInfo<DcCustomerTrackingVo> queryPageList(DcCustomerTrackingBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的客户跟踪列表
     *
     * @param bo 查询条件
     * @return 客户跟踪列表
     */
    List<DcCustomerTrackingVo> queryList(DcCustomerTrackingBo bo);

    /**
     * 新增客户跟踪
     *
     * @param bo 客户跟踪
     * @return 是否新增成功
     */
    Boolean insertByBo(DcCustomerTrackingBo bo);

    /**
     * 修改客户跟踪
     *
     * @param bo 客户跟踪
     * @return 是否修改成功
     */
    Boolean updateByBo(DcCustomerTrackingBo bo);

    /**
     * 校验并批量删除客户跟踪信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
