package org.dromara.common.service;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.caseDetail.domain.bo.DcCaseTrackingBo;
import org.dromara.caseDetail.domain.bo.DcInsuranceCaseBo;
import org.dromara.caseDetail.domain.vo.DcCaseTrackingVo;
import org.dromara.caseDetail.domain.vo.DcDebtCaseVo;
import org.dromara.caseDetail.domain.vo.DcInsuranceCaseVo;
import org.dromara.caseDetail.mapper.DcCaseTrackingMapper;
import org.dromara.caseDetail.mapper.DcDebtCaseMapper;
import org.dromara.caseDetail.mapper.DcInsuranceCaseMapper;
import org.dromara.caseDetail.service.IDcCaseTrackingService;
import org.dromara.caseDetail.service.IDcInsuranceCaseService;
import org.dromara.common.core.domain.dto.RoleDTO;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.customer.domain.DcCustomerInformation;
import org.dromara.customer.domain.DcCustomerIntention;
import org.dromara.customer.domain.DcCustomerRiskRefund;
import org.dromara.customer.domain.bo.DcCustomerInformationBo;
import org.dromara.customer.domain.bo.DcCustomerIntentionBo;
import org.dromara.customer.domain.vo.DcCustomerInformationVo;
import org.dromara.customer.domain.vo.DcCustomerIntentionVo;
import org.dromara.customer.mapper.DcCustomerInformationMapper;
import org.dromara.customer.mapper.DcCustomerIntentionMapper;
import org.dromara.customer.mapper.DcCustomerIntentionTrackingMapper;
import org.dromara.customer.mapper.DcCustomerRiskRefundMapper;
import org.dromara.customer.service.IDcCustomerInformationService;
import org.dromara.customer.service.IDcCustomerIntentionService;
import org.dromara.legalSupport.domain.bo.DcCustomerJobOrderBo;
import org.dromara.legalSupport.domain.bo.DcCustomerOutVisitBo;
import org.dromara.legalSupport.domain.vo.DcCustomerJobOrderVo;
import org.dromara.legalSupport.domain.vo.DcCustomerOutVisitVo;
import org.dromara.legalSupport.domain.vo.DcLegalSupportChangeRecordVo;
import org.dromara.legalSupport.mapper.DcCustomerJobOrderMapper;
import org.dromara.legalSupport.mapper.DcCustomerOutVisitMapper;
import org.dromara.legalSupport.mapper.DcLegalSupportChangeRecordMapper;
import org.dromara.legalSupport.service.IDcCustomerJobOrderService;
import org.dromara.legalSupport.service.IDcCustomerOutVisitService;
import org.dromara.myCustomer.domain.DcCustomerTransfer;
import org.dromara.myCustomer.domain.bo.DcCustomerTrackingBo;
import org.dromara.myCustomer.domain.bo.DcCustomerTransferBo;
import org.dromara.myCustomer.domain.vo.DcCustomerTrackingVo;
import org.dromara.myCustomer.domain.vo.DcCustomerTransferVo;
import org.dromara.myCustomer.mapper.DcCustomerTrackingMapper;
import org.dromara.myCustomer.mapper.DcCustomerTransferMapper;
import org.dromara.myCustomer.service.IDcCustomerTrackingService;
import org.dromara.myCustomer.service.IDcCustomerTransferService;
import org.dromara.performance.mapper.DcCustomerPerformanceMapper;
import org.dromara.performance.mapper.DcPerformanceTaskMapper;
import org.dromara.system.domain.bo.SysDeptBo;
import org.dromara.system.domain.bo.SysDictDataBo;
import org.dromara.system.domain.vo.SysDeptVo;
import org.dromara.system.domain.vo.SysDictDataVo;
import org.dromara.system.domain.vo.SysUserVo;
import org.dromara.system.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommonService {

    private final DcCustomerInformationMapper informationMapper;
    private final DcCustomerIntentionMapper intentionMapper;
    private final DcCustomerRiskRefundMapper riskRefundMapper;
    private final DcCustomerTransferMapper transferMapper;
    private final DcCustomerTrackingMapper trackingMapper;
    private final DcInsuranceCaseMapper insuranceCaseMapper;
    private final DcDebtCaseMapper debtCaseMapper;
    private final DcCustomerOutVisitMapper customerOutVisitMapper;
    private final DcCustomerJobOrderMapper dcCustomerJobOrderMapper;
    private final DcDebtCaseMapper dcDebtCaseMapper;
    private final DcCaseTrackingMapper dcCaseTrackingMapper;
    private final DcCustomerPerformanceMapper dcCustomerPerformanceMapper;
    private final DcPerformanceTaskMapper dcPerformanceTaskMapper;
    private final DcCustomerIntentionTrackingMapper dcCustomerIntentionTrackingMapper;
    private final DcLegalSupportChangeRecordMapper dcLegalSupportChangeRecordMapper;
    private final ISysRoleService roleService;

    private final IDcCustomerInformationService dcCustomerInformationService;
    private final IDcCustomerIntentionService dcCustomerIntentionService;
    private final IDcCustomerTrackingService dcCustomerTrackingService;
    private final IDcCustomerJobOrderService dcCustomerJobOrderService;
    private final IDcCustomerOutVisitService dcCustomerOutVisitService;
    private final IDcCaseTrackingService dcCaseTrackingService;
    private final IDcInsuranceCaseService dcInsuranceCaseService;
    private final IDcCustomerTransferService dcCustomerTransferService;

    private final ISysUserService userService;
    private final ISysDeptService deptService;
    private final ISysDictTypeService dictTypeService;
    private final ISysDictDataService dictDataService;

/*    public JSONArray getCustomerByUserId(long userId) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return null;
        }


        List<RoleDTO> roles = loginUser.getRoles();
        // 法务支持员工
        boolean isLegalSupport = false;
        if (roles != null && !roles.isEmpty()) {
            RoleDTO role = roles.get(0);
            if (role.getRoleKey().equals("LegalSupport_Employee")) {
                isLegalSupport = true;
            }
        }

        List<DcCustomerInformationVo> list = informationMapper.selectVoList();

        JSONArray json = new JSONArray();
        if (isLegalSupport) {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("legal_support_id", userId);
            List<DcLegalSupportChangeRecordVo> list1 = dcLegalSupportChangeRecordMapper.selectVoByMap(queryMap);
            for (DcLegalSupportChangeRecordVo recordVo : list1) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("transfer_id", recordVo.getCustomerId());
                jsonObject.put("customer_id", recordVo.getCustomerId());
                jsonObject.put("customer_name", recordVo.getCustomerName());
                jsonObject.put("customer_realName", recordVo.getCustomerName());
                json.add(jsonObject);
            }
            return json;
        }


        for (DcCustomerInformationVo customerInformationVo : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("transfer_id", customerInformationVo.getTransferId());
            jsonObject.put("customer_id", customerInformationVo.getId());
            jsonObject.put("customer_name", customerInformationVo.getCustomerName());
            jsonObject.put("customer_realName", customerInformationVo.getCustomerName());
            json.add(jsonObject);
        }
        return json;
    }*/

    // 重写getCustomerByUserId这个方法（changk666）
    /*public JSONArray getCustomerByUserId(long userId) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return null;
        }

        boolean superAdmin = LoginHelper.isSuperAdmin(userId);
        List<RoleDTO> roles = loginUser.getRoles();

        // 法务支持员工
        boolean isLegalSupportEmployee = false;
        // 法务主管 (城市级别)
        boolean isLegalSupportManager = false;
        // 法务支持经理 (全国级别)
        boolean isLegalSupportLeader = false;
        // 法务中心员工
        boolean  isLegalCenterEmployee = false;
        // 法务中心主管
        boolean  isLegalCenterManager = false;

        if (roles != null && !roles.isEmpty()) {
            RoleDTO role = roles.get(0);
            String roleKey = role.getRoleKey();
            if ("LegalSupport_Employee".equals(roleKey)) {
                isLegalSupportEmployee = true;
            } else if ("LegalSupport_Manager".equals(roleKey)) {
                isLegalSupportManager = true;
            } else if ("LegalSupport_Leader".equals(roleKey)) {
                isLegalSupportLeader = true;
            } else if ("LegalCenter_Employee".equals(roleKey)) {
                isLegalCenterEmployee = true;
            } else if ("LegalCenter_Manager".equals(roleKey)) {
                isLegalCenterManager = true;
            }

        }

        JSONArray json = new JSONArray();
        if (isLegalSupportEmployee) {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("lawyer_id", userId);
            List<DcCustomerInformationVo> list1 = informationMapper.selectVoByMap(queryMap);
            for (DcCustomerInformationVo recordVo : list1) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("transfer_id", recordVo.getTransferId());
                jsonObject.put("customer_id", recordVo.getId());
                jsonObject.put("customer_name", recordVo.getCustomerName());
                jsonObject.put("customer_realName", recordVo.getCustomerName());
                json.add(jsonObject);
            }
            return json;
        }

        if (superAdmin || isLegalSupportLeader || isLegalCenterEmployee || isLegalCenterManager) {
            List<DcCustomerInformationVo> list = informationMapper.selectVoList();
            for (DcCustomerInformationVo customerInformationVo : list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("transfer_id", customerInformationVo.getTransferId());
                jsonObject.put("customer_id", customerInformationVo.getId());
                jsonObject.put("customer_name", customerInformationVo.getCustomerName());
                jsonObject.put("customer_realName", customerInformationVo.getCustomerName());
                json.add(jsonObject);
            }
        } else if (isLegalSupportManager) {
            String deptCategory = loginUser.getDeptCategory();
            String city = null;
            if (StringUtils.isNotBlank(deptCategory) && !"ADMIN".equals(deptCategory)) {
                city = deptCategory.substring(0, deptCategory.indexOf('_'));
            }
            //List<Long> deptIds = getDeptIdsByCity(city);
            DcCustomerInformationBo bo = new DcCustomerInformationBo();
            if (city != null) {
                bo.setCustomerCity(city);
                List<DcCustomerInformationVo> list = dcCustomerInformationService.queryList(bo);
                for (DcCustomerInformationVo customerInformationVo : list) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("transfer_id", customerInformationVo.getTransferId());
                    jsonObject.put("customer_id", customerInformationVo.getId());
                    jsonObject.put("customer_name", customerInformationVo.getCustomerName());
                    jsonObject.put("customer_realName", customerInformationVo.getCustomerName());
                    json.add(jsonObject);
                }
            }

        } else {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("lawyer_id", userId);
            List<DcCustomerInformationVo> list = informationMapper.selectVoByMap(queryMap);
            for (DcCustomerInformationVo customerInformationVo : list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("transfer_id", customerInformationVo.getTransferId());
                jsonObject.put("customer_id", customerInformationVo.getId());
                jsonObject.put("customer_name", customerInformationVo.getCustomerName());
                jsonObject.put("customer_realName", customerInformationVo.getCustomerName());
                json.add(jsonObject);
            }
        }
        return json;
    }*/
    // 优化getCustomerByUserId方法 (changk666)
    public JSONArray getCustomerByUserId(long userId) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return null;
        }

        boolean superAdmin = LoginHelper.isSuperAdmin(userId);
        List<RoleDTO> roles = loginUser.getRoles();

        String roleKey = (roles != null && !roles.isEmpty()) ? roles.get(0).getRoleKey() : null;

        JSONArray json = new JSONArray();
        List<DcCustomerInformationVo> customerList = null;

        if ("LegalSupport_Employee".equals(roleKey)) {
            customerList = informationMapper.selectCustomerByLawyerId(userId);
        } else if (superAdmin || "LegalSupport_Leader".equals(roleKey)
            || "LegalCenter_Employee".equals(roleKey) || "LegalCenter_Manager".equals(roleKey)) {
            customerList = informationMapper.selectAllCustomerBasicInfo();
        } else if ("LegalSupport_Manager".equals(roleKey)) {
            String deptCategory = loginUser.getDeptCategory();
            if (StringUtils.isNotBlank(deptCategory) && !"ADMIN".equals(deptCategory)) {
                String city = deptCategory.substring(0, deptCategory.indexOf('_'));
                customerList = informationMapper.selectCustomerByCity(city);
            }
        } else {
            customerList = informationMapper.selectCustomerByLawyerId(userId);
        }

        if (customerList != null && !customerList.isEmpty()) {
            for (DcCustomerInformationVo vo : customerList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("transfer_id", vo.getTransferId());
                jsonObject.put("customer_id", vo.getId());
                jsonObject.put("customer_name", vo.getCustomerName());
                //jsonObject.put("customer_realName", vo.getCustomerName());
                json.add(jsonObject);
            }
        }

        return json;
    }


    public JSONArray getIntentionCustomerByUserId(long userId) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return null;
        }

        boolean superAdmin = LoginHelper.isSuperAdmin(userId);
        List<RoleDTO> roles = loginUser.getRoles();
        String roleKey = (roles != null && !roles.isEmpty()) ? roles.get(0).getRoleKey() : null;

        List<DcCustomerIntentionVo> list = new ArrayList<>();

        if ("LegalSupport_Employee".equals(roleKey)) {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("legal_support_id", userId);
            list = intentionMapper.selectVoByMap(queryMap);
        } else if (superAdmin || "LegalSupport_Leader".equals(roleKey)) {
            list = intentionMapper.selectVoList();
        } else if ("LegalSupport_Manager".equals(roleKey)) {
            String deptCategory = loginUser.getDeptCategory();
            if (StringUtils.isNotBlank(deptCategory) && !"ADMIN".equals(deptCategory)) {
                String city = deptCategory.substring(0, deptCategory.indexOf('_'));
                Map<String, Object> queryMap = new HashMap<>();
                queryMap.put("remark1", city);
                list = intentionMapper.selectVoByMap(queryMap);
            }
        } else {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("legal_support_id", userId);
            list = intentionMapper.selectVoByMap(queryMap);
        }

        JSONArray json = new JSONArray();
        //JSONObject data = new JSONObject();
        //data.put("intended_customer", "请选择意向客户");
        //json.add(data);
        for (DcCustomerIntentionVo customerIntentionVo : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("intention_id", customerIntentionVo.getId());
            jsonObject.put("intended_customer", customerIntentionVo.getIntendedCustomer());
            json.add(jsonObject);
        }
        return json;
    }


    public JSONArray getCustomerType(Long lawyerId, String city) {
        List<Map<String, Object>> list = informationMapper.selectCustomerCountByType(lawyerId, city);
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

    public JSONObject getAllTrackingRecords(String customerId, String city, String legalSupportId, Integer trackingType, String trackingTime,
                                            String nextTrackingTime, int pageNum, int pageSize) {
        JSONArray json = new JSONArray();
        DcCustomerTrackingBo trackingBo = new DcCustomerTrackingBo();
        DcCustomerOutVisitBo outVisitBo = new DcCustomerOutVisitBo();
        DcInsuranceCaseBo insuranceCaseBo = new DcInsuranceCaseBo();
        DcCustomerJobOrderBo jobOrderBo = new DcCustomerJobOrderBo();
        DcCaseTrackingBo caseTrackingBo = new DcCaseTrackingBo();
        if (StringUtils.isNotBlank(city)) {
            List<Long> customerIds = getCustomerIdsByCity(city);
            trackingBo.setCustomerIds(customerIds);
            outVisitBo.setCustomerIds(customerIds);
            insuranceCaseBo.setCustomerIds(customerIds);
            jobOrderBo.setCustomerIds(customerIds);
            caseTrackingBo.setCustomerIds(customerIds);
        }

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
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return null;
        }

        boolean superAdmin = LoginHelper.isSuperAdmin(userId);
        List<RoleDTO> roles = loginUser.getRoles();
        String roleKey = (roles != null && !roles.isEmpty()) ? roles.get(0).getRoleKey() : null;

        List<DcDebtCaseVo> list = new ArrayList<>();

        if ("LegalSupport_Employee".equals(roleKey)) {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("legal_support_id", userId);
            list = debtCaseMapper.selectVoByMap(queryMap);
        } else if (superAdmin || "LegalSupport_Leader".equals(roleKey)) {
            list = debtCaseMapper.selectVoList();
        } else if ("LegalSupport_Manager".equals(roleKey)) {
            String deptCategory = loginUser.getDeptCategory();
            if (StringUtils.isNotBlank(deptCategory) && !"ADMIN".equals(deptCategory)) {
                String city = deptCategory.substring(0, deptCategory.indexOf('_'));
                Map<String, Object> queryMap = new HashMap<>();
                queryMap.put("remark1", city);
                list = debtCaseMapper.selectVoByMap(queryMap);
            }
        } else {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("legal_support_id", userId);
            list = debtCaseMapper.selectVoByMap(queryMap);
        }

        JSONArray json = new JSONArray();
        for (DcDebtCaseVo dcDebtCaseVo : list) {
            DcCustomerInformation dcCustomerInformation = informationMapper.selectById(dcDebtCaseVo.getCustomerId());
            String companyName = dcCustomerInformation == null ? "" : dcCustomerInformation.getCustomerName();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("case_id", dcDebtCaseVo.getId());
            jsonObject.put("case_detail", "【" + companyName + "】-债务人【" + dcDebtCaseVo.getDebtorName() + "】");
            json.add(jsonObject);
        }
        return json;
    }


    public JSONObject getLegalSupportPerformance(Long userId, String city) {

        JSONObject result = new JSONObject();

        //  客户
        DcCustomerInformationBo dcCustomerInformationBo = new DcCustomerInformationBo();
        dcCustomerInformationBo.setLawyerId(userId);
        dcCustomerInformationBo.setCustomerCity(city);
        List<DcCustomerInformationVo> dcCustomerInformationVos = dcCustomerInformationService.queryList(dcCustomerInformationBo);
        //List<Long> deptIdList = getDeptIdsByCity(city);

        //System.out.println("changkai:"+deptIdList);
        // 近15天未跟进
        List<Map<String, Object>> outstandingCustomer = informationMapper.selectOutstandingCustomer(userId, city);

        // 意向客户
        DcCustomerIntentionBo dcCustomerIntentionBo = new DcCustomerIntentionBo();
        dcCustomerIntentionBo.setLegalSupportId(userId);
        dcCustomerIntentionBo.setRemark1(city);
        List<DcCustomerIntentionVo> dcCustomerIntentionVos = dcCustomerIntentionService.queryList(dcCustomerIntentionBo);
        // 临期（7天到期）客户
        List<Map<String, Object>> expiringCustomers = informationMapper.selectExpiringCustomers(userId, city);
        // 尾款客户
        List<Map<String, Object>> customersWithBalance = informationMapper.selectCustomersWithBalance(userId, city);
        // 本月内勤数量
        List<Map<String, Object>> monthlyTracking = trackingMapper.selectMonthlyTrackingByLegalSupport(userId, city);
        // 出访数量
        List<Map<String, Object>> monthlyOutVisit = customerOutVisitMapper.selectMonthlyOutVisit(userId, city);
        // 保险数量
        List<Map<String, Object>> monthlyInsurance = insuranceCaseMapper.selectMonthlyInsuranceCase(userId, city);
        // 下工单数量
        List<Map<String, Object>> monthlyJobOrder = dcCustomerJobOrderMapper.selectMonthlyJobOrder(userId, city);
        // 案件数量
        List<Map<String, Object>> monthlyCase = dcDebtCaseMapper.selectMonthlyDebtCase(userId, city);
        // 案件跟踪数量
        List<Map<String, Object>> monthlyCaseTracking = dcCaseTrackingMapper.selectMonthlyCaseTracking(userId, city);

        int customerTotal = dcCustomerInformationVos == null ? 0 : dcCustomerInformationVos.size();
        int intentionTotal = dcCustomerIntentionVos == null ? 0 : dcCustomerIntentionVos.size();
        int expiringTotal = expiringCustomers == null ? 0 : expiringCustomers.size();
        int balanceTotal = customersWithBalance == null ? 0 : customersWithBalance.size();
        int trackingTotal = monthlyTracking == null ? 0 : monthlyTracking.size();
        int outVisitTotal = monthlyOutVisit == null ? 0 : monthlyOutVisit.size();
        int insuranceTotal = monthlyInsurance == null ? 0 : monthlyInsurance.size();
        int jobOrderTotal = monthlyJobOrder == null ? 0 : monthlyJobOrder.size();
        int caseTotal = monthlyCase == null ? 0 : monthlyCase.size();
        int caseTrackingTotal = monthlyCaseTracking == null ? 0 : monthlyCaseTracking.size();
        int outstandingTotal = outstandingCustomer == null ? 0 : outstandingCustomer.size();

        // 客户统计
        JSONObject customerCount = new JSONObject();
        customerCount.put("customerTotal", customerTotal);
        customerCount.put("intentionTotal", intentionTotal);
        customerCount.put("expiringTotal", expiringTotal);
        customerCount.put("balanceTotal", balanceTotal);
        customerCount.put("trackingTotal", trackingTotal);
        customerCount.put("outVisitTotal", outVisitTotal);
        customerCount.put("insuranceTotal", insuranceTotal);
        customerCount.put("jobOrderTotal", jobOrderTotal);
        customerCount.put("caseTotal", caseTotal);
        customerCount.put("caseTrackingTotal", caseTrackingTotal);
        customerCount.put("outstandingTotal", outstandingTotal);

        // 业绩统计
        JSONObject performanceCount = performanceCount(userId, city);
        // 套餐类型对比
        List<Map<String, Object>> packageType = informationMapper.selectCustomerPackageType(userId, city);

        // 今日代办事项
        // 意向客户
        List<Map<String, Object>> intentionTodayNeed = dcCustomerIntentionTrackingMapper.selectTodayFollowUpByLegalSupport(userId, city);
        // 回访记录
        List<Map<String, Object>> trackingTodayNeed = trackingMapper.selectTodayFollowUpByLegalSupport(userId, city);
        // 出访记录
        List<Map<String, Object>> outVisitTodayNeed = customerOutVisitMapper.selectTodayFollowUpByLegalSupport(userId, city);
        // 案件跟踪记录
        List<Map<String, Object>> caseTodayNeed = dcCaseTrackingMapper.selectTodayFollowUpByLegalSupport(userId, city);
        // 下工单表交付
        List<Map<String, Object>> jobOrderTodayNeed = dcCustomerJobOrderMapper.selectTodayFollowUpByLegalSupport(userId, city);
//
        JSONArray neededInfo = new JSONArray();
        for (Map<String, Object> map : trackingTodayNeed) {
            JSONObject todayNeed = new JSONObject();
            Long customerId = map.get("customer_id") == null ? null : Long.parseLong(map.get("customer_id").toString());
           //DcCustomerTransfer dcCustomerTransfer = transferMapper.selectById(customerId);
            DcCustomerInformation dcCustomerInfo = informationMapper.selectById(customerId);
            todayNeed.put("customerId", map.get("customer_id"));
            //todayNeed.put("customerName", dcCustomerTransfer == null ? "" : dcCustomerTransfer.getCompanyName());
            todayNeed.put("customerName", dcCustomerInfo == null ? "" : dcCustomerInfo.getCustomerName());
            todayNeed.put("legalSupportId",map.get("legal_support_id"));
            todayNeed.put("city",map.get("remark2"));
            todayNeed.put("nextTrackingTime",map.get("next_time"));
            todayNeed.put("remark", "回访记录表-跟踪");
            neededInfo.add(todayNeed);
        }
        for (Map<String, Object> map : outVisitTodayNeed) {
            JSONObject todayNeed = new JSONObject();
            todayNeed.put("customerId", map.get("customer_id"));
            todayNeed.put("customerName", map.get("customer_name"));
            todayNeed.put("legalSupportId",map.get("legal_support_id"));
            todayNeed.put("city",map.get("remark1"));
            todayNeed.put("nextTrackingTime", Objects.toString(map.get("next_visit_time"), ""));
            todayNeed.put("remark", "出访记录表-跟踪");
            neededInfo.add(todayNeed);
        }
        for (Map<String, Object> map : jobOrderTodayNeed) {
            JSONObject todayNeed = new JSONObject();
            Long customerId = map.get("customer_id") == null ? null : Long.parseLong(map.get("customer_id").toString());
            DcCustomerInformation dcCustomerInfo = informationMapper.selectById(customerId);
            todayNeed.put("customerId", map.get("customer_id"));
            todayNeed.put("customerName", dcCustomerInfo == null ? "" : dcCustomerInfo.getCustomerName());
            todayNeed.put("legalSupportId",map.get("legal_support_id"));
            todayNeed.put("city",map.get("remark2"));
            todayNeed.put("nextTrackingTime",map.get("delivery_time"));
            todayNeed.put("remark", "下工单表-交付");
            neededInfo.add(todayNeed);
        }
        for (Map<String, Object> map : caseTodayNeed) {
            JSONObject todayNeed = new JSONObject();
            todayNeed.put("customerId", map.get("customer_id"));
            todayNeed.put("customerName", map.get("customer_name"));
            todayNeed.put("legalSupportId",map.get("legal_support_id"));
            todayNeed.put("city",map.get("remark1"));
            todayNeed.put("nextTrackingTime",map.get("next_tracking_time"));
            todayNeed.put("remark", "案件回访表-跟踪");
            neededInfo.add(todayNeed);
        }
        for (Map<String, Object> map : intentionTodayNeed) {
            JSONObject todayNeed = new JSONObject();
            todayNeed.put("customerId", map.get("intention_id"));
            todayNeed.put("customerName", map.get("intention_name"));
            todayNeed.put("legalSupportId",map.get("legal_support_id"));
            todayNeed.put("city",map.get("remark1"));
            todayNeed.put("nextTrackingTime",map.get("next_tracking_date"));
            todayNeed.put("remark", "意向客户跟踪表-跟踪");
            neededInfo.add(todayNeed);
        }
        result.put("customerCount", customerCount);
        result.put("performanceCount", performanceCount);
        result.put("neededInfo", neededInfo);
        result.put("packageType", packageType);
        return result;
    }

    public JSONObject performanceCount(Long userId, String city) {
        // 本月已完成业绩 userId, username,monthBalance,performanceRank
        List<Map<String, Object>> monthGoal = dcPerformanceTaskMapper.selectPerformanceTaskByLegalSupportAndMonth(userId, null, city);
        List<Map<String, Object>> monthAchievedPerformance = dcCustomerPerformanceMapper.selectUserPerformanceRank(null, null, userId, city);
        // 年度目标累积业绩金额
        List<Map<String, Object>> yearGoal = dcPerformanceTaskMapper.selectPerformanceTaskByLegalSupportAndYear(userId, null, city);
        // 年度累计业绩金额
        List<Map<String, Object>> yearAchievedPerformance = dcCustomerPerformanceMapper.selectUserPerformanceRank(LocalDate.now().getYear(), null, userId, city);

        JSONObject performanceCount = new JSONObject();
        //Map<String, Object> map1 = monthAchievedPerformance != null && !monthAchievedPerformance.isEmpty() ? monthAchievedPerformance.get(0) : null;
        //Map<String, Object> map2 = YearAchievedPerformance != null && !YearAchievedPerformance.isEmpty() ? YearAchievedPerformance.get(0) : null;
        //Map<String, Object> map3 = monthGoal != null && !monthGoal.isEmpty() ? monthGoal.get(0) : null;

        // 直接使用 List 而不是单个 Map
       // List<Map<String, Object>> monthGoalData = monthGoal != null && !monthGoal.isEmpty() ? monthGoal : new ArrayList<>();

        String monthAchievedBalance = "0";
        String monthPerformanceRank = "";
        String yearAchievedBalance = "0";
        String yearPerformanceRank = "";
        String monthPerformanceGoal = "0";
        String monthVisitGoal = "0";
        /*if (map1 != null && !map1.isEmpty()) {
            monthAchievedBalance = map1.get("monthBalance") == null ? "0" : map1.get("monthBalance").toString();
            monthPerformanceRank = map1.get("performanceRank") == null ? "" : map1.get("performanceRank").toString();
        }*/
        /*if (map2 != null && !map2.isEmpty()) {
            yearAchievedBalance = map2.get("monthBalance") == null ? "0" : map2.get("monthBalance").toString();
            yearPerformanceRank = map2.get("performanceRank") == null ? "" : map2.get("performanceRank").toString();
        }*/
       /* if (map3 != null && !map3.isEmpty()) {
            monthPerformanceGoal = map3.get("sum1") == null ? "0" : map3.get("sum1").toString();
            monthVisitGoal = map3.get("sum2") == null ? "0" : map3.get("sum2").toString();
        }*/

       /* if (monthGoalData != null && !monthGoalData.isEmpty()) {
            monthPerformanceGoal = monthGoalData.get("sum1") == null ? "0" : monthGoalData.get("sum1").toString();
            monthVisitGoal = monthGoalData.get("sum2") == null ? "0" : monthGoalData.get("sum2").toString();
        }*/
        List<Map<String, Object>> processedMonthAchieved = new ArrayList<>();
        if (monthAchievedPerformance != null && !monthAchievedPerformance.isEmpty()) {
            for (Map<String, Object> item : monthAchievedPerformance) {
                Map<String, Object> processedItem = new HashMap<>(item);
                processedItem.put("monthBalance", item.get("monthBalance") == null ? "0" : item.get("monthBalance").toString());
                processedItem.put("performanceRank", item.get("performanceRank") == null ? "" : item.get("performanceRank").toString());
                processedMonthAchieved.add(processedItem);
            }
        }
        List<Map<String, Object>> processedYearAchieved = new ArrayList<>();
        if (yearAchievedPerformance != null && !yearAchievedPerformance.isEmpty()) {
            for (Map<String, Object> item : yearAchievedPerformance) {
                Map<String, Object> processedItem = new HashMap<>(item);
                processedItem.put("monthBalance", item.get("monthBalance") == null ? "0" : item.get("monthBalance").toString());
                processedItem.put("performanceRank", item.get("performanceRank") == null ? "" : item.get("performanceRank").toString());
                processedYearAchieved.add(processedItem);
            }
        }

        List<Map<String, Object>> processedDataMonth = new ArrayList<>();
        if (monthGoal != null && !monthGoal.isEmpty()) {
            for (Map<String, Object> item : monthGoal) {
                Map<String, Object> processedItem = new HashMap<>(item);
                // 处理空值
                processedItem.put("sum1", item.get("sum1") == null ? "0" : item.get("sum1").toString());
                processedItem.put("sum2", item.get("sum2") == null ? "0" : item.get("sum2").toString());
                processedItem.put("sum3", item.get("sum3") == null ? "0" : item.get("sum3").toString());
                processedItem.put("sum4", item.get("sum4") == null ? "0" : item.get("sum4").toString());
                processedDataMonth.add(processedItem);
            }
        }

        List<Map<String, Object>> processedDataYear = new ArrayList<>();
        if (yearGoal != null && !yearGoal.isEmpty()) {
            for (Map<String, Object> item : yearGoal) {
                Map<String, Object> processedItem = new HashMap<>(item);
                // 处理空值
                processedItem.put("sum1", item.get("sum1") == null ? "0" : item.get("sum1").toString());
                processedItem.put("sum2", item.get("sum2") == null ? "0" : item.get("sum2").toString());
                processedItem.put("sum3", item.get("sum3") == null ? "0" : item.get("sum3").toString());
                processedItem.put("sum4", item.get("sum4") == null ? "0" : item.get("sum4").toString());
                processedDataYear.add(processedItem);
            }
        }
       // return processedData; // 返回完整列表

        //performanceCount.put("monthAchievedBalance", monthAchievedBalance);
        //performanceCount.put("monthPerformanceRank", monthPerformanceRank);
        //performanceCount.put("yearAchievedBalance", yearAchievedBalance);
        //performanceCount.put("yearPerformanceRank", yearPerformanceRank);
        //performanceCount.put("monthPerformanceGoal", monthPerformanceGoal);
        //performanceCount.put("monthVisitGoal", monthVisitGoal);
        performanceCount.put("monthPerformanceGoal", JSONArray.parseArray(JSON.toJSONString(processedDataMonth)));
        performanceCount.put("yearPerformanceGoal", JSONArray.parseArray(JSON.toJSONString(processedDataYear)));
        performanceCount.put("monthPerformanceAchieved", JSONArray.parseArray(JSON.toJSONString(processedMonthAchieved)));
        performanceCount.put("yearPerformanceAchieved", JSONArray.parseArray(JSON.toJSONString(processedYearAchieved)));

        return performanceCount;

    }


    public JSONObject getPerformance(LoginUser loginUser) {
        JSONObject result = new JSONObject();
        String deptCategory = loginUser.getDeptCategory();
        String city = null;
        if (StringUtils.isNotBlank(deptCategory) && !("ADMIN").equals(deptCategory)) {
            city = deptCategory.substring(0, deptCategory.indexOf('_'));
        }
        List<Long> deptIdList = getDeptIdsByCity(city);
        DcCustomerInformationBo dcCustomerInformationBo = new DcCustomerInformationBo();
        dcCustomerInformationBo.setCustomerCity(city);

        long customerTotal = dcCustomerInformationService.queryCount(dcCustomerInformationBo);
        List<Map<String, Object>> OutstandingCustomer = informationMapper.selectOutstandingCustomer(null, city);
        DcCustomerIntentionBo dcCustomerIntentionBo = new DcCustomerIntentionBo();
        List<DcCustomerIntentionVo> dcCustomerIntentionVos = dcCustomerIntentionService.queryList(dcCustomerIntentionBo);
        // 临期客户
        List<Map<String, Object>> expiringCustomers = informationMapper.selectExpiringCustomers(null, city);
        // 尾款客户
        List<Map<String, Object>> customersWithBalance = informationMapper.selectCustomersWithBalance(null, city);

        int outStandingTotal = OutstandingCustomer == null ? 0 : OutstandingCustomer.size();
        int intentionTotal = dcCustomerIntentionVos == null ? 0 : dcCustomerIntentionVos.size();
        int expiringTotal = expiringCustomers == null ? 0 : expiringCustomers.size();
        int balanceTotal = customersWithBalance == null ? 0 : customersWithBalance.size();


        result.put("customerTotal", customerTotal);
        result.put("outStandingTotal", outStandingTotal);
        result.put("intentionTotal", intentionTotal);
        result.put("expiringTotal", expiringTotal);
        result.put("balanceTotal", balanceTotal);
        // 团队业绩和目标累计变量
        JSONObject performance = getPerformanceByDept(loginUser.getDeptId());
        result.put("teamPerformanceList", performance == null ? new JSONArray() : performance.get("teamPerformanceList"));
        result.put("teamPerformance", performance == null ? new JSONObject() : performance.get("teamPerformance"));

        return result;
    }



    // 获取流转单数据
    public JSONArray getTransferList() {
        DcCustomerTransferBo bo = new DcCustomerTransferBo();
        bo.setIsSecondaryCharge(0);
        bo.setFinanceConfirmed(1);
        List<DcCustomerTransferVo> list = dcCustomerTransferService.queryList(bo);
        return JSONArray.parseArray(JSON.toJSONString(list));
    }
    // 根据流转单Id获取流转单数据 查询太慢，优化掉
    /*public JSONObject getTransferListById(Long transferId) {
        //查询一次收费流转单
        DcCustomerTransferBo bo = new DcCustomerTransferBo();
        bo.setId(transferId);
        bo.setIsSecondaryCharge(0);
        bo.setFinanceConfirmed(1);
        List<DcCustomerTransferVo> list = dcCustomerTransferService.queryList(bo);
        if (list != null && !list.isEmpty()) {
            return JSONObject.parseObject(JSON.toJSONString(list.get(0)));
        }
        //查询二次收费流转单
        bo.setIsSecondaryCharge(1);
        list = dcCustomerTransferService.queryList(bo);
        if (list != null && !list.isEmpty()) {
            return JSONObject.parseObject(JSON.toJSONString(list.get(0)));
        }
        return null;
    }*/
    public JSONObject getTransferListById(Long transferId) {
        DcCustomerTransferVo transferVo = dcCustomerTransferService.queryById(transferId);
        if (transferVo != null && Integer.valueOf(1).equals(transferVo.getFinanceConfirmed())) {
            return JSONObject.parseObject(JSON.toJSONString(transferVo));
        }
        return null;
    }

    // 接口查询太慢，需优化
    /*public JSONArray getCustomerWithTransferInfo() {
       try {
           DcCustomerInformationBo customerBo = new DcCustomerInformationBo();
           List<DcCustomerInformationVo> customerList = dcCustomerInformationService.queryList(customerBo);

        JSONArray result = new JSONArray();

        for (DcCustomerInformationVo customerVo : customerList) {
            JSONObject customerData;

            if (customerVo.getTransferId() != null) {

                JSONObject transferData = getTransferListById(customerVo.getTransferId());

                if (transferData != null) {
                    customerData = transferData;
                } else {
                    customerData = JSONObject.parseObject(JSON.toJSONString(customerVo));
                }
            } else {
                customerData = JSONObject.parseObject(JSON.toJSONString(customerVo));
            }

            result.add(customerData);
        }
        log.info("getCustomerWithTransferInfo 返回数据量: {}", result.size());
        return result;
    } catch (Exception e) {
           log.error("getCustomerWithTransferInfo 异常", e);
           return new JSONArray();
       }

    }*/
    public JSONArray getCustomerWithTransferInfo() {
        try {
            DcCustomerInformationBo customerBo = new DcCustomerInformationBo();
            List<DcCustomerInformationVo> customerList = dcCustomerInformationService.queryList(customerBo);

            if (customerList == null || customerList.isEmpty()) {
                return new JSONArray();
            }

            JSONArray result = new JSONArray();

            for (DcCustomerInformationVo customerVo : customerList) {
                JSONObject customerData;

                if (customerVo.getTransferId() != null) {
                    JSONObject transferData = getTransferListById(customerVo.getTransferId());

                    if (transferData != null) {
                        customerData = transferData;
                    } else {
                        customerData = JSONObject.parseObject(JSON.toJSONString(customerVo));
                    }
                } else {
                    customerData = JSONObject.parseObject(JSON.toJSONString(customerVo));
                }

                result.add(customerData);
            }

            log.info("getCustomerWithTransferInfo 返回数据量: {}", result.size());
            return result;
        } catch (Exception e) {
            log.error("getCustomerWithTransferInfo 异常", e);
            return new JSONArray();
        }
    }


    public JSONObject getLeaderPerformance() {
        JSONObject result = new JSONObject();
        SysDictDataBo sysDictDataBo = new SysDictDataBo();
        sysDictDataBo.setDictType("dc_sercive_city");
        List<SysDictDataVo> sysDictDataVos = dictDataService.selectDictDataList(sysDictDataBo);
        for (SysDictDataVo sysDictDataVo : sysDictDataVos) {
            JSONObject count = new JSONObject();
            List<Long> deptIds = getDeptIdsByCity(sysDictDataVo.getDictValue());
            if (CollectionUtils.isEmpty(deptIds)) {
                continue;
            }

            count.put("cityName", sysDictDataVo.getDictLabel());
            count.put("cityCode", sysDictDataVo.getDictValue());
            List<DcCustomerInformationVo> dcCustomerInformationVos = dcCustomerInformationService.queryListByCreateDepts(deptIds);
            List<Map<String, Object>> OutstandingCustomer = informationMapper.selectOutstandingCustomer(null, "ZB");
            List<DcCustomerIntentionVo> dcCustomerIntentionVos = dcCustomerIntentionService.queryListByCreateDepts(deptIds);
            List<Map<String, Object>> expiringCustomers = informationMapper.selectExpiringCustomers(null, "ZB");
            List<Map<String, Object>> customersWithBalance = informationMapper.selectCustomersWithBalance(null, "ZB");

            long customerTotal = dcCustomerInformationVos == null ? 0 : dcCustomerInformationVos.size();
            int balanceTotal = customersWithBalance == null ? 0 : customersWithBalance.size();
            int outStandingTotal = OutstandingCustomer == null ? 0 : OutstandingCustomer.size();
                     int intentionTotal = dcCustomerIntentionVos == null ? 0 : dcCustomerIntentionVos.size();
            int expiringTotal = expiringCustomers == null ? 0 : expiringCustomers.size();

            String deptCategory = sysDictDataVo.getDictValue() + "_LegalSupport";
            SysDeptBo sysDeptBo = new SysDeptBo();
            sysDeptBo.setDeptCategory(deptCategory);
            List<SysDeptVo> sysDeptVos = deptService.selectDeptList(sysDeptBo);
            if (CollectionUtils.isNotEmpty(sysDeptVos)) {
                SysDeptVo deptVo = sysDeptVos.get(0);
                JSONObject teamPerformance = getPerformanceByDept(deptVo.getDeptId());
               count.put("teamPerformance", teamPerformance == null ? new JSONObject() : teamPerformance.get("teamPerformance"));
                count.put("teamPerformanceList", teamPerformance == null ? new JSONArray() : teamPerformance.get("teamPerformanceList"));
            }


            count.put("customerTotal", customerTotal);
            count.put("outStandingTotal", outStandingTotal);
            count.put("intentionTotal", intentionTotal);
            count.put("expiringTotal", expiringTotal);
            count.put("balanceTotal", balanceTotal);
            result.put(sysDictDataVo.getDictValue(), count);
        }
        return result;
    }



    private double parseDouble(String value) {
        if (value == null || value.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public List<Long> getDeptIdsByCity(String deptCategory) {
        SysDeptBo sysDeptBo = new SysDeptBo();
        sysDeptBo.setDeptCategory(deptCategory);
        List<SysDeptVo> sysDeptVos = deptService.selectDeptList(sysDeptBo);
        if (CollectionUtils.isEmpty(sysDeptVos)) {
            return null;
        }
        SysDeptVo deptVo = sysDeptVos.get(0);
        SysDeptBo sysDeptBo1 = new SysDeptBo();
        sysDeptBo1.setParentId(deptVo.getDeptId());
        List<SysDeptVo> sysDeptVos1 = deptService.selectDeptList(sysDeptBo1);
        if (CollectionUtils.isEmpty(sysDeptVos1)) {
            return null;
        }

        return sysDeptVos1.stream()
            .map(SysDeptVo::getDeptId)
            .toList();
    }

    public JSONObject getPerformanceByDept(long deptId) {
        double teamMonthAchievedBalance = 0.0;
        double teamYearAchievedBalance = 0.0;
        double teamMonthPerformanceGoal = 0.0;
        double teamMonthVisitGoal = 0.0;
        JSONArray teamPerformanceList = new JSONArray();

        SysDeptVo dept = deptService.selectDeptById(deptId);
        String city = (dept != null && StringUtils.isNotBlank(dept.getDeptCategory()) && !"ADMIN".equals(dept.getDeptCategory()))
            ? dept.getDeptCategory().substring(0, dept.getDeptCategory().indexOf('_')) : null;


        List<SysUserVo> sysUserVos = userService.selectUserListByDept(deptId);
        for (SysUserVo sysUserVo : sysUserVos) {
            JSONObject performanceCount = performanceCount(sysUserVo.getUserId(), city);
            performanceCount.put("userName", sysUserVo.getNickName());
            teamPerformanceList.add(performanceCount);
            teamMonthAchievedBalance += parseDouble(performanceCount.getString("monthAchievedBalance"));
            teamYearAchievedBalance += parseDouble(performanceCount.getString("yearAchievedBalance"));
            teamMonthPerformanceGoal += parseDouble(performanceCount.getString("monthPerformanceGoal"));
            teamMonthVisitGoal += parseDouble(performanceCount.getString("monthVisitGoal"));
        }
        JSONObject result = new JSONObject();
        JSONObject teamPerformance = new JSONObject();
        teamPerformance.put("teamMonthAchievedBalance", teamMonthAchievedBalance);
        teamPerformance.put("teamYearAchievedBalance", teamYearAchievedBalance);
        teamPerformance.put("teamMonthPerformanceGoal", teamMonthPerformanceGoal);
        teamPerformance.put("teamMonthVisitGoal", teamMonthVisitGoal);
        result.put("teamPerformance", teamPerformance);
        result.put("teamPerformanceList", teamPerformanceList);
        return result;
    }

    public List<Long> getCustomerIdsByCity(String city) {
        DcCustomerInformationBo customerInfo = new DcCustomerInformationBo();
        customerInfo.setCustomerCity(city);
        List<DcCustomerInformationVo> informationVoList = dcCustomerInformationService.queryList(customerInfo);
        return informationVoList.stream().map(DcCustomerInformationVo::getId).collect(Collectors.toList());
    }

}
