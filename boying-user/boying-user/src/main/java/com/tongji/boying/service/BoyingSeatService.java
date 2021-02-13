package com.tongji.boying.service;

import com.tongji.boying.model.BoyingSeat;
import com.tongji.boying.model.BoyingSeatModel;

import java.util.List;

public interface BoyingSeatService {
    List<BoyingSeatModel> getShowSeatList(Integer showId);

    Integer getSeatStock(Integer seatId);

    BoyingSeat selectByPrimaryKey(Integer seatId);

    Integer decreaseStock(Integer seatId, Integer ticketCount);

    void increaseStock(Integer seatId, Integer ticketCount);

    BoyingSeatModel getShowSeat(Integer seatId);
}
