package com.tongji.boying.dto;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SmsStatisticsReturnParam
{
    List<Date> dataList;
    List<Long> orderAmountList;
    List<Double> orderCount;
}
