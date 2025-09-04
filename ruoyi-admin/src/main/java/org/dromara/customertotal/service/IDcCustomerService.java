package org.dromara.customertotal.service;

import org.dromara.customertotal.domain.vo.DcCustomerVo;
import org.dromara.customertotal.domain.bo.DcCustomerBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 全部客户Service接口
 *
 * @author chang
 * @date 2025-07-08
 */
public interface IDcCustomerService {

    /**
     * 查询全部客户
     *
     * @param id 主键
     * @return 全部客户
     */
    DcCustomerVo queryById(Long id);

    /**
     * 分页查询全部客户列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 全部客户分页列表
     */
    TableDataInfo<DcCustomerVo> queryPageList(DcCustomerBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的全部客户列表
     *
     * @param bo 查询条件
     * @return 全部客户列表
     */
    List<DcCustomerVo> queryList(DcCustomerBo bo);

    /**
     * 新增全部客户
     *
     * @param bo 全部客户
     * @return 是否新增成功
     */
    Boolean insertByBo(DcCustomerBo bo);

    /**
     * 修改全部客户
     *
     * @param bo 全部客户
     * @return 是否修改成功
     */
    Boolean updateByBo(DcCustomerBo bo);

    /**
     * 校验并批量删除全部客户信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
