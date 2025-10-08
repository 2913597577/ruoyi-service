package org.dromara.staff.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 员工档案对象 dc_staff_info
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_staff_info")
public class DcStaffInfo extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private Long mobile;

    /**
     * 性别:1男,2女,0未知
     */
    private Integer sex;

    /**
     * 别名
     */
    private String nickname;

    /**
     * 员工照片
     */
    private Long thumb;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 上级主管id
     */
    private Long pid;

    /**
     * 职位id
     */
    private Long positionId;

    /**
     * 职务
     */
    private String positionName;

    /**
     * 职级
     */
    private String positionRank;

    /**
     * 员工类型:0未设置,1正式,2试用,3实习
     */
    private String type;

    /**
     * 身份类型:0未设置,1企业员工,2劳务派遣,3兼职员工
     */
    private String isStaff;

    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 开始工作时间
     */
    private Date workDate;

    /**
     * 工作地点
     */
    private String workLocation;

    /**
     * 工作团队
     */
    private String team;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 民族
     */
    private String nation;

    /**
     * 家庭地址
     */
    private String homeAddress;

    /**
     * 现居地址
     */
    private String currentAddress;

    /**
     * 紧急联系人
     */
    private String contact;

    /**
     * 紧急联系人电话
     */
    private String contactMobile;

    /**
     * 户口性质:0未设置,1农村户口,2城镇户口
     */
    private String residentType;

    /**
     * 户口所在地
     */
    private String residentPlace;

    /**
     * 毕业学校
     */
    private String graduateSchool;

    /**
     * 毕业日期（时间戳，0表示未填写）
     */
    private Date graduateDay;

    /**
     * 政治面貌:0未设置,1中共党员,2团员
     */
    private String political;

    /**
     * 婚姻状况:0未设置,1未婚,2已婚,3离异
     */
    private String maritalStatus;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 学位
     */
    private String education;

    /**
     * 专业
     */
    private String speciality;

    /**
     * 社保账号
     */
    private String socialAccount;

    /**
     * 医保账号
     */
    private String medicalAccount;

    /**
     * 公积金账号
     */
    private String providentAccount;

    /**
     * 银行卡号
     */
    private String bankAccount;

    /**
     * 开户行
     */
    private String bankInfo;

    /**
     * 档案附件（0表示无附件）
     */
    private Long fileIds;

    /**
     * 员工个人简介
     */
    private String description;

    /**
     * 员工入职日期
     */
    private Date entryTime;

    /**
     * 员工离职日期
     */
    private Date levelTime;

    /**
     * 状态：-1待入职,1正常,2离职
     */
    private Integer status;

    /**
     * 备注1
     */
    private String remark1;

    /**
     * 备注2
     */
    private String remark2;

    /**
     * 备注3
     */
    private String remark3;

    /**
     * 备注4
     */
    private String remark4;

    /**
     * 备注5
     */
    private String remark5;

    /**
     * 删除标志 0存在 1删除
     */
    @TableLogic
    private String delFlag;


}
