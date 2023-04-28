package com.moxa.micro.core.util;

import com.moxa.micro.core.entity.LoginUser;
import org.apache.shiro.SecurityUtils;

public class ClientUtil {

    public static LoginUser getLoginUser() {
        return (LoginUser) SecurityUtils.getSubject().getPrincipal();
    }
}
