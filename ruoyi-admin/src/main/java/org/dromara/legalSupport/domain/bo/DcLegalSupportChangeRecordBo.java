package org.dromara.legalSupport.domain.bo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.legalSupport.domain.DcLegalSupportChangeRecord;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 法务支持变更业务对象 dc_legal_support_change_record
 *
 * @author Lion Li
 * @date 2025-11-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcLegalSupportChangeRecord.class, reverseConvertGenerate = false)
public class DcLegalSupportChangeRecordBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String customerName;

    /**
     * 客户id（流转单id）
     */
    private Long customerId;

    /**
     * 法务支持
     */
    private String legalSupportName;

    /**
     * 法务支持ID
     */
    private Long legalSupportId;

    private String remark1;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
