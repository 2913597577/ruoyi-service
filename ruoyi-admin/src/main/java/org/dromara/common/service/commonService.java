package org.dromara.common.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.customer.domain.vo.DcCustomerInformationVo;
import org.dromara.customer.mapper.DcCustomerInformationMapper;
import org.dromara.myCustomer.domain.DcCustomerTransfer;
import org.dromara.myCustomer.mapper.DcCustomerTransferMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class commonService {

    private final DcCustomerInformationMapper informationMapper;
    private final DcCustomerTransferMapper transferMapper;

    public JSONArray getCustomerByUserId(long userId) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("create_by", userId);
        List<DcCustomerInformationVo> list = informationMapper.selectVoByMap(queryMap);
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
}
