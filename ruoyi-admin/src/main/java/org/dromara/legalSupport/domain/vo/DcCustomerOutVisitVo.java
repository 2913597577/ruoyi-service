package org.dromara.legalSupport.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.legalSupport.domain.DcCustomerOutVisit;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 客户出访记录视图对象 dc_customer_out_visit
 *
 * @author Lion Li
 * @date 2025-10-16
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DcCustomerOutVisit.class)
public class DcCustomerOutVisitVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 客户id
     */
    @ExcelProperty(value = "客户id")
    private Long customerId;

    /**
     * 客户姓名
     */
    @ExcelProperty(value = "客户姓名")
    private String customerName;

    /**
     * 法务支持id
     */
    @ExcelProperty(value = "法务支持id")
    private Long legalSupportId;

    /**
     * 法务支持姓名
     */
    @ExcelProperty(value = "法务支持姓名")
    private String legalSupportName;

    /**
     * 出访时间
     */
    @ExcelProperty(value = "出访时间")
    private Date visitTime;

    /**
     * 下次出访时间
     */
    @ExcelProperty(value = "下次出访时间")
    private Date nextVisitTime;

    /**
     * 面访目的
     */
    @ExcelProperty(value = "面访目的")
    private String visitPurpose;

    /**
     * 是否本月第一次出访
     */
    @ExcelProperty(value = "是否本月第一次出访", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "dc_true_or_false")
    private Long isFirstVisit;

    /**
     * 是否计入外勤项数
     */
    @ExcelProperty(value = "是否计入外勤项数", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "dc_true_or_false")
    private Long isOutCount;

    /**
     * 客户地点照片
     */
    @ExcelProperty(value = "客户地点照片")
    private String placePic1;

    /**
     * 客户地点照片Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "placePic1")
    private String placePic1Url;
    /**
     * 客户地点照片
     */
    @ExcelProperty(value = "客户地点照片")
    private String placePic2;

    /**
     * 客户地点照片Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "placePic2")
    private String placePic2Url;
    /**
     * 客户地点照片
     */
    @ExcelProperty(value = "客户地点照片")
    private String placePic3;

    /**
     * 客户地点照片Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "placePic3")
    private String placePic3Url;
    /**
     * 客户地点照片
     */
    @ExcelProperty(value = "客户地点照片")
    private String placePic4;

    /**
     * 客户地点照片Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "placePic4")
    private String placePic4Url;
    /**
     * 面访记录附件
     */
    @ExcelProperty(value = "面访记录附件")
    private String outRecord;

    /**
     * 面访地点
     */
    @ExcelProperty(value = "面访地点")
    private String visitAddress;

    /**
     * 备注1
     */
    @ExcelProperty(value = "备注1")
    private String remark1;

    /**
     * 备注2
     */
    @ExcelProperty(value = "备注2")
    private String remark2;

    /**
     * 备注3
     */
    @ExcelProperty(value = "备注3")
    private String remark3;



}
