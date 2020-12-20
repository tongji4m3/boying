package com.tongji.boying.controller;

import cn.hutool.core.util.StrUtil;
import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.UserReviewParam;
import com.tongji.boying.model.Review;
import com.tongji.boying.service.UserReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 用户评价管理Controller
 */
@Controller
@Api(tags = "UserReviewController", description = "用户模块评价相关API")
@RequestMapping("/review")
public class UserReviewController
{
    @Autowired
    private UserReviewService userReviewService;

    @ApiOperation("添加评价,可能是一级评价,也可能是二级评价")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@Validated @RequestBody UserReviewParam param)
    {
        System.out.println(param);


        int count = userReviewService.add(param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除评价")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable int id)
    {
        int count = userReviewService.delete(id);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("显示所有评价")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<Review>> list(@RequestBody Map<String, String> map)
    {
        Integer type = null;
        Integer sort = null;
        Integer pageNum = null;
        Integer pageSize = null;
        Integer parentId = null;
        try {
            pageNum = Integer.parseInt(map.getOrDefault("pageNum", "0"));
            pageSize = Integer.parseInt(map.getOrDefault("pageSize", "5"));
            sort = Integer.parseInt(map.getOrDefault("sort", "0"));
            type = Integer.parseInt(map.getOrDefault("type", "0"));
            parentId = Integer.parseInt(map.getOrDefault("parentId", "0"));
        }
        catch (Exception e) {
            return CommonResult.failed("输入的参数格式有误!");
        }


        List<Review> reviewList = userReviewService.list(type, sort, parentId, pageNum, pageSize);
        if (reviewList.size() == 0) return CommonResult.failed("无评价!");
        return CommonResult.success(CommonPage.restPage(reviewList));
    }

    @ApiOperation("获取评价详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Review> getItem(@PathVariable int id)
    {
        Review review = userReviewService.getItem(id);
        if (review == null) return CommonResult.failed("无此评价!");
        return CommonResult.success(review);
    }

    @ApiOperation("对某条评论进行点赞或举报")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable int id, @RequestBody Map<String, String> map)
    {
        Integer type = null;
        try {
            type = Integer.parseInt(map.getOrDefault("type", "0"));
        }
        catch (Exception e) {
            return CommonResult.failed("输入的参数格式有误!");
        }
        int count = userReviewService.update(id, type);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
