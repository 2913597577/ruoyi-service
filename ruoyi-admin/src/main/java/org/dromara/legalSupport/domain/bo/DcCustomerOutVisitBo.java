package org.dromara.legalSupport.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.legalSupport.domain.DcCustomerOutVisit;

import java.util.Date;

/**
 * 客户出访记录业务对象 dc_customer_out_visit
 *
 * @author Lion Li
 * @date 2025-10-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DcCustomerOutVisit.class, reverseConvertGenerate = false)
public class DcCustomerOutVisitBo extends BaseEntity {

    /**
     * 主键
     */
    private Long id;
    /**
     * 客户id
     */
    @NotNull(message = "客户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long customerId;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 法务支持id
     */
    @NotNull(message = "法务支持id不能为空", groups = {AddGroup.class, EditGroup.class})
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


}
