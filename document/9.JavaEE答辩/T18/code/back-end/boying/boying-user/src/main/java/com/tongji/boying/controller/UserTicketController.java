package com.tongji.boying.controller;

import cn.hutool.core.util.StrUtil;
import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.model.Ticket;
import com.tongji.boying.service.UserTicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 前台票管理Controller
 */
@Controller
@Api(tags = "TicketController", description = "用户模块票相关API")
@RequestMapping("/ticket")
public class UserTicketController {
    @Autowired
    private UserTicketService userTicketService;


    @ApiOperation("显示所有票")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<Ticket>> list(@RequestBody Map<String, String> map) {
        String orderIdStr = map.get("orderId");
        if (StrUtil.isEmpty(orderIdStr)) {
            Asserts.fail("orderId不能为空");
        }
        Integer orderId = null;
        Integer pageNum = null;
        Integer pageSize = null;
        try {
            pageNum = Integer.parseInt(map.getOrDefault("pageNum", "0"));
            pageSize = Integer.parseInt(map.getOrDefault("pageSize", "5"));
            orderId = Integer.parseInt(orderIdStr);
        }
        catch (Exception e) {
            return CommonResult.failed("输入的参数格式有误!");
        }

        List<Ticket> tickets = userTicketService.list(orderId, pageNum, pageSize);
        if (tickets.size() == 0) return CommonResult.failed("当前用户无票!");
        return CommonResult.success(CommonPage.restPage(tickets));
    }

    @ApiOperation("获取票详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Ticket> getItem(@PathVariable int id) {
        Ticket ticket = userTicketService.getItem(id);
        if (ticket == null) return CommonResult.failed("当前用户无此票!");
        return CommonResult.success(ticket);
    }
}
