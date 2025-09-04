package org.dromara.lawperson.service;

import org.dromara.lawperson.domain.vo.DcLegalSupportVo;
import org.dromara.lawperson.domain.bo.DcLegalSupportBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 法务支持人员Service接口
 *
 * @author Lion Li
 * @date 2025-07-08
 */
public interface IDcLegalSupportService {

    /**
     * 查询法务支持人员
     *
     * @param id 主键
     * @return 法务支持人员
     */
    DcLegalSupportVo queryById(Long id);

    /**
     * 分页查询法务支持人员列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 法务支持人员分页列表
     */
    TableDataInfo<DcLegalSupportVo> queryPageList(DcLegalSupportBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的法务支持人员列表
     *
     * @param bo 查询条件
     * @return 法务支持人员列表
     */
    List<DcLegalSupportVo> queryList(DcLegalSupportBo bo);

    /**
     * 新增法务支持人员
     *
     * @param bo 法务支持人员
     * @return 是否新增成功
     */
    Boolean insertByBo(DcLegalSupportBo bo);

    /**
     * 修改法务支持人员
     *
     * @param bo 法务支持人员
     * @return 是否修改成功
     */
    Boolean updateByBo(DcLegalSupportBo bo);

    /**
     * 校验并批量删除法务支持人员信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
