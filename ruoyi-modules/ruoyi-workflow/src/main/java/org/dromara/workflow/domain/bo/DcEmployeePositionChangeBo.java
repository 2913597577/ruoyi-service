package org.dromara.workflow.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.workflow.domain.DcEmployeePositionChange;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 员工岗位变动申请业务对象 dc_employee_position_change
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcEmployeePositionChange.class, reverseConvertGenerate = false)
public class DcEmployeePositionChangeBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 申请日期
     */
    @NotNull(message = "申请日期不能为空", groups = {AddGroup.class, EditGroup.class})
    private LocalDate applyDate;

    /**
     * 申请人
     */
    @NotBlank(message = "申请人不能为空", groups = {AddGroup.class, EditGroup.class})
    private String applicant;

    /**
     * 申请人所属部门
     */
    @NotBlank(message = "申请人所属部门不能为空", groups = {AddGroup.class, EditGroup.class})
    private String applicantDept;

    /**
     * 岗位变动类型
     */
    @NotBlank(message = "岗位变动类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private String changeType;

    /**
     * 员工姓名
     */
    @NotBlank(message = "员工姓名不能为空", groups = {AddGroup.class, EditGroup.class})
    private String employeeName;

    /**
     * 员工工号
     */
    @NotBlank(message = "员工工号不能为空", groups = {AddGroup.class, EditGroup.class})
    private String employeeId;

    /**
     * 变动前所属部门
     */
    @NotBlank(message = "变动前所属部门不能为空", groups = {AddGroup.class, EditGroup.class})
    private String originalDept;

    /**
     * 变动前职务
     */
    @NotBlank(message = "变动前职务不能为空", groups = {AddGroup.class, EditGroup.class})
    private String originalPosition;

    /**
     * 变动前职级
     */
    @NotBlank(message = "变动前职级不能为空", groups = {AddGroup.class, EditGroup.class})
    private String originalRank;

    /**
     * 入职公司日期
     */
    @NotNull(message = "入职公司日期不能为空", groups = {AddGroup.class, EditGroup.class})
    private LocalDate companyEntryDate;

    /**
     * 现岗位任职日期
     */
    @NotNull(message = "现岗位任职日期不能为空", groups = {AddGroup.class, EditGroup.class})
    private LocalDate currentPositionEntryDate;

    /**
     * 原录用合同附件路径
     */
    private String originalContractAttachment;

    /**
     * 拟调动至部门
     */
    private String targetDept;

    /**
     * 拟担任职务
     */
    private String targetPosition;

    /**
     * 拟定职级
     */
    private String targetRank;

    /**
     * 预计到岗日期
     */
    private LocalDate expectedReportDate;

    /**
     * 新变动合同附件路径
     */
    private String newContractAttachment;

    /**
     * 当前月度基本工资
     */
    @NotNull(message = "当前月度基本工资不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal currentMonthlySalary;

    /**
     * 当前年度总薪酬
     */
    @NotNull(message = "当前年度总薪酬不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal currentAnnualSalary;

    /**
     * 拟定月度基本工资
     */
    private BigDecimal targetMonthlySalary;

    /**
     * 拟定年度总薪酬
     */
    private BigDecimal targetAnnualSalary;

    /**
     * 薪酬调整幅度(%)
     */
    private BigDecimal salaryAdjustmentRate;

    /**
     * 其他福利变更
     */
    private String welfareChange;

    /**
     * 岗位变动主要原因
     */
    @NotBlank(message = "岗位变动主要原因不能为空", groups = {AddGroup.class, EditGroup.class})
    private String changeReasons;

    /**
     * 详细评估意见
     */
    private String evaluationOpinion;

    /**
     * 新岗位是否设置试用期
     */
    private String probationPeriod;

    /**
     * 原部门负责人意见
     */
    private String originalDeptManagerOpinion;

    /**
     * 用人部门负责人意见
     */
    private String targetDeptManagerOpinion;

    /**
     * 人事部门意见
     */
    private String hrDeptOpinion;

    /**
     * 员工本人确认
     */
    private String employeeConfirmation;

    /**
     * 最终生效日期
     */
    private LocalDate effectiveDate;

    /**
     * 人事处理人
     */
    private String hrHandler;

    /**
     * 人事归档记录附件路径
     */
    private String hrArchiveAttachment;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态
     */
    private String status;
}
