package org.dromara.staff.service.impl;

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
import org.dromara.staff.domain.bo.DcStaffInfoBo;
import org.dromara.staff.domain.vo.DcStaffInfoVo;
import org.dromara.staff.domain.DcStaffInfo;
import org.dromara.staff.mapper.DcStaffInfoMapper;
import org.dromara.staff.service.IDcStaffInfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 员工档案Service业务层处理
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DcStaffInfoServiceImpl implements IDcStaffInfoService {

    private final DcStaffInfoMapper baseMapper;

    /**
     * 查询员工档案
     *
     * @param id 主键
     * @return 员工档案
     */
    @Override
    public DcStaffInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询员工档案列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 员工档案分页列表
     */
    @Override
    public TableDataInfo<DcStaffInfoVo> queryPageList(DcStaffInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DcStaffInfo> lqw = buildQueryWrapper(bo);
        Page<DcStaffInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的员工档案列表
     *
     * @param bo 查询条件
     * @return 员工档案列表
     */
    @Override
    public List<DcStaffInfoVo> queryList(DcStaffInfoBo bo) {
        LambdaQueryWrapper<DcStaffInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DcStaffInfo> buildQueryWrapper(DcStaffInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DcStaffInfo> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DcStaffInfo::getId);
        lqw.eq(bo.getUserId() != null, DcStaffInfo::getUserId, bo.getUserId());
        lqw.like(StringUtils.isNotBlank(bo.getName()), DcStaffInfo::getName, bo.getName());
        lqw.eq(bo.getSex() != null, DcStaffInfo::getSex, bo.getSex());
        lqw.eq(bo.getDeptId() != null, DcStaffInfo::getDeptId, bo.getDeptId());
        lqw.eq(bo.getPositionId() != null, DcStaffInfo::getPositionId, bo.getPositionId());
        lqw.like(StringUtils.isNotBlank(bo.getPositionName()), DcStaffInfo::getPositionName, bo.getPositionName());
        lqw.eq(StringUtils.isNotBlank(bo.getPositionRank()), DcStaffInfo::getPositionRank, bo.getPositionRank());
        lqw.like(StringUtils.isNotBlank(bo.getWorkLocation()), DcStaffInfo::getWorkLocation, bo.getWorkLocation());
        lqw.like(StringUtils.isNotBlank(bo.getTeam()), DcStaffInfo::getTeam, bo.getTeam());
        return lqw;
    }

    /**
     * 新增员工档案
     *
     * @param bo 员工档案
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DcStaffInfoBo bo) {
        DcStaffInfo add = MapstructUtils.convert(bo, DcStaffInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改员工档案
     *
     * @param bo 员工档案
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DcStaffInfoBo bo) {
        DcStaffInfo update = MapstructUtils.convert(bo, DcStaffInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DcStaffInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除员工档案信息
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
