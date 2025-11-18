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
import org.dromara.workflow.domain.DcHighRiskCustomer;
import org.dromara.workflow.domain.bo.DcHighRiskCustomerBo;
import org.dromara.workflow.domain.vo.DcHighRiskCustomerVo;
import org.dromara.workflow.mapper.DcHighRiskCustomerMapper;
import org.dromara.workflow.service.IDcHighRiskCustomerService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 高风险客户记录Service业务层处理
 */
@ConditionalOnEnable
@RequiredArgsConstructor
@Service
@Slf4j
public class DcHighRiskCustomerServiceImpl implements IDcHighRiskCustomerService {

    private final DcHighRiskCustomerMapper baseMapper;
    private final WorkflowService workflowService;

    /**
     * 查询高风险客户记录
     */
    @Override
    public DcHighRiskCustomerVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询高风险客户记录列表
     */
    @Override
    public TableDataInfo<DcHighRiskCustomerVo> queryPageList(DcHighRiskCustomerBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcHighRiskCustomer> lqw = buildQueryWrapper(bo);
        Page<DcHighRiskCustomerVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询高风险客户记录列表
     */
    @Override
    public List<DcHighRiskCustomerVo> queryList(DcHighRiskCustomerBo bo) {
        LambdaQueryWrapper<DcHighRiskCustomer> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcHighRiskCustomer> buildQueryWrapper(DcHighRiskCustomerBo bo) {
        LambdaQueryWrapper<DcHighRiskCustomer> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCustomerId() != null, DcHighRiskCustomer::getCustomerId, bo.getCustomerId());
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), DcHighRiskCustomer::getCustomerName, bo.getCustomerName());
        lqw.eq(StringUtils.isNotBlank(bo.getRiskDetermination()), DcHighRiskCustomer::getRiskDetermination, bo.getRiskDetermination());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增高风险客户记录
     */
    @Override
    public DcHighRiskCustomerVo insertByBo(DcHighRiskCustomerBo bo) {
        DcHighRiskCustomer add = MapstructUtils.convert(bo, DcHighRiskCustomer.class);
        if (StringUtils.isBlank(add.getStatus())) {
            add.setStatus(BusinessStatusEnum.DRAFT.getStatus());
        }
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return MapstructUtils.convert(add, DcHighRiskCustomerVo.class);
    }

    /**
     * 修改高风险客户记录
     */
    @Override
    public DcHighRiskCustomerVo updateByBo(DcHighRiskCustomerBo bo) {
        DcHighRiskCustomer update = MapstructUtils.convert(bo, DcHighRiskCustomer.class);
        baseMapper.updateById(update);
        return MapstructUtils.convert(update, DcHighRiskCustomerVo.class);
    }

    /**
     * 批量删除高风险客户记录
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(List<Long> ids) {
        workflowService.deleteInstance(ids);
        return baseMapper.deleteByIds(ids) > 0;
    }

    /**
     * 总体流程监听(例如: 草稿，撤销，退回，作废，终止，已完成，单任务完成等)
     * 正常使用只需#processEvent.flowCode=='highRiskCustomer'
     * 示例为了方便则使用startsWith匹配了全部示例key
     *
     * @param processEvent 参数
     */
    @EventListener(condition = "#processEvent.flowCode.startsWith('highRiskCustomer')")
    public void processHandler(ProcessEvent processEvent) {
        log.info("当前任务执行了{}", processEvent.toString());
        DcHighRiskCustomer dcHighRiskCustomer = baseMapper.selectById(Long.valueOf(processEvent.getBusinessId()));
        dcHighRiskCustomer.setStatus(processEvent.getStatus());
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
            dcHighRiskCustomer.setStatus(BusinessStatusEnum.WAITING.getStatus());
        }
        baseMapper.updateById(dcHighRiskCustomer);
    }

    /**
     * 执行任务创建监听
     * 示例：也可通过  @EventListener(condition = "#processTaskEvent.flowCode=='highRiskCustomer'")进行判断
     * 在方法中判断流程节点key
     * if ("xxx".equals(processTaskEvent.getNodeCode())) {
     * //执行业务逻辑
     * }
     *
     * @param processTaskEvent 参数
     */
    @EventListener(condition = "#processTaskEvent.flowCode.startsWith('highRiskCustomer')")
    public void processTaskHandler(ProcessTaskEvent processTaskEvent) {
        log.info("当前任务创建了{}", processTaskEvent.toString());
    }

    /**
     * 监听删除流程事件
     * 正常使用只需#processDeleteEvent.flowCode=='highRiskCustomer'
     * 示例为了方便则使用startsWith匹配了全部示例key
     *
     * @param processDeleteEvent 参数
     */
    @EventListener(condition = "#processDeleteEvent.flowCode.startsWith('highRiskCustomer')")
    public void processDeleteHandler(ProcessDeleteEvent processDeleteEvent) {
        log.info("监听删除流程事件，当前任务执行了{}", processDeleteEvent.toString());
        DcHighRiskCustomer dcHighRiskCustomer = baseMapper.selectById(Long.valueOf(processDeleteEvent.getBusinessId()));
        if (dcHighRiskCustomer == null) {
            return;
        }
        baseMapper.deleteById(dcHighRiskCustomer.getId());
    }
}
