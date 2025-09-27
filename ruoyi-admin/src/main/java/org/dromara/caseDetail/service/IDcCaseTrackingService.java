package org.dromara.caseDetail.service;

import org.dromara.caseDetail.domain.vo.DcCaseTrackingVo;
import org.dromara.caseDetail.domain.bo.DcCaseTrackingBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 案件进展表Service接口
 *
 * @author Lion Li
 * @date 2025-09-27
 */
public interface IDcCaseTrackingService {

    /**
     * 查询案件进展表
     *
     * @param id 主键
     * @return 案件进展表
     */
    DcCaseTrackingVo queryById(Long id);

    /**
     * 分页查询案件进展表列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 案件进展表分页列表
     */
    TableDataInfo<DcCaseTrackingVo> queryPageList(DcCaseTrackingBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的案件进展表列表
     *
     * @param bo 查询条件
     * @return 案件进展表列表
     */
    List<DcCaseTrackingVo> queryList(DcCaseTrackingBo bo);

    /**
     * 新增案件进展表
     *
     * @param bo 案件进展表
     * @return 是否新增成功
     */
    Boolean insertByBo(DcCaseTrackingBo bo);

    /**
     * 修改案件进展表
     *
     * @param bo 案件进展表
     * @return 是否修改成功
     */
    Boolean updateByBo(DcCaseTrackingBo bo);

    /**
     * 校验并批量删除案件进展表信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
