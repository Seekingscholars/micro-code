package com.moxa.micro.user.controller;



import com.moxa.micro.core.entity.Result;
import com.moxa.micro.user.model.BasicInfoModel;
import com.moxa.micro.user.model.PasswordModel;
import com.moxa.micro.user.model.SysUserModel;
import com.moxa.micro.user.view.SysUser;
import com.moxa.micro.user.view.SysUserEditView;
import com.moxa.micro.core.controller.BaseController;
import com.moxa.micro.user.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController<ISysUserService, SysUser, SysUserModel> {
    public SysUserController() {
        super("用户管理");
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    public Result<?> updatePassword(@RequestBody PasswordModel userPasswordModel) {
        if (retBool(service.updatePassword(userPasswordModel))) {
            return Result.ok(null, "密码修改成功");
        } else {
            return Result.error("密码修改失败");
        }
    }


    @PostMapping("/editUser")
    public Result editUser(@RequestBody SysUserEditView userEditView) {
        if (retBool(service.editUser(userEditView))) {
            return Result.ok(null, "编辑成功");
        } else {
            return Result.error("编辑失败");
        }
    }
}
