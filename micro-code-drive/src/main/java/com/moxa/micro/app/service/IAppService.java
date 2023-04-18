package com.moxa.micro.app.service;

import com.moxa.dream.template.service.IService;
import com.moxa.micro.app.view.AppCategoryBean;
import com.moxa.micro.app.view.AppEV;
import com.moxa.micro.app.view.AppLV;

import java.util.List;

public interface IAppService extends IService<AppLV, AppEV> {
    List<AppCategoryBean> listApp();
}
