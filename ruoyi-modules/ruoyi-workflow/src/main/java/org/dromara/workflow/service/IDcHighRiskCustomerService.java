package org.dromara.workflow.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.workflow.domain.bo.DcHighRiskCustomerBo;
import org.dromara.workflow.domain.vo.DcHighRiskCustomerVo;

import java.util.List;

/**
 * 高风险客户记录Service接口
 */
public interface IDcHighRiskCustomerService {

    /**
     * 查询高风险客户记录
     */
    DcHighRiskCustomerVo queryById(Long id);

    /**
     * 查询高风险客户记录列表
     */
    TableDataInfo<DcHighRiskCustomerVo> queryPageList(DcHighRiskCustomerBo bo, PageQuery pageQuery);

    /**
     * 查询高风险客户记录列表
     */
    List<DcHighRiskCustomerVo> queryList(DcHighRiskCustomerBo bo);

    /**
     * 新增高风险客户记录
     */
    DcHighRiskCustomerVo insertByBo(DcHighRiskCustomerBo bo);

    /**
     * 修改高风险客户记录
     */
    DcHighRiskCustomerVo updateByBo(DcHighRiskCustomerBo bo);

    /**
     * 校验并批量删除高风险客户记录信息
     */
    Boolean deleteWithValidByIds(List<Long> ids);
}
