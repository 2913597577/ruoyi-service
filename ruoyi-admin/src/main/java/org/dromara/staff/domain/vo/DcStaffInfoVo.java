package org.dromara.staff.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.staff.domain.DcStaffInfo;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 员工档案视图对象 dc_staff_info
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcStaffInfo.class)
public class DcStaffInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @ExcelProperty(value = "自增主键")
    private Long id;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
     * 员工姓名
     */
    @ExcelProperty(value = "员工姓名")
    private String name;

    /**
     * 电子邮箱
     */
    @ExcelProperty(value = "电子邮箱")
    private String email;

    /**
     * 手机号码
     */
    @ExcelProperty(value = "手机号码")
    private Long mobile;

    /**
     * 性别:1男,2女,0未知
     */
    @ExcelProperty(value = "性别:1男,2女,0未知")
    private Integer sex;

    /**
     * 别名
     */
    @ExcelProperty(value = "别名")
    private String nickname;

    /**
     * 员工照片
     */
    @ExcelProperty(value = "员工照片")
    private Long thumb;

    /**
     * 部门id
     */
    @ExcelProperty(value = "部门id")
    private Long deptId;

    /**
     * 上级主管id
     */
    @ExcelProperty(value = "上级主管id")
    private Long pid;

    /**
     * 职位id
     */
    @ExcelProperty(value = "职位id")
    private Long positionId;

    /**
     * 职务
     */
    @ExcelProperty(value = "职务")
    private String positionName;

    /**
     * 职级
     */
    @ExcelProperty(value = "职级")
    private String positionRank;

    /**
     * 员工类型:0未设置,1正式,2试用,3实习
     */
    @ExcelProperty(value = "员工类型:0未设置,1正式,2试用,3实习")
    private String type;

    /**
     * 身份类型:0未设置,1企业员工,2劳务派遣,3兼职员工
     */
    @ExcelProperty(value = "身份类型:0未设置,1企业员工,2劳务派遣,3兼职员工")
    private String isStaff;

    /**
     * 工号
     */
    @ExcelProperty(value = "工号")
    private String jobNumber;

    /**
     * 生日
     */
    @ExcelProperty(value = "生日")
    private Date birthday;

    /**
     * 年龄
     */
    @ExcelProperty(value = "年龄")
    private Integer age;

    /**
     * 开始工作时间
     */
    @ExcelProperty(value = "开始工作时间")
    private Date workDate;

    /**
     * 工作地点
     */
    @ExcelProperty(value = "工作地点")
    private String workLocation;

    /**
     * 工作团队
     */
    @ExcelProperty(value = "工作团队")
    private String team;

    /**
     * 籍贯
     */
    @ExcelProperty(value = "籍贯")
    private String nativePlace;

    /**
     * 民族
     */
    @ExcelProperty(value = "民族")
    private String nation;

    /**
     * 家庭地址
     */
    @ExcelProperty(value = "家庭地址")
    private String homeAddress;

    /**
     * 现居地址
     */
    @ExcelProperty(value = "现居地址")
    private String currentAddress;

    /**
     * 紧急联系人
     */
    @ExcelProperty(value = "紧急联系人")
    private String contact;

    /**
     * 紧急联系人电话
     */
    @ExcelProperty(value = "紧急联系人电话")
    private String contactMobile;

    /**
     * 户口性质:0未设置,1农村户口,2城镇户口
     */
    @ExcelProperty(value = "户口性质:0未设置,1农村户口,2城镇户口")
    private String residentType;

    /**
     * 户口所在地
     */
    @ExcelProperty(value = "户口所在地")
    private String residentPlace;

    /**
     * 毕业学校
     */
    @ExcelProperty(value = "毕业学校")
    private String graduateSchool;

    /**
     * 毕业日期（时间戳，0表示未填写）
     */
    @ExcelProperty(value = "毕业日期", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "时=间戳，0表示未填写")
    private Date graduateDay;

    /**
     * 政治面貌:0未设置,1中共党员,2团员
     */
    @ExcelProperty(value = "政治面貌:0未设置,1中共党员,2团员")
    private String political;

    /**
     * 婚姻状况:0未设置,1未婚,2已婚,3离异
     */
    @ExcelProperty(value = "婚姻状况:0未设置,1未婚,2已婚,3离异")
    private String maritalStatus;

    /**
     * 身份证
     */
    @ExcelProperty(value = "身份证")
    private String idcard;

    /**
     * 学位
     */
    @ExcelProperty(value = "学位")
    private String education;

    /**
     * 专业
     */
    @ExcelProperty(value = "专业")
    private String speciality;

    /**
     * 社保账号
     */
    @ExcelProperty(value = "社保账号")
    private String socialAccount;

    /**
     * 医保账号
     */
    @ExcelProperty(value = "医保账号")
    private String medicalAccount;

    /**
     * 公积金账号
     */
    @ExcelProperty(value = "公积金账号")
    private String providentAccount;

    /**
     * 银行卡号
     */
    @ExcelProperty(value = "银行卡号")
    private String bankAccount;

    /**
     * 开户行
     */
    @ExcelProperty(value = "开户行")
    private String bankInfo;

    /**
     * 档案附件（0表示无附件）
     */
    @ExcelProperty(value = "档案附件", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=表示无附件")
    private Long fileIds;

    /**
     * 员工个人简介
     */
    @ExcelProperty(value = "员工个人简介")
    private String desc;

    /**
     * 员工入职日期
     */
    @ExcelProperty(value = "员工入职日期")
    private Date entryTime;

    /**
     * 员工离职日期
     */
    @ExcelProperty(value = "员工离职日期")
    private Date levelTime;

    /**
     * 状态：-1待入职,1正常,2离职
     */
    @ExcelProperty(value = "状态：-1待入职,1正常,2离职")
    private Integer status;


}
