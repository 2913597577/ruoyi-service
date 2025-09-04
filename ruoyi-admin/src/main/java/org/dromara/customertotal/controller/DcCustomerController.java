package org.dromara.customertotal.controller;

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
import org.dromara.customertotal.domain.vo.DcCustomerVo;
import org.dromara.customertotal.domain.bo.DcCustomerBo;
import org.dromara.customertotal.service.IDcCustomerService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 全部客户
 *
 * @author chang
 * @date 2025-07-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customertotal/customertotal")
public class DcCustomerController extends BaseController {

    private final IDcCustomerService dcCustomerService;

    /**
     * 查询全部客户列表
     */
    @SaCheckPermission("customertotal:customertotal:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerVo> list(DcCustomerBo bo, PageQuery pageQuery) {
        return dcCustomerService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出全部客户列表
     */
    @SaCheckPermission("customertotal:customertotal:export")
    @Log(title = "全部客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerBo bo, HttpServletResponse response) {
        List<DcCustomerVo> list = dcCustomerService.queryList(bo);
        ExcelUtil.exportExcel(list, "全部客户", DcCustomerVo.class, response);
    }

    /**
     * 获取全部客户详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customertotal:customertotal:query")
    @GetMapping("/{id}")
    public R<DcCustomerVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcCustomerService.queryById(id));
    }

    /**
     * 新增全部客户
     */
    @SaCheckPermission("customertotal:customertotal:add")
    @Log(title = "全部客户", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerBo bo) {
        return toAjax(dcCustomerService.insertByBo(bo));
    }

    /**
     * 修改全部客户
     */
    @SaCheckPermission("customertotal:customertotal:edit")
    @Log(title = "全部客户", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerBo bo) {
        return toAjax(dcCustomerService.updateByBo(bo));
    }

    /**
     * 删除全部客户
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customertotal:customertotal:remove")
    @Log(title = "全部客户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerService.deleteWithValidByIds(List.of(ids), true));
    }
}
