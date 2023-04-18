package com.moxa.micro.core.service.impl;


import com.moxa.micro.core.entity.LoginUser;
import com.moxa.micro.core.exception.MicroException;
import com.moxa.micro.core.service.SysApiService;
import com.moxa.micro.user.service.ISysUserService;
import com.moxa.micro.user.view.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SysApiServiceImpl implements SysApiService {
    @Autowired
    private ISysUserService sysUserService;

    @Override
    public LoginUser getLoginUser(String username) {
        SysUser sysUser = sysUserService.selectOneUser(username);
        if (sysUser == null) {
            throw new MicroException("用户不存在");
        }
        Long id = sysUser.getId();
        LoginUser loginUser = new LoginUser();
        loginUser.setId(id);
        loginUser.setUsername(sysUser.getUsername());
        loginUser.setPassword(sysUser.getPassword());
        loginUser.setRealname(sysUser.getRealname());
        loginUser.setAvator(sysUser.getAvatar());
        loginUser.setSex(sysUser.getSex());
        loginUser.setPhone(sysUser.getPhone());
        return loginUser;
    }
}
