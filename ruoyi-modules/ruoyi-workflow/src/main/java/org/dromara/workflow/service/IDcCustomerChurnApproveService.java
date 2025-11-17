package org.dromara.workflow.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.workflow.domain.bo.DcCustomerChurnApproveBo;
import org.dromara.workflow.domain.vo.DcCustomerChurnApproveVo;

import java.util.List;

/**
 * 客户流失审批Service接口
 */
public interface IDcCustomerChurnApproveService {

    /**
     * 查询客户流失审批
     */
    DcCustomerChurnApproveVo queryById(Long id);

    /**
     * 查询客户流失审批列表
     */
    TableDataInfo<DcCustomerChurnApproveVo> queryPageList(DcCustomerChurnApproveBo bo, PageQuery pageQuery);

    /**
     * 查询客户流失审批列表
     */
    List<DcCustomerChurnApproveVo> queryList(DcCustomerChurnApproveBo bo);

    /**
     * 新增客户流失审批
     */
    DcCustomerChurnApproveVo insertByBo(DcCustomerChurnApproveBo bo);

    /**
     * 修改客户流失审批
     */
    DcCustomerChurnApproveVo updateByBo(DcCustomerChurnApproveBo bo);

    /**
     * 校验并批量删除客户流失审批信息
     */
    Boolean deleteWithValidByIds(List<Long> ids);
}
