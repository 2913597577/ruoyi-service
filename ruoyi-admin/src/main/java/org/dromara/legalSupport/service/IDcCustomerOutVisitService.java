package org.dromara.legalSupport.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.legalSupport.domain.bo.DcCustomerOutVisitBo;
import org.dromara.legalSupport.domain.vo.DcCustomerOutVisitVo;

import java.util.Collection;
import java.util.List;

/**
 * 客户出访记录Service接口
 *
 * @author Lion Li
 * @date 2025-10-16
 */
public interface IDcCustomerOutVisitService {

    /**
     * 查询客户出访记录
     *
     * @param id 主键
     * @return 客户出访记录
     */
    DcCustomerOutVisitVo queryById(Long id);

    /**
     * 分页查询客户出访记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户出访记录分页列表
     */
    TableDataInfo<DcCustomerOutVisitVo> queryPageList(DcCustomerOutVisitBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的客户出访记录列表
     *
     * @param bo 查询条件
     * @return 客户出访记录列表
     */
    List<DcCustomerOutVisitVo> queryList(DcCustomerOutVisitBo bo);

    /**
     * 新增客户出访记录
     *
     * @param bo 客户出访记录
     * @return 是否新增成功
     */
    Boolean insertByBo(DcCustomerOutVisitBo bo);

    /**
     * 修改客户出访记录
     *
     * @param bo 客户出访记录
     * @return 是否修改成功
     */
    Boolean updateByBo(DcCustomerOutVisitBo bo);

    /**
     * 校验并批量删除客户出访记录信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
