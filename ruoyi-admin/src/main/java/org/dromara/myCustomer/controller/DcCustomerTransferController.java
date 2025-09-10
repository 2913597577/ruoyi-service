package org.dromara.myCustomer.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.io.FileUtil;
import com.aizuda.snailjob.common.core.model.Result;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.utils.file.MimeTypeUtils;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.helper.DataPermissionHelper;
import org.dromara.common.web.core.BaseController;
import org.dromara.customer.service.impl.DcCustomerInformationServiceImpl;
import org.dromara.myCustomer.domain.bo.DcCustomerTransferBo;
import org.dromara.myCustomer.domain.vo.DcCustomerTransferVo;
import org.dromara.myCustomer.service.IDcCustomerTransferService;
import org.dromara.system.domain.vo.SysOssVo;
import org.dromara.system.service.ISysOssService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * 客户信息录入
 *
 * @author Lion Li
 * @date 2025-09-06
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/myCustomer/customerTransfer")
public class DcCustomerTransferController extends BaseController {

    private final IDcCustomerTransferService dcCustomerTransferService;
    private final DcCustomerInformationServiceImpl dcCustomerInformationService;
    private final ISysOssService ossService;

    /**
     * 查询客户信息录入列表
     */
    @SaCheckPermission("myCustomer:customerTransfer:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerTransferVo> list(DcCustomerTransferBo bo, PageQuery pageQuery) {
//        LoginUser loginUser = LoginHelper.getLoginUser();
//        if (loginUser==null){
//            return null;
//        }
//        long deptId = loginUser.getDeptId();
//        SysDeptVo deptVo= sysDeptService.selectDeptById(deptId);


        return dcCustomerTransferService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户信息录入列表
     */
    @SaCheckPermission("myCustomer:customerTransfer:export")
    @Log(title = "客户信息录入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerTransferBo bo, HttpServletResponse response) {
        List<DcCustomerTransferVo> list = dcCustomerTransferService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户信息录入", DcCustomerTransferVo.class, response);
    }

    /**
     * 获取客户信息录入详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("myCustomer:customerTransfer:query")
    @GetMapping("/{id}")
    public R<DcCustomerTransferVo> getInfo(@NotNull(message = "主键不能为空")
                                           @PathVariable Long id) {
        return R.ok(dcCustomerTransferService.queryById(id));
    }

    /**
     * 新增客户信息录入
     */
    @SaCheckPermission("myCustomer:customerTransfer:add")
    @Log(title = "客户信息录入", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerTransferBo bo) {
        return toAjax(dcCustomerTransferService.insertByBo(bo));
    }

    /**
     * 修改客户信息录入
     */
    @SaCheckPermission("myCustomer:customerTransfer:edit")
    @Log(title = "客户信息录入", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerTransferBo bo) {
        DcCustomerTransferVo dcCustomerTransferVo = dcCustomerTransferService.queryById(bo.getId());
        if (dcCustomerTransferVo.getFinanceConfirmed() == 1) {
            return R.warn("财务审核通过，不允许修改");
        }
        return toAjax(dcCustomerTransferService.updateByBo(bo));
    }

    /**
     * 删除客户信息录入
     *
     * @param ids 主键串
     */
    @SaCheckPermission("myCustomer:customerTransfer:remove")
    @Log(title = "客户信息录入", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        for (Long id : ids) {
            DcCustomerTransferVo dcCustomerTransferVo = dcCustomerTransferService.queryById(id);
            if (dcCustomerTransferVo.getFinanceConfirmed() == 1) {
                return R.warn("存在财务已审核的数据,不允许删除");
            }
        }
        return toAjax(dcCustomerTransferService.deleteWithValidByIds(List.of(ids), true));
    }

    @Log(title = "客户信息审核", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping(value = "/audit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<AvatarVo> audit(@RequestParam Long id, @RequestParam Integer auditStatus, @RequestPart("pictureUrl") MultipartFile pictureUrl) {
        DcCustomerTransferVo dcCustomerTransferVo = dcCustomerTransferService.queryById(id);
        if (dcCustomerTransferVo == null) {
            return R.warn("客户信息不存在");
        }
        boolean updateSuccess = false;
        String url = null;
        if (!pictureUrl.isEmpty()) {
            String extension = FileUtil.extName(pictureUrl.getOriginalFilename());
            if (!StringUtils.equalsAnyIgnoreCase(extension, MimeTypeUtils.IMAGE_EXTENSION)) {
                return R.fail("文件格式不正确，请上传" + Arrays.toString(MimeTypeUtils.IMAGE_EXTENSION) + "格式");
            }
            SysOssVo oss = ossService.upload(pictureUrl);
            url = oss.getUrl();
            Result result = dcCustomerInformationService.queryListByTransferId(id);
            if (result.getStatus() != 200) {
                return R.warn(result.getMessage());
            }

            if (dcCustomerTransferService.audit(id, auditStatus)) {
                updateSuccess = DataPermissionHelper.ignore(() -> dcCustomerTransferService.updatePicture(id, oss.getOssId()));
            }

        }
        if (updateSuccess) {
            return R.ok(new DcCustomerTransferController.AvatarVo(url));
        }
        return R.fail("审核失败，请联系管理员");
    }

    public record AvatarVo(String imgUrl) {
    }
}
