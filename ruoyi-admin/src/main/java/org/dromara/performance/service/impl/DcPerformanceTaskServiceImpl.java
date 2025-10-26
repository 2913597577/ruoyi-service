package org.dromara.performance.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.dromara.performance.domain.bo.DcPerformanceTaskBo;
import org.dromara.performance.domain.vo.DcPerformanceTaskVo;
import org.dromara.performance.domain.DcPerformanceTask;
import org.dromara.performance.mapper.DcPerformanceTaskMapper;
import org.dromara.performance.service.IDcPerformanceTaskService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 业绩任务Service业务层处理
 *
 * @author Lion Li
 * @date 2025-10-26
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcPerformanceTaskServiceImpl implements IDcPerformanceTaskService {

    private final DcPerformanceTaskMapper baseMapper;

    /**
     * 查询业绩任务
     *
     * @param id 主键
     * @return 业绩任务
     */
    @Override
    public DcPerformanceTaskVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询业绩任务列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 业绩任务分页列表
     */
    @Override
    public TableDataInfo<DcPerformanceTaskVo> queryPageList(DcPerformanceTaskBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcPerformanceTask> lqw = buildQueryWrapper(bo);
        Page<DcPerformanceTaskVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的业绩任务列表
     *
     * @param bo 查询条件
     * @return 业绩任务列表
     */
    @Override
    public List<DcPerformanceTaskVo> queryList(DcPerformanceTaskBo bo) {
        LambdaQueryWrapper<DcPerformanceTask> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcPerformanceTask> buildQueryWrapper(DcPerformanceTaskBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcPerformanceTask> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcPerformanceTask::getId);
        lqw.eq(bo.getLegalSupportId() != null, DcPerformanceTask::getLegalSupportId, bo.getLegalSupportId());
        lqw.like(StringUtils.isNotBlank(bo.getLegalSupportName()), DcPerformanceTask::getLegalSupportName, bo.getLegalSupportName());
        lqw.eq(StringUtils.isNotBlank(bo.getTaskMonth()), DcPerformanceTask::getTaskMonth, bo.getTaskMonth());
        lqw.eq(bo.getPerformanceGoal() != null, DcPerformanceTask::getPerformanceGoal, bo.getPerformanceGoal());
        lqw.eq(bo.getVisitGoal() != null, DcPerformanceTask::getVisitGoal, bo.getVisitGoal());
        lqw.eq(bo.getAchievedPerformanceGoal() != null, DcPerformanceTask::getAchievedPerformanceGoal, bo.getAchievedPerformanceGoal());
        lqw.eq(bo.getAchievedVisitGoal() != null, DcPerformanceTask::getAchievedVisitGoal, bo.getAchievedVisitGoal());
        return lqw;
    }

    /**
     * 新增业绩任务
     *
     * @param bo 业绩任务
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcPerformanceTaskBo bo) {
        DcPerformanceTask add = MapstructUtils.convert(bo, DcPerformanceTask.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改业绩任务
     *
     * @param bo 业绩任务
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcPerformanceTaskBo bo) {
        DcPerformanceTask update = MapstructUtils.convert(bo, DcPerformanceTask.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcPerformanceTask entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除业绩任务信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
