package org.dromara.customer.service.impl;

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
import org.dromara.customer.domain.bo.DcIntentionCustomerBo;
import org.dromara.customer.domain.vo.DcIntentionCustomerVo;
import org.dromara.customer.domain.DcIntentionCustomer;
import org.dromara.customer.mapper.DcIntentionCustomerMapper;
import org.dromara.customer.service.IDcIntentionCustomerService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 客户意向登记Service业务层处理
 *
 * @author leo
 * @date 2025-07-08
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcIntentionCustomerServiceImpl implements IDcIntentionCustomerService {

    private final DcIntentionCustomerMapper baseMapper;

    /**
     * 查询客户意向登记
     *
     * @param id 主键
     * @return 客户意向登记
     */
    @Override
    public DcIntentionCustomerVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询客户意向登记列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户意向登记分页列表
     */
    @Override
    public TableDataInfo<DcIntentionCustomerVo> queryPageList(DcIntentionCustomerBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcIntentionCustomer> lqw = buildQueryWrapper(bo);
        Page<DcIntentionCustomerVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的客户意向登记列表
     *
     * @param bo 查询条件
     * @return 客户意向登记列表
     */
    @Override
    public List<DcIntentionCustomerVo> queryList(DcIntentionCustomerBo bo) {
        LambdaQueryWrapper<DcIntentionCustomer> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcIntentionCustomer> buildQueryWrapper(DcIntentionCustomerBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcIntentionCustomer> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcIntentionCustomer::getId);
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), DcIntentionCustomer::getCustomerName, bo.getCustomerName());
        lqw.eq(StringUtils.isNotBlank(bo.getContactPhone()), DcIntentionCustomer::getContactPhone, bo.getContactPhone());
        lqw.eq(StringUtils.isNotBlank(bo.getIntentionType()), DcIntentionCustomer::getIntentionType, bo.getIntentionType());
        lqw.eq(bo.getExpectedAmount() != null, DcIntentionCustomer::getExpectedAmount, bo.getExpectedAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getCity()), DcIntentionCustomer::getCity, bo.getCity());
        lqw.eq(StringUtils.isNotBlank(bo.getReferralSource()), DcIntentionCustomer::getReferralSource, bo.getReferralSource());
        lqw.eq(bo.getFlowInstanceId() != null, DcIntentionCustomer::getFlowInstanceId, bo.getFlowInstanceId());
        lqw.eq(bo.getCreateBy() != null, DcIntentionCustomer::getCreateBy, bo.getCreateBy());
        return lqw;
    }

    /**
     * 新增客户意向登记
     *
     * @param bo 客户意向登记
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcIntentionCustomerBo bo) {
        DcIntentionCustomer add = MapstructUtils.convert(bo, DcIntentionCustomer.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改客户意向登记
     *
     * @param bo 客户意向登记
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcIntentionCustomerBo bo) {
        DcIntentionCustomer update = MapstructUtils.convert(bo, DcIntentionCustomer.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcIntentionCustomer entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除客户意向登记信息
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
