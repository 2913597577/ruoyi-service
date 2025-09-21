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
import org.dromara.customer.domain.bo.DcCustomerIntentionBo;
import org.dromara.customer.domain.vo.DcCustomerIntentionVo;
import org.dromara.customer.domain.DcCustomerIntention;
import org.dromara.customer.mapper.DcCustomerIntentionMapper;
import org.dromara.customer.service.IDcCustomerIntentionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 客户意向登记Service业务层处理
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcCustomerIntentionServiceImpl implements IDcCustomerIntentionService {

    private final DcCustomerIntentionMapper baseMapper;

    /**
     * 查询客户意向登记
     *
     * @param id 主键
     * @return 客户意向登记
     */
    @Override
    public DcCustomerIntentionVo queryById(Long id){
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
    public TableDataInfo<DcCustomerIntentionVo> queryPageList(DcCustomerIntentionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCustomerIntention> lqw = buildQueryWrapper(bo);
        Page<DcCustomerIntentionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的客户意向登记列表
     *
     * @param bo 查询条件
     * @return 客户意向登记列表
     */
    @Override
    public List<DcCustomerIntentionVo> queryList(DcCustomerIntentionBo bo) {
        LambdaQueryWrapper<DcCustomerIntention> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomerIntention> buildQueryWrapper(DcCustomerIntentionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCustomerIntention> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcCustomerIntention::getId);
        lqw.eq(bo.getSubmissionDate() != null, DcCustomerIntention::getSubmissionDate, bo.getSubmissionDate());
        lqw.like(StringUtils.isNotBlank(bo.getLegalSupport()), DcCustomerIntention::getLegalSupport, bo.getLegalSupport());
        lqw.eq(bo.getLegalSupportId() != null, DcCustomerIntention::getLegalSupportId, bo.getLegalSupportId());
        lqw.like(StringUtils.isNotBlank(bo.getIntendedCustomer()), DcCustomerIntention::getIntendedCustomer, bo.getIntendedCustomer());
        lqw.eq(bo.getIntendedCustomerId() != null, DcCustomerIntention::getIntendedCustomerId, bo.getIntendedCustomerId());
        lqw.eq(bo.getType() != null, DcCustomerIntention::getType, bo.getType());
        lqw.like(StringUtils.isNotBlank(bo.getSource()), DcCustomerIntention::getSource, bo.getSource());
        lqw.eq(StringUtils.isNotBlank(bo.getIntroducer()), DcCustomerIntention::getIntroducer, bo.getIntroducer());
        lqw.like(bo.getFollowUpResult() != null, DcCustomerIntention::getFollowUpResult, bo.getFollowUpResult());
        return lqw;
    }

    /**
     * 新增客户意向登记
     *
     * @param bo 客户意向登记
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcCustomerIntentionBo bo) {
        DcCustomerIntention add = MapstructUtils.convert(bo, DcCustomerIntention.class);
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
    public Boolean updateByBo(DcCustomerIntentionBo bo) {
        DcCustomerIntention update = MapstructUtils.convert(bo, DcCustomerIntention.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCustomerIntention entity){
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
