package com.tongji.boying.user;

import com.tongji.boying.mbg.MbgTest;
import com.tongji.boying.security.SecurityTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class UserTest
{
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String show()
    {
        SecurityTest securityTest = new SecurityTest();
        securityTest.show();
        MbgTest mbgTest = new MbgTest();
        mbgTest.show();
        System.out.println("showUser");
        return "HELLO";
    }
}
