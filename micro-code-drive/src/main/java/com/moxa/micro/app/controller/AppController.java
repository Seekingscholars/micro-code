package com.moxa.micro.app.controller;

import com.moxa.micro.app.model.AppModel;
import com.moxa.micro.app.service.IAppService;
import com.moxa.micro.app.view.AppCategoryBean;
import com.moxa.micro.app.view.AppEV;
import com.moxa.micro.core.controller.BaseController;
import com.moxa.micro.core.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/app")
public class AppController extends BaseController<IAppService, AppEV, AppModel> {
    public AppController() {
        super("应用");
    }

    @ResponseBody
    @GetMapping(value = "/listApp")
    public Result listApp() {
        List<AppCategoryBean> result = service.listApp();
        return Result.ok(result);
    }
}
