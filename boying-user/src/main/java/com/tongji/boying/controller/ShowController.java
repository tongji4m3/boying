package com.tongji.boying.controller;

import cn.hutool.core.util.StrUtil;
import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.service.ShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 前台演出管理Controller
 */
@Controller
@Api(tags = "ShowController", description = "前台演出相关API")
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @ApiOperation(value = "对演出的综合搜索、筛选、排序")
    @ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按推荐；2->按时间；3->最低价格从低到高；4->最低价格从高到低",
            defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<BoyingShow>> search(@RequestBody Map<String, String> map) throws ParseException {
        String keyword = map.getOrDefault("keyword", "");
        String city = map.getOrDefault("city", "");
        Integer categoryId = Integer.parseInt(map.getOrDefault("categoryId", "-1"));
        Integer pageNum = Integer.parseInt(map.getOrDefault("pageNum", "0"));
        Integer pageSize = Integer.parseInt(map.getOrDefault("pageSize", "5"));
        Integer sort = Integer.parseInt(map.getOrDefault("sort", "0"));
        String dateString = map.getOrDefault("date", null);
        Date date = null;

        System.out.println(categoryId+ "categoryId");
        System.out.println(pageNum+ "pageNum");
        System.out.println(pageSize+ "pageSize");
        System.out.println(sort+ "sort");

        if (StrUtil.isNotEmpty(dateString)) {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        }
        System.out.println(date+"date");

        List<BoyingShow> productList = showService.search(keyword, city, categoryId, date, pageNum, pageSize, sort);
        return CommonResult.success(CommonPage.restPage(productList));
    }

    @ApiOperation("获取演出详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<BoyingShow> detail(@PathVariable Integer id) {
        BoyingShow show = showService.detail(id);
        return CommonResult.success(show);
    }
}
