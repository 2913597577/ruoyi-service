package org.dromara.workflow.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.event.ProcessDeleteEvent;
import org.dromara.common.core.domain.event.ProcessEvent;
import org.dromara.common.core.domain.event.ProcessTaskEvent;
import org.dromara.common.core.enums.BusinessStatusEnum;
import org.dromara.common.core.service.WorkflowService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.workflow.common.ConditionalOnEnable;
import org.dromara.workflow.domain.DcEmployeePositionChange;
import org.dromara.workflow.domain.bo.DcEmployeePositionChangeBo;
import org.dromara.workflow.domain.vo.DcEmployeePositionChangeVo;
import org.dromara.workflow.mapper.DcEmployeePositionChangeMapper;
import org.dromara.workflow.service.IDcEmployeePositionChangeService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 员工岗位变动申请Service业务层处理
 */
@ConditionalOnEnable
@RequiredArgsConstructor
@Service
@Slf4j
public class DcEmployeePositionChangeServiceImpl implements IDcEmployeePositionChangeService {

    private final DcEmployeePositionChangeMapper baseMapper;
    private final WorkflowService workflowService;

    /**
     * 查询员工岗位变动申请
     */
    @Override
    public DcEmployeePositionChangeVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询员工岗位变动申请列表
     */
    @Override
    public TableDataInfo<DcEmployeePositionChangeVo> queryPageList(DcEmployeePositionChangeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcEmployeePositionChange> lqw = buildQueryWrapper(bo);
        Page<DcEmployeePositionChangeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询员工岗位变动申请列表
     */
    @Override
    public List<DcEmployeePositionChangeVo> queryList(DcEmployeePositionChangeBo bo) {
        LambdaQueryWrapper<DcEmployeePositionChange> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcEmployeePositionChange> buildQueryWrapper(DcEmployeePositionChangeBo bo) {
        LambdaQueryWrapper<DcEmployeePositionChange> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getEmployeeId() != null, DcEmployeePositionChange::getEmployeeId, bo.getEmployeeId());
        lqw.like(StringUtils.isNotBlank(bo.getEmployeeName()), DcEmployeePositionChange::getEmployeeName, bo.getEmployeeName());
        lqw.eq(StringUtils.isNotBlank(bo.getChangeType()), DcEmployeePositionChange::getChangeType, bo.getChangeType());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增员工岗位变动申请
     */
    @Override
    public DcEmployeePositionChangeVo insertByBo(DcEmployeePositionChangeBo bo) {
        DcEmployeePositionChange add = MapstructUtils.convert(bo, DcEmployeePositionChange.class);
        if (StringUtils.isBlank(add.getStatus())) {
            add.setStatus(BusinessStatusEnum.DRAFT.getStatus());
        }
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return MapstructUtils.convert(add, DcEmployeePositionChangeVo.class);
    }

    /**
     * 修改员工岗位变动申请
     */
    @Override
    public DcEmployeePositionChangeVo updateByBo(DcEmployeePositionChangeBo bo) {
        DcEmployeePositionChange update = MapstructUtils.convert(bo, DcEmployeePositionChange.class);
        baseMapper.updateById(update);
        return MapstructUtils.convert(update, DcEmployeePositionChangeVo.class);
    }

    /**
     * 批量删除员工岗位变动申请
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(List<Long> ids) {
        workflowService.deleteInstance(ids);
        return baseMapper.deleteByIds(ids) > 0;
    }

    /**
     * 总体流程监听(例如: 草稿，撤销，退回，作废，终止，已完成，单任务完成等)
     */
    @EventListener(condition = "#processEvent.flowCode.startsWith('employeePositionChange')")
    public void processHandler(ProcessEvent processEvent) {
        log.info("当前任务执行了{}", processEvent.toString());
        DcEmployeePositionChange entity = baseMapper.selectById(Long.valueOf(processEvent.getBusinessId()));
        entity.setStatus(processEvent.getStatus());
        Map<String, Object> params = processEvent.getParams();
        if (MapUtil.isNotEmpty(params)) {
            // 历史任务扩展(通常为附件)
            String hisTaskExt = Convert.toStr(params.get("hisTaskExt"));
            // 办理人
            String handler = Convert.toStr(params.get("handler"));
            // 办理意见
            String message = Convert.toStr(params.get("message"));
        }
        if (processEvent.getSubmit()) {
            entity.setStatus(BusinessStatusEnum.WAITING.getStatus());
        }
        baseMapper.updateById(entity);
    }

    /**
     * 执行任务创建监听
     */
    @EventListener(condition = "#processTaskEvent.flowCode.startsWith('employeePositionChange')")
    public void processTaskHandler(ProcessTaskEvent processTaskEvent) {
        log.info("当前任务创建了{}", processTaskEvent.toString());
    }

    /**
     * 监听删除流程事件
     */
    @EventListener(condition = "#processDeleteEvent.flowCode.startsWith('employeePositionChange')")
    public void processDeleteHandler(ProcessDeleteEvent processDeleteEvent) {
        log.info("监听删除流程事件，当前任务执行了{}", processDeleteEvent.toString());
        DcEmployeePositionChange entity = baseMapper.selectById(Long.valueOf(processDeleteEvent.getBusinessId()));
        if (entity == null) {
            return;
        }
        baseMapper.deleteById(entity.getId());
    }
}
