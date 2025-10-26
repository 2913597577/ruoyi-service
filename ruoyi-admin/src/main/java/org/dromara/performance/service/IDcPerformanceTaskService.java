package org.dromara.performance.service;

import org.dromara.performance.domain.vo.DcPerformanceTaskVo;
import org.dromara.performance.domain.bo.DcPerformanceTaskBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 业绩任务Service接口
 *
 * @author Lion Li
 * @date 2025-10-26
 */
public interface IDcPerformanceTaskService {

    /**
     * 查询业绩任务
     *
     * @param id 主键
     * @return 业绩任务
     */
    DcPerformanceTaskVo queryById(Long id);

    /**
     * 分页查询业绩任务列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 业绩任务分页列表
     */
    TableDataInfo<DcPerformanceTaskVo> queryPageList(DcPerformanceTaskBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的业绩任务列表
     *
     * @param bo 查询条件
     * @return 业绩任务列表
     */
    List<DcPerformanceTaskVo> queryList(DcPerformanceTaskBo bo);

    /**
     * 新增业绩任务
     *
     * @param bo 业绩任务
     * @return 是否新增成功
     */
    Boolean insertByBo(DcPerformanceTaskBo bo);

    /**
     * 修改业绩任务
     *
     * @param bo 业绩任务
     * @return 是否修改成功
     */
    Boolean updateByBo(DcPerformanceTaskBo bo);

    /**
     * 校验并批量删除业绩任务信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
