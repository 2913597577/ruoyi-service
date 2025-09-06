package org.dromara.myCustomer.service.impl;

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
import org.dromara.myCustomer.domain.bo.DcCustomerTransferBo;
import org.dromara.myCustomer.domain.vo.DcCustomerTransferVo;
import org.dromara.myCustomer.domain.DcCustomerTransfer;
import org.dromara.myCustomer.mapper.DcCustomerTransferMapper;
import org.dromara.myCustomer.service.IDcCustomerTransferService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 客户信息录入Service业务层处理
 *
 * @author Lion Li
 * @date 2025-09-06
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcCustomerTransferServiceImpl implements IDcCustomerTransferService {

    private final DcCustomerTransferMapper baseMapper;

    /**
     * 查询客户信息录入
     *
     * @param id 主键
     * @return 客户信息录入
     */
    @Override
    public DcCustomerTransferVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询客户信息录入列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户信息录入分页列表
     */
    @Override
    public TableDataInfo<DcCustomerTransferVo> queryPageList(DcCustomerTransferBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCustomerTransfer> lqw = buildQueryWrapper(bo);
        Page<DcCustomerTransferVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的客户信息录入列表
     *
     * @param bo 查询条件
     * @return 客户信息录入列表
     */
    @Override
    public List<DcCustomerTransferVo> queryList(DcCustomerTransferBo bo) {
        LambdaQueryWrapper<DcCustomerTransfer> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomerTransfer> buildQueryWrapper(DcCustomerTransferBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCustomerTransfer> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcCustomerTransfer::getId);
        lqw.like(StringUtils.isNotBlank(bo.getCompanyName()), DcCustomerTransfer::getCompanyName, bo.getCompanyName());
        lqw.eq(StringUtils.isNotBlank(bo.getContactPerson()), DcCustomerTransfer::getContactPerson, bo.getContactPerson());
        lqw.eq(StringUtils.isNotBlank(bo.getContactInfo()), DcCustomerTransfer::getContactInfo, bo.getContactInfo());
        lqw.eq(StringUtils.isNotBlank(bo.getContactPosition()), DcCustomerTransfer::getContactPosition, bo.getContactPosition());
        lqw.eq(bo.getContactAge() != null, DcCustomerTransfer::getContactAge, bo.getContactAge());
        lqw.eq(StringUtils.isNotBlank(bo.getAdditionalPerson()), DcCustomerTransfer::getAdditionalPerson, bo.getAdditionalPerson());
        lqw.eq(StringUtils.isNotBlank(bo.getAdditionalContact()), DcCustomerTransfer::getAdditionalContact, bo.getAdditionalContact());
        lqw.eq(StringUtils.isNotBlank(bo.getAdditionalPosition()), DcCustomerTransfer::getAdditionalPosition, bo.getAdditionalPosition());
        lqw.eq(bo.getAdditionalAge() != null, DcCustomerTransfer::getAdditionalAge, bo.getAdditionalAge());
        lqw.eq(StringUtils.isNotBlank(bo.getCompanyIndustry()), DcCustomerTransfer::getCompanyIndustry, bo.getCompanyIndustry());
        lqw.eq(StringUtils.isNotBlank(bo.getCompanyAddress()), DcCustomerTransfer::getCompanyAddress, bo.getCompanyAddress());
        lqw.eq(bo.getEmployeeCount() != null, DcCustomerTransfer::getEmployeeCount, bo.getEmployeeCount());
        lqw.eq(bo.getAccountingCompany() != null, DcCustomerTransfer::getAccountingCompany, bo.getAccountingCompany());
        lqw.eq(StringUtils.isNotBlank(bo.getCustomerDescription()), DcCustomerTransfer::getCustomerDescription, bo.getCustomerDescription());
        lqw.eq(bo.getActualPayment() != null, DcCustomerTransfer::getActualPayment, bo.getActualPayment());
        lqw.eq(bo.getBalanceStatus() != null, DcCustomerTransfer::getBalanceStatus, bo.getBalanceStatus());
        lqw.eq(bo.getContractType() != null, DcCustomerTransfer::getContractType, bo.getContractType());
        lqw.eq(bo.getServiceType() != null, DcCustomerTransfer::getServiceType, bo.getServiceType());
        lqw.eq(bo.getServiceStart() != null, DcCustomerTransfer::getServiceStart, bo.getServiceStart());
        lqw.eq(bo.getServiceEnd() != null, DcCustomerTransfer::getServiceEnd, bo.getServiceEnd());
        lqw.eq(StringUtils.isNotBlank(bo.getLawyerConsultation()), DcCustomerTransfer::getLawyerConsultation, bo.getLawyerConsultation());
        lqw.eq(StringUtils.isNotBlank(bo.getOtherFee()), DcCustomerTransfer::getOtherFee, bo.getOtherFee());
        lqw.eq(bo.getFinanceConfirmed() != null, DcCustomerTransfer::getFinanceConfirmed, bo.getFinanceConfirmed());
        return lqw;
    }

    /**
     * 新增客户信息录入
     *
     * @param bo 客户信息录入
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcCustomerTransferBo bo) {
        DcCustomerTransfer add = MapstructUtils.convert(bo, DcCustomerTransfer.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改客户信息录入
     *
     * @param bo 客户信息录入
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcCustomerTransferBo bo) {
        DcCustomerTransfer update = MapstructUtils.convert(bo, DcCustomerTransfer.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCustomerTransfer entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除客户信息录入信息
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
