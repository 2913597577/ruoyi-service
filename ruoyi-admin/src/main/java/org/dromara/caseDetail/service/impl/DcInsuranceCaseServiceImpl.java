package org.dromara.caseDetail.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.caseDetail.domain.DcInsuranceCase;
import org.dromara.caseDetail.domain.bo.DcInsuranceCaseBo;
import org.dromara.caseDetail.domain.vo.DcInsuranceCaseVo;
import org.dromara.caseDetail.mapper.DcInsuranceCaseMapper;
import org.dromara.caseDetail.service.IDcInsuranceCaseService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 保险记录表Service业务层处理
 *
 * @author Lion Li
 * @date 2025-09-29
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcInsuranceCaseServiceImpl implements IDcInsuranceCaseService {

    private final DcInsuranceCaseMapper baseMapper;

    /**
     * 查询保险记录表
     *
     * @param id 主键
     * @return 保险记录表
     */
    @Override
    public DcInsuranceCaseVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询保险记录表列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 保险记录表分页列表
     */
    @Override
    public TableDataInfo<DcInsuranceCaseVo> queryPageList(DcInsuranceCaseBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcInsuranceCase> lqw = buildQueryWrapper(bo);
        Page<DcInsuranceCaseVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的保险记录表列表
     *
     * @param bo 查询条件
     * @return 保险记录表列表
     */
    @Override
    public List<DcInsuranceCaseVo> queryList(DcInsuranceCaseBo bo) {
        LambdaQueryWrapper<DcInsuranceCase> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcInsuranceCase> buildQueryWrapper(DcInsuranceCaseBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcInsuranceCase> lqw = Wrappers.lambdaQuery();
        lqw.orderByDesc(DcInsuranceCase::getCreateTime);
        lqw.eq(bo.getCustomerId() != null, DcInsuranceCase::getCustomerId, bo.getCustomerId());
        lqw.eq(bo.getOrderDate() != null, DcInsuranceCase::getOrderDate, bo.getOrderDate());
        lqw.eq(StringUtils.isNotBlank(bo.getInsuranceNumber()), DcInsuranceCase::getInsuranceNumber, bo.getInsuranceNumber());
        lqw.eq(bo.getLegalSupportId() != null, DcInsuranceCase::getLegalSupportId, bo.getLegalSupportId());
        lqw.like(StringUtils.isNotBlank(bo.getLegalSupportName()), DcInsuranceCase::getLegalSupportName, bo.getLegalSupportName());
        lqw.eq(StringUtils.isNotBlank(bo.getPlaintiff()), DcInsuranceCase::getPlaintiff, bo.getPlaintiff());
        lqw.eq(StringUtils.isNotBlank(bo.getDefendant()), DcInsuranceCase::getDefendant, bo.getDefendant());
        lqw.eq(bo.getSubjectAmount() != null, DcInsuranceCase::getSubjectAmount, bo.getSubjectAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getCaseReason()), DcInsuranceCase::getCaseReason, bo.getCaseReason());
        lqw.eq(StringUtils.isNotBlank(bo.getJurisdictionCourt()), DcInsuranceCase::getJurisdictionCourt, bo.getJurisdictionCourt());
        lqw.eq(bo.getPremium() != null, DcInsuranceCase::getPremium, bo.getPremium());
        lqw.in(bo.getCustomerIds() != null && !bo.getCustomerIds().isEmpty(), DcInsuranceCase::getCustomerId, bo.getCustomerIds());
        return lqw;
    }

    /**
     * 新增保险记录表
     *
     * @param bo 保险记录表
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcInsuranceCaseBo bo) {
        DcInsuranceCase add = MapstructUtils.convert(bo, DcInsuranceCase.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改保险记录表
     *
     * @param bo 保险记录表
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcInsuranceCaseBo bo) {
        DcInsuranceCase update = MapstructUtils.convert(bo, DcInsuranceCase.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcInsuranceCase entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除保险记录表信息
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
