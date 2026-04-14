package org.dromara.myCustomer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.customer.domain.bo.DcCustomerInformationBo;
import org.dromara.customer.domain.bo.DcCustomerInformationLogBo;
import org.dromara.customer.domain.vo.DcCustomerInformationVo;
import org.dromara.customer.service.impl.DcCustomerInformationLogServiceImpl;
import org.dromara.customer.service.impl.DcCustomerInformationServiceImpl;
import org.dromara.myCustomer.domain.DcCustomerTransfer;
import org.dromara.myCustomer.domain.bo.DcCustomerTransferBo;
import org.dromara.myCustomer.domain.vo.DcCustomerTransferVo;
import org.dromara.myCustomer.mapper.DcCustomerTransferMapper;
import org.dromara.myCustomer.service.IDcCustomerTransferService;
import org.dromara.performance.domain.bo.DcCustomerPerformanceBo;
import org.dromara.performance.domain.vo.DcCustomerPerformanceVo;
import org.dromara.performance.service.IDcCustomerPerformanceService;
import org.dromara.system.domain.vo.SysUserVo;
import org.dromara.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private final DcCustomerInformationServiceImpl dcCustomerInformationService;
    private final DcCustomerInformationLogServiceImpl dcCustomerInformationLogService;
    private final ISysUserService sysUserService;
    private final IDcCustomerPerformanceService dcCustomerPerformanceService;

    /**
     * 查询客户信息录入
     *
     * @param id 主键
     * @return 客户信息录入
     */
    @Override
    public DcCustomerTransferVo queryById(Long id) {
        DcCustomerTransferVo vo = baseMapper.selectVoById(id);
        if (vo != null) {
            DcCustomerPerformanceBo dcCustomerPerformanceBo = new DcCustomerPerformanceBo();
            dcCustomerPerformanceBo.setTransferId(vo.getId());
            List<DcCustomerPerformanceVo> dcCustomerPerformanceVos = dcCustomerPerformanceService.queryList(dcCustomerPerformanceBo);
            vo.setPerformanceInfo(dcCustomerPerformanceVos);
        }
        return vo;
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
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return TableDataInfo.build();
        }

/*        if (loginUser.getRolePermission() != null && loginUser.getRolePermission().contains("SalesCenter")) {
            bo.setInviterId(loginUser.getUserId());
        }*/
        Set<String> rolePermission = loginUser.getRolePermission();
        String deptCategory = loginUser.getDeptCategory();

        if (rolePermission != null) {
            if (rolePermission.contains("SalesCenter_Employee")) {
                bo.setInviterId(loginUser.getUserId());
            } else if (rolePermission.contains("SalesCenter_Manager") || rolePermission.contains("SalesCenter_Leader")) {
                String city = deptCategory != null && deptCategory.contains("_") ? deptCategory.substring(0, deptCategory.indexOf('_')) : null;
                if (city != null) {
                    bo.setCustomerCity(city);
                }
            } else if (rolePermission.contains("LegalSupport_Manager") || rolePermission.contains("LegalSupport_Leader")) {
                String city = deptCategory != null && deptCategory.contains("_") ? deptCategory.substring(0, deptCategory.indexOf('_')) : null;
                if (city != null) {
                    bo.setCustomerCity(city);
                }
            } else if (rolePermission.contains("LegalSupport_Employee")) {
                bo.setAccountManagerId(loginUser.getUserId());
                bo.setInviterId(loginUser.getUserId());
            }
        }



        LambdaQueryWrapper<DcCustomerTransfer> lqw = buildQueryWrapper(bo);
        Page<DcCustomerTransferVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        for (DcCustomerTransferVo vo : result.getRecords()) {
            DcCustomerPerformanceBo dcCustomerPerformanceBo = new DcCustomerPerformanceBo();
            dcCustomerPerformanceBo.setTransferId(vo.getId());
            List<DcCustomerPerformanceVo> dcCustomerPerformanceVos = dcCustomerPerformanceService.queryList(dcCustomerPerformanceBo);
            vo.setPerformanceInfo(dcCustomerPerformanceVos);
           /* DcCustomerInformationVo dcCustomerInformationVo = dcCustomerInformationService.queryListByTransferId(vo.getId());
            if (dcCustomerInformationVo == null) {
                continue;
            }
            SysUserVo sysUserVo = sysUserService.selectUserById(dcCustomerInformationVo.getLawyerId());
            if (sysUserVo == null) {
                continue;
            }
            vo.setLegalSupport(sysUserVo.getNickName());*/
        }
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
        List<DcCustomerTransferVo> list = baseMapper.selectVoList(lqw);
        for (DcCustomerTransferVo vo : list) {
            DcCustomerPerformanceBo dcCustomerPerformanceBo = new DcCustomerPerformanceBo();
            dcCustomerPerformanceBo.setTransferId(vo.getId());
            List<DcCustomerPerformanceVo> dcCustomerPerformanceVos = dcCustomerPerformanceService.queryList(dcCustomerPerformanceBo);
            vo.setPerformanceInfo(dcCustomerPerformanceVos);
        }
        return list;
    }

    private LambdaQueryWrapper<DcCustomerTransfer> buildQueryWrapper(DcCustomerTransferBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCustomerTransfer> lqw = Wrappers.lambdaQuery();
        lqw.orderByDesc(DcCustomerTransfer::getCreateTime);
        lqw.like(StringUtils.isNotBlank(bo.getCompanyName()), DcCustomerTransfer::getCompanyName, bo.getCompanyName());
        lqw.like(StringUtils.isNotBlank(bo.getContactPerson()), DcCustomerTransfer::getContactPerson, bo.getContactPerson());
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
        lqw.ge(bo.getActualPayment() != null, DcCustomerTransfer::getActualPayment, bo.getActualPayment());
        lqw.ge(bo.getBalanceStatus() != null, DcCustomerTransfer::getBalanceStatus, bo.getBalanceStatus());
        lqw.ge(bo.getContractAmount() != null, DcCustomerTransfer::getContractAmount, bo.getContractAmount());
        lqw.eq(bo.getContractType() != null, DcCustomerTransfer::getContractType, bo.getContractType());
        lqw.eq(StringUtils.isNotBlank(bo.getContractCode()), DcCustomerTransfer::getContractCode, bo.getContractCode());
        lqw.eq(bo.getServiceType() != null, DcCustomerTransfer::getServiceType, bo.getServiceType());
        lqw.ge(bo.getServiceStart() != null, DcCustomerTransfer::getServiceStart, bo.getServiceStart());
        lqw.le(bo.getServiceEnd() != null, DcCustomerTransfer::getServiceEnd, bo.getServiceEnd());
        lqw.eq(StringUtils.isNotBlank(bo.getLawyerConsultation()), DcCustomerTransfer::getLawyerConsultation, bo.getLawyerConsultation());
        lqw.eq(StringUtils.isNotBlank(bo.getOtherFee()), DcCustomerTransfer::getOtherFee, bo.getOtherFee());
        lqw.eq(bo.getFinanceConfirmed() != null, DcCustomerTransfer::getFinanceConfirmed, bo.getFinanceConfirmed());
        lqw.eq(bo.getCustomerCity() != null, DcCustomerTransfer::getCustomerCity, bo.getCustomerCity());
        lqw.eq(bo.getAuditUserId() != null, DcCustomerTransfer::getAuditUserId, bo.getAuditUserId());
        //lqw.eq(bo.getInviterId() != null, DcCustomerTransfer::getInviterId, bo.getInviterId());
        lqw.eq(bo.getInvoiceStatus() != null, DcCustomerTransfer::getInvoiceStatus, bo.getInvoiceStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getInvoiceRequirements()), DcCustomerTransfer::getInvoiceRequirements, bo.getInvoiceRequirements());
        lqw.eq(StringUtils.isNotBlank(bo.getInvoiceContent()), DcCustomerTransfer::getInvoiceContent, bo.getInvoiceContent());
        lqw.like(StringUtils.isNotBlank(bo.getAuditUserName()), DcCustomerTransfer::getAuditUserName, bo.getAuditUserName());
        // 决策人信息
        lqw.like(StringUtils.isNotBlank(bo.getDecisionMaker()), DcCustomerTransfer::getDecisionMaker, bo.getDecisionMaker());
        lqw.like(StringUtils.isNotBlank(bo.getDecisionMakerContact()), DcCustomerTransfer::getDecisionMakerContact, bo.getDecisionMakerContact());
        lqw.eq(StringUtils.isNotBlank(bo.getDecisionMakerPosition()), DcCustomerTransfer::getDecisionMakerPosition, bo.getDecisionMakerPosition());
        lqw.eq(bo.getDecisionMakerAge() != null, DcCustomerTransfer::getDecisionMakerAge, bo.getDecisionMakerAge());

        // 二开相关
        lqw.eq(bo.getSecondDevelopmentType() != null, DcCustomerTransfer::getSecondDevelopmentType, bo.getSecondDevelopmentType());
        lqw.eq(bo.getIsSecondaryCharge() != null, DcCustomerTransfer::getIsSecondaryCharge, bo.getIsSecondaryCharge());

        // 债务人信息
        lqw.like(StringUtils.isNotBlank(bo.getDebtor()), DcCustomerTransfer::getDebtor, bo.getDebtor());
        lqw.eq(bo.getDebtAmount() != null, DcCustomerTransfer::getDebtAmount, bo.getDebtAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getDebtorContact()), DcCustomerTransfer::getDebtorContact, bo.getDebtorContact());
        lqw.like(StringUtils.isNotBlank(bo.getEvidenceRemark()), DcCustomerTransfer::getEvidenceRemark, bo.getEvidenceRemark());

        // 客户来源与推荐人
        lqw.eq(StringUtils.isNotBlank(bo.getCustomerSource()), DcCustomerTransfer::getCustomerSource, bo.getCustomerSource());
        lqw.eq(bo.getReferrerId() != null, DcCustomerTransfer::getReferrerId, bo.getReferrerId());
        lqw.like(StringUtils.isNotBlank(bo.getReferrer()), DcCustomerTransfer::getReferrer, bo.getReferrer());

        // 省市区地址
        lqw.eq(StringUtils.isNotBlank(bo.getProvince()), DcCustomerTransfer::getProvince, bo.getProvince());
        lqw.eq(StringUtils.isNotBlank(bo.getCity()), DcCustomerTransfer::getCity, bo.getCity());
        lqw.like(StringUtils.isNotBlank(bo.getDistrict()), DcCustomerTransfer::getDistrict, bo.getDistrict());

        //lqw.eq(bo.getCreateBy() != null, DcCustomerTransfer::getCreateBy, bo.getCreateBy());
        //lqw.eq(bo.getCreateDept() != null, DcCustomerTransfer::getCreateDept, bo.getCreateDept());
        lqw.in(bo.getDeptIds() != null && !bo.getDeptIds().isEmpty(), DcCustomerTransfer::getCreateDept, bo.getDeptIds());

        // 当 accountManagerId 和 inviterId 都存在(法务员工),生成 OR 查询,法务员工看到:自己创建的 或 分配给自己的
        if (bo.getAccountManagerId() != null && bo.getInviterId() != null) {
            lqw.and(wrapper -> wrapper
                .eq(DcCustomerTransfer::getAccountManagerId, bo.getAccountManagerId())
                .or()
                .eq(DcCustomerTransfer::getInviterId, bo.getInviterId())
            );
        } else if (bo.getAccountManagerId() != null) {
            lqw.eq(DcCustomerTransfer::getAccountManagerId, bo.getAccountManagerId());
        } else if (bo.getInviterId() != null) {
            lqw.eq(DcCustomerTransfer::getInviterId, bo.getInviterId());
        }

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
            for (DcCustomerPerformanceBo dcCustomerPerformanceBo : bo.getPerformanceInfo()) {
                if (dcCustomerPerformanceBo.getUserId() == null) {
                    continue;
                }
                dcCustomerPerformanceBo.setTransferId(add.getId());
                dcCustomerPerformanceBo.setUserId(dcCustomerPerformanceBo.getUserId());
                dcCustomerPerformanceBo.setUserName(dcCustomerPerformanceBo.getUserName());
                dcCustomerPerformanceBo.setBalance(dcCustomerPerformanceBo.getBalance());
                dcCustomerPerformanceBo.setCity(dcCustomerPerformanceBo.getCity());
                dcCustomerPerformanceBo.setCreateBy(LoginHelper.getUserId());
                dcCustomerPerformanceBo.setCreaterId(LoginHelper.getUserId());
                dcCustomerPerformanceBo.setCreaterName(LoginHelper.getLoginUser() == null ? LoginHelper.getUsername() : LoginHelper.getLoginUser().getNickname());
                dcCustomerPerformanceService.insertByBo(dcCustomerPerformanceBo);
            }
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
        // 避免bo.getPerformanceInfo()为null时，空指针问题
        if (bo.getPerformanceInfo() != null && !bo.getPerformanceInfo().isEmpty()) {
        for (DcCustomerPerformanceBo dcCustomerPerformanceBo : bo.getPerformanceInfo()) {
            if (dcCustomerPerformanceBo.getUserId() == null) {
                continue;
            }
            dcCustomerPerformanceBo.setTransferId(update.getId());
            dcCustomerPerformanceBo.setUserId(dcCustomerPerformanceBo.getUserId());
            dcCustomerPerformanceBo.setUserName(dcCustomerPerformanceBo.getUserName());
            dcCustomerPerformanceBo.setBalance(dcCustomerPerformanceBo.getBalance());
            dcCustomerPerformanceBo.setCity(dcCustomerPerformanceBo.getCity());
            dcCustomerPerformanceBo.setCreateBy(dcCustomerPerformanceBo.getCreateBy());
            dcCustomerPerformanceBo.setCreaterId(dcCustomerPerformanceBo.getCreaterId());
            dcCustomerPerformanceBo.setUpdateBy(LoginHelper.getUserId());
            dcCustomerPerformanceBo.setCreaterName(LoginHelper.getLoginUser() == null ? LoginHelper.getUsername() : LoginHelper.getLoginUser().getNickname());
            if (dcCustomerPerformanceBo.getId() == null) {
                dcCustomerPerformanceService.insertByBo(dcCustomerPerformanceBo);
                continue;
            }
            dcCustomerPerformanceService.updateByBo(dcCustomerPerformanceBo);
        }
        }
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCustomerTransfer entity) {
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
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        boolean flag = baseMapper.deleteByIds(ids) > 0;
        if (flag) {
            for (Long id : ids) {
                DcCustomerPerformanceBo dcCustomerPerformanceBo = new DcCustomerPerformanceBo();
                dcCustomerPerformanceBo.setTransferId(id);
                List<DcCustomerPerformanceVo> dcCustomerPerformanceVos = dcCustomerPerformanceService.queryList(dcCustomerPerformanceBo);
                for (DcCustomerPerformanceVo dcCustomerPerformanceVo : dcCustomerPerformanceVos) {
                    dcCustomerPerformanceService.deleteWithValidByIds(Collections.singletonList(dcCustomerPerformanceVo.getId()), true);
                }
            }
        }
        return flag;
    }

    @Override
    public Boolean audit(Long id, Integer auditStatus) {
        Long userId = LoginHelper.getUserId();
        LoginUser loginUser = LoginHelper.getLoginUser();
        DcCustomerTransfer dcCustomerTransfer = baseMapper.selectById(id);
        dcCustomerTransfer.setFinanceConfirmed(auditStatus);
        dcCustomerTransfer.setAuditUserId(userId);
        dcCustomerTransfer.setAuditUserName(loginUser == null ? "" : loginUser.getNickname());
        dcCustomerTransfer.setAuditTime(new Date());
        boolean flag = baseMapper.updateById(dcCustomerTransfer) > 0;
        if (flag && auditStatus == 1) {
            DcCustomerInformationBo dcCustomerInformation = new DcCustomerInformationBo();
            dcCustomerInformation.setSignDate(dcCustomerTransfer.getSignDate());
            dcCustomerInformation.setContractNo(dcCustomerTransfer.getContractOssId());
            dcCustomerInformation.setCustomerName(dcCustomerTransfer.getCompanyName());
            dcCustomerInformation.setPrincipal(dcCustomerTransfer.getContactPerson());
            dcCustomerInformation.setPrincipalPhone(dcCustomerTransfer.getContactInfo());
            dcCustomerInformation.setContractType(0);
            dcCustomerInformation.setPackageType(dcCustomerTransfer.getServiceType());
            dcCustomerInformation.setActualReceipt(dcCustomerTransfer.getActualPayment());
            dcCustomerInformation.setBalance(dcCustomerTransfer.getBalanceStatus());
            dcCustomerInformation.setStartDate(dcCustomerTransfer.getServiceStart());
            dcCustomerInformation.setExpireDate(dcCustomerTransfer.getServiceEnd());
            dcCustomerInformation.setTransferId(dcCustomerTransfer.getId());
            dcCustomerInformation.setAccountManagerId(dcCustomerTransfer.getAccountManagerId());
            dcCustomerInformation.setInviterId(dcCustomerTransfer.getInviterId());
            dcCustomerInformation.setServiceDuration(dcCustomerTransfer.getServiceDuration());
            dcCustomerInformation.setContractAmount(dcCustomerTransfer.getContractAmount());
            dcCustomerInformation.setContractCode(dcCustomerTransfer.getContractCode());
            dcCustomerInformation.setCustomerCity(dcCustomerTransfer.getCustomerCity());
            dcCustomerInformation.setCustomerType(1);
            SysUserVo inviter = sysUserService.selectUserById(dcCustomerTransfer.getInviterId());
            SysUserVo closer = sysUserService.selectUserById(dcCustomerTransfer.getAccountManagerId());
            dcCustomerInformation.setTransferPerson(inviter == null ? "" : inviter.getNickName());
            dcCustomerInformation.setCloser(closer == null ? "" : closer.getNickName());
            long customerId = dcCustomerInformationService.insertByBo(dcCustomerInformation);
            dcCustomerTransfer.setCustomerId(customerId);
            //dcCustomerTransfer.setAccountManagerId();
            baseMapper.updateById(dcCustomerTransfer);
            flag = customerId > 0;
        }
        return flag;
    }

    @Override
    public Boolean auditSecond(Long id, Integer auditStatus) {
        Long userId = LoginHelper.getUserId();
        LoginUser loginUser = LoginHelper.getLoginUser();
        DcCustomerTransfer dcCustomerTransfer = baseMapper.selectById(id);
        dcCustomerTransfer.setFinanceConfirmed(auditStatus);
        dcCustomerTransfer.setAuditUserId(userId);
        dcCustomerTransfer.setAuditUserName(loginUser == null ? "" : loginUser.getNickname());
        dcCustomerTransfer.setAuditTime(new Date());
        boolean flag = baseMapper.updateById(dcCustomerTransfer) > 0;
        if (flag && auditStatus == 1) {
            DcCustomerInformationLogBo dcCustomerInformation = new DcCustomerInformationLogBo();
            dcCustomerInformation.setSignDate(dcCustomerTransfer.getSignDate());
            dcCustomerInformation.setContractNo(dcCustomerTransfer.getContractOssId());
            dcCustomerInformation.setCustomerName(dcCustomerTransfer.getCompanyName());
            dcCustomerInformation.setPrincipal(dcCustomerTransfer.getContactPerson());
            dcCustomerInformation.setPrincipalPhone(dcCustomerTransfer.getContactInfo());
            dcCustomerInformation.setContractType(0);
            dcCustomerInformation.setPackageType(dcCustomerTransfer.getSecondDevelopmentType());
            dcCustomerInformation.setActualReceipt(dcCustomerTransfer.getActualPayment());
            dcCustomerInformation.setBalance(dcCustomerTransfer.getBalanceStatus());
            dcCustomerInformation.setStartDate(dcCustomerTransfer.getServiceStart());
            dcCustomerInformation.setExpireDate(dcCustomerTransfer.getServiceEnd());
            dcCustomerInformation.setTransferId(dcCustomerTransfer.getId());
            dcCustomerInformation.setAccountManagerId(dcCustomerTransfer.getAccountManagerId());
            dcCustomerInformation.setInviterId(dcCustomerTransfer.getInviterId());
            dcCustomerInformation.setServiceDuration(dcCustomerTransfer.getServiceDuration());
            dcCustomerInformation.setContractAmount(dcCustomerTransfer.getContractAmount());
            dcCustomerInformation.setContractCode(dcCustomerTransfer.getContractCode());
            dcCustomerInformation.setCustomerCity(dcCustomerTransfer.getCustomerCity());
            // 设置客户二次收费表customerId
            dcCustomerInformation.setCustomerInfoId(dcCustomerTransfer.getCustomerId());
            dcCustomerInformation.setCustomerType(1);
            SysUserVo inviter = sysUserService.selectUserById(dcCustomerTransfer.getInviterId());
            SysUserVo closer = sysUserService.selectUserById(dcCustomerTransfer.getAccountManagerId());
            dcCustomerInformation.setTransferPerson(inviter == null ? "" : inviter.getNickName());
            dcCustomerInformation.setCloser(closer == null ? "" : closer.getNickName());

            flag = dcCustomerInformationLogService.insertByBo(dcCustomerInformation);
        }
        return flag;
    }


    @Override
    public boolean updatePicture(Long Id, Long pictureId) {
        return baseMapper.update(null,
            new LambdaUpdateWrapper<DcCustomerTransfer>()
                .set(DcCustomerTransfer::getFinanceSignature, pictureId)
                .eq(DcCustomerTransfer::getId, Id)) > 0;
    }
}
