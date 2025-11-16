package org.dromara.customer.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户信息记录对象 dc_customer_information_log
 *
 * @author Lion Li
 * @date 2025-11-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_customer_information_log")
public class DcCustomerInformationLog extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 签约日期
     */
    private Date signDate;

    /**
     * 编号
     */
    private String contractNo;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 负责人电话
     */
    private String principalPhone;

    /**
     * 法务法务支持（律师id）
     */
    private Long lawyerId;

    /**
     * 甩单人
     */
    private String transferPerson;

    /**
     * 杀单手
     */
    private String closer;

    /**
     * 签约类型
     */
    private Integer contractType;

    /**
     * 套餐类型
     */
    private Integer packageType;

    /**
     * 实收金额
     */
    private BigDecimal actualReceipt;

    /**
     * 尾款金额
     */
    private BigDecimal balance;

    /**
     * 到期时间
     */
    private Date expireDate;

    /**
     * 是否转为意向客户
     */
    private Integer isIntention;

    /**
     * 是否转为风险客户
     */
    private Integer isRisk;

    /**
     * 是否转为退费客户
     */
    private Integer isRefund;

    /**
     * 合同编号
     */
    private String contractCode;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 续费/尾款动作(1-续费 2-付尾款 3-其他)
     */
    private Integer actionType;

    /**
     * 客户id（流转单id）
     */
    private Long transferId;

    /**
     * 客户总表id
     */
    private Long customerInfoId;

    /**
     * 客户总表创建时间
     */
    private Date infoCreateTime;

    /**
     * 客户总表更新时间
     */
    private Date infoUpdateTime;

    /**
     * 删除标志 0存在 1删除
     */
    @TableLogic
    private String delFlag;

    /**
     * 客户经理id
     */
    private Long accountManagerId;

    /**
     * 邀约人id
     */
    private Long inviterId;

    /**
     * 服务时长
     */
    private String serviceDuration;

    /**
     * 合同金额
     */
    private BigDecimal contractAmount;

    /**
     * 客户类型  A-0 B-1 C-2 D-3
     */
    private Integer customerType;

    /**
     * 立案账号
     */
    private String caseFillingAccount;

    /**
     * 立案密码
     */
    private String caseFillingPwd;

    /**
     * 客户服务城市
     */
    private String customerCity;

    /**
     * 是否分配法务支持 0-未分配 1-已分配
     */
    private Integer isAssigned;


}
