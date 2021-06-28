package com.tongji.boying.integration;

import base.InitHttpCase;
import com.tongji.boying.model.BoyingStock;
import com.tongji.boying.service.BoyingStockService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IT_TD_001_Test extends InitHttpCase {
    @Autowired
    private BoyingStockService boyingStockService;

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/IT_TD_001_Test.csv", numLinesToSkip = 1)
    public void selectByPrimaryKey(Integer id, String expected) {
        BoyingStock boyingStock = boyingStockService.selectByPrimaryKey(id);
        if (boyingStock == null) assertEquals(expected, "null");
        else assertEquals(expected, String.valueOf(boyingStock.getId()));
    }
}
