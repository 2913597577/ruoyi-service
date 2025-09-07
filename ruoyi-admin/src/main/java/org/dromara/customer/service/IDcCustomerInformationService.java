package org.dromara.customer.service;

import com.aizuda.snailjob.common.core.model.Result;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.customer.domain.bo.DcCustomerInformationBo;
import org.dromara.customer.domain.vo.DcCustomerInformationVo;

import java.util.Collection;
import java.util.List;

/**
 * 客户总表Service接口
 *
 * @author Lion Li
 * @date 2025-09-06
 */
public interface IDcCustomerInformationService {

    /**
     * 查询客户总表
     *
     * @param id 主键
     * @return 客户总表
     */
    DcCustomerInformationVo queryById(Long id);

    /**
     * 分页查询客户总表列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户总表分页列表
     */
    TableDataInfo<DcCustomerInformationVo> queryPageList(DcCustomerInformationBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的客户总表列表
     *
     * @param bo 查询条件
     * @return 客户总表列表
     */
    List<DcCustomerInformationVo> queryList(DcCustomerInformationBo bo);

    /**
     * 新增客户总表
     *
     * @param bo 客户总表
     * @return 是否新增成功
     */
    Boolean insertByBo(DcCustomerInformationBo bo);

    /**
     * 修改客户总表
     *
     * @param bo 客户总表
     * @return 是否修改成功
     */
    Boolean updateByBo(DcCustomerInformationBo bo);

    /**
     * 校验并批量删除客户总表信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 根据transferId查询客户总表列表
     *
     * @param transferId transferId
     * @return 客户总表列表
     */
    Result queryListByTransferId(Long transferId);
}
