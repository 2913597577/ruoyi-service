package org.dromara.customer.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.common.core.domain.dto.RoleDTO;
import org.dromara.common.core.utils.StringUtils;
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
import org.dromara.customer.domain.vo.DcCustomerIntentionTrackingVo;
import org.dromara.customer.domain.bo.DcCustomerIntentionTrackingBo;
import org.dromara.customer.service.IDcCustomerIntentionTrackingService;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.satoken.utils.LoginHelper;


/**
 * 意向客户跟踪记录
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customerIntentionTracking/customerIntentionTracking")
public class DcCustomerIntentionTrackingController extends BaseController {

    private final IDcCustomerIntentionTrackingService dcCustomerIntentionTrackingService;

    /**
     * 查询意向客户跟踪记录列表
     */
    @SaCheckPermission("customerIntentionTracking:customerIntentionTracking:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerIntentionTrackingVo> list(DcCustomerIntentionTrackingBo bo, PageQuery pageQuery) {

        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return null;
        }

        List<RoleDTO> roles = loginUser.getRoles();
        String roleKey = (roles != null && !roles.isEmpty()) ? roles.get(0).getRoleKey() : null;

        if ("LegalSupport_Employee".equals(roleKey)) {
            bo.setLegalSupportId(loginUser.getUserId());
        } else if ("LegalSupport_Manager".equals(roleKey)) {
            String deptCategory = loginUser.getDeptCategory();
            if (StringUtils.isNotBlank(deptCategory) && !"ADMIN".equals(deptCategory)) {
                String city = deptCategory.substring(0, deptCategory.indexOf('_'));
                bo.setRemark1(city);
            }
        }

        return dcCustomerIntentionTrackingService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出意向客户跟踪记录列表
     */
    @SaCheckPermission("customerIntentionTracking:customerIntentionTracking:export")
    @Log(title = "意向客户跟踪记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerIntentionTrackingBo bo, HttpServletResponse response) {
        List<DcCustomerIntentionTrackingVo> list = dcCustomerIntentionTrackingService.queryList(bo);
        ExcelUtil.exportExcel(list, "意向客户跟踪记录", DcCustomerIntentionTrackingVo.class, response);
    }

    /**
     * 获取意向客户跟踪记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customerIntentionTracking:customerIntentionTracking:query")
    @GetMapping("/{id}")
    public R<DcCustomerIntentionTrackingVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dcCustomerIntentionTrackingService.queryById(id));
    }

    /**
     * 新增意向客户跟踪记录
     */
    @SaCheckPermission("customerIntentionTracking:customerIntentionTracking:add")
    @Log(title = "意向客户跟踪记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerIntentionTrackingBo bo) {
        return toAjax(dcCustomerIntentionTrackingService.insertByBo(bo));
    }

    /**
     * 修改意向客户跟踪记录
     */
    @SaCheckPermission("customerIntentionTracking:customerIntentionTracking:edit")
    @Log(title = "意向客户跟踪记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerIntentionTrackingBo bo) {
        return toAjax(dcCustomerIntentionTrackingService.updateByBo(bo));
    }

    /**
     * 删除意向客户跟踪记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customerIntentionTracking:customerIntentionTracking:remove")
    @Log(title = "意向客户跟踪记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerIntentionTrackingService.deleteWithValidByIds(List.of(ids), true));
    }
}
