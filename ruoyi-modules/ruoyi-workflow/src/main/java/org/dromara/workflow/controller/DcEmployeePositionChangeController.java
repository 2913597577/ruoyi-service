package org.dromara.workflow.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.workflow.common.ConditionalOnEnable;
import org.dromara.workflow.domain.bo.DcEmployeePositionChangeBo;
import org.dromara.workflow.domain.vo.DcEmployeePositionChangeVo;
import org.dromara.workflow.service.IDcEmployeePositionChangeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工岗位变动申请
 */
@ConditionalOnEnable
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/employee/position/change")
public class DcEmployeePositionChangeController extends BaseController {

    private final IDcEmployeePositionChangeService dcEmployeePositionChangeService;

    /**
     * 查询员工岗位变动申请列表
     */
    @SaCheckPermission("workflow:employeePositionChange:list")
    @GetMapping("/list")
    public TableDataInfo<DcEmployeePositionChangeVo> list(DcEmployeePositionChangeBo bo, PageQuery pageQuery) {
        return dcEmployeePositionChangeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出员工岗位变动申请列表
     */
    @SaCheckPermission("workflow:employeePositionChange:export")
    @Log(title = "员工岗位变动申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcEmployeePositionChangeBo bo, HttpServletResponse response) {
        List<DcEmployeePositionChangeVo> list = dcEmployeePositionChangeService.queryList(bo);
        ExcelUtil.exportExcel(list, "员工岗位变动申请", DcEmployeePositionChangeVo.class, response);
    }

    /**
     * 获取员工岗位变动申请详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:employeePositionChange:query")
    @GetMapping("/{id}")
    public R<DcEmployeePositionChangeVo> getInfo(@NotNull(message = "主键不能为空")
                                                 @PathVariable Long id) {
        return R.ok(dcEmployeePositionChangeService.queryById(id));
    }

    /**
     * 新增员工岗位变动申请
     */
    @SaCheckPermission("workflow:employeePositionChange:add")
    @Log(title = "员工岗位变动申请", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<DcEmployeePositionChangeVo> add(@Validated(AddGroup.class) @RequestBody DcEmployeePositionChangeBo bo) {
        return R.ok(dcEmployeePositionChangeService.insertByBo(bo));
    }

    /**
     * 修改员工岗位变动申请
     */
    @SaCheckPermission("workflow:employeePositionChange:edit")
    @Log(title = "员工岗位变动申请", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<DcEmployeePositionChangeVo> edit(@Validated(EditGroup.class) @RequestBody DcEmployeePositionChangeBo bo) {
        return R.ok(dcEmployeePositionChangeService.updateByBo(bo));
    }

    /**
     * 删除员工岗位变动申请
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:employeePositionChange:remove")
    @Log(title = "员工岗位变动申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcEmployeePositionChangeService.deleteWithValidByIds(List.of(ids)));
    }
}
