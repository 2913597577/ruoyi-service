package org.dromara.caseDetail.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.caseDetail.domain.DcCaseTracking;
import org.dromara.caseDetail.domain.DcDebtCase;
import org.dromara.caseDetail.domain.bo.DcCaseTrackingBo;
import org.dromara.caseDetail.domain.vo.DcCaseTrackingVo;
import org.dromara.caseDetail.mapper.DcCaseTrackingMapper;
import org.dromara.caseDetail.service.IDcCaseTrackingService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 案件进展表Service业务层处理
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcCaseTrackingServiceImpl implements IDcCaseTrackingService {

    private final DcCaseTrackingMapper baseMapper;

    /**
     * 查询案件进展表
     *
     * @param id 主键
     * @return 案件进展表
     */
    @Override
    public DcCaseTrackingVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询案件进展表列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 案件进展表分页列表
     */
    @Override
    public TableDataInfo<DcCaseTrackingVo> queryPageList(DcCaseTrackingBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCaseTracking> lqw = buildQueryWrapper(bo);
        Page<DcCaseTrackingVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的案件进展表列表
     *
     * @param bo 查询条件
     * @return 案件进展表列表
     */
    @Override
    public List<DcCaseTrackingVo> queryList(DcCaseTrackingBo bo) {
        LambdaQueryWrapper<DcCaseTracking> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCaseTracking> buildQueryWrapper(DcCaseTrackingBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCaseTracking> lqw = Wrappers.lambdaQuery();
        lqw.orderByDesc(DcCaseTracking::getCreateTime);
        lqw.eq(bo.getCaseId() != null, DcCaseTracking::getCaseId, bo.getCaseId());
        lqw.eq(StringUtils.isNotBlank(bo.getCaseType()), DcCaseTracking::getCaseType, bo.getCaseType());
        lqw.eq(bo.getCustomerId() != null, DcCaseTracking::getCustomerId, bo.getCustomerId());
        lqw.like(bo.getCustomerName() != null, DcCaseTracking::getCustomerName, bo.getCustomerName());
        lqw.eq(bo.getLegalSupportId() != null, DcCaseTracking::getLegalSupportId, bo.getLegalSupportId());
        lqw.like(bo.getLegalSupportName() != null, DcCaseTracking::getLegalSupportName, bo.getLegalSupportName());
        lqw.eq(StringUtils.isNotBlank(bo.getCaseProgress()), DcCaseTracking::getCaseProgress, bo.getCaseProgress());
        lqw.eq(bo.getTrackingTime() != null, DcCaseTracking::getTrackingTime, bo.getTrackingTime());
        lqw.eq(bo.getNextTrackingTime() != null, DcCaseTracking::getNextTrackingTime, bo.getNextTrackingTime());
        lqw.in(bo.getCustomerIds() != null && !bo.getCustomerIds().isEmpty(), DcCaseTracking::getCustomerId, bo.getCustomerIds());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark1()), DcCaseTracking::getRemark1, bo.getRemark1());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark2()), DcCaseTracking::getRemark2, bo.getRemark2());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark3()), DcCaseTracking::getRemark3, bo.getRemark3());
        return lqw;
    }

    /**
     * 新增案件进展表
     *
     * @param bo 案件进展表
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcCaseTrackingBo bo) {
        DcCaseTracking add = MapstructUtils.convert(bo, DcCaseTracking.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改案件进展表
     *
     * @param bo 案件进展表
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcCaseTrackingBo bo) {
        DcCaseTracking update = MapstructUtils.convert(bo, DcCaseTracking.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCaseTracking entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除案件进展表信息
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
