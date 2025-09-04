package org.dromara.customer.service;

import org.dromara.customer.domain.vo.DcIntentionCustomerVo;
import org.dromara.customer.domain.bo.DcIntentionCustomerBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 客户意向登记Service接口
 *
 * @author leo
 * @date 2025-07-08
 */
public interface IDcIntentionCustomerService {

    /**
     * 查询客户意向登记
     *
     * @param id 主键
     * @return 客户意向登记
     */
    DcIntentionCustomerVo queryById(Long id);

    /**
     * 分页查询客户意向登记列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户意向登记分页列表
     */
    TableDataInfo<DcIntentionCustomerVo> queryPageList(DcIntentionCustomerBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的客户意向登记列表
     *
     * @param bo 查询条件
     * @return 客户意向登记列表
     */
    List<DcIntentionCustomerVo> queryList(DcIntentionCustomerBo bo);

    /**
     * 新增客户意向登记
     *
     * @param bo 客户意向登记
     * @return 是否新增成功
     */
    Boolean insertByBo(DcIntentionCustomerBo bo);

    /**
     * 修改客户意向登记
     *
     * @param bo 客户意向登记
     * @return 是否修改成功
     */
    Boolean updateByBo(DcIntentionCustomerBo bo);

    /**
     * 校验并批量删除客户意向登记信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
