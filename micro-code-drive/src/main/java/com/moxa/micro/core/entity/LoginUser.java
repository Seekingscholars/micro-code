package com.moxa.micro.core.entity;

import lombok.Data;

@Data
public class LoginUser {
    private Long id;
    private String username;
    private String password;
    private String realname;
    private String avator;
    private Integer sex;
    private String phone;
}
