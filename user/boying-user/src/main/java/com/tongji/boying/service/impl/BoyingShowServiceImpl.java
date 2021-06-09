package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.showParam.ShowParam;
import com.tongji.boying.mapper.BoyingOrderMapper;
import com.tongji.boying.mapper.BoyingShowMapper;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.model.BoyingUser;
import com.tongji.boying.service.BoyingShowService;
import com.tongji.boying.service.BoyingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoyingShowServiceImpl implements BoyingShowService {
    @Resource
    private BoyingShowMapper boyingShowMapper;

    @Override
    public List<BoyingShow> search(ShowParam param) {
        Map<String, Object> map = new HashMap<>();
        map.put("sort", param.getSort());
        map.put("city", param.getCity());
        map.put("keyword", param.getKeyword());
        map.put("categoryId", param.getCategoryId());

        SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd");

        if (param.getStartDay() != null && param.getEndDay() != null) {
            map.put("startDay", formatDay.format(param.getStartDay()) + " 00:00:00");
            map.put("endDay", formatDay.format(param.getEndDay()) + " 23:59:59");
        }

        //开启分页相关
        Integer pageNum = param.getPageNum();
        if (pageNum == null || pageNum == 0) pageNum = 1;
        Integer pageSize = param.getPageSize();
        if (pageSize == null || pageSize == 0) pageSize = 5;

        PageHelper.startPage(pageNum, pageSize);
        return boyingShowMapper.selectList(map);
    }

    @Override
    public BoyingShow detail(int id) {
        BoyingShow boyingShow = boyingShowMapper.selectByPrimaryKey(id);
        if (boyingShow == null) Asserts.fail("演出信息不存在！");
        return boyingShow;
    }
}
