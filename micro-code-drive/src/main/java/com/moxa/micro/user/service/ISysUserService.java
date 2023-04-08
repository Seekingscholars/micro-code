package com.moxa.micro.user.service;

import com.moxa.dream.template.service.IService;
import com.moxa.micro.user.model.BasicInfoModel;
import com.moxa.micro.user.model.PasswordModel;
import com.moxa.micro.user.view.SysUser;
import com.moxa.micro.user.view.SysUserEditView;
import com.moxa.micro.user.view.SysUserListView;

public interface ISysUserService extends IService<SysUserListView, SysUser> {

    SysUser selectOneUser(String username);

    int updatePassword(PasswordModel userPasswordModel);

    int updateBasicInfo(BasicInfoModel basicInfoModel);

    int editUser(SysUserEditView userEditView);

}
