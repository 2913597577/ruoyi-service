package org.dromara.caseDetail.service;

import org.dromara.caseDetail.domain.vo.DcInsuranceCaseVo;
import org.dromara.caseDetail.domain.bo.DcInsuranceCaseBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 保险记录表Service接口
 *
 * @author Lion Li
 * @date 2025-09-29
 */
public interface IDcInsuranceCaseService {

    /**
     * 查询保险记录表
     *
     * @param id 主键
     * @return 保险记录表
     */
    DcInsuranceCaseVo queryById(Long id);

    /**
     * 分页查询保险记录表列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 保险记录表分页列表
     */
    TableDataInfo<DcInsuranceCaseVo> queryPageList(DcInsuranceCaseBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的保险记录表列表
     *
     * @param bo 查询条件
     * @return 保险记录表列表
     */
    List<DcInsuranceCaseVo> queryList(DcInsuranceCaseBo bo);

    /**
     * 新增保险记录表
     *
     * @param bo 保险记录表
     * @return 是否新增成功
     */
    Boolean insertByBo(DcInsuranceCaseBo bo);

    /**
     * 修改保险记录表
     *
     * @param bo 保险记录表
     * @return 是否修改成功
     */
    Boolean updateByBo(DcInsuranceCaseBo bo);

    /**
     * 校验并批量删除保险记录表信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
