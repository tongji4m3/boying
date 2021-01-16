package com.tongji.boying.dto.userParam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BoyingUserReturn {
    private Integer id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String icon;

    private String realName;

    private String identityNumber;

    private Integer age;

    private Date createTime;

    private Boolean gender;

    private Boolean adminDelete;
}
