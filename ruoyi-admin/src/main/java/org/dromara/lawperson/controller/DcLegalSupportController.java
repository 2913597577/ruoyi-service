package org.dromara.lawperson.controller;

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
import org.dromara.lawperson.domain.vo.DcLegalSupportVo;
import org.dromara.lawperson.domain.bo.DcLegalSupportBo;
import org.dromara.lawperson.service.IDcLegalSupportService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 法务支持人员
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lawperson/lawPerson")
public class DcLegalSupportController extends BaseController {

    private final IDcLegalSupportService dcLegalSupportService;

    /**
     * 查询法务支持人员列表
     */
    @SaCheckPermission("lawperson:lawPerson:list")
    @GetMapping("/list")
    public TableDataInfo<DcLegalSupportVo> list(DcLegalSupportBo bo, PageQuery pageQuery) {
        return dcLegalSupportService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出法务支持人员列表
     */
    @SaCheckPermission("lawperson:lawPerson:export")
    @Log(title = "法务支持人员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcLegalSupportBo bo, HttpServletResponse response) {
        List<DcLegalSupportVo> list = dcLegalSupportService.queryList(bo);
        ExcelUtil.exportExcel(list, "法务支持人员", DcLegalSupportVo.class, response);
    }

    /**
     * 获取法务支持人员详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lawperson:lawPerson:query")
    @GetMapping("/{id}")
    public R<DcLegalSupportVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcLegalSupportService.queryById(id));
    }

    /**
     * 新增法务支持人员
     */
    @SaCheckPermission("lawperson:lawPerson:add")
    @Log(title = "法务支持人员", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcLegalSupportBo bo) {
        return toAjax(dcLegalSupportService.insertByBo(bo));
    }

    /**
     * 修改法务支持人员
     */
    @SaCheckPermission("lawperson:lawPerson:edit")
    @Log(title = "法务支持人员", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcLegalSupportBo bo) {
        return toAjax(dcLegalSupportService.updateByBo(bo));
    }

    /**
     * 删除法务支持人员
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lawperson:lawPerson:remove")
    @Log(title = "法务支持人员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcLegalSupportService.deleteWithValidByIds(List.of(ids), true));
    }
}
