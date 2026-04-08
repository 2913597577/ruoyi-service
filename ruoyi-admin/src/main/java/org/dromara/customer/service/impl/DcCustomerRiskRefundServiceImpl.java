package org.dromara.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.customer.domain.DcCustomerRiskRefund;
import org.dromara.customer.domain.bo.DcCustomerRiskRefundBo;
import org.dromara.customer.domain.vo.DcCustomerRiskRefundVo;
import org.dromara.customer.mapper.DcCustomerRiskRefundMapper;
import org.dromara.customer.service.IDcCustomerRiskRefundService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 客户风险/退费Service业务层处理
 *
 * @author Lion Li
 * @date 2025-09-19
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcCustomerRiskRefundServiceImpl implements IDcCustomerRiskRefundService {

    private final DcCustomerRiskRefundMapper baseMapper;

    /**
     * 查询客户风险/退费
     *
     * @param id 主键
     * @return 客户风险/退费
     */
    @Override
    public DcCustomerRiskRefundVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询客户风险/退费列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户风险/退费分页列表
     */
    @Override
    public TableDataInfo<DcCustomerRiskRefundVo> queryPageList(DcCustomerRiskRefundBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCustomerRiskRefund> lqw = buildQueryWrapper(bo);
        Page<DcCustomerRiskRefundVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的客户风险/退费列表
     *
     * @param bo 查询条件
     * @return 客户风险/退费列表
     */
    @Override
    public List<DcCustomerRiskRefundVo> queryList(DcCustomerRiskRefundBo bo) {
        LambdaQueryWrapper<DcCustomerRiskRefund> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomerRiskRefund> buildQueryWrapper(DcCustomerRiskRefundBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCustomerRiskRefund> lqw = Wrappers.lambdaQuery();
        lqw.orderByDesc(DcCustomerRiskRefund::getId);
        lqw.eq(bo.getLawyerId() != null, DcCustomerRiskRefund::getLawyerId, bo.getLawyerId());
        lqw.eq(bo.getCustomerId() != null, DcCustomerRiskRefund::getCustomerId, bo.getCustomerId());
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), DcCustomerRiskRefund::getCustomerName, bo.getCustomerName());
        lqw.eq(StringUtils.isNotBlank(bo.getPrincipal()), DcCustomerRiskRefund::getPrincipal, bo.getPrincipal());
        lqw.eq(StringUtils.isNotBlank(bo.getPrincipalPhone()), DcCustomerRiskRefund::getPrincipalPhone, bo.getPrincipalPhone());
        lqw.eq(bo.getInviterId() != null, DcCustomerRiskRefund::getInviterId, bo.getInviterId());
        lqw.eq(bo.getSignDate() != null, DcCustomerRiskRefund::getSignDate, bo.getSignDate());
        lqw.le(bo.getExpireDate() != null, DcCustomerRiskRefund::getExpireDate, bo.getExpireDate());
        lqw.eq(bo.getContractAmount() != null, DcCustomerRiskRefund::getContractAmount, bo.getContractAmount());
        lqw.like(StringUtils.isNotBlank(bo.getServiceHours()), DcCustomerRiskRefund::getServiceHours, bo.getServiceHours());
        lqw.eq(bo.getCustomerType() != null, DcCustomerRiskRefund::getCustomerType, bo.getCustomerType());
        lqw.eq(StringUtils.isNotBlank(bo.getReasons()), DcCustomerRiskRefund::getReasons, bo.getReasons());
        lqw.eq(bo.getRefundAmount() != null, DcCustomerRiskRefund::getRefundAmount, bo.getRefundAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark1()), DcCustomerRiskRefund::getRemark1, bo.getRemark1());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark2()), DcCustomerRiskRefund::getRemark2, bo.getRemark2());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark3()), DcCustomerRiskRefund::getRemark3, bo.getRemark3());
        return lqw;
    }

    /**
     * 新增客户风险/退费
     *
     * @param bo 客户风险/退费
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcCustomerRiskRefundBo bo) {
        DcCustomerRiskRefund add = MapstructUtils.convert(bo, DcCustomerRiskRefund.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改客户风险/退费
     *
     * @param bo 客户风险/退费
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcCustomerRiskRefundBo bo) {
        DcCustomerRiskRefund update = MapstructUtils.convert(bo, DcCustomerRiskRefund.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCustomerRiskRefund entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除客户风险/退费信息
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
