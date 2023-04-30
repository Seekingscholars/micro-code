package com.moxa.micro.datasource.controller;

import com.moxa.micro.core.controller.BaseController;
import com.moxa.micro.core.entity.Result;
import com.moxa.micro.datasource.model.DatasourceModel;
import com.moxa.micro.datasource.service.IDatasourceService;
import com.moxa.micro.datasource.view.DatasourceEV;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/datasource")
public class DatasourceController extends BaseController<IDatasourceService, DatasourceEV, DatasourceModel> {
    public DatasourceController() {
        super("数据源");
    }
    @ResponseBody
    @PostMapping(value = "/testConnection")
    public Result testConnection(@RequestBody DatasourceEV datasourceEV) {
        String data=service.testConnection(datasourceEV);
        return Result.ok(null,data);
    }

}

