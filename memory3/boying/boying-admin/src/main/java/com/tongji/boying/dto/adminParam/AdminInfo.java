package com.tongji.boying.dto.adminParam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class AdminInfo {
    private String username;
    private Date lastTime;
    private String icon;
}
