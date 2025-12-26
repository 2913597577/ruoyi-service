package org.dromara.financial.service;

import com.alibaba.fastjson.JSONObject;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.financial.domain.FinancialStatisticsQuery;
import org.dromara.financial.domain.bo.DcFinancialStatisticsBo;
import org.dromara.financial.domain.vo.DcFinancialStatisticsVo;

import java.util.Collection;
import java.util.List;

/**
 * 财务统计Service接口
 *
 * @author Lion Li
 * @date 2025-12-26
 */
public interface IDcFinancialStatisticsService {

    /**
     * 查询财务统计
     *
     * @param id 主键
     * @return 财务统计
     */
    DcFinancialStatisticsVo queryById(Long id);

    /**
     * 分页查询财务统计列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 财务统计分页列表
     */
    TableDataInfo<DcFinancialStatisticsVo> queryPageList(DcFinancialStatisticsBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的财务统计列表
     *
     * @param bo 查询条件
     * @return 财务统计列表
     */
    List<DcFinancialStatisticsVo> queryList(DcFinancialStatisticsBo bo);

    /**
     * 新增财务统计
     *
     * @param bo 财务统计
     * @return 是否新增成功
     */
    Boolean insertByBo(DcFinancialStatisticsBo bo);

    /**
     * 修改财务统计
     *
     * @param bo 财务统计
     * @return 是否修改成功
     */
    Boolean updateByBo(DcFinancialStatisticsBo bo);

    /**
     * 校验并批量删除财务统计信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    JSONObject getStatistics(FinancialStatisticsQuery query);
}
