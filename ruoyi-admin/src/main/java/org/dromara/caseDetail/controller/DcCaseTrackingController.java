package org.dromara.caseDetail.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.caseDetail.domain.bo.DcCaseTrackingBo;
import org.dromara.caseDetail.domain.vo.DcCaseTrackingVo;
import org.dromara.caseDetail.domain.vo.DcDebtCaseVo;
import org.dromara.caseDetail.service.IDcCaseTrackingService;
import org.dromara.caseDetail.service.IDcDebtCaseService;
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
import org.dromara.myCustomer.domain.vo.DcCustomerTransferVo;
import org.dromara.myCustomer.service.IDcCustomerTransferService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 案件进展表
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/caseProgress/caseProgress")
public class DcCaseTrackingController extends BaseController {

    private final IDcCaseTrackingService dcCaseTrackingService;
    private final IDcDebtCaseService dcDebtCaseService;
    private final IDcCustomerTransferService dcCustomerTransferService;

    /**
     * 查询案件进展表列表
     */
    @SaCheckPermission("caseProgress:caseProgress:list")
    @GetMapping("/list")
    public TableDataInfo<DcCaseTrackingVo> list(DcCaseTrackingBo bo, PageQuery pageQuery) {
        return dcCaseTrackingService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出案件进展表列表
     */
    @SaCheckPermission("caseProgress:caseProgress:export")
    @Log(title = "案件进展表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCaseTrackingBo bo, HttpServletResponse response) {
        List<DcCaseTrackingVo> list = dcCaseTrackingService.queryList(bo);
        ExcelUtil.exportExcel(list, "案件进展表", DcCaseTrackingVo.class, response);
    }

    /**
     * 获取案件进展表详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("caseProgress:caseProgress:query")
    @GetMapping("/{id}")
    public R<DcCaseTrackingVo> getInfo(@NotNull(message = "主键不能为空")
                                       @PathVariable Long id) {
        return R.ok(dcCaseTrackingService.queryById(id));
    }

    /**
     * 新增案件进展表
     */
    @SaCheckPermission("caseProgress:caseProgress:add")
    @Log(title = "案件进展表", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCaseTrackingBo bo) {
        DcDebtCaseVo dcDebtCaseVo = dcDebtCaseService.queryById(bo.getCaseId());
        if (dcDebtCaseVo == null) {
            return R.warn("案件不存在");
        }
        DcCustomerTransferVo dcCustomerTransferVo = dcCustomerTransferService.queryById(dcDebtCaseVo.getCustomerId());
        if (dcCustomerTransferVo == null) {
            return R.warn("案件客户信息不存在");
        }
        bo.setCustomerId(dcCustomerTransferVo.getId());
        bo.setCustomerName(dcCustomerTransferVo.getCompanyName());
        return toAjax(dcCaseTrackingService.insertByBo(bo));
    }

    /**
     * 修改案件进展表
     */
    @SaCheckPermission("caseProgress:caseProgress:edit")
    @Log(title = "案件进展表", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCaseTrackingBo bo) {
        return toAjax(dcCaseTrackingService.updateByBo(bo));
    }

    /**
     * 删除案件进展表
     *
     * @param ids 主键串
     */
    @SaCheckPermission("caseProgress:caseProgress:remove")
    @Log(title = "案件进展表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCaseTrackingService.deleteWithValidByIds(List.of(ids), true));
    }
}
