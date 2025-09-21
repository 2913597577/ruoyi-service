package org.dromara.legalSupport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.legalSupport.domain.DcCustomerJobOrder;
import org.dromara.legalSupport.domain.bo.DcCustomerJobOrderBo;
import org.dromara.legalSupport.domain.vo.DcCustomerJobOrderVo;
import org.dromara.legalSupport.mapper.DcCustomerJobOrderMapper;
import org.dromara.legalSupport.service.IDcCustomerJobOrderService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 工单管理Service业务层处理
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcCustomerJobOrderServiceImpl implements IDcCustomerJobOrderService {

    private final DcCustomerJobOrderMapper baseMapper;

    /**
     * 查询工单管理
     *
     * @param id 主键
     * @return 工单管理
     */
    @Override
    public DcCustomerJobOrderVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询工单管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 工单管理分页列表
     */
    @Override
    public TableDataInfo<DcCustomerJobOrderVo> queryPageList(DcCustomerJobOrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCustomerJobOrder> lqw = buildQueryWrapper(bo);
        Page<DcCustomerJobOrderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的工单管理列表
     *
     * @param bo 查询条件
     * @return 工单管理列表
     */
    @Override
    public List<DcCustomerJobOrderVo> queryList(DcCustomerJobOrderBo bo) {
        LambdaQueryWrapper<DcCustomerJobOrder> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomerJobOrder> buildQueryWrapper(DcCustomerJobOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCustomerJobOrder> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcCustomerJobOrder::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getLegalSupport()), DcCustomerJobOrder::getLegalSupport, bo.getLegalSupport());
        lqw.eq(bo.getLegalSupportId() != null, DcCustomerJobOrder::getLegalSupportId, bo.getLegalSupportId());
        lqw.eq(bo.getPreContractAddress() != null, DcCustomerJobOrder::getPreContractAddress, bo.getPreContractAddress());
        lqw.like(StringUtils.isNotBlank(bo.getPreContractName()), DcCustomerJobOrder::getPreContractName, bo.getPreContractName());
        lqw.eq(bo.getNewContractAddress() != null, DcCustomerJobOrder::getNewContractAddress, bo.getNewContractAddress());
        lqw.like(StringUtils.isNotBlank(bo.getNewContractName()), DcCustomerJobOrder::getNewContractName, bo.getNewContractName());
        lqw.eq(StringUtils.isNotBlank(bo.getCustomerRequirements()), DcCustomerJobOrder::getCustomerRequirements, bo.getCustomerRequirements());
        lqw.eq(bo.getDeliveryTime() != null, DcCustomerJobOrder::getDeliveryTime, bo.getDeliveryTime());
        lqw.eq(bo.getTrackingId() != null, DcCustomerJobOrder::getTrackingId, bo.getTrackingId());
        lqw.eq(bo.getContractHandler() != null, DcCustomerJobOrder::getContractHandler, bo.getContractHandler());
        lqw.like(StringUtils.isNotBlank(bo.getContractHandlerName()), DcCustomerJobOrder::getContractHandlerName, bo.getContractHandlerName());
        lqw.eq(bo.getProcessingStatus() != null, DcCustomerJobOrder::getProcessingStatus, bo.getProcessingStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark1()), DcCustomerJobOrder::getRemark1, bo.getRemark1());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark2()), DcCustomerJobOrder::getRemark2, bo.getRemark2());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark3()), DcCustomerJobOrder::getRemark3, bo.getRemark3());
        return lqw;
    }

    /**
     * 新增工单管理
     *
     * @param bo 工单管理
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcCustomerJobOrderBo bo) {
        DcCustomerJobOrder add = MapstructUtils.convert(bo, DcCustomerJobOrder.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改工单管理
     *
     * @param bo 工单管理
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcCustomerJobOrderBo bo) {
        DcCustomerJobOrder update = MapstructUtils.convert(bo, DcCustomerJobOrder.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCustomerJobOrder entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除工单管理信息
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

    public DcCustomerJobOrderVo queryByTrackingId(Long trackingId) {
        LambdaQueryWrapper<DcCustomerJobOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(DcCustomerJobOrder::getTrackingId, trackingId);
        return baseMapper.selectVoOne(lqw);
    }
}
