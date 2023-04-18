package com.moxa.micro.form.controller;

import com.moxa.micro.core.controller.BaseController;
import com.moxa.micro.form.model.AppFormModel;
import com.moxa.micro.form.service.IAppFormService;
import com.moxa.micro.form.view.AppFormEV;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/form")
public class AppFormController extends BaseController<IAppFormService, AppFormEV, AppFormModel> {
    public AppFormController() {
        super("应用表单");
    }
}
