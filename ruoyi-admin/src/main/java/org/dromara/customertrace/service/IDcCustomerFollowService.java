package org.dromara.customertrace.service;

import org.dromara.customertrace.domain.vo.DcCustomerFollowVo;
import org.dromara.customertrace.domain.bo.DcCustomerFollowBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 客户跟进记录Service接口
 *
 * @author Lion Li
 * @date 2025-07-08
 */
public interface IDcCustomerFollowService {

    /**
     * 查询客户跟进记录
     *
     * @param id 主键
     * @return 客户跟进记录
     */
    DcCustomerFollowVo queryById(Long id);

    /**
     * 分页查询客户跟进记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户跟进记录分页列表
     */
    TableDataInfo<DcCustomerFollowVo> queryPageList(DcCustomerFollowBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的客户跟进记录列表
     *
     * @param bo 查询条件
     * @return 客户跟进记录列表
     */
    List<DcCustomerFollowVo> queryList(DcCustomerFollowBo bo);

    /**
     * 新增客户跟进记录
     *
     * @param bo 客户跟进记录
     * @return 是否新增成功
     */
    Boolean insertByBo(DcCustomerFollowBo bo);

    /**
     * 修改客户跟进记录
     *
     * @param bo 客户跟进记录
     * @return 是否修改成功
     */
    Boolean updateByBo(DcCustomerFollowBo bo);

    /**
     * 校验并批量删除客户跟进记录信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
