package org.dromara.customer.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.customer.domain.bo.DcCustomerIntentionBo;
import org.dromara.customer.domain.vo.DcCustomerIntentionVo;

import java.util.Collection;
import java.util.List;

/**
 * 客户意向登记Service接口
 *
 * @author Lion Li
 * @date 2025-09-21
 */
public interface IDcCustomerIntentionService {

    /**
     * 查询客户意向登记
     *
     * @param id 主键
     * @return 客户意向登记
     */
    DcCustomerIntentionVo queryById(Long id);

    /**
     * 分页查询客户意向登记列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户意向登记分页列表
     */
    TableDataInfo<DcCustomerIntentionVo> queryPageList(DcCustomerIntentionBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的客户意向登记列表
     *
     * @param bo 查询条件
     * @return 客户意向登记列表
     */
    List<DcCustomerIntentionVo> queryList(DcCustomerIntentionBo bo);

    /**
     * 新增客户意向登记
     *
     * @param bo 客户意向登记
     * @return 是否新增成功
     */
    Boolean insertByBo(DcCustomerIntentionBo bo);

    /**
     * 修改客户意向登记
     *
     * @param bo 客户意向登记
     * @return 是否修改成功
     */
    Boolean updateByBo(DcCustomerIntentionBo bo);

    /**
     * 校验并批量删除客户意向登记信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 根据创建部门数组查询客户总表列表
     *
     * @param createDepts 创建部门ID数组
     * @return 客户总表列表
     */
    List<DcCustomerIntentionVo> queryListByCreateDepts(List<Long> createDepts);

}
