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
import org.dromara.workflow.domain.DcExpenseReimbursement;
import org.dromara.workflow.domain.bo.DcExpenseReimbursementBo;
import org.dromara.workflow.domain.vo.DcExpenseReimbursementVo;
import org.dromara.workflow.mapper.DcExpenseReimbursementMapper;
import org.dromara.workflow.service.IDcExpenseReimbursementService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 报销申请Service业务层处理
 */
@ConditionalOnEnable
@RequiredArgsConstructor
@Service
@Slf4j
public class DcExpenseReimbursementServiceImpl implements IDcExpenseReimbursementService {

    private final DcExpenseReimbursementMapper baseMapper;
    private final WorkflowService workflowService;

    /**
     * 查询报销申请
     */
    @Override
    public DcExpenseReimbursementVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询报销申请列表
     */
    @Override
    public TableDataInfo<DcExpenseReimbursementVo> queryPageList(DcExpenseReimbursementBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcExpenseReimbursement> lqw = buildQueryWrapper(bo);
        Page<DcExpenseReimbursementVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询报销申请列表
     */
    @Override
    public List<DcExpenseReimbursementVo> queryList(DcExpenseReimbursementBo bo) {
        LambdaQueryWrapper<DcExpenseReimbursement> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcExpenseReimbursement> buildQueryWrapper(DcExpenseReimbursementBo bo) {
        LambdaQueryWrapper<DcExpenseReimbursement> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getApplicantId() != null, DcExpenseReimbursement::getApplicantId, bo.getApplicantId());
        lqw.like(StringUtils.isNotBlank(bo.getApplicantName()), DcExpenseReimbursement::getApplicantName, bo.getApplicantName());
        lqw.eq(StringUtils.isNotBlank(bo.getExpenseType()), DcExpenseReimbursement::getExpenseType, bo.getExpenseType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), DcExpenseReimbursement::getStatus, bo.getStatus());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增报销申请
     */
    @Override
    public DcExpenseReimbursementVo insertByBo(DcExpenseReimbursementBo bo) {
        DcExpenseReimbursement add = MapstructUtils.convert(bo, DcExpenseReimbursement.class);
        if (StringUtils.isBlank(add.getStatus())) {
            add.setStatus(BusinessStatusEnum.DRAFT.getStatus());
        }
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return MapstructUtils.convert(add, DcExpenseReimbursementVo.class);
    }

    /**
     * 修改报销申请
     */
    @Override
    public DcExpenseReimbursementVo updateByBo(DcExpenseReimbursementBo bo) {
        DcExpenseReimbursement update = MapstructUtils.convert(bo, DcExpenseReimbursement.class);
        baseMapper.updateById(update);
        return MapstructUtils.convert(update, DcExpenseReimbursementVo.class);
    }

    /**
     * 批量删除报销申请
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(List<Long> ids) {
        workflowService.deleteInstance(ids);
        return baseMapper.deleteByIds(ids) > 0;
    }

    /**
     * 总体流程监听(例如: 草稿，撤销，退回，作废，终止，已完成，单任务完成等)
     * 正常使用只需#processEvent.flowCode=='expenseReimbursement'
     *
     * @param processEvent 参数
     */
    @EventListener(condition = "#processEvent.flowCode.startsWith('expenseReimbursement')")
    public void processHandler(ProcessEvent processEvent) {
        log.info("当前任务执行了{}", processEvent.toString());
        DcExpenseReimbursement dcExpenseReimbursement = baseMapper.selectById(Long.valueOf(processEvent.getBusinessId()));
        dcExpenseReimbursement.setStatus(processEvent.getStatus());
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
            dcExpenseReimbursement.setStatus(BusinessStatusEnum.WAITING.getStatus());
        }
        baseMapper.updateById(dcExpenseReimbursement);
    }

    /**
     * 执行任务创建监听
     *
     * @param processTaskEvent 参数
     */
    @EventListener(condition = "#processTaskEvent.flowCode.startsWith('expenseReimbursement')")
    public void processTaskHandler(ProcessTaskEvent processTaskEvent) {
        log.info("当前任务创建了{}", processTaskEvent.toString());
    }

    /**
     * 监听删除流程事件
     *
     * @param processDeleteEvent 参数
     */
    @EventListener(condition = "#processDeleteEvent.flowCode.startsWith('expenseReimbursement')")
    public void processDeleteHandler(ProcessDeleteEvent processDeleteEvent) {
        log.info("监听删除流程事件，当前任务执行了{}", processDeleteEvent.toString());
        DcExpenseReimbursement dcExpenseReimbursement = baseMapper.selectById(Long.valueOf(processDeleteEvent.getBusinessId()));
        if (dcExpenseReimbursement == null) {
            return;
        }
        baseMapper.deleteById(dcExpenseReimbursement.getId());
    }
}
