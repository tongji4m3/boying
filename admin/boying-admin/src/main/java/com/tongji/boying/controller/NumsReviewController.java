package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.Review;
import com.tongji.boying.service.NumsReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "NumsReviewController", description = "后台用户评论管理")
@RequestMapping("/review")
public class NumsReviewController {
    @Autowired
    private NumsReviewService numsReviewService;

    @ApiOperation("获取指定ID用户所有评论")
    @RequestMapping(value = "/getUserReview/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getReviewByUserid(@PathVariable Integer id)
    {
        List<Review> list= numsReviewService.getReviewByUserid(id);
        if (list != null)
        {
            return CommonResult.success(list);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除指定ID用户所有评论")
    @RequestMapping(value = "/deleteReview/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteReviewByUserid(@PathVariable Integer id)
    {
        int num= numsReviewService.delete(id);
        if (num >0)
        {
            return CommonResult.success(num);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取指定ID演出所有评论")
    @RequestMapping(value = "/getShowReview/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getReviewByShowid(@PathVariable Integer id)
    {
        List<Review> list= numsReviewService.getReviewByShowid(id);
        if (list != null)
        {
            return CommonResult.success(list);
        }
        return CommonResult.failed();
    }

}
