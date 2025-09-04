package org.dromara.customerflow.service.impl;

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
import org.dromara.customerflow.domain.bo.DcCustomerFlowBo;
import org.dromara.customerflow.domain.vo.DcCustomerFlowVo;
import org.dromara.customerflow.domain.DcCustomerFlow;
import org.dromara.customerflow.mapper.DcCustomerFlowMapper;
import org.dromara.customerflow.service.IDcCustomerFlowService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 待提交流转单Service业务层处理
 *
 * @author leo
 * @date 2025-07-08
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcCustomerFlowServiceImpl implements IDcCustomerFlowService {

    private final DcCustomerFlowMapper baseMapper;

    /**
     * 查询待提交流转单
     *
     * @param id 主键
     * @return 待提交流转单
     */
    @Override
    public DcCustomerFlowVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询待提交流转单列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 待提交流转单分页列表
     */
    @Override
    public TableDataInfo<DcCustomerFlowVo> queryPageList(DcCustomerFlowBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCustomerFlow> lqw = buildQueryWrapper(bo);
        Page<DcCustomerFlowVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的待提交流转单列表
     *
     * @param bo 查询条件
     * @return 待提交流转单列表
     */
    @Override
    public List<DcCustomerFlowVo> queryList(DcCustomerFlowBo bo) {
        LambdaQueryWrapper<DcCustomerFlow> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomerFlow> buildQueryWrapper(DcCustomerFlowBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCustomerFlow> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcCustomerFlow::getId);
        lqw.eq(bo.getCustomerId() != null, DcCustomerFlow::getCustomerId, bo.getCustomerId());
        lqw.eq(bo.getIntentionId() != null, DcCustomerFlow::getIntentionId, bo.getIntentionId());
        lqw.eq(bo.getFlowInstanceId() != null, DcCustomerFlow::getFlowInstanceId, bo.getFlowInstanceId());
        lqw.eq(StringUtils.isNotBlank(bo.getFlowStatus()), DcCustomerFlow::getFlowStatus, bo.getFlowStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getFlowType()), DcCustomerFlow::getFlowType, bo.getFlowType());
        lqw.eq(bo.getSubmitBy() != null, DcCustomerFlow::getSubmitBy, bo.getSubmitBy());
        lqw.eq(bo.getSubmitTime() != null, DcCustomerFlow::getSubmitTime, bo.getSubmitTime());
        lqw.eq(bo.getConfirmBy() != null, DcCustomerFlow::getConfirmBy, bo.getConfirmBy());
        lqw.eq(bo.getConfirmTime() != null, DcCustomerFlow::getConfirmTime, bo.getConfirmTime());
        lqw.eq(bo.getArchiveBy() != null, DcCustomerFlow::getArchiveBy, bo.getArchiveBy());
        lqw.eq(bo.getArchiveTime() != null, DcCustomerFlow::getArchiveTime, bo.getArchiveTime());
        return lqw;
    }

    /**
     * 新增待提交流转单
     *
     * @param bo 待提交流转单
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcCustomerFlowBo bo) {
        DcCustomerFlow add = MapstructUtils.convert(bo, DcCustomerFlow.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改待提交流转单
     *
     * @param bo 待提交流转单
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcCustomerFlowBo bo) {
        DcCustomerFlow update = MapstructUtils.convert(bo, DcCustomerFlow.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCustomerFlow entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除待提交流转单信息
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
