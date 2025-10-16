package org.dromara.caseDetail.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 案件进展表对象 dc_case_tracking
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_case_tracking")
public class DcCaseTracking extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 案件id
     */
    private Long caseId;

    /**
     * 案件类型
     */
    private String caseType;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 客户姓名
     */
    private Long customerName;

    /**
     * 案件进展
     */
    private String caseProgress;

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

    /**
     * 跟进时间
     */
    private Date trackingTime;
    /**
     * 下次跟进时间
     */
    private Date nextTrackingTime;


}
