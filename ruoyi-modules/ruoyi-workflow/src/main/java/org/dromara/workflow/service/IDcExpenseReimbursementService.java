// IDcExpenseReimbursementService.java
package org.dromara.workflow.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.workflow.domain.bo.DcExpenseReimbursementBo;
import org.dromara.workflow.domain.vo.DcExpenseReimbursementVo;

import java.util.List;

/**
 * 报销申请Service接口
 */
public interface IDcExpenseReimbursementService {

    /**
     * 查询报销申请
     */
    DcExpenseReimbursementVo queryById(Long id);

    /**
     * 查询报销申请列表
     */
    TableDataInfo<DcExpenseReimbursementVo> queryPageList(DcExpenseReimbursementBo bo, PageQuery pageQuery);

    /**
     * 查询报销申请列表
     */
    List<DcExpenseReimbursementVo> queryList(DcExpenseReimbursementBo bo);

    /**
     * 新增报销申请
     */
    DcExpenseReimbursementVo insertByBo(DcExpenseReimbursementBo bo);

    /**
     * 修改报销申请
     */
    DcExpenseReimbursementVo updateByBo(DcExpenseReimbursementBo bo);

    /**
     * 校验并批量删除报销申请信息
     */
    Boolean deleteWithValidByIds(List<Long> ids);
}
