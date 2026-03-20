package org.dromara.caseDetail.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.caseDetail.domain.DcDebtCase;
import org.dromara.caseDetail.domain.bo.DcDebtCaseBo;
import org.dromara.caseDetail.domain.vo.DcDebtCaseVo;
import org.dromara.caseDetail.mapper.DcDebtCaseMapper;
import org.dromara.caseDetail.service.IDcDebtCaseService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 欠款案件表Service业务层处理
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcDebtCaseServiceImpl implements IDcDebtCaseService {

    private final DcDebtCaseMapper baseMapper;

    /**
     * 查询欠款案件表
     *
     * @param id 主键
     * @return 欠款案件表
     */
    @Override
    public DcDebtCaseVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询欠款案件表列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 欠款案件表分页列表
     */
    @Override
    public TableDataInfo<DcDebtCaseVo> queryPageList(DcDebtCaseBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcDebtCase> lqw = buildQueryWrapper(bo);
        Page<DcDebtCaseVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的欠款案件表列表
     *
     * @param bo 查询条件
     * @return 欠款案件表列表
     */
    @Override
    public List<DcDebtCaseVo> queryList(DcDebtCaseBo bo) {
        LambdaQueryWrapper<DcDebtCase> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcDebtCase> buildQueryWrapper(DcDebtCaseBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcDebtCase> lqw = Wrappers.lambdaQuery();
        lqw.orderByDesc(DcDebtCase::getCreateTime);
        lqw.eq(bo.getCustomerId() != null, DcDebtCase::getCustomerId, bo.getCustomerId());
        lqw.like(StringUtils.isNotBlank(bo.getDebtorName()), DcDebtCase::getDebtorName, bo.getDebtorName());
        lqw.eq(bo.getDebtAmount() != null, DcDebtCase::getDebtAmount, bo.getDebtAmount());
        lqw.eq(bo.getRemainingAmount() != null, DcDebtCase::getRemainingAmount, bo.getRemainingAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getContactPhone()), DcDebtCase::getContactPhone, bo.getContactPhone());
        lqw.eq(StringUtils.isNotBlank(bo.getIdCard()), DcDebtCase::getIdCard, bo.getIdCard());
        lqw.eq(bo.getRequestReceiveTime() != null, DcDebtCase::getRequestReceiveTime, bo.getRequestReceiveTime());
        lqw.eq(StringUtils.isNotBlank(bo.getEvidenceNotes()), DcDebtCase::getEvidenceNotes, bo.getEvidenceNotes());
        lqw.eq(StringUtils.isNotBlank(bo.getFilingSystemAccount()), DcDebtCase::getFilingSystemAccount, bo.getFilingSystemAccount());
        lqw.eq(StringUtils.isNotBlank(bo.getFilingPassword()), DcDebtCase::getFilingPassword, bo.getFilingPassword());
        lqw.eq(bo.getFilingDate() != null, DcDebtCase::getFilingDate, bo.getFilingDate());
        lqw.eq(bo.getNextContactTime() != null, DcDebtCase::getNextContactTime, bo.getNextContactTime());
        lqw.eq(bo.getCaseStatus() != null, DcDebtCase::getCaseStatus, bo.getCaseStatus());
        lqw.eq(bo.getLegalSupportId() != null, DcDebtCase::getLegalSupportId, bo.getLegalSupportId());
        lqw.like(StringUtils.isNotBlank(bo.getLegalSupportName()), DcDebtCase::getLegalSupportName, bo.getLegalSupportName());
        lqw.eq(bo.getCreateDept() != null, DcDebtCase::getCreateDept, bo.getCreateDept());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark()), DcDebtCase::getRemark, bo.getRemark());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark1()), DcDebtCase::getRemark1, bo.getRemark1());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark2()), DcDebtCase::getRemark2, bo.getRemark2());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark3()), DcDebtCase::getRemark3, bo.getRemark3());
        return lqw;
    }

    /**
     * 新增欠款案件表
     *
     * @param bo 欠款案件表
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcDebtCaseBo bo) {
        DcDebtCase add = MapstructUtils.convert(bo, DcDebtCase.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            if (add != null) {
                bo.setId(add.getId());
            }
        }
        return flag;
    }

    /**
     * 修改欠款案件表
     *
     * @param bo 欠款案件表
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcDebtCaseBo bo) {
        DcDebtCase update = MapstructUtils.convert(bo, DcDebtCase.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcDebtCase entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除欠款案件表信息
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
