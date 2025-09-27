package org.dromara.staff.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.staff.domain.vo.DcStaffInfoVo;
import org.dromara.staff.domain.bo.DcStaffInfoBo;
import org.dromara.staff.service.IDcStaffInfoService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 员工档案
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/staffInfo/staffInfo")
public class DcStaffInfoController extends BaseController {

    private final IDcStaffInfoService dcStaffInfoService;

    /**
     * 查询员工档案列表
     */
    @SaCheckPermission("staffInfo:staffInfo:list")
    @GetMapping("/list")
    public TableDataInfo<DcStaffInfoVo> list(DcStaffInfoBo bo, PageQuery pageQuery) {
        return dcStaffInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出员工档案列表
     */
    @SaCheckPermission("staffInfo:staffInfo:export")
    @Log(title = "员工档案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcStaffInfoBo bo, HttpServletResponse response) {
        List<DcStaffInfoVo> list = dcStaffInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "员工档案", DcStaffInfoVo.class, response);
    }

    /**
     * 获取员工档案详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("staffInfo:staffInfo:query")
    @GetMapping("/{id}")
    public R<DcStaffInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcStaffInfoService.queryById(id));
    }

    /**
     * 新增员工档案
     */
    @SaCheckPermission("staffInfo:staffInfo:add")
    @Log(title = "员工档案", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcStaffInfoBo bo) {
        return toAjax(dcStaffInfoService.insertByBo(bo));
    }

    /**
     * 修改员工档案
     */
    @SaCheckPermission("staffInfo:staffInfo:edit")
    @Log(title = "员工档案", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcStaffInfoBo bo) {
        return toAjax(dcStaffInfoService.updateByBo(bo));
    }

    /**
     * 删除员工档案
     *
     * @param ids 主键串
     */
    @SaCheckPermission("staffInfo:staffInfo:remove")
    @Log(title = "员工档案", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcStaffInfoService.deleteWithValidByIds(List.of(ids), true));
    }
}
