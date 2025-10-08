package org.dromara.common.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.customer.domain.DcCustomerIntention;
import org.dromara.customer.domain.DcCustomerRiskRefund;
import org.dromara.customer.domain.vo.DcCustomerInformationVo;
import org.dromara.customer.domain.vo.DcCustomerIntentionVo;
import org.dromara.customer.mapper.DcCustomerInformationMapper;
import org.dromara.customer.mapper.DcCustomerIntentionMapper;
import org.dromara.customer.mapper.DcCustomerRiskRefundMapper;
import org.dromara.myCustomer.domain.DcCustomerTransfer;
import org.dromara.myCustomer.mapper.DcCustomerTransferMapper;
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
    private final ISysRoleService roleService;

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
        data.put("customer_name", "请选择意向客户");
        json.add(data);
        for (DcCustomerIntentionVo customerIntentionVo : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("customer_id", customerIntentionVo.getIntendedCustomerId());
            jsonObject.put("customer_name", customerIntentionVo.getIntendedCustomer());
            json.add(jsonObject);
        }
        return json;
    }

    public JSONArray getCustomerType() {
        List<Map<String, Object>> list = informationMapper.selectCustomerCountByType();
        return JSONArray.parseArray(JSONObject.toJSONString(list));
    }

    // 在 getCustomerCategory 方法中补充完整代码
    public JSONObject getCustomerCategory() {
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

}
