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
import org.dromara.workflow.domain.TestApprove;
import org.dromara.workflow.domain.bo.TestApproveBo;
import org.dromara.workflow.domain.vo.TestApproveVo;
import org.dromara.workflow.mapper.TestApproveMapper;
import org.dromara.workflow.service.ITestApproveService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 审批申请Service业务层处理
 *
 * @author may
 * @date 2025-11-01
 */
@ConditionalOnEnable
@RequiredArgsConstructor
@Service
@Slf4j
public class TestApproveServiceImpl implements ITestApproveService {

    private final TestApproveMapper baseMapper;
    private final WorkflowService workflowService;

    /**
     * 查询审批申请
     */
    @Override
    public TestApproveVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询审批申请列表
     */
    @Override
    public TableDataInfo<TestApproveVo> queryPageList(TestApproveBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TestApprove> lqw = buildQueryWrapper(bo);
        Page<TestApproveVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询审批申请列表
     */
    @Override
    public List<TestApproveVo> queryList(TestApproveBo bo) {
        LambdaQueryWrapper<TestApprove> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TestApprove> buildQueryWrapper(TestApproveBo bo) {
        LambdaQueryWrapper<TestApprove> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getApplyType()), TestApprove::getApplyType, bo.getApplyType());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增审批申请
     */
    @Override
    public TestApproveVo insertByBo(TestApproveBo bo) {
        TestApprove add = MapstructUtils.convert(bo, TestApprove.class);
        if (StringUtils.isBlank(add.getStatus())) {
            add.setStatus(BusinessStatusEnum.DRAFT.getStatus());
        }
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return MapstructUtils.convert(add, TestApproveVo.class);
    }

    /**
     * 修改审批申请
     */
    @Override
    public TestApproveVo updateByBo(TestApproveBo bo) {
        TestApprove update = MapstructUtils.convert(bo, TestApprove.class);
        baseMapper.updateById(update);
        return MapstructUtils.convert(update, TestApproveVo.class);
    }

    /**
     * 批量删除审批申请
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(List<Long> ids) {
        workflowService.deleteInstance(ids);
        return baseMapper.deleteByIds(ids) > 0;
    }

    /**
     * 总体流程监听(例如: 草稿，撤销，退回，作废，终止，已完成，单任务完成等)
     * 正常使用只需#processEvent.flowCode=='approve1'
     * 示例为了方便则使用startsWith匹配了全部示例key
     *
     * @param processEvent 参数
     */
    @EventListener(condition = "#processEvent.flowCode.startsWith('approve')")
    public void processHandler(ProcessEvent processEvent) {
        log.info("当前任务执行了{}", processEvent.toString());
        TestApprove testApprove = baseMapper.selectById(Long.valueOf(processEvent.getBusinessId()));
        testApprove.setStatus(processEvent.getStatus());
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
            testApprove.setStatus(BusinessStatusEnum.WAITING.getStatus());
        }
        baseMapper.updateById(testApprove);
    }

    /**
     * 执行任务创建监听
     * 示例：也可通过  @EventListener(condition = "#processTaskEvent.flowCode=='approve1'")进行判断
     * 在方法中判断流程节点key
     * if ("xxx".equals(processTaskEvent.getNodeCode())) {
     * //执行业务逻辑
     * }
     *
     * @param processTaskEvent 参数
     */
    @EventListener(condition = "#processTaskEvent.flowCode.startsWith('approve')")
    public void processTaskHandler(ProcessTaskEvent processTaskEvent) {
        log.info("当前任务创建了{}", processTaskEvent.toString());
    }

    /**
     * 监听删除流程事件
     * 正常使用只需#processDeleteEvent.flowCode=='approve1'
     * 示例为了方便则使用startsWith匹配了全部示例key
     *
     * @param processDeleteEvent 参数
     */
    @EventListener(condition = "#processDeleteEvent.flowCode.startsWith('approve')")
    public void processDeleteHandler(ProcessDeleteEvent processDeleteEvent) {
        log.info("监听删除流程事件，当前任务执行了{}", processDeleteEvent.toString());
        TestApprove testApprove = baseMapper.selectById(Long.valueOf(processDeleteEvent.getBusinessId()));
        if (testApprove == null) {
            return;
        }
        baseMapper.deleteById(testApprove.getId());
    }
}
