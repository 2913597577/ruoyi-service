package org.dromara.performance.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.performance.domain.bo.DcCustomerPerformanceBo;
import org.dromara.performance.domain.vo.DcCustomerPerformanceVo;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 业绩归属登记Service接口
 *
 * @author Lion Li
 * @date 2025-10-21
 */
public interface IDcCustomerPerformanceService {

    /**
     * 查询业绩归属登记
     *
     * @param id 主键
     * @return 业绩归属登记
     */
    DcCustomerPerformanceVo queryById(Long id);

    /**
     * 分页查询业绩归属登记列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 业绩归属登记分页列表
     */
    TableDataInfo<DcCustomerPerformanceVo> queryPageList(DcCustomerPerformanceBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的业绩归属登记列表
     *
     * @param bo 查询条件
     * @return 业绩归属登记列表
     */
    List<DcCustomerPerformanceVo> queryList(DcCustomerPerformanceBo bo);

    /**
     * 新增业绩归属登记
     *
     * @param bo 业绩归属登记
     * @return 是否新增成功
     */
    Boolean insertByBo(DcCustomerPerformanceBo bo);

    /**
     * 修改业绩归属登记
     *
     * @param bo 业绩归属登记
     * @return 是否修改成功
     */
    Boolean updateByBo(DcCustomerPerformanceBo bo);

    /**
     * 校验并批量删除业绩归属登记信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    List<Map<String, Object>> selectListByPage(List<Long> userId, List<Long> transferId, List<String> city,
                                               List<String> serviceCity, List<Long> inviterId, List<Integer> serviceType,
                                               List<Integer> secondDevelopmentType, Date serviceStart, Date serviceEnd, List<String> companyName, String updateTimeMonth, Integer page, Integer pageSize);

    int countListByPage(List<Long> userId, List<Long> transferId, List<String> city,
                        List<String> serviceCity, List<Long> inviterId, List<Integer> serviceType, List<Integer> secondDevelopmentType,
                        Date serviceStart, Date serviceEnd, List<String> companyName, String updateTimeMonth);

}
