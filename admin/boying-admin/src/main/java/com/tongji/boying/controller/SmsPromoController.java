package com.tongji.boying.controller;

import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.api.CommonResult;
//import com.tongji.boying.dto.UmsRoleParam;
import com.tongji.boying.dto.UmsPromoParam;
import com.tongji.boying.model.BoyingPromo;
//import com.tongji.boying.model.Role;
import com.tongji.boying.service.SmsPromoService;
//import com.tongji.boying.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台活动管理
 */
@Controller
@Api(tags="SmsPromoController",description = "后台活动管理")
@RequestMapping("/promo")
public class SmsPromoController {
    @Autowired
    private SmsPromoService promoService;

    @ApiOperation("查看所有活动")
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> list(){
        List<BoyingPromo> promoList=promoService.list();
        if(ObjectUtil.isEmpty(promoList))return CommonResult.failed("无角色！");
        return CommonResult.success(promoList);
    }

    @ApiOperation("获得某个活动")
    @RequestMapping(value="/list/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<BoyingPromo> getPromo(@PathVariable Integer id){
        BoyingPromo boyingPromo=promoService.getPromo(id);
        return CommonResult.success(boyingPromo);
    }

    @ApiOperation("删除某个活动")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> deleteShow(@PathVariable Integer id) {
        int res = promoService.delete(id);
        if (res > 0) {
            return CommonResult.success(res);
        }
        return CommonResult.failed("删除失败");
    }

    @ApiOperation("添加活动")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> create(@Validated @RequestBody UmsPromoParam param) {
        int count = promoService.create(param);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改活动")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> update(@PathVariable Integer id, @Validated @RequestBody UmsPromoParam param) {
        int count = promoService.update(id, param);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("要修改的活动不存在!");
    }



    @ApiOperation("批量删除演出")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> delete(@RequestParam("ids") List<Integer> ids) {
        int count = promoService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
