package org.dromara.staff.service;

import org.dromara.staff.domain.vo.DcStaffInfoVo;
import org.dromara.staff.domain.bo.DcStaffInfoBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 员工档案Service接口
 *
 * @author Lion Li
 * @date 2025-09-27
 */
public interface IDcStaffInfoService {

    /**
     * 查询员工档案
     *
     * @param id 主键
     * @return 员工档案
     */
    DcStaffInfoVo queryById(Long id);

    /**
     * 分页查询员工档案列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 员工档案分页列表
     */
    TableDataInfo<DcStaffInfoVo> queryPageList(DcStaffInfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的员工档案列表
     *
     * @param bo 查询条件
     * @return 员工档案列表
     */
    List<DcStaffInfoVo> queryList(DcStaffInfoBo bo);

    /**
     * 新增员工档案
     *
     * @param bo 员工档案
     * @return 是否新增成功
     */
    Boolean insertByBo(DcStaffInfoBo bo);

    /**
     * 修改员工档案
     *
     * @param bo 员工档案
     * @return 是否修改成功
     */
    Boolean updateByBo(DcStaffInfoBo bo);

    /**
     * 校验并批量删除员工档案信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
