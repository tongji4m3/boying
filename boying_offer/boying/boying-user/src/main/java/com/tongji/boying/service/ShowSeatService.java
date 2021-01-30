package com.tongji.boying.service;

import com.tongji.boying.model.BoyingSeat;

import java.util.List;

public interface ShowSeatService {
    List<BoyingSeat> getShowSeatList(Integer showId);

    Integer getSeatStock(Integer seatId);

    BoyingSeat selectByPrimaryKey(Integer seatId);

    Integer decreaseStock(Integer seatId, Integer ticketCount);

    void increaseStock(Integer seatId, Integer ticketCount);
}
