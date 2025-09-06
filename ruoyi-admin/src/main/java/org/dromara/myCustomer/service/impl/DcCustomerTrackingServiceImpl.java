package org.dromara.myCustomer.service.impl;

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
import org.dromara.myCustomer.domain.bo.DcCustomerTrackingBo;
import org.dromara.myCustomer.domain.vo.DcCustomerTrackingVo;
import org.dromara.myCustomer.domain.DcCustomerTracking;
import org.dromara.myCustomer.mapper.DcCustomerTrackingMapper;
import org.dromara.myCustomer.service.IDcCustomerTrackingService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 客户跟踪Service业务层处理
 *
 * @author Lion Li
 * @date 2025-09-06
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcCustomerTrackingServiceImpl implements IDcCustomerTrackingService {

    private final DcCustomerTrackingMapper baseMapper;

    /**
     * 查询客户跟踪
     *
     * @param id 主键
     * @return 客户跟踪
     */
    @Override
    public DcCustomerTrackingVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询客户跟踪列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户跟踪分页列表
     */
    @Override
    public TableDataInfo<DcCustomerTrackingVo> queryPageList(DcCustomerTrackingBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCustomerTracking> lqw = buildQueryWrapper(bo);
        Page<DcCustomerTrackingVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的客户跟踪列表
     *
     * @param bo 查询条件
     * @return 客户跟踪列表
     */
    @Override
    public List<DcCustomerTrackingVo> queryList(DcCustomerTrackingBo bo) {
        LambdaQueryWrapper<DcCustomerTracking> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomerTracking> buildQueryWrapper(DcCustomerTrackingBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCustomerTracking> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcCustomerTracking::getId);
        lqw.eq(bo.getCustomerId() != null, DcCustomerTracking::getCustomerId, bo.getCustomerId());
        lqw.eq(StringUtils.isNotBlank(bo.getCustomerRemark()), DcCustomerTracking::getCustomerRemark, bo.getCustomerRemark());
        lqw.eq(bo.getCumtomerStatus() != null, DcCustomerTracking::getCumtomerStatus, bo.getCumtomerStatus());
        lqw.eq(bo.getTrackingTime() != null, DcCustomerTracking::getTrackingTime, bo.getTrackingTime());
        lqw.eq(bo.getNextTime() != null, DcCustomerTracking::getNextTime, bo.getNextTime());
        return lqw;
    }

    /**
     * 新增客户跟踪
     *
     * @param bo 客户跟踪
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcCustomerTrackingBo bo) {
        DcCustomerTracking add = MapstructUtils.convert(bo, DcCustomerTracking.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改客户跟踪
     *
     * @param bo 客户跟踪
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcCustomerTrackingBo bo) {
        DcCustomerTracking update = MapstructUtils.convert(bo, DcCustomerTracking.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCustomerTracking entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除客户跟踪信息
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
