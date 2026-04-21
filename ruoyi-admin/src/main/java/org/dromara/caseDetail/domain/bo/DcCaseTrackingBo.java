package org.dromara.caseDetail.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.caseDetail.domain.DcCaseTracking;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 案件进展表业务对象 dc_case_tracking
 *
 * @author Lion Li
 * @date 2025-09-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCaseTracking.class, reverseConvertGenerate = false)
public class DcCaseTrackingBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 案件id
     */
    @NotNull(message = "案件id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long caseId;

    /**
     * 案件类型
     */
    @NotBlank(message = "案件类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private String caseType;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 客户姓名
     */
    private String customerName;


    /**
     * 案件进展
     */
    private String caseProgress;

    /**
     * 跟进时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date trackingTime;

    /**
     * 下次跟进时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nextTrackingTime;

    /**
     * 法务支持id
     */
    private Long legalSupportId;

    /**
     * 法务支持姓名
     */
    private String legalSupportName;

    private List<Long> customerIds;

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

}
