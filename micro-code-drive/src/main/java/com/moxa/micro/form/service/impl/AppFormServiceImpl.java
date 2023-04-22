package com.moxa.micro.form.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.moxa.dream.boot.impl.ServiceImpl;
import com.moxa.micro.core.entity.Result;
import com.moxa.micro.form.service.IAppFormService;
import com.moxa.micro.form.table.AppForm;
import com.moxa.micro.form.view.AppFormEV;
import com.moxa.micro.form.view.AppFormLV;
import com.moxa.micro.form.view.AppFormPermission;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class AppFormServiceImpl extends ServiceImpl<AppFormLV, AppFormEV> implements IAppFormService {

    @Override
    public AppFormPermission getPermission(Long id) {
        return templateMapper.selectById(AppFormPermission.class,id);
    }

    @Override
    public Integer savePermission(AppFormPermission formPermission) {
        return templateMapper.updateById(formPermission);
    }

    @Override
    public Result<String> getFormJson(Long id, String password) {
        AppForm appForm = templateMapper.selectById(AppForm.class, id);
        if(appForm==null||!appForm.getOpen()){
            return new Result(1004,"表单"+id+"不存在");
        }
        Date startExpireTime = appForm.getStartExpireTime();
        Date endExpireTime = appForm.getEndExpireTime();
        Date now=new Date();
        if(startExpireTime!=null&&startExpireTime.after(now)){
            return  new Result(1003,"表单未开始");
        }
        if(endExpireTime!=null&&endExpireTime.before(now)){
            return new Result(1002,"表单已失效");
        }
        String formPassword = appForm.getPassword();
        if(StrUtil.isNotEmpty(formPassword)&&!formPassword.equals(password)){
            return new Result(1001, SecureUtil.md5(SecureUtil.md5(formPassword)));
        }

        return Result.ok(appForm.getFormJson());
    }
}
