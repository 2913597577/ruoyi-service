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
import org.dromara.legalSupport.domain.DcCustomerOutVisit;
import org.dromara.legalSupport.domain.bo.DcCustomerOutVisitBo;
import org.dromara.legalSupport.domain.vo.DcCustomerOutVisitVo;
import org.dromara.legalSupport.mapper.DcCustomerOutVisitMapper;
import org.dromara.legalSupport.service.IDcCustomerOutVisitService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 客户出访记录Service业务层处理
 *
 * @author Lion Li
 * @date 2025-10-16
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcCustomerOutVisitServiceImpl implements IDcCustomerOutVisitService {

    private final DcCustomerOutVisitMapper baseMapper;

    /**
     * 查询客户出访记录
     *
     * @param id 主键
     * @return 客户出访记录
     */
    @Override
    public DcCustomerOutVisitVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询客户出访记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户出访记录分页列表
     */
    @Override
    public TableDataInfo<DcCustomerOutVisitVo> queryPageList(DcCustomerOutVisitBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCustomerOutVisit> lqw = buildQueryWrapper(bo);
        Page<DcCustomerOutVisitVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的客户出访记录列表
     *
     * @param bo 查询条件
     * @return 客户出访记录列表
     */
    @Override
    public List<DcCustomerOutVisitVo> queryList(DcCustomerOutVisitBo bo) {
        LambdaQueryWrapper<DcCustomerOutVisit> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomerOutVisit> buildQueryWrapper(DcCustomerOutVisitBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCustomerOutVisit> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcCustomerOutVisit::getId);
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), DcCustomerOutVisit::getCustomerName, bo.getCustomerName());
        lqw.like(StringUtils.isNotBlank(bo.getLegalSupportName()), DcCustomerOutVisit::getLegalSupportName, bo.getLegalSupportName());
        lqw.eq(bo.getVisitTime() != null, DcCustomerOutVisit::getVisitTime, bo.getVisitTime());
        lqw.eq(bo.getNextVisitTime() != null, DcCustomerOutVisit::getNextVisitTime, bo.getNextVisitTime());
        lqw.eq(bo.getIsFirstVisit() != null, DcCustomerOutVisit::getIsFirstVisit, bo.getIsFirstVisit());
        lqw.eq(bo.getIsOutCount() != null, DcCustomerOutVisit::getIsOutCount, bo.getIsOutCount());
        lqw.eq(StringUtils.isNotBlank(bo.getVisitAddress()), DcCustomerOutVisit::getVisitAddress, bo.getVisitAddress());
        return lqw;
    }

    /**
     * 新增客户出访记录
     *
     * @param bo 客户出访记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcCustomerOutVisitBo bo) {
        DcCustomerOutVisit add = MapstructUtils.convert(bo, DcCustomerOutVisit.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改客户出访记录
     *
     * @param bo 客户出访记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcCustomerOutVisitBo bo) {
        DcCustomerOutVisit update = MapstructUtils.convert(bo, DcCustomerOutVisit.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCustomerOutVisit entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除客户出访记录信息
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
