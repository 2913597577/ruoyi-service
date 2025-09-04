package org.dromara.lawperson.service.impl;

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
import org.dromara.lawperson.domain.bo.DcLegalSupportBo;
import org.dromara.lawperson.domain.vo.DcLegalSupportVo;
import org.dromara.lawperson.domain.DcLegalSupport;
import org.dromara.lawperson.mapper.DcLegalSupportMapper;
import org.dromara.lawperson.service.IDcLegalSupportService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 法务支持人员Service业务层处理
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcLegalSupportServiceImpl implements IDcLegalSupportService {

    private final DcLegalSupportMapper baseMapper;

    /**
     * 查询法务支持人员
     *
     * @param id 主键
     * @return 法务支持人员
     */
    @Override
    public DcLegalSupportVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询法务支持人员列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 法务支持人员分页列表
     */
    @Override
    public TableDataInfo<DcLegalSupportVo> queryPageList(DcLegalSupportBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcLegalSupport> lqw = buildQueryWrapper(bo);
        Page<DcLegalSupportVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的法务支持人员列表
     *
     * @param bo 查询条件
     * @return 法务支持人员列表
     */
    @Override
    public List<DcLegalSupportVo> queryList(DcLegalSupportBo bo) {
        LambdaQueryWrapper<DcLegalSupport> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcLegalSupport> buildQueryWrapper(DcLegalSupportBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcLegalSupport> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcLegalSupport::getId);
        lqw.eq(bo.getUserId() != null, DcLegalSupport::getUserId, bo.getUserId());
        lqw.eq(bo.getSupportLevel() != null, DcLegalSupport::getSupportLevel, bo.getSupportLevel());
        lqw.eq(bo.getMaxCaseCount() != null, DcLegalSupport::getMaxCaseCount, bo.getMaxCaseCount());
        lqw.eq(bo.getCurrentCaseCount() != null, DcLegalSupport::getCurrentCaseCount, bo.getCurrentCaseCount());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), DcLegalSupport::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增法务支持人员
     *
     * @param bo 法务支持人员
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcLegalSupportBo bo) {
        DcLegalSupport add = MapstructUtils.convert(bo, DcLegalSupport.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改法务支持人员
     *
     * @param bo 法务支持人员
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcLegalSupportBo bo) {
        DcLegalSupport update = MapstructUtils.convert(bo, DcLegalSupport.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcLegalSupport entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除法务支持人员信息
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
