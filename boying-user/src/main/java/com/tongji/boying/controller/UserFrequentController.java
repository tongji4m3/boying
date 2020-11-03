package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.UserFrequentParam;
import com.tongji.boying.model.Frequent;
import com.tongji.boying.service.UserFrequentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户常用联系人管理Controller
 */
@Controller
@Api(tags = "UserFrequentController", description = "用户常用联系人管理")
@RequestMapping("/frequent")
public class UserFrequentController
{
    @Autowired
    private UserFrequentService userFrequentService;

    @ApiOperation("添加常用联系人")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@Validated @RequestBody UserFrequentParam param)
    {
        int count = userFrequentService.add(param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除常用联系人")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable int id)
    {
        int count = userFrequentService.delete(id);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改常用联系人")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable int id, @RequestBody UserFrequentParam param)
    {
        int count = userFrequentService.update(id, param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("显示所有常用联系人")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<Frequent>> list(@RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                   @RequestParam(required = false, defaultValue = "5") Integer pageSize)
    {
        List<Frequent> frequentList = userFrequentService.list(pageNum, pageSize);
        if (frequentList.size() == 0) return CommonResult.failed("当前用户无常用联系人!");
        return CommonResult.success(CommonPage.restPage(frequentList));
    }

    @ApiOperation("获取常用联系人详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Frequent> getItem(@PathVariable int id)
    {
        Frequent frequent = userFrequentService.getItem(id);
        if (frequent == null) return CommonResult.failed("当前用户无此常用联系人!");
        return CommonResult.success(frequent);
    }


    @ApiOperation("获取默认常用联系人")
    @RequestMapping(value = "/getDefault", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Frequent> getDefault()
    {
        Frequent frequent = userFrequentService.getDefault();
        if (frequent == null) return CommonResult.failed("当前用户无默认常用联系人!");
        return CommonResult.success(frequent);
    }

    @ApiOperation("设置为默认常用联系人")
    @RequestMapping(value = "/setDefault/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult setDefault(@PathVariable int id)
    {
        userFrequentService.setDefault(id);
        return CommonResult.success("设置默认常用联系人成功");
    }
}
