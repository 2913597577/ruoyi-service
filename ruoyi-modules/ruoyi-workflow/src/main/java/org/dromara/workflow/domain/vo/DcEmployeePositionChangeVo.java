package org.dromara.workflow.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.workflow.domain.DcEmployeePositionChange;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * 员工岗位变动申请视图对象 dc_employee_position_change
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcEmployeePositionChange.class)
public class DcEmployeePositionChangeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 申请日期
     */
    @ExcelProperty(value = "申请日期")
    private LocalDate applyDate;

    /**
     * 申请人
     */
    @ExcelProperty(value = "申请人")
    private String applicant;

    /**
     * 申请人所属部门
     */
    @ExcelProperty(value = "申请人所属部门")
    private String applicantDept;

    /**
     * 岗位变动类型
     */
    @ExcelProperty(value = "岗位变动类型")
    private String changeType;

    /**
     * 员工姓名
     */
    @ExcelProperty(value = "员工姓名")
    private String employeeName;

    /**
     * 员工工号
     */
    @ExcelProperty(value = "员工工号")
    private String employeeId;

    /**
     * 变动前所属部门
     */
    @ExcelProperty(value = "变动前所属部门")
    private String originalDept;

    /**
     * 变动前职务
     */
    @ExcelProperty(value = "变动前职务")
    private String originalPosition;

    /**
     * 变动前职级
     */
    @ExcelProperty(value = "变动前职级")
    private String originalRank;

    /**
     * 入职公司日期
     */
    @ExcelProperty(value = "入职公司日期")
    private LocalDate companyEntryDate;

    /**
     * 现岗位任职日期
     */
    @ExcelProperty(value = "现岗位任职日期")
    private LocalDate currentPositionEntryDate;

    /**
     * 原录用合同附件路径
     */
    @ExcelProperty(value = "原录用合同附件路径")
    private String originalContractAttachment;

    /**
     * 拟调动至部门
     */
    @ExcelProperty(value = "拟调动至部门")
    private String targetDept;

    /**
     * 拟担任职务
     */
    @ExcelProperty(value = "拟担任职务")
    private String targetPosition;

    /**
     * 拟定职级
     */
    @ExcelProperty(value = "拟定职级")
    private String targetRank;

    /**
     * 预计到岗日期
     */
    @ExcelProperty(value = "预计到岗日期")
    private LocalDate expectedReportDate;

    /**
     * 新变动合同附件路径
     */
    @ExcelProperty(value = "新变动合同附件路径")
    private String newContractAttachment;

    /**
     * 当前月度基本工资
     */
    @ExcelProperty(value = "当前月度基本工资")
    private BigDecimal currentMonthlySalary;

    /**
     * 当前年度总薪酬
     */
    @ExcelProperty(value = "当前年度总薪酬")
    private BigDecimal currentAnnualSalary;

    /**
     * 拟定月度基本工资
     */
    @ExcelProperty(value = "拟定月度基本工资")
    private BigDecimal targetMonthlySalary;

    /**
     * 拟定年度总薪酬
     */
    @ExcelProperty(value = "拟定年度总薪酬")
    private BigDecimal targetAnnualSalary;

    /**
     * 薪酬调整幅度(%)
     */
    @ExcelProperty(value = "薪酬调整幅度(%)")
    private BigDecimal salaryAdjustmentRate;

    /**
     * 其他福利变更
     */
    @ExcelProperty(value = "其他福利变更")
    private String welfareChange;

    /**
     * 岗位变动主要原因
     */
    @ExcelProperty(value = "岗位变动主要原因")
    private String changeReasons;

    /**
     * 详细评估意见
     */
    @ExcelProperty(value = "详细评估意见")
    private String evaluationOpinion;

    /**
     * 新岗位是否设置试用期
     */
    @ExcelProperty(value = "新岗位是否设置试用期")
    private String probationPeriod;

    /**
     * 原部门负责人意见
     */
    @ExcelProperty(value = "原部门负责人意见")
    private String originalDeptManagerOpinion;

    /**
     * 用人部门负责人意见
     */
    @ExcelProperty(value = "用人部门负责人意见")
    private String targetDeptManagerOpinion;

    /**
     * 人事部门意见
     */
    @ExcelProperty(value = "人事部门意见")
    private String hrDeptOpinion;

    /**
     * 员工本人确认
     */
    @ExcelProperty(value = "员工本人确认")
    private String employeeConfirmation;

    /**
     * 最终生效日期
     */
    @ExcelProperty(value = "最终生效日期")
    private LocalDate effectiveDate;

    /**
     * 人事处理人
     */
    @ExcelProperty(value = "人事处理人")
    private String hrHandler;

    /**
     * 人事归档记录附件路径
     */
    @ExcelProperty(value = "人事归档记录附件路径")
    private String hrArchiveAttachment;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remarks;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
