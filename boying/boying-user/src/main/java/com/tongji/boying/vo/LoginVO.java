package com.tongji.boying.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginVO {
    private String token;
    private String tokenHead;


    public LoginVO(String token, String tokenHead) {
        this.token = token;
        this.tokenHead = tokenHead;
    }
}
