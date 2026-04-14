package org.dromara.common.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.domain.dto.RoleDTO;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.service.CommonService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/common")
public class CommonController {

    @Resource
    private CommonService commonService;


    /**
     * 根据登录用户获取客户信息
     *
     * @return
     */
    @GetMapping("/getCustomerByUserId")
    public R<JSONArray> getCustomerByUserId() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("用户未登录");
        }
        return R.ok(commonService.getCustomerByUserId(loginUser.getUserId()));
    }

    /**
     * 根据登录用户获取意向客户信息
     */
    @GetMapping("/getIntentionCustomerByUserId")
    public R<JSONArray> getIntentionCustomerByUserId() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("用户未登录");
        }
        return R.ok(commonService.getIntentionCustomerByUserId(loginUser.getUserId()));
    }

    /**
     * 获取客户分类信息
     */
    @GetMapping("/getCustomerType")
    public R<JSONArray> getCustomerType() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("用户未登录");
        }
        return R.ok(commonService.getCustomerType());
    }

    /**
     * 统计客户类型
     */
    @GetMapping("/getCustomerCategory")
    public R<JSONObject> getCustomerCategory() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("用户未登录");
        }
        return R.ok(commonService.getCustomerCategory(loginUser.getUserId()));
    }

    /**
     * 获取服务数据
     */

    @GetMapping("/getServiceData")
    public R<JSONArray> getTrackingCount(@RequestParam(required = false) Integer year,
                                         @RequestParam(required = false) Integer month,
                                         @RequestParam(required = false) Integer day,
                                         @RequestParam(required = false) String lawyerId) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("用户未登录");
        }
        return R.ok(commonService.getServiceData(year, month, day, loginUser.getUserId(), lawyerId == null ? null : Long.valueOf(lawyerId)));
    }

    /**
     * 获取服务数据
     */

    @GetMapping("/getRiskRefundData")
    public R<JSONObject> getRiskRefundData(@RequestParam(required = false) Integer year,
                                           @RequestParam(required = false) Integer month,
                                           @RequestParam(required = false) Integer day,
                                           @RequestParam(required = false) String lawyerId) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("用户未登录");
        }
        return R.ok(commonService.getRiskRefundData(year, month, day, loginUser.getUserId(), lawyerId == null ? null : Long.valueOf(lawyerId)));
    }

    @GetMapping("getAllTrackingRecords")
    public R<JSONObject> getAllTrackingRecords(@RequestParam(required = false) String customerId,
                                               @RequestParam(required = false) String legalSupportId,
                                               @RequestParam(required = false) Integer trackingType,
                                               @RequestParam(required = false) String trackingTime,
                                               @RequestParam(required = false) String nextTrackingTime,
                                               @RequestParam(defaultValue = "0") int pageNum,
                                               @RequestParam(defaultValue = "10") int pageSize) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("用户未登录");
        }
        String deptCategory = loginUser.getDeptCategory();
        String city = null;
        if (StringUtils.isNotBlank(deptCategory) && !("ADMIN").equals(deptCategory)) {
            city = deptCategory.substring(0, deptCategory.indexOf('_'));
        }
        List<RoleDTO> roles = loginUser.getRoles();
        if (roles != null && !roles.isEmpty()) {
            RoleDTO role = roles.get(0);
            if (role.getRoleKey().equals("LegalSupport_Employee")) {
                legalSupportId = loginUser.getUserId() + "";
            }
        }
        return R.ok(commonService.getAllTrackingRecords(customerId, city, legalSupportId, trackingType, trackingTime, nextTrackingTime, pageNum, pageSize));
    }

    /**
     * 根据登录用户获取案件信息
     *
     * @return
     */
    @GetMapping("/getCaseDetail")
    public R<JSONArray> getCaseDetail() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("用户未登录");
        }
        return R.ok(commonService.getCaseDetail(loginUser.getUserId()));
    }

    /**
     * 法务支持绩效
     *
     * @return
     */
    @GetMapping("/getLegalSupportPerformance")
    public R<JSONObject> getLegalSupportPerformance() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("用户未登录");
        }
        List<RoleDTO> roles = loginUser.getRoles();
        Long legalSupportId = null;
        Long deptId = null;
        // 法务支持
        if (roles != null && !roles.isEmpty()) {
            RoleDTO role = roles.get(0);
            if (role.getRoleKey().equals("LegalSupport_Employee")) {
                legalSupportId = loginUser.getUserId();
                deptId = loginUser.getDeptId();
            }
        }
        String deptCategory = loginUser.getDeptCategory();
        String city = null;
        if (StringUtils.isNotBlank(deptCategory) && !("ADMIN").equals(deptCategory)) {
            city = deptCategory.substring(0, deptCategory.indexOf('_'));
        }
        return R.ok(commonService.getLegalSupportPerformance(legalSupportId, city, deptId));
    }

    /**
     * 获取流转单客户基本信息
     */
    @GetMapping("/getTransferList")
    public R<JSONArray> getTransferList() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("用户未登录");
        }
        return R.ok(commonService.getTransferList());
    }
    /**
     * 获取流转单、二次收费流转单、客户总表 客户基本信息
     */
    @GetMapping("/getCustomerWithTransferInfo")
    public R<JSONArray> getCustomerWithTransferInfo() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("用户未登录");
        }
        return R.ok(commonService.getCustomerWithTransferInfo());
    }

    /**
     * 获取团队业绩统计
     */
    @GetMapping("/getPerformance")
    public R<JSONObject> getPerformance() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("用户未登录");
        }
        return R.ok(commonService.getPerformance(loginUser));
    }

    @GetMapping("/getLeaderPerformance")
    public R<JSONObject> getLeaderPerformance() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.warn("用户未登录");
        }
        return R.ok(commonService.getLeaderPerformance());
    }

}
