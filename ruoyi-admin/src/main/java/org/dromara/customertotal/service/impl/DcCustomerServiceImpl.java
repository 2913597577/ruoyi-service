package org.dromara.customertotal.service.impl;

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
import org.dromara.customertotal.domain.bo.DcCustomerBo;
import org.dromara.customertotal.domain.vo.DcCustomerVo;
import org.dromara.customertotal.domain.DcCustomer;
import org.dromara.customertotal.mapper.DcCustomerMapper;
import org.dromara.customertotal.service.IDcCustomerService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 全部客户Service业务层处理
 *
 * @author chang
 * @date 2025-07-08
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcCustomerServiceImpl implements IDcCustomerService {

    private final DcCustomerMapper baseMapper;

    /**
     * 查询全部客户
     *
     * @param id 主键
     * @return 全部客户
     */
    @Override
    public DcCustomerVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询全部客户列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 全部客户分页列表
     */
    @Override
    public TableDataInfo<DcCustomerVo> queryPageList(DcCustomerBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCustomer> lqw = buildQueryWrapper(bo);
        Page<DcCustomerVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的全部客户列表
     *
     * @param bo 查询条件
     * @return 全部客户列表
     */
    @Override
    public List<DcCustomerVo> queryList(DcCustomerBo bo) {
        LambdaQueryWrapper<DcCustomer> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomer> buildQueryWrapper(DcCustomerBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCustomer> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcCustomer::getId);
        lqw.eq(bo.getIntentionId() != null, DcCustomer::getIntentionId, bo.getIntentionId());
        lqw.eq(StringUtils.isNotBlank(bo.getCustomerType()), DcCustomer::getCustomerType, bo.getCustomerType());
        lqw.eq(bo.getLastFollowTime() != null, DcCustomer::getLastFollowTime, bo.getLastFollowTime());
        lqw.eq(StringUtils.isNotBlank(bo.getLegalStatus()), DcCustomer::getLegalStatus, bo.getLegalStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getContractUrl()), DcCustomer::getContractUrl, bo.getContractUrl());
        lqw.eq(bo.getArchiveTime() != null, DcCustomer::getArchiveTime, bo.getArchiveTime());
        lqw.eq(bo.getArchiveBy() != null, DcCustomer::getArchiveBy, bo.getArchiveBy());
        lqw.eq(StringUtils.isNotBlank(bo.getArchiveRemark()), DcCustomer::getArchiveRemark, bo.getArchiveRemark());
        lqw.eq(StringUtils.isNotBlank(bo.getArchiveResult()), DcCustomer::getArchiveResult, bo.getArchiveResult());
        lqw.eq(bo.getArchivePost() != null, DcCustomer::getArchivePost, bo.getArchivePost());
        lqw.eq(bo.getArchiveRole() != null, DcCustomer::getArchiveRole, bo.getArchiveRole());
        lqw.eq(StringUtils.isNotBlank(bo.getArchiveSource()), DcCustomer::getArchiveSource, bo.getArchiveSource());
        return lqw;
    }

    /**
     * 新增全部客户
     *
     * @param bo 全部客户
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcCustomerBo bo) {
        DcCustomer add = MapstructUtils.convert(bo, DcCustomer.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改全部客户
     *
     * @param bo 全部客户
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcCustomerBo bo) {
        DcCustomer update = MapstructUtils.convert(bo, DcCustomer.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCustomer entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除全部客户信息
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
