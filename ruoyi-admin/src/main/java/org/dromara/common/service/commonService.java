package org.dromara.common.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.customer.domain.vo.DcCustomerInformationVo;
import org.dromara.customer.mapper.DcCustomerInformationMapper;
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
}
