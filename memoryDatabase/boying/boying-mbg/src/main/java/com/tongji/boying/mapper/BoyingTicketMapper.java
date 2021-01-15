package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingTicket;
import com.tongji.boying.model.BoyingTicketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BoyingTicketMapper {
    long countByExample(BoyingTicketExample example);

    int deleteByExample(BoyingTicketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoyingTicket record);

    int insertSelective(BoyingTicket record);

    List<BoyingTicket> selectByExample(BoyingTicketExample example);

    BoyingTicket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BoyingTicket record, @Param("example") BoyingTicketExample example);

    int updateByExample(@Param("record") BoyingTicket record, @Param("example") BoyingTicketExample example);

    int updateByPrimaryKeySelective(BoyingTicket record);

    int updateByPrimaryKey(BoyingTicket record);
}
