package org.dromara.laworder.controller;

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
import org.dromara.laworder.domain.vo.DcLegalOrderVo;
import org.dromara.laworder.domain.bo.DcLegalOrderBo;
import org.dromara.laworder.service.IDcLegalOrderService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 法务接单记录
 *
 * @author Lion Li
 * @date 2025-07-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/laworder/lawOrder")
public class DcLegalOrderController extends BaseController {

    private final IDcLegalOrderService dcLegalOrderService;

    /**
     * 查询法务接单记录列表
     */
    @SaCheckPermission("laworder:lawOrder:list")
    @GetMapping("/list")
    public TableDataInfo<DcLegalOrderVo> list(DcLegalOrderBo bo, PageQuery pageQuery) {
        return dcLegalOrderService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出法务接单记录列表
     */
    @SaCheckPermission("laworder:lawOrder:export")
    @Log(title = "法务接单记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcLegalOrderBo bo, HttpServletResponse response) {
        List<DcLegalOrderVo> list = dcLegalOrderService.queryList(bo);
        ExcelUtil.exportExcel(list, "法务接单记录", DcLegalOrderVo.class, response);
    }

    /**
     * 获取法务接单记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("laworder:lawOrder:query")
    @GetMapping("/{id}")
    public R<DcLegalOrderVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcLegalOrderService.queryById(id));
    }

    /**
     * 新增法务接单记录
     */
    @SaCheckPermission("laworder:lawOrder:add")
    @Log(title = "法务接单记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcLegalOrderBo bo) {
        return toAjax(dcLegalOrderService.insertByBo(bo));
    }

    /**
     * 修改法务接单记录
     */
    @SaCheckPermission("laworder:lawOrder:edit")
    @Log(title = "法务接单记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcLegalOrderBo bo) {
        return toAjax(dcLegalOrderService.updateByBo(bo));
    }

    /**
     * 删除法务接单记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("laworder:lawOrder:remove")
    @Log(title = "法务接单记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcLegalOrderService.deleteWithValidByIds(List.of(ids), true));
    }
}
