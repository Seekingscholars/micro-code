package com.moxa.micro.core.service;


import com.moxa.micro.core.entity.LoginUser;

public interface SysApiService {
    LoginUser getLoginUser(String username);
}
