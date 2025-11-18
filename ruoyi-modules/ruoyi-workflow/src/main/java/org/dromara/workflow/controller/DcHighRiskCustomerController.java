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
import org.dromara.workflow.domain.bo.DcHighRiskCustomerBo;
import org.dromara.workflow.domain.vo.DcHighRiskCustomerVo;
import org.dromara.workflow.service.IDcHighRiskCustomerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 高风险客户记录
 */
@ConditionalOnEnable
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/high/risk/customer")
public class DcHighRiskCustomerController extends BaseController {

    private final IDcHighRiskCustomerService dcHighRiskCustomerService;

    /**
     * 查询高风险客户记录列表
     */
    @SaCheckPermission("workflow:highRiskCustomer:list")
    @GetMapping("/list")
    public TableDataInfo<DcHighRiskCustomerVo> list(DcHighRiskCustomerBo bo, PageQuery pageQuery) {
        return dcHighRiskCustomerService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出高风险客户记录列表
     */
    @SaCheckPermission("workflow:highRiskCustomer:export")
    @Log(title = "高风险客户记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcHighRiskCustomerBo bo, HttpServletResponse response) {
        List<DcHighRiskCustomerVo> list = dcHighRiskCustomerService.queryList(bo);
        ExcelUtil.exportExcel(list, "高风险客户记录", DcHighRiskCustomerVo.class, response);
    }

    /**
     * 获取高风险客户记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:highRiskCustomer:query")
    @GetMapping("/{id}")
    public R<DcHighRiskCustomerVo> getInfo(@NotNull(message = "主键不能为空")
                                           @PathVariable Long id) {
        return R.ok(dcHighRiskCustomerService.queryById(id));
    }

    /**
     * 新增高风险客户记录
     */
    @SaCheckPermission("workflow:highRiskCustomer:add")
    @Log(title = "高风险客户记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<DcHighRiskCustomerVo> add(@Validated(AddGroup.class) @RequestBody DcHighRiskCustomerBo bo) {
        return R.ok(dcHighRiskCustomerService.insertByBo(bo));
    }

    /**
     * 修改高风险客户记录
     */
    @SaCheckPermission("workflow:highRiskCustomer:edit")
    @Log(title = "高风险客户记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<DcHighRiskCustomerVo> edit(@Validated(EditGroup.class) @RequestBody DcHighRiskCustomerBo bo) {
        return R.ok(dcHighRiskCustomerService.updateByBo(bo));
    }

    /**
     * 删除高风险客户记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:highRiskCustomer:remove")
    @Log(title = "高风险客户记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcHighRiskCustomerService.deleteWithValidByIds(List.of(ids)));
    }
}
