package com.moxa.micro.user.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.moxa.dream.boot.impl.ServiceImpl;
import com.moxa.micro.core.entity.LoginUser;
import com.moxa.micro.core.exception.SoothException;
import com.moxa.micro.core.util.ClientUtil;
import com.moxa.micro.user.mapper.SysUserMapper;
import com.moxa.micro.user.model.BasicInfoModel;
import com.moxa.micro.user.model.PasswordModel;
import com.moxa.micro.user.service.ISysUserService;
import com.moxa.micro.user.view.SysUser;
import com.moxa.micro.user.view.SysUserEditView;
import com.moxa.micro.user.view.SysUserListView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserListView, SysUser> implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser selectOneUser(String username) {
        return sysUserMapper.selectOneUser(username);
    }

    @Override
    public int updatePassword(PasswordModel userPasswordModel) {
        String oldPassword = userPasswordModel.getOldPassword();
        String newPassword = userPasswordModel.getPassword();
        String confirmPassword = userPasswordModel.getConfirmPassword();
        if (StrUtil.isEmpty(oldPassword)) {
            throw new SoothException("旧密码不允许为空");
        }
        if (StrUtil.isEmpty(newPassword)) {
            throw new SoothException("新密码不允许为空");
        }
        if (!newPassword.equals(confirmPassword)) {
            throw new SoothException("两次输入密码不一致");
        }
        LoginUser loginUser = ClientUtil.getLoginUser();
        if (loginUser == null) {
            throw new SoothException("用户不存在");
        }
        if (!BCrypt.checkpw(oldPassword, loginUser.getPassword())) {
            throw new SoothException("旧密码输入错误");
        }
        String password = BCrypt.hashpw(userPasswordModel.getPassword());
        return sysUserMapper.updatePassword(loginUser.getId(), password);
    }

    @Override
    public int updateBasicInfo(BasicInfoModel basicInfoModel) {
        LoginUser loginUser = ClientUtil.getLoginUser();
        if (loginUser == null) {
            throw new SoothException("用户不存在");
        }
        basicInfoModel.setId(loginUser.getId());
        return templateMapper.updateById(basicInfoModel);
    }


    @Override
    public int editUser(SysUserEditView userEditView) {
        return templateMapper.updateNonById(userEditView);
    }
}
