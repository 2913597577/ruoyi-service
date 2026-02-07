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
import org.dromara.legalSupport.domain.DcLegalSupportChangeRecord;
import org.dromara.legalSupport.domain.bo.DcLegalSupportChangeRecordBo;
import org.dromara.legalSupport.domain.vo.DcLegalSupportChangeRecordVo;
import org.dromara.legalSupport.mapper.DcLegalSupportChangeRecordMapper;
import org.dromara.legalSupport.service.IDcLegalSupportChangeRecordService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 法务支持变更Service业务层处理
 *
 * @author Lion Li
 * @date 2025-11-01
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcLegalSupportChangeRecordServiceImpl implements IDcLegalSupportChangeRecordService {

    private final DcLegalSupportChangeRecordMapper baseMapper;

    /**
     * 查询法务支持变更
     *
     * @param id 主键
     * @return 法务支持变更
     */
    @Override
    public DcLegalSupportChangeRecordVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询法务支持变更列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 法务支持变更分页列表
     */
    @Override
    public TableDataInfo<DcLegalSupportChangeRecordVo> queryPageList(DcLegalSupportChangeRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcLegalSupportChangeRecord> lqw = buildQueryWrapper(bo);
        Page<DcLegalSupportChangeRecordVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的法务支持变更列表
     *
     * @param bo 查询条件
     * @return 法务支持变更列表
     */
    @Override
    public List<DcLegalSupportChangeRecordVo> queryList(DcLegalSupportChangeRecordBo bo) {
        LambdaQueryWrapper<DcLegalSupportChangeRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcLegalSupportChangeRecord> buildQueryWrapper(DcLegalSupportChangeRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcLegalSupportChangeRecord> lqw = Wrappers.lambdaQuery();
        lqw.orderByDesc(DcLegalSupportChangeRecord::getId);
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), DcLegalSupportChangeRecord::getCustomerName, bo.getCustomerName());
        lqw.eq(bo.getCustomerId() != null, DcLegalSupportChangeRecord::getCustomerId, bo.getCustomerId());
        lqw.like(StringUtils.isNotBlank(bo.getLegalSupportName()), DcLegalSupportChangeRecord::getLegalSupportName, bo.getLegalSupportName());
        lqw.eq(bo.getLegalSupportId() != null, DcLegalSupportChangeRecord::getLegalSupportId, bo.getLegalSupportId());
        lqw.eq(bo.getCreateTime() != null, DcLegalSupportChangeRecord::getCreateTime, bo.getCreateTime());
        lqw.eq(bo.getCreateBy() != null, DcLegalSupportChangeRecord::getCreateBy, bo.getCreateBy());
        lqw.in(bo.getLegalSupportIds() != null && !bo.getLegalSupportIds().isEmpty(), DcLegalSupportChangeRecord::getLegalSupportId, bo.getLegalSupportIds());
        return lqw;
    }

    /**
     * 新增法务支持变更
     *
     * @param bo 法务支持变更
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcLegalSupportChangeRecordBo bo) {
        DcLegalSupportChangeRecord add = MapstructUtils.convert(bo, DcLegalSupportChangeRecord.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改法务支持变更
     *
     * @param bo 法务支持变更
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcLegalSupportChangeRecordBo bo) {
        DcLegalSupportChangeRecord update = MapstructUtils.convert(bo, DcLegalSupportChangeRecord.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcLegalSupportChangeRecord entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除法务支持变更信息
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
