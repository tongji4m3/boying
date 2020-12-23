package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.UserAddressParam;
import com.tongji.boying.model.Address;
import com.tongji.boying.service.UserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户收货地址管理Controller
 */
@Controller
@Api(tags = "UserAddressController", description = "用户收货地址相关API")
@RequestMapping("/address")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    @ApiOperation("添加收货地址")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@Validated @RequestBody UserAddressParam param) {
        int count = userAddressService.add(param);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除收货地址")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable int id) {
        int count = userAddressService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改收货地址")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable int id, @Validated @RequestBody UserAddressParam param) {
        int count = userAddressService.update(id, param);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("显示所有收货地址")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<Address>> list(@RequestBody Map<String, String> map) {
        Integer pageNum = null;
        Integer pageSize = null;
        try {
            pageNum = Integer.parseInt(map.getOrDefault("pageNum", "0"));
            pageSize = Integer.parseInt(map.getOrDefault("pageSize", "5"));
        }
        catch (Exception e) {
            return CommonResult.failed("输入的参数格式有误!");
        }

        List<Address> addressList = userAddressService.list(pageNum, pageSize);
        if (addressList.size() == 0) return CommonResult.failed("当前用户无收货地址!");
        return CommonResult.success(CommonPage.restPage(addressList));
    }

    @ApiOperation("获取收货地址详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Address> getItem(@PathVariable int id) {
        Address address = userAddressService.getItem(id);
        if (address == null) return CommonResult.failed("当前用户无此收货地址!");
        return CommonResult.success(address);
    }

    @ApiOperation("获取默认收货地址")
    @RequestMapping(value = "/getDefault", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Address> getDefault() {
        Address address = userAddressService.getDefault();
        if (address == null) return CommonResult.failed("当前用户无默认收货地址!");
        return CommonResult.success(address);
    }

    @ApiOperation("设置为默认收货地址")
    @RequestMapping(value = "/setDefault/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult setDefault(@PathVariable int id) {
        System.out.println(id);
        userAddressService.setDefault(id);
        return CommonResult.success("设置默认收货地址成功");
    }
    @ApiOperation("取消设置默认收货地址")
    @RequestMapping(value = "/cancelDefaultAddress", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult setNullAddress() {
        userAddressService.setNullAddress();
        return CommonResult.success("取消默认收货地址成功");
    }
}
