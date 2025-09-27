package org.dromara.caseDetail.service;

import org.dromara.caseDetail.domain.vo.DcDebtCaseVo;
import org.dromara.caseDetail.domain.bo.DcDebtCaseBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 欠款案件表Service接口
 *
 * @author Lion Li
 * @date 2025-09-27
 */
public interface IDcDebtCaseService {

    /**
     * 查询欠款案件表
     *
     * @param id 主键
     * @return 欠款案件表
     */
    DcDebtCaseVo queryById(Long id);

    /**
     * 分页查询欠款案件表列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 欠款案件表分页列表
     */
    TableDataInfo<DcDebtCaseVo> queryPageList(DcDebtCaseBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的欠款案件表列表
     *
     * @param bo 查询条件
     * @return 欠款案件表列表
     */
    List<DcDebtCaseVo> queryList(DcDebtCaseBo bo);

    /**
     * 新增欠款案件表
     *
     * @param bo 欠款案件表
     * @return 是否新增成功
     */
    Boolean insertByBo(DcDebtCaseBo bo);

    /**
     * 修改欠款案件表
     *
     * @param bo 欠款案件表
     * @return 是否修改成功
     */
    Boolean updateByBo(DcDebtCaseBo bo);

    /**
     * 校验并批量删除欠款案件表信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
