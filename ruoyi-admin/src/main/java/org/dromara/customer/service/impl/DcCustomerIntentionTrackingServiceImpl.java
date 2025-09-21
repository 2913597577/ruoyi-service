package org.dromara.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.customer.domain.DcCustomerIntentionTracking;
import org.dromara.customer.domain.bo.DcCustomerIntentionTrackingBo;
import org.dromara.customer.domain.vo.DcCustomerIntentionTrackingVo;
import org.dromara.customer.mapper.DcCustomerIntentionTrackingMapper;
import org.dromara.customer.service.IDcCustomerIntentionTrackingService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 意向客户跟踪记录Service业务层处理
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcCustomerIntentionTrackingServiceImpl implements IDcCustomerIntentionTrackingService {

    private final DcCustomerIntentionTrackingMapper baseMapper;

    /**
     * 查询意向客户跟踪记录
     *
     * @param id 主键
     * @return 意向客户跟踪记录
     */
    @Override
    public DcCustomerIntentionTrackingVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询意向客户跟踪记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 意向客户跟踪记录分页列表
     */
    @Override
    public TableDataInfo<DcCustomerIntentionTrackingVo> queryPageList(DcCustomerIntentionTrackingBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCustomerIntentionTracking> lqw = buildQueryWrapper(bo);
        Page<DcCustomerIntentionTrackingVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的意向客户跟踪记录列表
     *
     * @param bo 查询条件
     * @return 意向客户跟踪记录列表
     */
    @Override
    public List<DcCustomerIntentionTrackingVo> queryList(DcCustomerIntentionTrackingBo bo) {
        LambdaQueryWrapper<DcCustomerIntentionTracking> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomerIntentionTracking> buildQueryWrapper(DcCustomerIntentionTrackingBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCustomerIntentionTracking> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcCustomerIntentionTracking::getId);
        lqw.eq(bo.getInentionId() != null, DcCustomerIntentionTracking::getInentionId, bo.getInentionId());
        lqw.eq(bo.getCustomerId() != null, DcCustomerIntentionTracking::getCustomerId, bo.getCustomerId());
        lqw.like(bo.getCustomerName() != null, DcCustomerIntentionTracking::getCustomerName, bo.getCustomerName());
        lqw.eq(bo.getCreateTime() != null, DcCustomerIntentionTracking::getCreateTime, bo.getCreateTime());
        return lqw;
    }

    /**
     * 新增意向客户跟踪记录
     *
     * @param bo 意向客户跟踪记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcCustomerIntentionTrackingBo bo) {
        DcCustomerIntentionTracking add = MapstructUtils.convert(bo, DcCustomerIntentionTracking.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改意向客户跟踪记录
     *
     * @param bo 意向客户跟踪记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcCustomerIntentionTrackingBo bo) {
        DcCustomerIntentionTracking update = MapstructUtils.convert(bo, DcCustomerIntentionTracking.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCustomerIntentionTracking entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除意向客户跟踪记录信息
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
