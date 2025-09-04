package org.dromara.riskrefund.service.impl;

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
import org.dromara.riskrefund.domain.bo.DcRiskRefundBo;
import org.dromara.riskrefund.domain.vo.DcRiskRefundVo;
import org.dromara.riskrefund.domain.DcRiskRefund;
import org.dromara.riskrefund.mapper.DcRiskRefundMapper;
import org.dromara.riskrefund.service.IDcRiskRefundService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 风险退费客户Service业务层处理
 *
 * @author Lion Li
 * @date 2025-07-09
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcRiskRefundServiceImpl implements IDcRiskRefundService {

    private final DcRiskRefundMapper baseMapper;

    /**
     * 查询风险退费客户
     *
     * @param id 主键
     * @return 风险退费客户
     */
    @Override
    public DcRiskRefundVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询风险退费客户列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 风险退费客户分页列表
     */
    @Override
    public TableDataInfo<DcRiskRefundVo> queryPageList(DcRiskRefundBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcRiskRefund> lqw = buildQueryWrapper(bo);
        Page<DcRiskRefundVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的风险退费客户列表
     *
     * @param bo 查询条件
     * @return 风险退费客户列表
     */
    @Override
    public List<DcRiskRefundVo> queryList(DcRiskRefundBo bo) {
        LambdaQueryWrapper<DcRiskRefund> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcRiskRefund> buildQueryWrapper(DcRiskRefundBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcRiskRefund> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcRiskRefund::getId);
        lqw.eq(bo.getCustomerId() != null, DcRiskRefund::getCustomerId, bo.getCustomerId());
        lqw.eq(bo.getRefundAmount() != null, DcRiskRefund::getRefundAmount, bo.getRefundAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getRefundReason()), DcRiskRefund::getRefundReason, bo.getRefundReason());
        lqw.eq(bo.getRefundTime() != null, DcRiskRefund::getRefundTime, bo.getRefundTime());
        lqw.eq(bo.getApproveBy() != null, DcRiskRefund::getApproveBy, bo.getApproveBy());
        return lqw;
    }

    /**
     * 新增风险退费客户
     *
     * @param bo 风险退费客户
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcRiskRefundBo bo) {
        DcRiskRefund add = MapstructUtils.convert(bo, DcRiskRefund.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改风险退费客户
     *
     * @param bo 风险退费客户
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcRiskRefundBo bo) {
        DcRiskRefund update = MapstructUtils.convert(bo, DcRiskRefund.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcRiskRefund entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除风险退费客户信息
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
