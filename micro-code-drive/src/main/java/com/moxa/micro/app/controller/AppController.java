package com.moxa.micro.app.controller;

import com.moxa.micro.app.model.AppModel;
import com.moxa.micro.app.service.IAppService;
import com.moxa.micro.app.view.AppEV;
import com.moxa.micro.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/app")
public class AppController extends BaseController<IAppService, AppEV, AppModel> {
    public AppController() {
        super("应用");
    }
}
