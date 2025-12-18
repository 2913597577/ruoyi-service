package org.dromara.workflow.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.workflow.domain.bo.DcEmployeePositionChangeBo;
import org.dromara.workflow.domain.vo.DcEmployeePositionChangeVo;

import java.util.List;

/**
 * 员工岗位变动申请Service接口
 */
public interface IDcEmployeePositionChangeService {

    /**
     * 查询员工岗位变动申请
     */
    DcEmployeePositionChangeVo queryById(Long id);

    /**
     * 查询员工岗位变动申请列表
     */
    TableDataInfo<DcEmployeePositionChangeVo> queryPageList(DcEmployeePositionChangeBo bo, PageQuery pageQuery);

    /**
     * 查询员工岗位变动申请列表
     */
    List<DcEmployeePositionChangeVo> queryList(DcEmployeePositionChangeBo bo);

    /**
     * 新增员工岗位变动申请
     */
    DcEmployeePositionChangeVo insertByBo(DcEmployeePositionChangeBo bo);

    /**
     * 修改员工岗位变动申请
     */
    DcEmployeePositionChangeVo updateByBo(DcEmployeePositionChangeBo bo);

    /**
     * 校验并批量删除员工岗位变动申请信息
     */
    Boolean deleteWithValidByIds(List<Long> ids);
}
