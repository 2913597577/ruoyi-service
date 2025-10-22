package org.dromara.customer.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.core.utils.MapstructUtils;
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
import org.dromara.customer.domain.bo.DcCustomerInformationBo;
import org.dromara.customer.domain.bo.DcCustomerIntentionBo;
import org.dromara.customer.domain.vo.DcCustomerInformationVo;
import org.dromara.customer.domain.vo.DcCustomerIntentionVo;
import org.dromara.customer.service.IDcCustomerInformationService;
import org.dromara.customer.service.IDcCustomerIntentionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户意向登记
 *
 * @author Lion Li
 * @date 2025-09-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customerIntention/customerIntention")
public class DcCustomerIntentionController extends BaseController {

    private final IDcCustomerIntentionService dcCustomerIntentionService;

    private final IDcCustomerInformationService dcCustomerInformationService;

    /**
     * 查询客户意向登记列表
     */
    @SaCheckPermission("customerIntention:customerIntention:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerIntentionVo> list(DcCustomerIntentionBo bo, PageQuery pageQuery) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return null;
        }
        // 法务支持
        if (loginUser.getRoleId() == 1980464458593992706L) {
            bo.setLegalSupportId(loginUser.getUserId());
        }
        return dcCustomerIntentionService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户意向登记列表
     */
    @SaCheckPermission("customerIntention:customerIntention:export")
    @Log(title = "客户意向登记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerIntentionBo bo, HttpServletResponse response) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return;
        }
        // 法务支持
        if (loginUser.getRoleId() == 1980464458593992706L) {
            bo.setLegalSupportId(loginUser.getUserId());
        }
        List<DcCustomerIntentionVo> list = dcCustomerIntentionService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户意向登记", DcCustomerIntentionVo.class, response);
    }

    /**
     * 获取客户意向登记详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customerIntention:customerIntention:query")
    @GetMapping("/{id}")
    public R<DcCustomerIntentionVo> getInfo(@NotNull(message = "主键不能为空")
                                            @PathVariable Long id) {
        return R.ok(dcCustomerIntentionService.queryById(id));
    }

    /**
     * 新增客户意向登记
     */
    @SaCheckPermission("customerIntention:customerIntention:add")
    @Log(title = "客户意向登记", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerIntentionBo bo) {
        return toAjax(dcCustomerIntentionService.insertByBo(bo));
    }

    /**
     * 新增客户意向登记
     */
    @SaCheckPermission("customerIntention:customerIntention:add")
    @Log(title = "转为意向客户", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/addIntention")
    public R<Void> addIntention(@Validated(AddGroup.class) @RequestBody DcCustomerIntentionBo bo) {
        Long customerId = bo.getIntroducerId();
        if (customerId == null) {
            return R.warn("介绍人不能为空");
        }
        DcCustomerInformationVo customerInformation = dcCustomerInformationService.queryListByTransferId(customerId);
        if (customerInformation == null) {
            return R.warn("介绍人信息不存在");
        }
        if (!dcCustomerIntentionService.insertByBo(bo)) {
            return R.warn("客户介绍意向客户失败");
        }
        customerInformation.setIsIntention(1);
        DcCustomerInformationBo update = new DcCustomerInformationBo();
        MapstructUtils.convert(customerInformation, update);
        dcCustomerInformationService.updateByBo(update);
        return R.ok();
    }

    /**
     * 修改客户意向登记
     */
    @SaCheckPermission("customerIntention:customerIntention:edit")
    @Log(title = "客户意向登记", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerIntentionBo bo) {
        return toAjax(dcCustomerIntentionService.updateByBo(bo));
    }

    /**
     * 删除客户意向登记
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customerIntention:customerIntention:remove")
    @Log(title = "客户意向登记", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerIntentionService.deleteWithValidByIds(List.of(ids), true));
    }
}
