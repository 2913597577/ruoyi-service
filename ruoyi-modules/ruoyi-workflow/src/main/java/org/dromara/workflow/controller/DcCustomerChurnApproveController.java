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
import org.dromara.workflow.domain.bo.DcCustomerChurnApproveBo;
import org.dromara.workflow.domain.vo.DcCustomerChurnApproveVo;
import org.dromara.workflow.service.IDcCustomerChurnApproveService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户流失审批
 */
@ConditionalOnEnable
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/customer/churn/approve")
public class DcCustomerChurnApproveController extends BaseController {

    private final IDcCustomerChurnApproveService dcCustomerChurnApproveService;

    /**
     * 查询客户流失审批列表
     */
    @SaCheckPermission("workflow:customerChurnApprove:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerChurnApproveVo> list(DcCustomerChurnApproveBo bo, PageQuery pageQuery) {
        return dcCustomerChurnApproveService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户流失审批列表
     */
    @SaCheckPermission("workflow:customerChurnApprove:export")
    @Log(title = "客户流失审批", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerChurnApproveBo bo, HttpServletResponse response) {
        List<DcCustomerChurnApproveVo> list = dcCustomerChurnApproveService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户流失审批", DcCustomerChurnApproveVo.class, response);
    }

    /**
     * 获取客户流失审批详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:customerChurnApprove:query")
    @GetMapping("/{id}")
    public R<DcCustomerChurnApproveVo> getInfo(@NotNull(message = "主键不能为空")
                                               @PathVariable Long id) {
        return R.ok(dcCustomerChurnApproveService.queryById(id));
    }

    /**
     * 新增客户流失审批
     */
    @SaCheckPermission("workflow:customerChurnApprove:add")
    @Log(title = "客户流失审批", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<DcCustomerChurnApproveVo> add(@Validated(AddGroup.class) @RequestBody DcCustomerChurnApproveBo bo) {
        return R.ok(dcCustomerChurnApproveService.insertByBo(bo));
    }

    /**
     * 修改客户流失审批
     */
    @SaCheckPermission("workflow:customerChurnApprove:edit")
    @Log(title = "客户流失审批", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<DcCustomerChurnApproveVo> edit(@Validated(EditGroup.class) @RequestBody DcCustomerChurnApproveBo bo) {
        return R.ok(dcCustomerChurnApproveService.updateByBo(bo));
    }

    /**
     * 删除客户流失审批
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:customerChurnApprove:remove")
    @Log(title = "客户流失审批", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerChurnApproveService.deleteWithValidByIds(List.of(ids)));
    }
}
