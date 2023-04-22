package com.moxa.micro.form.service;

import com.moxa.dream.template.service.IService;
import com.moxa.micro.core.entity.Result;
import com.moxa.micro.form.view.AppFormEV;
import com.moxa.micro.form.view.AppFormLV;
import com.moxa.micro.form.view.AppFormPermission;

public interface IAppFormService extends IService<AppFormLV, AppFormEV> {
    AppFormPermission getPermission(Long id);

    Integer savePermission(AppFormPermission formPermission);

    Result<String> getFormJson(Long id, String password);
}
