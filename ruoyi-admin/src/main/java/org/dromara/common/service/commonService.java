package org.dromara.common.service;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.caseDetail.domain.bo.DcCaseTrackingBo;
import org.dromara.caseDetail.domain.bo.DcInsuranceCaseBo;
import org.dromara.caseDetail.domain.vo.DcCaseTrackingVo;
import org.dromara.caseDetail.domain.vo.DcDebtCaseVo;
import org.dromara.caseDetail.domain.vo.DcInsuranceCaseVo;
import org.dromara.caseDetail.mapper.DcDebtCaseMapper;
import org.dromara.caseDetail.mapper.DcInsuranceCaseMapper;
import org.dromara.caseDetail.service.IDcCaseTrackingService;
import org.dromara.caseDetail.service.IDcInsuranceCaseService;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.customer.domain.DcCustomerIntention;
import org.dromara.customer.domain.DcCustomerRiskRefund;
import org.dromara.customer.domain.vo.DcCustomerInformationVo;
import org.dromara.customer.domain.vo.DcCustomerIntentionVo;
import org.dromara.customer.mapper.DcCustomerInformationMapper;
import org.dromara.customer.mapper.DcCustomerIntentionMapper;
import org.dromara.customer.mapper.DcCustomerRiskRefundMapper;
import org.dromara.legalSupport.domain.bo.DcCustomerJobOrderBo;
import org.dromara.legalSupport.domain.bo.DcCustomerOutVisitBo;
import org.dromara.legalSupport.domain.vo.DcCustomerJobOrderVo;
import org.dromara.legalSupport.domain.vo.DcCustomerOutVisitVo;
import org.dromara.legalSupport.service.IDcCustomerJobOrderService;
import org.dromara.legalSupport.service.IDcCustomerOutVisitService;
import org.dromara.myCustomer.domain.DcCustomerTransfer;
import org.dromara.myCustomer.domain.bo.DcCustomerTrackingBo;
import org.dromara.myCustomer.domain.vo.DcCustomerTrackingVo;
import org.dromara.myCustomer.domain.vo.DcCustomerTransferVo;
import org.dromara.myCustomer.mapper.DcCustomerTrackingMapper;
import org.dromara.myCustomer.mapper.DcCustomerTransferMapper;
import org.dromara.myCustomer.service.IDcCustomerTrackingService;
import org.dromara.system.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class commonService {

    private final DcCustomerInformationMapper informationMapper;
    private final DcCustomerIntentionMapper intentionMapper;
    private final DcCustomerRiskRefundMapper riskRefundMapper;
    private final DcCustomerTransferMapper transferMapper;
    private final DcCustomerTrackingMapper trackingMapper;
    private final DcInsuranceCaseMapper insuranceCaseMapper;
    private final DcDebtCaseMapper debtCaseMapper;
    private final ISysRoleService roleService;

    private final IDcCustomerTrackingService dcCustomerTrackingService;
    private final IDcCustomerJobOrderService dcCustomerJobOrderService;
    private final IDcCustomerOutVisitService dcCustomerOutVisitService;
    private final IDcCaseTrackingService dcCaseTrackingService;
    private final IDcInsuranceCaseService dcInsuranceCaseService;

    public JSONArray getCustomerByUserId(long userId) {
        boolean superAdmin = LoginHelper.isSuperAdmin(userId);
        List<DcCustomerInformationVo> list = new ArrayList<>();
        if (superAdmin) {
            list = informationMapper.selectVoList();
        }
        if (!superAdmin) {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("lawyer_id", userId);
            list = informationMapper.selectVoByMap(queryMap);
        }

        JSONArray json = new JSONArray();
        for (DcCustomerInformationVo customerInformationVo : list) {
            DcCustomerTransfer dcCustomerTransfer = transferMapper.selectById(customerInformationVo.getTransferId());
            String companyName = dcCustomerTransfer == null ? "" : dcCustomerTransfer.getCompanyName();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("transfer_id", customerInformationVo.getTransferId());
            jsonObject.put("customer_name", customerInformationVo.getCustomerName() + "(" + companyName + ")");
            jsonObject.put("customer_realName", customerInformationVo.getCustomerName());
            json.add(jsonObject);
        }
        return json;
    }

    public JSONArray getIntentionCustomerByUserId(long userId) {
        boolean superAdmin = LoginHelper.isSuperAdmin(userId);
        List<DcCustomerIntentionVo> list = new ArrayList<>();
        if (superAdmin) {
            list = intentionMapper.selectVoList();
        }
        if (!superAdmin) {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("legal_support_id", userId);
            list = intentionMapper.selectVoByMap(queryMap);
        }

        JSONArray json = new JSONArray();
        JSONObject data = new JSONObject();
        data.put("intended_customer", "请选择意向客户");
        json.add(data);
        for (DcCustomerIntentionVo customerIntentionVo : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("intention_id", customerIntentionVo.getId());
            jsonObject.put("intended_customer", customerIntentionVo.getIntendedCustomer());
            json.add(jsonObject);
        }
        return json;
    }

    public JSONArray getCustomerType() {
        List<Map<String, Object>> list = informationMapper.selectCustomerCountByType();
        return JSONArray.parseArray(JSONObject.toJSONString(list));
    }

    // 在 getCustomerCategory 方法中补充完整代码
    public JSONObject getCustomerCategory(Long userId) {
        boolean superAdmin = LoginHelper.isSuperAdmin(userId);

        // 创建查询条件
        LambdaQueryWrapper<DcCustomerIntention> intentionCountQW = new LambdaQueryWrapper<>();
        intentionCountQW.eq(DcCustomerIntention::getDelFlag, 0);

        LambdaQueryWrapper<DcCustomerRiskRefund> riskCountQW = new LambdaQueryWrapper<>();
        riskCountQW.eq(DcCustomerRiskRefund::getDelFlag, 0)
            .eq(DcCustomerRiskRefund::getCustomerType, 1);

        LambdaQueryWrapper<DcCustomerRiskRefund> refundCountQW = new LambdaQueryWrapper<>();
        refundCountQW.eq(DcCustomerRiskRefund::getDelFlag, 0)
            .eq(DcCustomerRiskRefund::getCustomerType, 2);
        // 执行查询
        Long intentionCount = intentionMapper.selectCount(intentionCountQW);
        Long riskCount = riskRefundMapper.selectCount(riskCountQW);
        Long refundCount = riskRefundMapper.selectCount(refundCountQW);

        // 封装返回结果

        JSONObject json = new JSONObject();
        json.put("intention_count", intentionCount);
        json.put("risk_count", riskCount);
        json.put("refund_count", refundCount);
        return json;
    }

    public JSONArray getServiceData(Integer year, Integer month, Integer day, Long userId, Long lawyerId) {
        if (year == null) {
            year = DateUtil.thisYear();
        }
        boolean superAdmin = LoginHelper.isSuperAdmin(userId);
        List<Map<String, Object>> trackingList = trackingMapper.selectByYearMonth(year, month, day, null, null, superAdmin ? lawyerId : userId, null, false);
        List<Map<String, Object>> customerList = informationMapper.selectByYearMonth(year, month, day, superAdmin ? lawyerId : userId);
        List<Map<String, Object>> insuranceList = insuranceCaseMapper.selectByYearMonth(year, month, day, superAdmin ? lawyerId : userId);
        List<Map<String, Object>> returnTrackingList = trackingMapper.selectByYearMonth(year, month, day, null, null, superAdmin ? lawyerId : userId, 1, false);
        List<Map<String, Object>> vistTrackingList = trackingMapper.selectByYearMonth(year, month, day, 1, null, superAdmin ? lawyerId : userId, null, false);
        List<Map<String, Object>> caseTrackingList = trackingMapper.selectByYearMonth(year, month, day, null, null, superAdmin ? lawyerId : userId, null, true);
        List<Map<String, Object>> didTrackingList = trackingMapper.selectByYearMonth(year, month, day, null, 2, superAdmin ? lawyerId : userId, null, true);
        List<Map<String, Object>> doingTrackingList = trackingMapper.selectByYearMonth(year, month, day, null, 1, superAdmin ? lawyerId : userId, null, true);
        List<Map<String, Object>> undoTrackingList = trackingMapper.selectByYearMonth(year, month, day, null, 0, superAdmin ? lawyerId : userId, null, true);
        List<Map<String, Object>> referralList = intentionMapper.selectByYearMonth(year, month, day, 3, superAdmin ? lawyerId : userId);
        Map<String, Object> riskDataList = riskRefundMapper.selectRefundCount(year, month, day, 1, superAdmin ? lawyerId : userId);
        Map<String, Object> refundDataList = riskRefundMapper.selectRefundCount(year, month, day, 2, superAdmin ? lawyerId : userId);

        JSONObject json = new JSONObject();
        json.put("trackingCount", trackingList == null ? 0 : trackingList.size());
        json.put("customerCount", customerList == null ? 0 : customerList.size());
        json.put("insuranceCount", insuranceList == null ? 0 : insuranceList.size());
        json.put("riskDataCount", riskDataList == null ? 0 : riskDataList.get("count"));
        json.put("refundDataCount", refundDataList == null ? 0 : refundDataList.get("count"));
        json.put("refundAmountSum", refundDataList == null ? 0 : refundDataList.get("sum"));
        json.put("returnTrackingCount", returnTrackingList == null ? 0 : returnTrackingList.size());
        json.put("visitTrackingCount", vistTrackingList == null ? 0 : vistTrackingList.size());
        json.put("caseTrackingCount", caseTrackingList == null ? 0 : caseTrackingList.size());
        json.put("didTrackingCount", didTrackingList == null ? 0 : didTrackingList.size());
        json.put("doingTrackingCount", doingTrackingList == null ? 0 : doingTrackingList.size());
        json.put("undoTrackingCount", undoTrackingList == null ? 0 : undoTrackingList.size());
        json.put("referralCount", referralList == null ? 0 : referralList.size());
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(json);
        return jsonArray;
    }

    public JSONObject getRiskRefundData(Integer year, Integer month, Integer day, Long userId, Long lawyerId) {
        if (year == null) {
            year = DateUtil.thisYear();
        }
        boolean superAdmin = LoginHelper.isSuperAdmin(userId);
        Map<String, Object> riskDataList = riskRefundMapper.selectRefundCount(year, month, day, 1, superAdmin ? lawyerId : userId);
        Map<String, Object> refundDataList = riskRefundMapper.selectRefundCount(year, month, day, 2, superAdmin ? lawyerId : userId);
        JSONObject json = new JSONObject();
        json.put("riskDataCount", riskDataList == null ? 0 : riskDataList.get("count"));
        json.put("refundDataCount", refundDataList == null ? 0 : refundDataList.get("count"));
        json.put("refundAmountSum", refundDataList == null ? 0 : refundDataList.get("sum"));
        return json;
    }

    public JSONObject getAllTrackingRecords(String customerId, String legalSupportId, Integer trackingType, String trackingTime,
                                            String nextTrackingTime, int pageNum, int pageSize) {
        JSONArray json = new JSONArray();
        DcCustomerTrackingBo trackingBo = new DcCustomerTrackingBo();
        DcCustomerOutVisitBo outVisitBo = new DcCustomerOutVisitBo();
        DcInsuranceCaseBo insuranceCaseBo = new DcInsuranceCaseBo();
        DcCustomerJobOrderBo jobOrderBo = new DcCustomerJobOrderBo();
        DcCaseTrackingBo caseTrackingBo = new DcCaseTrackingBo();
        if (StringUtils.isNotBlank(customerId)) {
            trackingBo.setCustomerId(Long.valueOf(customerId));
            outVisitBo.setCustomerId(Long.valueOf(customerId));
            insuranceCaseBo.setCustomerId(Long.valueOf(customerId));
            jobOrderBo.setCustomerId(Long.valueOf(customerId));
            caseTrackingBo.setCustomerId(Long.valueOf(customerId));
        }
        if (StringUtils.isNotBlank(legalSupportId)) {
            trackingBo.setLegalSupportId(Long.valueOf(legalSupportId));
            outVisitBo.setLegalSupportId(Long.valueOf(legalSupportId));
            insuranceCaseBo.setLegalSupportId(Long.valueOf(legalSupportId));
            jobOrderBo.setLegalSupportId(Long.valueOf(legalSupportId));
            caseTrackingBo.setLegalSupportId(Long.valueOf(legalSupportId));
        }
        if (StringUtils.isNotBlank(trackingTime)) {
            trackingBo.setTrackingTime(DateUtil.parse(trackingTime));
            outVisitBo.setVisitTime(DateUtil.parse(trackingTime));
            insuranceCaseBo.setCreateTime(DateUtil.parse(trackingTime));
            jobOrderBo.setCreateTime(DateUtil.parse(trackingTime));
            caseTrackingBo.setTrackingTime(DateUtil.parse(trackingTime));
        }
        if (StringUtils.isNotBlank(nextTrackingTime)) {
            trackingBo.setNextTime(DateUtil.parse(nextTrackingTime));
            outVisitBo.setNextVisitTime(DateUtil.parse(nextTrackingTime));
            insuranceCaseBo.setOrderDate(DateUtil.parse(nextTrackingTime));//订单时间
            jobOrderBo.setDeliveryTime(DateUtil.parse(nextTrackingTime)); //交付时间
            caseTrackingBo.setNextTrackingTime(DateUtil.parse(nextTrackingTime));
        }
        if (trackingType == null || trackingType == 1) {
            // 回访
            List<DcCustomerTrackingVo> dcCustomerTrackingVos = dcCustomerTrackingService.queryList(trackingBo);
            for (DcCustomerTrackingVo trackingVo : dcCustomerTrackingVos) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", trackingVo.getId());
                jsonObject.put("customerId", trackingVo.getCustomerId());
                DcCustomerTransferVo transferVo = transferMapper.selectVoById(trackingVo.getCustomerId());
                jsonObject.put("customerName", transferVo == null ? "" : transferVo.getCompanyName());
                jsonObject.put("legalSupportId", trackingVo.getLegalSupportId());
                jsonObject.put("legalSupportName", trackingVo.getLegalSupportName());
                jsonObject.put("trackingTime", trackingVo.getTrackingTime());
                jsonObject.put("nextTrackingTime", trackingVo.getNextTime());
                jsonObject.put("trackingType", 1);
                jsonObject.put("remark", trackingVo.getCustomerRemark());
                json.add(jsonObject);
            }
        }
        if (trackingType == null || trackingType == 2) {
            // 出访
            List<DcCustomerOutVisitVo> dcCustomerOutVisitVos = dcCustomerOutVisitService.queryList(outVisitBo);
            for (DcCustomerOutVisitVo outVisitVo : dcCustomerOutVisitVos) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", outVisitVo.getId());
                jsonObject.put("customerId", outVisitVo.getCustomerId());
                jsonObject.put("customerName", outVisitVo.getCustomerName());
                jsonObject.put("legalSupportId", outVisitVo.getLegalSupportId());
                jsonObject.put("legalSupportName", outVisitVo.getLegalSupportName());
                jsonObject.put("trackingTime", outVisitVo.getVisitTime());
                jsonObject.put("nextTrackingTime", outVisitVo.getNextVisitTime());
                jsonObject.put("trackingType", 2);
                jsonObject.put("remark", outVisitVo.getVisitPurpose());
                json.add(jsonObject);
            }
        }
        if (trackingType == null || trackingType == 3) {
            // 保险
            List<DcInsuranceCaseVo> dcInsuranceCaseVos = dcInsuranceCaseService.queryList(insuranceCaseBo);
            for (DcInsuranceCaseVo insuranceCaseVo : dcInsuranceCaseVos) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", insuranceCaseVo.getId());
                jsonObject.put("customerId", insuranceCaseVo.getCustomerId());
                DcCustomerTransferVo transferVo = transferMapper.selectVoById(insuranceCaseVo.getCustomerId());
                jsonObject.put("customerName", transferVo == null ? "" : transferVo.getCompanyName());
                jsonObject.put("legalSupportId", insuranceCaseVo.getLegalSupportId());
                jsonObject.put("legalSupportName", insuranceCaseVo.getLegalSupportName());
                jsonObject.put("trackingTime", "");
                jsonObject.put("nextTrackingTime", insuranceCaseVo.getOrderDate());
                jsonObject.put("trackingType", 3);
                jsonObject.put("remark", "买保险记录");
                json.add(jsonObject);
            }
        }
        if (trackingType == null || trackingType == 4) {
            // 工单
            List<DcCustomerJobOrderVo> dcCustomerJobOrderVos = dcCustomerJobOrderService.queryList(jobOrderBo);
            for (DcCustomerJobOrderVo jobOrderVo : dcCustomerJobOrderVos) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", jobOrderVo.getId());
                jsonObject.put("customerId", jobOrderVo.getCustomerId());
                jsonObject.put("customerName", jobOrderVo.getCustomerName());
                jsonObject.put("legalSupportId", jobOrderVo.getContractHandler());
                jsonObject.put("legalSupportName", jobOrderVo.getLegalSupport());
                jsonObject.put("trackingTime", "");
                jsonObject.put("nextTrackingTime", jobOrderVo.getDeliveryTime());
                jsonObject.put("trackingType", 4);
                jsonObject.put("remark", "下工单记录");
                json.add(jsonObject);
            }
        }
        if (trackingType == null || trackingType == 5) {
            //案件
            List<DcCaseTrackingVo> dcCaseTrackingVos = dcCaseTrackingService.queryList(caseTrackingBo);
            for (DcCaseTrackingVo caseTrackingVo : dcCaseTrackingVos) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", caseTrackingVo.getId());
                jsonObject.put("customerId", caseTrackingVo.getCustomerId());
                jsonObject.put("customerName", caseTrackingVo.getCustomerName());
                jsonObject.put("legalSupportId", caseTrackingVo.getLegalSupportId());
                jsonObject.put("legalSupportName", caseTrackingVo.getLegalSupportName());
                jsonObject.put("trackingTime", caseTrackingVo.getTrackingTime());
                jsonObject.put("nextTrackingTime", caseTrackingVo.getNextTrackingTime());
                jsonObject.put("trackingType", 5);
                jsonObject.put("remark", "案件跟踪记录");
                json.add(jsonObject);
            }
        }
        JSONObject result = new JSONObject();
        JSONArray data = new JSONArray();
        data.addAll(ListUtil.page(pageNum - 1, pageSize, json));
        result.put("total", json.size());
        result.put("data", data);
        return result;
    }

    public JSONArray getCaseDetail(long userId) {
        boolean superAdmin = LoginHelper.isSuperAdmin(userId);
        List<DcDebtCaseVo> list = new ArrayList<>();
        if (superAdmin) {
            list = debtCaseMapper.selectVoList();
        }
        if (!superAdmin) {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("legal_support_id", userId);
            list = debtCaseMapper.selectVoByMap(queryMap);
        }

        JSONArray json = new JSONArray();
        for (DcDebtCaseVo dcDebtCaseVo : list) {
            DcCustomerTransfer dcCustomerTransfer = transferMapper.selectById(dcDebtCaseVo.getCustomerId());
            String companyName = dcCustomerTransfer == null ? "" : dcCustomerTransfer.getCompanyName();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("case_id", dcDebtCaseVo.getId());
            jsonObject.put("case_detail", "客户【" + companyName + "】--债务人【" + dcDebtCaseVo.getDebtorName() + "】");
            json.add(jsonObject);
        }
        return json;
    }

}
