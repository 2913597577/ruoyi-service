package org.dromara.legalSupport.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.web.core.BaseController;
import org.dromara.customer.service.IDcCustomerInformationService;
import org.dromara.legalSupport.domain.bo.DcCustomerJobOrderBo;
import org.dromara.legalSupport.domain.vo.DcCustomerJobOrderVo;
import org.dromara.legalSupport.service.IDcCustomerJobOrderService;
import org.dromara.myCustomer.domain.vo.DcCustomerTrackingVo;
import org.dromara.myCustomer.service.IDcCustomerTrackingService;
import org.dromara.myCustomer.service.IDcCustomerTransferService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工单管理
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customerJobOrder/customerJobOrder")
public class DcCustomerJobOrderController extends BaseController {

    private final IDcCustomerJobOrderService dcCustomerJobOrderService;

    private final IDcCustomerTrackingService dcCustomerTrackingService;

    private final IDcCustomerInformationService customerInformationService;

    private final IDcCustomerTransferService dcCustomerTransferService;

    /**
     * 查询工单管理列表
     */
    @SaCheckPermission("customerJobOrder:customerJobOrder:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerJobOrderVo> list(DcCustomerJobOrderBo bo, PageQuery pageQuery) {
        return dcCustomerJobOrderService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出工单管理列表
     */
    @SaCheckPermission("customerJobOrder:customerJobOrder:export")
    @Log(title = "工单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerJobOrderBo bo, HttpServletResponse response) {
        List<DcCustomerJobOrderVo> list = dcCustomerJobOrderService.queryList(bo);
        ExcelUtil.exportExcel(list, "工单管理", DcCustomerJobOrderVo.class, response);
    }

    /**
     * 获取工单管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customerJobOrder:customerJobOrder:query")
    @GetMapping("/{id}")
    public R<DcCustomerJobOrderVo> getInfo(@NotNull(message = "主键不能为空")
                                           @PathVariable Long id) {
        return R.ok(dcCustomerJobOrderService.queryById(id));
    }

    /**
     * 新增工单管理
     */
    @SaCheckPermission("customerJobOrder:customerJobOrder:add")
    @Log(title = "工单管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerJobOrderBo bo) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("请登录");
        }
        DcCustomerTrackingVo vo = dcCustomerTrackingService.queryById(bo.getTrackingId());
        if (vo == null) {
            return R.warn("跟踪记录不存在");
        }
        bo.setLegalSupportId(loginUser.getUserId());
        bo.setLegalSupport(loginUser.getUsername());
        return toAjax(dcCustomerJobOrderService.insertByBo(bo));
    }

    /**
     * 修改工单管理
     */
    @SaCheckPermission("customerJobOrder:customerJobOrder:edit")
    @Log(title = "工单管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerJobOrderBo bo) {
        return toAjax(dcCustomerJobOrderService.updateByBo(bo));
    }

    /**
     * 删除工单管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customerJobOrder:customerJobOrder:remove")
    @Log(title = "工单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerJobOrderService.deleteWithValidByIds(List.of(ids), true));
    }
}
