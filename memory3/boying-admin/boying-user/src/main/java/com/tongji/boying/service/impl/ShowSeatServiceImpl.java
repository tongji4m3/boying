package com.tongji.boying.service.impl;

import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.mapper.BoyingSeatMapper;
import com.tongji.boying.model.*;
import com.tongji.boying.service.ShowSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowSeatServiceImpl implements ShowSeatService {
    @Autowired
    private BoyingSeatMapper showSeatMapper;

    @Override
    public List<BoyingSeat> getShowSeatList(Integer showId) {
        List<BoyingSeat> boyingSeats = showSeatMapper.selectList(showId);
        if (boyingSeats == null || boyingSeats.size() == 0) {
            Asserts.fail("演出座次不存在！");
        }
        return boyingSeats;
    }
}
