package org.dromara.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.customer.domain.DcCustomerInformation;
import org.dromara.customer.domain.DcCustomerInformationLog;
import org.dromara.customer.domain.bo.DcCustomerInformationLogBo;
import org.dromara.customer.domain.vo.DcCustomerInformationLogVo;
import org.dromara.customer.mapper.DcCustomerInformationLogMapper;
import org.dromara.customer.service.IDcCustomerInformationLogService;
import org.dromara.myCustomer.domain.DcCustomerTransfer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 客户信息记录Service业务层处理
 *
 * @author Lion Li
 * @date 2025-11-16
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcCustomerInformationLogServiceImpl implements IDcCustomerInformationLogService {

    private final DcCustomerInformationLogMapper baseMapper;

    /**
     * 查询客户信息记录
     *
     * @param id 主键
     * @return 客户信息记录
     */
    @Override
    public DcCustomerInformationLogVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询客户信息记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户信息记录分页列表
     */
    @Override
    public TableDataInfo<DcCustomerInformationLogVo> queryPageList(DcCustomerInformationLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCustomerInformationLog> lqw = buildQueryWrapper(bo);
        Page<DcCustomerInformationLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的客户信息记录列表
     *
     * @param bo 查询条件
     * @return 客户信息记录列表
     */
    @Override
    public List<DcCustomerInformationLogVo> queryList(DcCustomerInformationLogBo bo) {
        LambdaQueryWrapper<DcCustomerInformationLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomerInformationLog> buildQueryWrapper(DcCustomerInformationLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCustomerInformationLog> lqw = Wrappers.lambdaQuery();
        lqw.orderByDesc(DcCustomerInformationLog::getCreateTime);
        lqw.eq(StringUtils.isNotBlank(bo.getPrincipal()), DcCustomerInformationLog::getPrincipal, bo.getPrincipal());
        lqw.eq(bo.getLawyerId() != null, DcCustomerInformationLog::getLawyerId, bo.getLawyerId());
        lqw.eq(bo.getContractType() != null, DcCustomerInformationLog::getContractType, bo.getContractType());
        lqw.eq(bo.getPackageType() != null, DcCustomerInformationLog::getPackageType, bo.getPackageType());
        lqw.eq(bo.getIsIntention() != null, DcCustomerInformationLog::getIsIntention, bo.getIsIntention());
        lqw.eq(bo.getIsRisk() != null, DcCustomerInformationLog::getIsRisk, bo.getIsRisk());
        lqw.eq(bo.getIsRefund() != null, DcCustomerInformationLog::getIsRefund, bo.getIsRefund());
        lqw.eq(bo.getCustomerType() != null, DcCustomerInformationLog::getCustomerType, bo.getCustomerType());
        lqw.eq(StringUtils.isNotBlank(bo.getCustomerCity()), DcCustomerInformationLog::getCustomerCity, bo.getCustomerCity());
        lqw.eq(StringUtils.isNotBlank(bo.getTransferPerson()), DcCustomerInformationLog::getTransferPerson, bo.getTransferPerson());
        lqw.eq(bo.getIsAssigned() != null, DcCustomerInformationLog::getIsAssigned, bo.getIsAssigned());
        lqw.eq(bo.getCustomerInfoId() != null, DcCustomerInformationLog::getCustomerInfoId, bo.getCustomerInfoId());
        lqw.eq(bo.getTransferId() != null, DcCustomerInformationLog::getTransferId, bo.getTransferId());
        lqw.eq(bo.getSignDate() != null, DcCustomerInformationLog::getSignDate, bo.getSignDate());
        lqw.ge(bo.getContractAmount() != null, DcCustomerInformationLog::getContractAmount, bo.getContractAmount());
        lqw.ge(bo.getActualReceipt() != null, DcCustomerInformationLog::getActualReceipt, bo.getActualReceipt());
        lqw.ge(bo.getBalance() != null, DcCustomerInformationLog::getBalance, bo.getBalance());
        lqw.ge(bo.getStartDate() != null, DcCustomerInformationLog::getStartDate, bo.getStartDate());
        lqw.le(bo.getExpireDate() != null, DcCustomerInformationLog::getExpireDate, bo.getExpireDate());

        // 获取前端传入的到期时间范围参数
        if (params != null) {
            lqw.ge(params.get("beginExpireDate") != null,
                DcCustomerInformationLog::getExpireDate,
                params.get("beginExpireDate"));
            lqw.le(params.get("endExpireDate") != null,
                DcCustomerInformationLog::getExpireDate,
                params.get("endExpireDate"));
        }

        lqw.apply(StringUtils.isNotBlank(bo.getSignDateMonth()),
            "DATE_FORMAT(sign_date, '%Y-%m') = {0}", bo.getSignDateMonth());

        return lqw;
    }

    /**
     * 新增客户信息记录
     *
     * @param bo 客户信息记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcCustomerInformationLogBo bo) {
        DcCustomerInformationLog add = MapstructUtils.convert(bo, DcCustomerInformationLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改客户信息记录
     *
     * @param bo 客户信息记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcCustomerInformationLogBo bo) {
        DcCustomerInformationLog update = MapstructUtils.convert(bo, DcCustomerInformationLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCustomerInformationLog entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除客户信息记录信息
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
}
