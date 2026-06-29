package org.dromara.performance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.performance.domain.DcSalescenterPerformanceTask;
import org.dromara.performance.domain.bo.DcSalescenterPerformanceTaskBo;
import org.dromara.performance.domain.vo.DcSalescenterPerformanceTaskVo;
import org.dromara.performance.mapper.DcSalescenterPerformanceTaskMapper;
import org.dromara.performance.service.IDcSalescenterPerformanceTaskService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 业绩任务Service业务层处理
 *
 * @author Lion Li
 * @date 2025-10-26
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcSalescenterPerformanceTaskServiceImpl implements IDcSalescenterPerformanceTaskService {

    private final DcSalescenterPerformanceTaskMapper baseMapper;

    /**
     * 查询业绩任务
     *
     * @param id 主键
     * @return 业绩任务
     */
    @Override
    public DcSalescenterPerformanceTaskVo queryById(Long id) {
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
    public TableDataInfo<DcSalescenterPerformanceTaskVo> queryPageList(DcSalescenterPerformanceTaskBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcSalescenterPerformanceTask> lqw = buildQueryWrapper(bo);
        Page<DcSalescenterPerformanceTaskVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的业绩任务列表
     *
     * @param bo 查询条件
     * @return 业绩任务列表
     */
    @Override
    public List<DcSalescenterPerformanceTaskVo> queryList(DcSalescenterPerformanceTaskBo bo) {
        LambdaQueryWrapper<DcSalescenterPerformanceTask> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcSalescenterPerformanceTask> buildQueryWrapper(DcSalescenterPerformanceTaskBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcSalescenterPerformanceTask> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcSalescenterPerformanceTask::getId);
        lqw.eq(bo.getSalesCenterId() != null, DcSalescenterPerformanceTask::getSalesCenterId, bo.getSalesCenterId());
        lqw.like(StringUtils.isNotBlank(bo.getSalesCenterName()), DcSalescenterPerformanceTask::getSalesCenterName, bo.getSalesCenterName());
        lqw.eq(StringUtils.isNotBlank(bo.getTaskMonth()), DcSalescenterPerformanceTask::getTaskMonth, bo.getTaskMonth());
        lqw.eq(bo.getPerformanceGoal() != null, DcSalescenterPerformanceTask::getPerformanceGoal, bo.getPerformanceGoal());
        lqw.eq(bo.getVisitGoal() != null, DcSalescenterPerformanceTask::getVisitGoal, bo.getVisitGoal());
        lqw.eq(bo.getAchievedPerformanceGoal() != null, DcSalescenterPerformanceTask::getAchievedPerformanceGoal, bo.getAchievedPerformanceGoal());
        lqw.eq(bo.getAchievedVisitGoal() != null, DcSalescenterPerformanceTask::getAchievedVisitGoal, bo.getAchievedVisitGoal());

        lqw.in(bo.getSalesCenterIds() != null && !bo.getSalesCenterIds().isEmpty(), DcSalescenterPerformanceTask::getSalesCenterId, bo.getSalesCenterIds());
        return lqw;
    }

    /**
     * 新增业绩任务
     *
     * @param bo 业绩任务
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcSalescenterPerformanceTaskBo bo) {
        DcSalescenterPerformanceTask add = MapstructUtils.convert(bo, DcSalescenterPerformanceTask.class);
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
    public Boolean updateByBo(DcSalescenterPerformanceTaskBo bo) {
        DcSalescenterPerformanceTask update = MapstructUtils.convert(bo, DcSalescenterPerformanceTask.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcSalescenterPerformanceTask entity) {
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
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
