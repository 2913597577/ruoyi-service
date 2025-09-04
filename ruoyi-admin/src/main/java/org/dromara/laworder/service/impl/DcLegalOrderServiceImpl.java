package org.dromara.laworder.service.impl;

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
import org.dromara.laworder.domain.bo.DcLegalOrderBo;
import org.dromara.laworder.domain.vo.DcLegalOrderVo;
import org.dromara.laworder.domain.DcLegalOrder;
import org.dromara.laworder.mapper.DcLegalOrderMapper;
import org.dromara.laworder.service.IDcLegalOrderService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 法务接单记录Service业务层处理
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcLegalOrderServiceImpl implements IDcLegalOrderService {

    private final DcLegalOrderMapper baseMapper;

    /**
     * 查询法务接单记录
     *
     * @param id 主键
     * @return 法务接单记录
     */
    @Override
    public DcLegalOrderVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询法务接单记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 法务接单记录分页列表
     */
    @Override
    public TableDataInfo<DcLegalOrderVo> queryPageList(DcLegalOrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcLegalOrder> lqw = buildQueryWrapper(bo);
        Page<DcLegalOrderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的法务接单记录列表
     *
     * @param bo 查询条件
     * @return 法务接单记录列表
     */
    @Override
    public List<DcLegalOrderVo> queryList(DcLegalOrderBo bo) {
        LambdaQueryWrapper<DcLegalOrder> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcLegalOrder> buildQueryWrapper(DcLegalOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcLegalOrder> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcLegalOrder::getId);
        lqw.eq(bo.getCustomerId() != null, DcLegalOrder::getCustomerId, bo.getCustomerId());
        lqw.eq(bo.getLegalSupportId() != null, DcLegalOrder::getLegalSupportId, bo.getLegalSupportId());
        lqw.eq(bo.getAcceptTime() != null, DcLegalOrder::getAcceptTime, bo.getAcceptTime());
        lqw.eq(bo.getDeadline() != null, DcLegalOrder::getDeadline, bo.getDeadline());
        lqw.eq(bo.getCompleteTime() != null, DcLegalOrder::getCompleteTime, bo.getCompleteTime());
        lqw.eq(bo.getServiceDuration() != null, DcLegalOrder::getServiceDuration, bo.getServiceDuration());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderStatus()), DcLegalOrder::getOrderStatus, bo.getOrderStatus());
        return lqw;
    }

    /**
     * 新增法务接单记录
     *
     * @param bo 法务接单记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcLegalOrderBo bo) {
        DcLegalOrder add = MapstructUtils.convert(bo, DcLegalOrder.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改法务接单记录
     *
     * @param bo 法务接单记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcLegalOrderBo bo) {
        DcLegalOrder update = MapstructUtils.convert(bo, DcLegalOrder.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcLegalOrder entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除法务接单记录信息
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
