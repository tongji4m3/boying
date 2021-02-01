package com.tongji.boying.service;

import com.tongji.boying.model.BoyingSeat;
import com.tongji.boying.model.BoyingSeatModel;
import com.tongji.boying.model.BoyingStock;

import java.util.List;

public interface BoyingStockService {

    BoyingStock selectByPrimaryKey(Integer id);
}
