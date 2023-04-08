package com.moxa.micro.core.util;

import com.moxa.micro.core.entity.LoginUser;
import org.apache.shiro.SecurityUtils;

public class ClientUtil {
    private static LoginUser loginUser;

    public static LoginUser getLoginUser() {
        if (loginUser == null) {
            loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        }
        return loginUser;
    }

    public static void setLoginUser(LoginUser loginUser) {
        ClientUtil.loginUser = loginUser;
    }
}
