package org.dromara.myCustomer.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.io.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.domain.dto.RoleDTO;
import org.dromara.common.core.domain.model.LoginUser;
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
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.web.core.BaseController;
import org.dromara.customer.domain.vo.DcCustomerInformationVo;
import org.dromara.customer.service.impl.DcCustomerInformationServiceImpl;
import org.dromara.financial.domain.bo.DcFinancialStatisticsBo;
import org.dromara.financial.service.impl.DcFinancialStatisticsServiceImpl;
import org.dromara.myCustomer.domain.bo.DcCustomerTransferBo;
import org.dromara.myCustomer.domain.vo.DcCustomerTransferVo;
import org.dromara.myCustomer.service.IDcCustomerTransferService;
import org.dromara.performance.domain.bo.DcCustomerPerformanceBo;
import org.dromara.system.domain.vo.SysDeptVo;
import org.dromara.system.domain.vo.SysOssVo;
import org.dromara.system.service.ISysDeptService;
import org.dromara.system.service.ISysOssService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    private final DcFinancialStatisticsServiceImpl dcFinancialStatisticsService;
    private final ISysDeptService sysDeptService;
    private final ISysOssService ossService;

    /**
     * 查询客户信息录入列表
     */
    @SaCheckPermission("myCustomer:customerTransfer:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerTransferVo> list(DcCustomerTransferBo bo, PageQuery pageQuery) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return null;
        }
        String deptCategory = loginUser.getDeptCategory();
        String city = null;
        if (StringUtils.isNotBlank(deptCategory) && !("ADMIN").equals(deptCategory)) {
            city = deptCategory.substring(0, deptCategory.indexOf('_'));
            bo.setCustomerCity(city);
        }
        List<RoleDTO> roles = loginUser.getRoles();
        if (roles != null && !roles.isEmpty()) {
            RoleDTO role = roles.get(0);
            if (role.getRoleKey() != null && !role.getRoleKey().contains("FinanceCenter")) {
                if (role.getRoleKey().contains("Employee")) {
                    bo.setCreateBy(loginUser.getUserId());
                    bo.setCustomerCity(null);
                }
                if (role.getRoleKey().contains("Manager")) {
                    bo.setCreateDept(loginUser.getDeptId());
                    bo.setCustomerCity(null);
                }
            }
            if (role.getRoleKey() != null && role.getRoleKey().contains("FinanceCenter")) {
                SysDeptVo dept1 = sysDeptService.selectDeptByCode(city + "_LegalSupport");
                SysDeptVo dept2 = sysDeptService.selectDeptByCode(city + "_SalesCenter");
                SysDeptVo dept3 = sysDeptService.selectDeptByCode(city + "_FinanceCenter");
                SysDeptVo dept4 = sysDeptService.selectDeptByCode(city + "_PersonnelCenter");
                List<Long> deptIds = new ArrayList<>();
                if (dept1 != null) {
                    deptIds.add(dept1.getDeptId());
                }
                if (dept2 != null) {
                    deptIds.add(dept2.getDeptId());
                }
                if (dept3 != null) {
                    deptIds.add(dept3.getDeptId());
                }
                if (dept4 != null) {
                    deptIds.add(dept4.getDeptId());
                }
                if (!deptIds.isEmpty()) {
                    bo.setDeptIds(deptIds);
                    bo.setCustomerCity(null);
                }
            }
        }

        return dcCustomerTransferService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户信息录入列表
     */
    @SaCheckPermission("myCustomer:customerTransfer:export")
    @Log(title = "客户信息录入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerTransferBo bo, HttpServletResponse response) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return;
        }
        String deptCategory = loginUser.getDeptCategory();
        String city = null;
        if (StringUtils.isNotBlank(deptCategory) && !("ADMIN").equals(deptCategory)) {
            city = deptCategory.substring(0, deptCategory.indexOf('_'));
            bo.setCustomerCity(city);
        }
        List<RoleDTO> roles = loginUser.getRoles();
        if (roles != null && !roles.isEmpty()) {
            RoleDTO role = roles.get(0);
            if (role.getRoleKey() != null && !role.getRoleKey().contains("FinanceCenter")) {
                if (role.getRoleKey().contains("Employee")) {
                    bo.setCreateBy(loginUser.getUserId());
                    bo.setCustomerCity(null);
                }
                if (role.getRoleKey().contains("Manager")) {
                    bo.setCreateDept(loginUser.getDeptId());
                    bo.setCustomerCity(null);
                }
            }
            if (role.getRoleKey() != null && role.getRoleKey().contains("FinanceCenter")) {
                SysDeptVo dept1 = sysDeptService.selectDeptByCode(city + "_LegalSupport");
                SysDeptVo dept2 = sysDeptService.selectDeptByCode(city + "_SalesCenter");
                SysDeptVo dept3 = sysDeptService.selectDeptByCode(city + "_FinanceCenter");
                SysDeptVo dept4 = sysDeptService.selectDeptByCode(city + "_PersonnelCenter");
                List<Long> deptIds = new ArrayList<>();
                if (dept1 != null) {
                    deptIds.add(dept1.getDeptId());
                }
                if (dept2 != null) {
                    deptIds.add(dept2.getDeptId());
                }
                if (dept3 != null) {
                    deptIds.add(dept3.getDeptId());
                }
                if (dept4 != null) {
                    deptIds.add(dept4.getDeptId());
                }
                if (!deptIds.isEmpty()) {
                    bo.setDeptIds(deptIds);
                    bo.setCustomerCity(null);
                }

            }
        }

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
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("请先登录");
        }
        BigDecimal total = BigDecimal.ZERO;
        if (bo.getActualPayment() != null) {
            total = bo.getActualPayment().add(bo.getBalanceStatus() == null ? BigDecimal.ZERO : bo.getBalanceStatus());
        }

        // 计算 performanceInfo 中的 balance 总和
        BigDecimal performanceTotal = BigDecimal.ZERO;
        for (DcCustomerPerformanceBo dcCustomerPerformanceBo : bo.getPerformanceInfo()) {
            if (dcCustomerPerformanceBo.getBalance() != null) {
                performanceTotal = performanceTotal.add(dcCustomerPerformanceBo.getBalance());
            }
        }

        // 检查 performance balance 总和是否超过 total
        if (performanceTotal.compareTo(total) > 0) {
            return R.warn("业绩分配总额不能超过合同总金额");
        }
        bo.setFinanceConfirmed(0);
        bo.setInviterId(loginUser.getUserId());
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
        if (dcCustomerTransferVo.getFinanceConfirmed() != null && dcCustomerTransferVo.getFinanceConfirmed() == 1) {
            return R.warn("财务审核通过，不允许修改");
        }
        BigDecimal total = BigDecimal.ZERO;
        if (bo.getActualPayment() != null) {
            total = bo.getActualPayment().add(bo.getBalanceStatus() == null ? BigDecimal.ZERO : bo.getBalanceStatus());
        }

        // 计算 performanceInfo 中的 balance 总和
        BigDecimal performanceTotal = BigDecimal.ZERO;
        for (DcCustomerPerformanceBo dcCustomerPerformanceBo : bo.getPerformanceInfo()) {
            if (dcCustomerPerformanceBo.getBalance() != null) {
                performanceTotal = performanceTotal.add(dcCustomerPerformanceBo.getBalance());
            }
        }

        // 检查 performance balance 总和是否超过 total
        if (performanceTotal.compareTo(total) > 0) {
            return R.warn("业绩分配总额不能超过合同总金额");
        }
        bo.setFinanceConfirmed(0);
        bo.setAuditUserName(null);
        bo.setAuditUserId(null);
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
    public R<AvatarVo> audit(@RequestParam Long id,
                             @RequestParam Integer auditStatus,
                             @RequestParam Integer isSecondaryCharge,
                             @RequestPart(value = "pictureUrl", required = false) MultipartFile pictureUrl) {
        DcCustomerTransferVo dcCustomerTransferVo = dcCustomerTransferService.queryById(id);
        if (dcCustomerTransferVo == null) {
            return R.warn("客户信息不存在");
        }

        if (auditStatus == 2) { //
            if (dcCustomerTransferService.audit(id, auditStatus)) {
                return R.ok(new DcCustomerTransferController.AvatarVo(null));
            } else {
                return R.fail("审核失败，请联系管理员");
            }
        }

        // 通过审核时需要图片
        if (pictureUrl == null || pictureUrl.isEmpty()) {
            return R.fail("通过审核时必须上传图片");
        }

        boolean updateSuccess = false;
        String url = null;

        String extension = FileUtil.extName(pictureUrl.getOriginalFilename());
        if (!StringUtils.equalsAnyIgnoreCase(extension, MimeTypeUtils.IMAGE_EXTENSION)) {
            return R.fail("文件格式不正确，请上传" + Arrays.toString(MimeTypeUtils.IMAGE_EXTENSION) + "格式");
        }

        SysOssVo oss = ossService.upload(pictureUrl);
        url = oss.getUrl();

        boolean updateStatus = false;
        if (isSecondaryCharge == 0) {
            DcCustomerInformationVo result = dcCustomerInformationService.queryListByTransferId(id);
            if (result != null) {
                return R.warn("客户总表已存在");
            }
            updateStatus = dcCustomerTransferService.audit(id, auditStatus);
        }
        if (isSecondaryCharge == 1) {
            updateStatus = dcCustomerTransferService.auditSecond(id, auditStatus);
        }

        if (updateStatus) {
            updateSuccess = DataPermissionHelper.ignore(() -> dcCustomerTransferService.updatePicture(id, oss.getOssId()));
            DcFinancialStatisticsBo dcFinancialStatisticsBo = new DcFinancialStatisticsBo();
            dcFinancialStatisticsBo.setContractNo(dcCustomerTransferVo.getContractCode());
            dcFinancialStatisticsBo.setBalance(new BigDecimal(dcCustomerTransferVo.getActualPayment()));
            dcFinancialStatisticsBo.setFlowTime(dcCustomerTransferVo.getCreateTime());
            dcFinancialStatisticsBo.setSourceType("actual_receipt");
            dcFinancialStatisticsBo.setCity(dcCustomerTransferVo.getCustomerCity());
            dcFinancialStatisticsBo.setFinancialType(1L);
            dcFinancialStatisticsBo.setRemark("客户实收");
            dcFinancialStatisticsBo.setCreateBy(LoginHelper.getUserId());
            dcFinancialStatisticsBo.setUpdateBy(LoginHelper.getUserId());
            dcFinancialStatisticsBo.setCreateDept(LoginHelper.getDeptId());
            dcFinancialStatisticsBo.setCreateTime(new Date());
            dcFinancialStatisticsBo.setUpdateTime(new Date());
            dcFinancialStatisticsService.insertByBo(dcFinancialStatisticsBo);
        }

        if (updateSuccess) {
            return R.ok(new DcCustomerTransferController.AvatarVo(url));
        }
        return R.fail("审核失败，请联系管理员");
    }


    @Log(title = "提交个人签名", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping(value = "/auditPicUrl", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<AvatarVo> auditPicUrl(@RequestParam Long id, @RequestPart("pictureUrl") MultipartFile pictureUrl) {

        if (!pictureUrl.isEmpty()) {
            String extension = FileUtil.extName(pictureUrl.getOriginalFilename());
            if (!StringUtils.equalsAnyIgnoreCase(extension, MimeTypeUtils.IMAGE_EXTENSION)) {
                return R.fail("文件格式不正确，请上传" + Arrays.toString(MimeTypeUtils.IMAGE_EXTENSION) + "格式");
            }
            SysOssVo oss = ossService.upload(pictureUrl);
            String url = oss.getUrl();
            boolean updateSuccess = DataPermissionHelper.ignore(() -> dcCustomerTransferService.updatePicture(id, oss.getOssId()));
            if (updateSuccess) {
                return R.ok(new DcCustomerTransferController.AvatarVo(url));
            }
        }

        return R.fail("审核失败，请联系管理员");
    }

    public record AvatarVo(String imgUrl) {
    }
}
