package org.dromara.common.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.service.commonService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/common")
public class commonController {

    @Resource
    private commonService commonService;


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
        return R.ok(commonService.getCustomerCategory());
    }

}
