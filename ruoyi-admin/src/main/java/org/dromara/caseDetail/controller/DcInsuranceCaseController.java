package org.dromara.caseDetail.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.caseDetail.domain.bo.DcInsuranceCaseBo;
import org.dromara.caseDetail.domain.vo.DcInsuranceCaseVo;
import org.dromara.caseDetail.service.IDcInsuranceCaseService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 保险记录表
 *
 * @author Lion Li
 * @date 2025-09-29
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/insuranceCase/insuranceCase")
public class DcInsuranceCaseController extends BaseController {

    private final IDcInsuranceCaseService dcInsuranceCaseService;

    /**
     * 查询保险记录表列表
     */
    @SaCheckPermission("insuranceCase:insuranceCase:list")
    @GetMapping("/list")
    public TableDataInfo<DcInsuranceCaseVo> list(DcInsuranceCaseBo bo, PageQuery pageQuery) {
        return dcInsuranceCaseService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出保险记录表列表
     */
    @SaCheckPermission("insuranceCase:insuranceCase:export")
    @Log(title = "保险记录表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcInsuranceCaseBo bo, HttpServletResponse response) {
        List<DcInsuranceCaseVo> list = dcInsuranceCaseService.queryList(bo);
        ExcelUtil.exportExcel(list, "保险记录表", DcInsuranceCaseVo.class, response);
    }

    /**
     * 获取保险记录表详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("insuranceCase:insuranceCase:query")
    @GetMapping("/{id}")
    public R<DcInsuranceCaseVo> getInfo(@NotNull(message = "主键不能为空")
                                        @PathVariable Long id) {
        return R.ok(dcInsuranceCaseService.queryById(id));
    }

    /**
     * 新增保险记录表
     */
    @SaCheckPermission("insuranceCase:insuranceCase:add")
    @Log(title = "保险记录表", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcInsuranceCaseBo bo) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.fail("请先登录");
        }

        return toAjax(dcInsuranceCaseService.insertByBo(bo));
    }

    /**
     * 修改保险记录表
     */
    @SaCheckPermission("insuranceCase:insuranceCase:edit")
    @Log(title = "保险记录表", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcInsuranceCaseBo bo) {
        return toAjax(dcInsuranceCaseService.updateByBo(bo));
    }

    /**
     * 删除保险记录表
     *
     * @param ids 主键串
     */
    @SaCheckPermission("insuranceCase:insuranceCase:remove")
    @Log(title = "保险记录表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcInsuranceCaseService.deleteWithValidByIds(List.of(ids), true));
    }
}
