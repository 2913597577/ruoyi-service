package org.dromara.performance.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.domain.dto.RoleDTO;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.web.core.BaseController;
import org.dromara.performance.domain.bo.DcCustomerPerformanceBo;
import org.dromara.performance.domain.vo.DcCustomerPerformanceVo;
import org.dromara.performance.service.IDcCustomerPerformanceService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 业绩归属登记
 *
 * @author Lion Li
 * @date 2025-10-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customerPerformance/customerPerformance")
public class DcCustomerPerformanceController extends BaseController {

    private final IDcCustomerPerformanceService dcCustomerPerformanceService;

    /**
     * 查询业绩归属登记列表
     */
    @SaCheckPermission("customerPerformance:customerPerformance:list")
    @GetMapping("/list")
    public TableDataInfo<DcCustomerPerformanceVo> list(DcCustomerPerformanceBo bo, PageQuery pageQuery) {
        return dcCustomerPerformanceService.queryPageList(bo, pageQuery);
    }


    @PostMapping("/selectListByPage")
    public R<JSONObject> list(@RequestBody Map<String, Object> params) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return null;
        }
        List<Long> userId = convertToList(params.get("userId"), Long.class);
        List<Long> transferId = convertToList(params.get("transferId"), Long.class);
        //System.out.println("transferId 实际类型: " + (transferId.isEmpty() ? "empty" : transferId.get(0).getClass().getName()));
        //System.out.println("transferId 值: " + transferId);
        List<String> city = convertToList(params.get("city"), String.class);
        List<String> serviceCity = convertToList(params.get("serviceCity"), String.class);
        List<Long> inviterId = convertToList(params.get("inviterId"), Long.class);
        List<Integer> serviceType = convertToList(params.get("serviceType"), Integer.class);
        List<Integer> secondDevelopmentType = convertToList(params.get("secondDevelopmentType"), Integer.class);
        List<String> companyName = convertToList(params.get("companyName"), String.class);
        // 按月份筛选
        String updateTimeMonth = params.get("updateTimeMonth") != null ? params.get("updateTimeMonth").toString() : null;

        Date serviceStart = parseDate(params.get("serviceStart"));
        Date serviceEnd = parseDate(params.get("serviceEnd"));
        Integer pageNum = params.get("pageNum") != null ? Integer.valueOf(params.get("pageNum").toString()) : 0;
        Integer pageSize = params.get("pageSize") != null ? Integer.valueOf(params.get("pageSize").toString()) : 10;

        String deptCategory = loginUser.getDeptCategory();
        String cityCode = null;
        if (StringUtils.isNotBlank(deptCategory) && !("ADMIN").equals(deptCategory)) {
            cityCode = deptCategory.substring(0, deptCategory.indexOf('_'));
            serviceCity.add(cityCode);
        }
        List<RoleDTO> roles = loginUser.getRoles();
        if (roles != null && !roles.isEmpty()) {
            RoleDTO role = roles.get(0);
            if (role.getRoleKey().contains("Employee")) {
                userId.add(loginUser.getUserId());
            }
        }

        int count = dcCustomerPerformanceService.countListByPage(
            userId, transferId, city, serviceCity, inviterId, serviceType, secondDevelopmentType,
            serviceStart, serviceEnd, companyName,updateTimeMonth);
        if (count == 0) {
            return R.ok();
        }
        List<Map<String, Object>> list = dcCustomerPerformanceService.selectListByPage(
            userId, transferId, city, serviceCity, inviterId, serviceType, secondDevelopmentType,
            serviceStart, serviceEnd, companyName, updateTimeMonth, pageNum, pageSize);
        JSONObject data = new JSONObject();
        data.put("count", count);
        data.put("list", list);
        return R.ok(data);
    }

  /* @PostMapping("/selectListByPage")
   public R<JSONObject> list(@RequestBody DcCustomerPerformanceBo bo) {
       LoginUser loginUser = LoginHelper.getLoginUser();
       if (loginUser == null) {
           return null;
       }

       List<Long> userId = convertToList(bo.getParams().get("userId"), Long.class);
       List<Long> transferId = convertToList(bo.getParams().get("transferId"), Long.class);
       List<String> city = convertToList(bo.getParams().get("city"), String.class);
       List<String> serviceCity = convertToList(bo.getParams().get("serviceCity"), String.class);
       List<Long> inviterId = convertToList(bo.getParams().get("inviterId"), Long.class);
       List<Integer> serviceType = convertToList(bo.getParams().get("serviceType"), Integer.class);
       List<Integer> secondDevelopmentType = convertToList(bo.getParams().get("secondDevelopmentType"), Integer.class);
       List<String> companyName = convertToList(bo.getParams().get("companyName"), String.class);

       Date serviceStart = parseDate(bo.getParams().get("serviceStart"));
       Date serviceEnd = parseDate(bo.getParams().get("serviceEnd"));
       Integer pageNum = bo.getParams().get("pageNum") != null ? Integer.valueOf(bo.getParams().get("pageNum").toString()) : 1;
       Integer pageSize = bo.getParams().get("pageSize") != null ? Integer.valueOf(bo.getParams().get("pageSize").toString()) : 20;

       String deptCategory = loginUser.getDeptCategory();
       String cityCode = null;
       if (StringUtils.isNotBlank(deptCategory) && !("ADMIN").equals(deptCategory)) {
           cityCode = deptCategory.substring(0, deptCategory.indexOf('_'));
           if (serviceCity == null) {
               serviceCity = new ArrayList<>();
           }
           serviceCity.add(cityCode);
       }
       List<RoleDTO> roles = loginUser.getRoles();
       if (roles != null && !roles.isEmpty()) {
           RoleDTO role = roles.get(0);
           if (role.getRoleKey().contains("Employee")) {
               userId.add(loginUser.getUserId());
           }
       }

       int count = dcCustomerPerformanceService.countListByPage(
           userId, transferId, city, serviceCity, inviterId, serviceType, secondDevelopmentType,
           serviceStart, serviceEnd, companyName, bo.getUpdateTimeMonth());
       if (count == 0) {
           return R.ok();
       }
       List<Map<String, Object>> list = dcCustomerPerformanceService.selectListByPage(
           userId, transferId, city, serviceCity, inviterId, serviceType, secondDevelopmentType,
           serviceStart, serviceEnd, companyName, pageNum, pageSize,bo.getUpdateTimeMonth());
       JSONObject data = new JSONObject();
       data.put("count", count);
       data.put("list", list);
       return R.ok(data);
   }
*/

    /**
     * 导出业绩归属登记列表
     */
    @SaCheckPermission("customerPerformance:customerPerformance:export")
    @Log(title = "业绩归属登记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DcCustomerPerformanceBo bo, HttpServletResponse response) {
        List<DcCustomerPerformanceVo> list = dcCustomerPerformanceService.queryList(bo);
        ExcelUtil.exportExcel(list, "业绩归属登记", DcCustomerPerformanceVo.class, response);
    }

    /**
     * 获取业绩归属登记详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("customerPerformance:customerPerformance:query")
    @GetMapping("/{id}")
    public R<DcCustomerPerformanceVo> getInfo(@NotNull(message = "主键不能为空")
                                              @PathVariable Long id) {
        return R.ok(dcCustomerPerformanceService.queryById(id));
    }

    /**
     * 新增业绩归属登记
     */
    @SaCheckPermission("customerPerformance:customerPerformance:add")
    @Log(title = "业绩归属登记", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DcCustomerPerformanceBo bo) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return R.fail("请先登录");
        }
        bo.setCreaterId(loginUser.getUserId());
        bo.setCreaterName(loginUser.getNickname() == null ? loginUser.getUsername() : loginUser.getNickname());
        return toAjax(dcCustomerPerformanceService.insertByBo(bo));
    }

    /**
     * 修改业绩归属登记
     */
    @SaCheckPermission("customerPerformance:customerPerformance:edit")
    @Log(title = "业绩归属登记", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DcCustomerPerformanceBo bo) {
        return toAjax(dcCustomerPerformanceService.updateByBo(bo));
    }

    /**
     * 删除业绩归属登记
     *
     * @param ids 主键串
     */
    @SaCheckPermission("customerPerformance:customerPerformance:remove")
    @Log(title = "业绩归属登记", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dcCustomerPerformanceService.deleteWithValidByIds(List.of(ids), true));
    }

    private <T> List<T> convertToList(Object obj, Class<T> clazz) {
        if (obj == null) {
            return new ArrayList<>();
        }
        if (obj instanceof List) {
            return (List<T>) obj;
        }
        if (obj instanceof Object[]) {
            return Arrays.asList((T[]) obj);
        }
        return new ArrayList<>();
    }

    private Date parseDate(Object dateObj) {
        if (dateObj == null) {
            return null;
        }
        try {
            if (dateObj instanceof String) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.parse((String) dateObj);
            }
            return (Date) dateObj;
        } catch (Exception e) {
            return null;
        }
    }
}
