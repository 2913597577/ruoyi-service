package org.dromara.customer.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.dromara.customer.domain.bo.DcCustomerInformationBo;
import org.dromara.customer.domain.vo.DcCustomerInformationVo;
import org.dromara.customer.domain.DcCustomerInformation;
import org.dromara.customer.mapper.DcCustomerInformationMapper;
import org.dromara.customer.service.IDcCustomerInformationService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 客户总表Service业务层处理
 *
 * @author Lion Li
 * @date 2025-09-06
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcCustomerInformationServiceImpl implements IDcCustomerInformationService {

    private final DcCustomerInformationMapper baseMapper;

    /**
     * 查询客户总表
     *
     * @param id 主键
     * @return 客户总表
     */
    @Override
    public DcCustomerInformationVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询客户总表列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户总表分页列表
     */
    @Override
    public TableDataInfo<DcCustomerInformationVo> queryPageList(DcCustomerInformationBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCustomerInformation> lqw = buildQueryWrapper(bo);
        Page<DcCustomerInformationVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的客户总表列表
     *
     * @param bo 查询条件
     * @return 客户总表列表
     */
    @Override
    public List<DcCustomerInformationVo> queryList(DcCustomerInformationBo bo) {
        LambdaQueryWrapper<DcCustomerInformation> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomerInformation> buildQueryWrapper(DcCustomerInformationBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCustomerInformation> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcCustomerInformation::getId);
        lqw.eq(bo.getSignDate() != null, DcCustomerInformation::getSignDate, bo.getSignDate());
        lqw.eq(StringUtils.isNotBlank(bo.getContractNo()), DcCustomerInformation::getContractNo, bo.getContractNo());
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), DcCustomerInformation::getCustomerName, bo.getCustomerName());
        lqw.eq(StringUtils.isNotBlank(bo.getPrincipal()), DcCustomerInformation::getPrincipal, bo.getPrincipal());
        lqw.eq(StringUtils.isNotBlank(bo.getPrincipalPhone()), DcCustomerInformation::getPrincipalPhone, bo.getPrincipalPhone());
        lqw.eq(bo.getLawyerId() != null, DcCustomerInformation::getLawyerId, bo.getLawyerId());
        lqw.eq(StringUtils.isNotBlank(bo.getTransferPerson()), DcCustomerInformation::getTransferPerson, bo.getTransferPerson());
        lqw.eq(StringUtils.isNotBlank(bo.getCloser()), DcCustomerInformation::getCloser, bo.getCloser());
        lqw.eq(bo.getContractType() != null, DcCustomerInformation::getContractType, bo.getContractType());
        lqw.eq(bo.getPackageType() != null, DcCustomerInformation::getPackageType, bo.getPackageType());
        lqw.eq(bo.getActualReceipt() != null, DcCustomerInformation::getActualReceipt, bo.getActualReceipt());
        lqw.eq(bo.getBalance() != null, DcCustomerInformation::getBalance, bo.getBalance());
        lqw.eq(bo.getExpireDate() != null, DcCustomerInformation::getExpireDate, bo.getExpireDate());
        lqw.eq(StringUtils.isNotBlank(bo.getContractCode()), DcCustomerInformation::getContractCode, bo.getContractCode());
        lqw.eq(StringUtils.isNotBlank(bo.getRemarks()), DcCustomerInformation::getRemarks, bo.getRemarks());
        lqw.eq(bo.getActionType() != null, DcCustomerInformation::getActionType, bo.getActionType());
        lqw.eq(bo.getTransferId() != null, DcCustomerInformation::getTransferId, bo.getTransferId());
        return lqw;
    }

    /**
     * 新增客户总表
     *
     * @param bo 客户总表
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcCustomerInformationBo bo) {
        DcCustomerInformation add = MapstructUtils.convert(bo, DcCustomerInformation.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改客户总表
     *
     * @param bo 客户总表
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcCustomerInformationBo bo) {
        DcCustomerInformation update = MapstructUtils.convert(bo, DcCustomerInformation.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCustomerInformation entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除客户总表信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
