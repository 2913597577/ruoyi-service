package org.dromara.performance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.performance.domain.DcCustomerPerformance;
import org.dromara.performance.domain.bo.DcCustomerPerformanceBo;
import org.dromara.performance.domain.vo.DcCustomerPerformanceVo;
import org.dromara.performance.mapper.DcCustomerPerformanceMapper;
import org.dromara.performance.service.IDcCustomerPerformanceService;
import org.dromara.system.domain.vo.SysUserVo;
import org.dromara.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 业绩归属登记Service业务层处理
 *
 * @author Lion Li
 * @date 2025-10-21
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcCustomerPerformanceServiceImpl implements IDcCustomerPerformanceService {

    private final DcCustomerPerformanceMapper baseMapper;
    private final ISysUserService sysUserService;

    /**
     * 查询业绩归属登记
     *
     * @param id 主键
     * @return 业绩归属登记
     */
    @Override
    public DcCustomerPerformanceVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询业绩归属登记列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 业绩归属登记分页列表
     */
    @Override
    public TableDataInfo<DcCustomerPerformanceVo> queryPageList(DcCustomerPerformanceBo bo, PageQuery pageQuery) {
        //test();
        LambdaQueryWrapper<DcCustomerPerformance> lqw = buildQueryWrapper(bo);
        Page<DcCustomerPerformanceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的业绩归属登记列表
     *
     * @param bo 查询条件
     * @return 业绩归属登记列表
     */
    @Override
    public List<DcCustomerPerformanceVo> queryList(DcCustomerPerformanceBo bo) {
        LambdaQueryWrapper<DcCustomerPerformance> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomerPerformance> buildQueryWrapper(DcCustomerPerformanceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCustomerPerformance> lqw = Wrappers.lambdaQuery();
        lqw.orderByDesc(DcCustomerPerformance::getId);
        lqw.eq(bo.getTransferId() != null, DcCustomerPerformance::getTransferId, bo.getTransferId());
        lqw.eq(bo.getUserId() != null, DcCustomerPerformance::getUserId, bo.getUserId());
        lqw.like(bo.getUserName() != null, DcCustomerPerformance::getUserName, bo.getUserName());
        lqw.eq(bo.getBalance() != null, DcCustomerPerformance::getBalance, bo.getBalance());
        lqw.eq(StringUtils.isNotBlank(bo.getCity()), DcCustomerPerformance::getCity, bo.getCity());
        lqw.eq(bo.getCreaterId() != null, DcCustomerPerformance::getCreaterId, bo.getCreaterId());
        lqw.eq(StringUtils.isNotBlank(bo.getCreaterName()), DcCustomerPerformance::getCreaterName, bo.getCreaterName());

        return lqw;
    }

    /**
     * 新增业绩归属登记
     *
     * @param bo 业绩归属登记
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcCustomerPerformanceBo bo) {
        DcCustomerPerformance add = MapstructUtils.convert(bo, DcCustomerPerformance.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改业绩归属登记
     *
     * @param bo 业绩归属登记
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcCustomerPerformanceBo bo) {
        DcCustomerPerformance update = MapstructUtils.convert(bo, DcCustomerPerformance.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCustomerPerformance entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除业绩归属登记信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }

    @Override
    public List<Map<String, Object>> selectListByPage(List<Long> userId, List<Long> transferId, List<String> city,
                                                      List<String> serviceCity, List<Long> inviterId, List<Integer> serviceType,
                                                      List<Integer> secondDevelopmentType, Date serviceStart, Date serviceEnd, List<String> companyName, Integer page, Integer pageSize) {
        // 计算偏移量
        Integer offset = (page != null && pageSize != null) ? (page - 1) * pageSize : null;

        return baseMapper.selectListByPage(userId, transferId, city, serviceCity,
            inviterId, serviceType, secondDevelopmentType, serviceStart, serviceEnd, companyName,
            offset, pageSize);
    }

    @Override
    public int countListByPage(List<Long> userId, List<Long> transferId, List<String> city,
                               List<String> serviceCity, List<Long> inviterId, List<Integer> serviceType,
                               List<Integer> secondDevelopmentType,
                               Date serviceStart, Date serviceEnd, List<String> companyName) {
        return baseMapper.selectListByPageCount(userId, transferId, city, serviceCity,
            inviterId, serviceType, secondDevelopmentType, serviceStart, serviceEnd, companyName);
    }


    public void test() {
        List<DcCustomerPerformanceVo> dcCustomerPerformanceVos = queryList(new DcCustomerPerformanceBo());
        for (DcCustomerPerformanceVo dcCustomerPerformanceVo : dcCustomerPerformanceVos) {
            SysUserVo sysUserVo = sysUserService.selectUserById(dcCustomerPerformanceVo.getCreateBy());
            DcCustomerPerformanceBo dcCustomerPerformanceBo = new DcCustomerPerformanceBo();
            dcCustomerPerformanceBo.setId(dcCustomerPerformanceVo.getId());
            dcCustomerPerformanceBo.setTransferId(dcCustomerPerformanceVo.getTransferId());
            dcCustomerPerformanceBo.setUserId(dcCustomerPerformanceVo.getUserId());
            dcCustomerPerformanceBo.setUserName(dcCustomerPerformanceVo.getUserName());
            dcCustomerPerformanceBo.setBalance(dcCustomerPerformanceVo.getBalance());
            dcCustomerPerformanceBo.setCity(dcCustomerPerformanceVo.getCity());
            dcCustomerPerformanceBo.setCreaterId(sysUserVo.getUserId());
            dcCustomerPerformanceBo.setCreaterName(sysUserVo.getNickName());
            updateByBo(dcCustomerPerformanceBo);
        }
    }
}
