package org.dromara.legalSupport.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 法务支持变更对象 dc_legal_support_change_record
 *
 * @author Lion Li
 * @date 2025-11-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_legal_support_change_record")
public class DcLegalSupportChangeRecord extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 客户名称
     */
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
