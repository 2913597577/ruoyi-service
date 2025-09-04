package org.dromara.customertrace.service.impl;

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
import org.dromara.customertrace.domain.bo.DcCustomerFollowBo;
import org.dromara.customertrace.domain.vo.DcCustomerFollowVo;
import org.dromara.customertrace.domain.DcCustomerFollow;
import org.dromara.customertrace.mapper.DcCustomerFollowMapper;
import org.dromara.customertrace.service.IDcCustomerFollowService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 客户跟进记录Service业务层处理
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcCustomerFollowServiceImpl implements IDcCustomerFollowService {

    private final DcCustomerFollowMapper baseMapper;

    /**
     * 查询客户跟进记录
     *
     * @param id 主键
     * @return 客户跟进记录
     */
    @Override
    public DcCustomerFollowVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询客户跟进记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户跟进记录分页列表
     */
    @Override
    public TableDataInfo<DcCustomerFollowVo> queryPageList(DcCustomerFollowBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcCustomerFollow> lqw = buildQueryWrapper(bo);
        Page<DcCustomerFollowVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的客户跟进记录列表
     *
     * @param bo 查询条件
     * @return 客户跟进记录列表
     */
    @Override
    public List<DcCustomerFollowVo> queryList(DcCustomerFollowBo bo) {
        LambdaQueryWrapper<DcCustomerFollow> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcCustomerFollow> buildQueryWrapper(DcCustomerFollowBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcCustomerFollow> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcCustomerFollow::getId);
        lqw.eq(bo.getCustomerId() != null, DcCustomerFollow::getCustomerId, bo.getCustomerId());
        lqw.eq(StringUtils.isNotBlank(bo.getFollowType()), DcCustomerFollow::getFollowType, bo.getFollowType());
        lqw.eq(StringUtils.isNotBlank(bo.getFollowContent()), DcCustomerFollow::getFollowContent, bo.getFollowContent());
        lqw.eq(bo.getNextFollowTime() != null, DcCustomerFollow::getNextFollowTime, bo.getNextFollowTime());
        lqw.eq(bo.getFollowBy() != null, DcCustomerFollow::getFollowBy, bo.getFollowBy());
        return lqw;
    }

    /**
     * 新增客户跟进记录
     *
     * @param bo 客户跟进记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcCustomerFollowBo bo) {
        DcCustomerFollow add = MapstructUtils.convert(bo, DcCustomerFollow.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改客户跟进记录
     *
     * @param bo 客户跟进记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcCustomerFollowBo bo) {
        DcCustomerFollow update = MapstructUtils.convert(bo, DcCustomerFollow.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcCustomerFollow entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除客户跟进记录信息
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
