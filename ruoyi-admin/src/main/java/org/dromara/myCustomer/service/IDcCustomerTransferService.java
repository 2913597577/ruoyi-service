package org.dromara.myCustomer.service;

import org.dromara.myCustomer.domain.vo.DcCustomerTransferVo;
import org.dromara.myCustomer.domain.bo.DcCustomerTransferBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 客户信息录入Service接口
 *
 * @author Lion Li
 * @date 2025-09-06
 */
public interface IDcCustomerTransferService {

    /**
     * 查询客户信息录入
     *
     * @param id 主键
     * @return 客户信息录入
     */
    DcCustomerTransferVo queryById(Long id);

    /**
     * 分页查询客户信息录入列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户信息录入分页列表
     */
    TableDataInfo<DcCustomerTransferVo> queryPageList(DcCustomerTransferBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的客户信息录入列表
     *
     * @param bo 查询条件
     * @return 客户信息录入列表
     */
    List<DcCustomerTransferVo> queryList(DcCustomerTransferBo bo);

    /**
     * 新增客户信息录入
     *
     * @param bo 客户信息录入
     * @return 是否新增成功
     */
    Boolean insertByBo(DcCustomerTransferBo bo);

    /**
     * 修改客户信息录入
     *
     * @param bo 客户信息录入
     * @return 是否修改成功
     */
    Boolean updateByBo(DcCustomerTransferBo bo);

    /**
     * 校验并批量删除客户信息录入信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 审核客户信息录入
     *
     * @param id 主键
     * @return 是否审核成功
     */
    Boolean audit(Long id,Integer auditStatus,String url);
}
