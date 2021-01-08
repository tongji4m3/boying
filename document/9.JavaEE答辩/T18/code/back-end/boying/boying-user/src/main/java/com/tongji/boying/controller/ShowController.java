package com.tongji.boying.controller;

import cn.hutool.core.util.StrUtil;
import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.service.ShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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


    @ApiOperation("获取演出详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<BoyingShow> detail(@PathVariable Integer id) {
        BoyingShow show = showService.detail(id);
        if (show == null) return CommonResult.failed("要查询的showId不存在!");
        return CommonResult.success(show);
    }

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "keyword", value = "演出名称关键词,可模糊查询(默认为空)"),
                    @ApiImplicitParam(name = "city", value = "演出所在城市(默认为空,即全国)"),
                    @ApiImplicitParam(name = "categoryId", value = "演出目录Id(默认不指定id,即查询出所有演出)(如果传入一级目录Id,则查询该一级目录的所有二级目录所对应的演出)"),
                    @ApiImplicitParam(name = "pageNum", value = "第几页(默认为0)(0和1都代表第一页)"),
                    @ApiImplicitParam(name = "pageSize", value = "每页大小(默认为5)"),
                    @ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按推荐降序；2->按时间(按照开始时间降序)；3->最低价格从低到高；4->最低价格从高到低(默认为0) 其他数字默认为按相关度排序"),
                    @ApiImplicitParam(name = "date", value = "搜索演出开始结束日期包含该日期的演出,格式为:yyyy-MM-dd")
            })
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "对演出的综合搜索、筛选、排序(所有字段均为可选字段)")
    public CommonResult<CommonPage<BoyingShow>> search(@RequestBody Map<String, String> map) throws ParseException {
        String keyword = null;
        String city = null;
        Integer categoryId = null;
        Integer pageNum = null;
        Integer pageSize = null;
        Integer sort = null;
        String dateString = null;
        Date date = null;
        try {
            keyword = map.getOrDefault("keyword", "");
            city = map.getOrDefault("city", "");
            //没有则不限制categoryId
            categoryId = Integer.parseInt(map.getOrDefault("categoryId", "-1"));
            pageNum = Integer.parseInt(map.getOrDefault("pageNum", "0"));
            pageSize = Integer.parseInt(map.getOrDefault("pageSize", "5"));
            sort = Integer.parseInt(map.getOrDefault("sort", "0"));
            dateString = map.getOrDefault("date", null);
            if (StrUtil.isNotEmpty(dateString)) {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            }
        }
        catch (Exception e) {
            return CommonResult.failed("输入的参数格式有误!");
        }

        List<BoyingShow> productList = showService.search(keyword, city, categoryId, date, pageNum, pageSize, sort);
        if (productList == null || productList.isEmpty()) return CommonResult.failed("查询列表为空!");
        return CommonResult.success(CommonPage.restPage(productList));
    }
}
