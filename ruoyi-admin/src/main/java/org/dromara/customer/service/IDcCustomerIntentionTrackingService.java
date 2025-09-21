package org.dromara.customer.service;

import org.dromara.customer.domain.vo.DcCustomerIntentionTrackingVo;
import org.dromara.customer.domain.bo.DcCustomerIntentionTrackingBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 意向客户跟踪记录Service接口
 *
 * @author Lion Li
 * @date 2025-09-21
 */
public interface IDcCustomerIntentionTrackingService {

    /**
     * 查询意向客户跟踪记录
     *
     * @param id 主键
     * @return 意向客户跟踪记录
     */
    DcCustomerIntentionTrackingVo queryById(Long id);

    /**
     * 分页查询意向客户跟踪记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 意向客户跟踪记录分页列表
     */
    TableDataInfo<DcCustomerIntentionTrackingVo> queryPageList(DcCustomerIntentionTrackingBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的意向客户跟踪记录列表
     *
     * @param bo 查询条件
     * @return 意向客户跟踪记录列表
     */
    List<DcCustomerIntentionTrackingVo> queryList(DcCustomerIntentionTrackingBo bo);

    /**
     * 新增意向客户跟踪记录
     *
     * @param bo 意向客户跟踪记录
     * @return 是否新增成功
     */
    Boolean insertByBo(DcCustomerIntentionTrackingBo bo);

    /**
     * 修改意向客户跟踪记录
     *
     * @param bo 意向客户跟踪记录
     * @return 是否修改成功
     */
    Boolean updateByBo(DcCustomerIntentionTrackingBo bo);

    /**
     * 校验并批量删除意向客户跟踪记录信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
