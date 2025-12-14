// DcCustomerRiskRefundServiceImpl.java
package org.dromara.workflow.service.impl;

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
import org.dromara.workflow.domain.DcCustomerRefund;
import org.dromara.workflow.domain.bo.DcCustomerRefundBo;
import org.dromara.workflow.domain.vo.DcCustomerRefundVo;
import org.dromara.workflow.mapper.DcCustomerRefundMapper;
import org.dromara.workflow.service.IDcCustomerRefundService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 客户风险/退费记录Service业务层处理
 */
@ConditionalOnEnable
@RequiredArgsConstructor
@Service
@Slf4j
public class DcCustomerRefundServiceImpl implements IDcCustomerRefundService {

    private final DcCustomerRefundMapper baseMapper;
    private final WorkflowService workflowService;

    @Override
    public DcCustomerRefundVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    @Override
    public TableDataInfo<DcCustomerRefundVo> queryPageList(DcCustomerRefundBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCustomerRefund> lqw = buildQueryWrapper(bo);
        Page<DcCustomerRefundVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public List<DcCustomerRefundVo> queryList(DcCustomerRefundBo bo) {
        LambdaQueryWrapper<DcCustomerRefund> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomerRefund> buildQueryWrapper(DcCustomerRefundBo bo) {
        LambdaQueryWrapper<DcCustomerRefund> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCustomerId() != null, DcCustomerRefund::getCustomerId, bo.getCustomerId());
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), DcCustomerRefund::getCustomerName, bo.getCustomerName());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    @Override
    public DcCustomerRefundVo insertByBo(DcCustomerRefundBo bo) {
        DcCustomerRefund add = MapstructUtils.convert(bo, DcCustomerRefund.class);
        if (StringUtils.isBlank(add.getStatus())) {
            add.setStatus(BusinessStatusEnum.DRAFT.getStatus());
        }
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return MapstructUtils.convert(add, DcCustomerRefundVo.class);
    }

    @Override
    public DcCustomerRefundVo updateByBo(DcCustomerRefundBo bo) {
        DcCustomerRefund update = MapstructUtils.convert(bo, DcCustomerRefund.class);
        baseMapper.updateById(update);
        return MapstructUtils.convert(update, DcCustomerRefundVo.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(List<Long> ids) {
        workflowService.deleteInstance(ids);
        return baseMapper.deleteByIds(ids) > 0;
    }

    /**
     * 流程状态变更监听
     */
    @EventListener(condition = "#processEvent.flowCode.startsWith('customerRiskRefund')")
    public void processHandler(ProcessEvent processEvent) {
        log.info("当前任务执行了{}", processEvent.toString());
        DcCustomerRefund entity = baseMapper.selectById(Long.valueOf(processEvent.getBusinessId()));
        entity.setStatus(processEvent.getStatus());
        Map<String, Object> params = processEvent.getParams();
        if (MapUtil.isNotEmpty(params)) {
            // 可添加参数处理逻辑
        }
        if (processEvent.getSubmit()) {
            entity.setStatus(BusinessStatusEnum.WAITING.getStatus());
        }
        baseMapper.updateById(entity);
        if (BusinessStatusEnum.FINISH.getStatus().equals(processEvent.getStatus())) {
            baseMapper.updateRefundTypeById(entity.getId());
        }
    }

    /**
     * 任务创建监听
     */
    @EventListener(condition = "#processTaskEvent.flowCode.startsWith('customerRiskRefund')")
    public void processTaskHandler(ProcessTaskEvent processTaskEvent) {
        log.info("当前任务创建了{}", processTaskEvent.toString());
    }

    /**
     * 删除流程监听
     */
    @EventListener(condition = "#processDeleteEvent.flowCode.startsWith('customerRiskRefund')")
    public void processDeleteHandler(ProcessDeleteEvent processDeleteEvent) {
        log.info("监听删除流程事件，当前任务执行了{}", processDeleteEvent.toString());
        DcCustomerRefund entity = baseMapper.selectById(Long.valueOf(processDeleteEvent.getBusinessId()));
        if (entity == null) {
            return;
        }
        baseMapper.deleteById(entity.getId());
    }
}
