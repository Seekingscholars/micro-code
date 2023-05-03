package com.moxa.micro.api.controller;

import com.moxa.micro.api.model.RestApiModel;
import com.moxa.micro.api.service.IRestApiService;
import com.moxa.micro.api.view.RestApiEV;
import com.moxa.micro.core.controller.BaseController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/rest")
public class RestApiController extends BaseController<IRestApiService, RestApiEV, RestApiModel> {
    public RestApiController() {
        super("restApi数据源");
    }
}

