package com.tongji.boying.dto.userParam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginReturn {
    private String token;
    private String tokenHead;


    public LoginReturn(String token, String tokenHead) {
        this.token = token;
        this.tokenHead = tokenHead;
    }
}
