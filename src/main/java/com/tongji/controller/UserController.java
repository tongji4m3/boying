package com.tongji.controller;

import com.tongji.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    public String test()
    {
        userService.select(1);
        return ".toString()";
    }
}
