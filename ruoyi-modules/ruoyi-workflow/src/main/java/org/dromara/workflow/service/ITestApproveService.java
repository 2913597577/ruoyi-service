package org.dromara.workflow.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.workflow.domain.bo.TestApproveBo;
import org.dromara.workflow.domain.vo.TestApproveVo;

import java.util.List;

/**
 * 审批申请Service接口
 *
 * @date 2025-11-01
 */
public interface ITestApproveService {

    /**
     * 查询审批申请
     */
    TestApproveVo queryById(Long id);

    /**
     * 查询审批申请列表
     */
    TableDataInfo<TestApproveVo> queryPageList(TestApproveBo bo, PageQuery pageQuery);

    /**
     * 查询审批申请列表
     */
    List<TestApproveVo> queryList(TestApproveBo bo);

    /**
     * 新增审批申请
     */
    TestApproveVo insertByBo(TestApproveBo bo);

    /**
     * 修改审批申请
     */
    TestApproveVo updateByBo(TestApproveBo bo);

    /**
     * 校验并批量删除审批申请信息
     */
    Boolean deleteWithValidByIds(List<Long> ids);
}
