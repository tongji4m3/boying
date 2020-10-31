package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.Ticket;
import com.tongji.boying.model.Ticket;
import com.tongji.boying.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 前台票管理Controller
 */
@Controller
@Api(tags = "TicketController", description = "票管理")
@RequestMapping("/ticket")
public class TicketController
{
    @Autowired
    private TicketService ticketService;

    @ApiOperation("添加票")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestParam int orderId,
                            @RequestParam int showClassId)
    {
        int count = ticketService.add(orderId,showClassId);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("显示所有票")
    @RequestMapping(value = "/list/{orderId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<Ticket>> list(@PathVariable int orderId,
                                                   @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                   @RequestParam(required = false, defaultValue = "5") Integer pageSize)
    {
        List<Ticket> tickets = ticketService.list(orderId,pageNum, pageSize);
        if(tickets.size()==0) return CommonResult.failed("当前用户无票!");
        return CommonResult.success(CommonPage.restPage(tickets));
    }

    @ApiOperation("获取票详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Ticket> getItem(@PathVariable int id)
    {
        Ticket ticket = ticketService.getItem(id);
        if(ticket==null) return CommonResult.failed("当前用户无此票!");
        return CommonResult.success(ticket);
    }
}
