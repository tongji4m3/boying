package com.tongji.boying.search;

import com.tongji.boying.mbg.MbgTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin1")
public class SearchTest
{
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String show()
    {
        MbgTest mbgTest = new MbgTest();
        mbgTest.show();
        System.out.println("showUser");
        return "HELLO World";
    }
}
