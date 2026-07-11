package org.dromara.financial.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.financial.domain.DcFinancialStatistics;
import org.dromara.financial.domain.FinancialStatisticsQuery;
import org.dromara.financial.domain.bo.DcFinancialStatisticsBo;
import org.dromara.financial.domain.vo.DcFinancialStatisticsVo;
import org.dromara.financial.mapper.DcFinancialStatisticsMapper;
import org.dromara.financial.service.IDcFinancialStatisticsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 财务统计Service业务层处理
 *
 * @author Lion Li
 * @date 2025-12-26
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcFinancialStatisticsServiceImpl implements IDcFinancialStatisticsService {

    private final DcFinancialStatisticsMapper baseMapper;

    /**
     * 查询财务统计
     *
     * @param id 主键
     * @return 财务统计
     */
    @Override
    public DcFinancialStatisticsVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询财务统计列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 财务统计分页列表
     */
    @Override
    public TableDataInfo<DcFinancialStatisticsVo> queryPageList(DcFinancialStatisticsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcFinancialStatistics> lqw = buildQueryWrapper(bo);
        Page<DcFinancialStatisticsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的财务统计列表
     *
     * @param bo 查询条件
     * @return 财务统计列表
     */
    @Override
    public List<DcFinancialStatisticsVo> queryList(DcFinancialStatisticsBo bo) {
        LambdaQueryWrapper<DcFinancialStatistics> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcFinancialStatistics> buildQueryWrapper(DcFinancialStatisticsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcFinancialStatistics> lqw = Wrappers.lambdaQuery();
        lqw.orderByDesc(DcFinancialStatistics::getId);
        lqw.eq(bo.getBalance() != null, DcFinancialStatistics::getBalance, bo.getBalance());
        lqw.eq(bo.getFinancialType() != null, DcFinancialStatistics::getFinancialType, bo.getFinancialType());
        lqw.eq(StringUtils.isNotBlank(bo.getSourceType()), DcFinancialStatistics::getSourceType, bo.getSourceType());
        lqw.eq(StringUtils.isNotBlank(bo.getContractNo()), DcFinancialStatistics::getContractNo, bo.getContractNo());
        lqw.eq(StringUtils.isNotBlank(bo.getCity()), DcFinancialStatistics::getCity, bo.getCity());
        lqw.like(StringUtils.isNotBlank(bo.getCreaterName()), DcFinancialStatistics::getCreaterName, bo.getCreaterName());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark1()), DcFinancialStatistics::getRemark1, bo.getRemark1());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark2()), DcFinancialStatistics::getRemark2, bo.getRemark2());
        lqw.eq(StringUtils.isNotBlank(bo.getRemark3()), DcFinancialStatistics::getRemark3, bo.getRemark3());
        lqw.eq(bo.getFlowTime() != null, DcFinancialStatistics::getFlowTime, bo.getFlowTime());
        lqw.eq(StringUtils.isNotBlank(bo.getCompanyName()), DcFinancialStatistics::getCompanyName, bo.getCompanyName());
        return lqw;
    }

    /**
     * 新增财务统计
     *
     * @param bo 财务统计
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcFinancialStatisticsBo bo) {
        DcFinancialStatistics add = MapstructUtils.convert(bo, DcFinancialStatistics.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改财务统计
     *
     * @param bo 财务统计
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcFinancialStatisticsBo bo) {
        DcFinancialStatistics update = MapstructUtils.convert(bo, DcFinancialStatistics.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcFinancialStatistics entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除财务统计信息
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

    @Override
    public JSONObject getStatistics(FinancialStatisticsQuery query) {
        JSONObject result = new JSONObject();

        // 按城市统计收支
        List<Map<String, Object>> cityStats = baseMapper.selectCityStatistics(query);
        result.put("cityStatistics", cityStats);

        // 按来源类型统计
        List<Map<String, Object>> sourceTypeStats = baseMapper.selectSourceTypeStatistics(query);
        result.put("sourceTypeStatistics", sourceTypeStats);

        // 按财务类型统计
        List<Map<String, Object>> financialTypeStats = baseMapper.selectFinancialTypeStatistics(query);
        result.put("financialTypeStatistics", financialTypeStats);

        return result;
    }
}
