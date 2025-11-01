package org.dromara.legalSupport.service;

import org.dromara.legalSupport.domain.vo.DcLegalSupportChangeRecordVo;
import org.dromara.legalSupport.domain.bo.DcLegalSupportChangeRecordBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 法务支持变更Service接口
 *
 * @author Lion Li
 * @date 2025-11-01
 */
public interface IDcLegalSupportChangeRecordService {

    /**
     * 查询法务支持变更
     *
     * @param id 主键
     * @return 法务支持变更
     */
    DcLegalSupportChangeRecordVo queryById(Long id);

    /**
     * 分页查询法务支持变更列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 法务支持变更分页列表
     */
    TableDataInfo<DcLegalSupportChangeRecordVo> queryPageList(DcLegalSupportChangeRecordBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的法务支持变更列表
     *
     * @param bo 查询条件
     * @return 法务支持变更列表
     */
    List<DcLegalSupportChangeRecordVo> queryList(DcLegalSupportChangeRecordBo bo);

    /**
     * 新增法务支持变更
     *
     * @param bo 法务支持变更
     * @return 是否新增成功
     */
    Boolean insertByBo(DcLegalSupportChangeRecordBo bo);

    /**
     * 修改法务支持变更
     *
     * @param bo 法务支持变更
     * @return 是否修改成功
     */
    Boolean updateByBo(DcLegalSupportChangeRecordBo bo);

    /**
     * 校验并批量删除法务支持变更信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
