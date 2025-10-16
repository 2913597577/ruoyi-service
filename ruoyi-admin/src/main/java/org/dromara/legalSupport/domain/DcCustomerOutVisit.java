package org.dromara.legalSupport.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 客户出访记录对象 dc_customer_out_visit
 *
 * @author Lion Li
 * @date 2025-10-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_customer_out_visit")
public class DcCustomerOutVisit extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 法务支持id
     */
    private Long legalSupportId;

    /**
     * 法务支持姓名
     */
    private String legalSupportName;

    /**
     * 出访时间
     */
    private Date visitTime;

    /**
     * 下次出访时间
     */
    private Date nextVisitTime;

    /**
     * 出访状态
     */
    private Long visitStatus;

    /**
     * 面访目的
     */
    private String visitPurpose;

    /**
     * 是否本月第一次出访
     */
    private Long isFirstVisit;

    /**
     * 是否计入外勤项数
     */
    private Long isOutCount;

    /**
     * 客户地点照片
     */
    private String placePic1;

    /**
     * 客户地点照片
     */
    private String placePic2;

    /**
     * 客户地点照片
     */
    private String placePic3;

    /**
     * 客户地点照片
     */
    private String placePic4;

    /**
     * 面访记录附件
     */
    private String outRecord;

    /**
     * 面访地点
     */
    private String visitAddress;

    /**
     * 面访地点 x坐标
     */
    private String visitAddressX;

    /**
     * 面访地点 y坐标
     */
    private String visitAddressY;

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
     * 删除标志 0存在 1删除
     */
    @TableLogic
    private String delFlag;


}
