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
import org.dromara.workflow.domain.DcCustomerChurnApprove;
import org.dromara.workflow.domain.bo.DcCustomerChurnApproveBo;
import org.dromara.workflow.domain.vo.DcCustomerChurnApproveVo;
import org.dromara.workflow.mapper.DcCustomerChurnApproveMapper;
import org.dromara.workflow.service.IDcCustomerChurnApproveService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 客户流失审批Service业务层处理
 */
@ConditionalOnEnable
@RequiredArgsConstructor
@Service
@Slf4j
public class DcCustomerChurnApproveServiceImpl implements IDcCustomerChurnApproveService {

    private final DcCustomerChurnApproveMapper baseMapper;
    private final WorkflowService workflowService;

    /**
     * 查询客户流失审批
     */
    @Override
    public DcCustomerChurnApproveVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询客户流失审批列表
     */
    @Override
    public TableDataInfo<DcCustomerChurnApproveVo> queryPageList(DcCustomerChurnApproveBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCustomerChurnApprove> lqw = buildQueryWrapper(bo);
        Page<DcCustomerChurnApproveVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询客户流失审批列表
     */
    @Override
    public List<DcCustomerChurnApproveVo> queryList(DcCustomerChurnApproveBo bo) {
        LambdaQueryWrapper<DcCustomerChurnApprove> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomerChurnApprove> buildQueryWrapper(DcCustomerChurnApproveBo bo) {
        LambdaQueryWrapper<DcCustomerChurnApprove> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getApplyType()), DcCustomerChurnApprove::getApplyType, bo.getApplyType());
        lqw.eq(bo.getCustomerId() != null, DcCustomerChurnApprove::getCustomerId, bo.getCustomerId());
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), DcCustomerChurnApprove::getCustomerName, bo.getCustomerName());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增客户流失审批
     */
    @Override
    public DcCustomerChurnApproveVo insertByBo(DcCustomerChurnApproveBo bo) {
        DcCustomerChurnApprove add = MapstructUtils.convert(bo, DcCustomerChurnApprove.class);
        if (StringUtils.isBlank(add.getStatus())) {
            add.setStatus(BusinessStatusEnum.DRAFT.getStatus());
        }
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return MapstructUtils.convert(add, DcCustomerChurnApproveVo.class);
    }

    /**
     * 修改客户流失审批
     */
    @Override
    public DcCustomerChurnApproveVo updateByBo(DcCustomerChurnApproveBo bo) {
        DcCustomerChurnApprove update = MapstructUtils.convert(bo, DcCustomerChurnApprove.class);
        baseMapper.updateById(update);
        return MapstructUtils.convert(update, DcCustomerChurnApproveVo.class);
    }

    /**
     * 批量删除客户流失审批
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(List<Long> ids) {
        workflowService.deleteInstance(ids);
        return baseMapper.deleteByIds(ids) > 0;
    }

    /**
     * 总体流程监听(例如: 草稿，撤销，退回，作废，终止，已完成，单任务完成等)
     * 正常使用只需#processEvent.flowCode=='customerChurnApprove'
     * 示例为了方便则使用startsWith匹配了全部示例key
     *
     * @param processEvent 参数
     */
    @EventListener(condition = "#processEvent.flowCode.startsWith('customerChurn')")
    public void processHandler(ProcessEvent processEvent) {
        log.info("当前任务执行了{}", processEvent.toString());
        DcCustomerChurnApprove dcCustomerChurnApprove = baseMapper.selectById(Long.valueOf(processEvent.getBusinessId()));
        dcCustomerChurnApprove.setStatus(processEvent.getStatus());
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
            dcCustomerChurnApprove.setStatus(BusinessStatusEnum.WAITING.getStatus());
        }
        baseMapper.updateById(dcCustomerChurnApprove);
        if (BusinessStatusEnum.FINISH.getStatus().equals(processEvent.getStatus())) {
            baseMapper.updateCustomerTypeById(dcCustomerChurnApprove.getCustomerId(), 3);
        }
    }

    /**
     * 执行任务创建监听
     * 示例：也可通过  @EventListener(condition = "#processTaskEvent.flowCode=='customerChurnApprove'")进行判断
     * 在方法中判断流程节点key
     * if ("xxx".equals(processTaskEvent.getNodeCode())) {
     * //执行业务逻辑
     * }
     *
     * @param processTaskEvent 参数
     */
    @EventListener(condition = "#processTaskEvent.flowCode.startsWith('customerChurn')")
    public void processTaskHandler(ProcessTaskEvent processTaskEvent) {
        log.info("当前任务创建了{}", processTaskEvent.toString());
    }

    /**
     * 监听删除流程事件
     * 正常使用只需#processDeleteEvent.flowCode=='customerChurnApprove'
     * 示例为了方便则使用startsWith匹配了全部示例key
     *
     * @param processDeleteEvent 参数
     */
    @EventListener(condition = "#processDeleteEvent.flowCode.startsWith('customerChurn')")
    public void processDeleteHandler(ProcessDeleteEvent processDeleteEvent) {
        log.info("监听删除流程事件，当前任务执行了{}", processDeleteEvent.toString());
        DcCustomerChurnApprove dcCustomerChurnApprove = baseMapper.selectById(Long.valueOf(processDeleteEvent.getBusinessId()));
        if (dcCustomerChurnApprove == null) {
            return;
        }
        baseMapper.deleteById(dcCustomerChurnApprove.getId());
    }
}
