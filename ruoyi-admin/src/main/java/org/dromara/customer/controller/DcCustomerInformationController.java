package org.dromara.customer.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.customer.domain.bo.DcCustomerInformationBo;
import org.dromara.customer.domain.vo.DcCustomerInformationVo;
import org.dromara.customer.service.IDcCustomerInformationService;
import org.dromara.system.domain.vo.SysUserVo;
import org.dromara.system.service.ISysUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户总表
 *
 * @author Lion Li
 * @date 2025-09-06
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customerInfo/customerInfo")
public class DcCustomerInformationController extends BaseController {

    private final IDcCustomerInformationService dcCustomerInformationService;
    private final ISysUserService sysUserService;

    /**
     * 查询客户总表列表
     */
    @SaCheckPermission("customerInfo:customerInfo:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerInformationVo> list(DcCustomerInformationBo bo, PageQuery pageQuery) {
        return dcCustomerInformationService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户总表列表
     */
    @SaCheckPermission("customerInfo:customerInfo:export")
    @Log(title = "客户总表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerInformationBo bo, HttpServletResponse response) {
        List<DcCustomerInformationVo> list = dcCustomerInformationService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户总表", DcCustomerInformationVo.class, response);
    }

    /**
     * 获取客户总表详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customerInfo:customerInfo:query")
    @GetMapping("/{id}")
    public R<DcCustomerInformationVo> getInfo(@NotNull(message = "主键不能为空")
                                              @PathVariable Long id) {
        return R.ok(dcCustomerInformationService.queryById(id));
    }

    /**
     * 新增客户总表
     */
    @SaCheckPermission("customerInfo:customerInfo:add")
    @Log(title = "客户总表", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerInformationBo bo) {
        return toAjax(dcCustomerInformationService.insertByBo(bo));
    }

    /**
     * 修改客户总表
     */
    @SaCheckPermission("customerInfo:customerInfo:edit")
    @Log(title = "客户总表", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerInformationBo bo) {
        return toAjax(dcCustomerInformationService.updateByBo(bo));
    }

    /**
     * 删除客户总表
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customerInfo:customerInfo:remove")
    @Log(title = "客户总表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerInformationService.deleteWithValidByIds(List.of(ids), true));
    }

    /**
     * 分配法务支持
     */
    @SaCheckPermission("customerInfo:customerInfo:assign")
    @Log(title = "分配法务支持", businessType = BusinessType.UPDATE)
    @GetMapping("/assign")
    public R<Void> assign(@RequestParam(defaultValue = "0") String id, @RequestParam(defaultValue = "0") String userId) {

        DcCustomerInformationVo dcCustomerInformationVo = dcCustomerInformationService.queryById(Long.valueOf(id));
        if (dcCustomerInformationVo == null) {
            return R.warn("客户不存在");
        }
        if (Long.parseLong(userId) < 1) {
            return R.warn("请选择法务支持");
        }
        SysUserVo sysUserVo = sysUserService.selectUserById(Long.parseLong(userId));
        if (sysUserVo == null) {
            return R.warn("请选择法务支持");
        }
        // 1969581806504747009L 法务支持部门id
        if (sysUserVo.getDeptId() != 1969581806504747009L) {
            return R.warn("该员工不是法务支持员工");
        }
        dcCustomerInformationVo.setLawyerId(Long.parseLong(userId));
        DcCustomerInformationBo update = new DcCustomerInformationBo();
        MapstructUtils.convert(dcCustomerInformationVo, update);
        if (dcCustomerInformationService.updateByBo(update)) {
            return R.ok();
        }
        return R.warn("分配失败");
    }
}
