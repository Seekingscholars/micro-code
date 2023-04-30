package com.moxa.micro.form.controller;

import com.moxa.micro.core.controller.BaseController;
import com.moxa.micro.core.entity.Result;
import com.moxa.micro.form.model.AppFormModel;
import com.moxa.micro.form.service.IAppFormService;
import com.moxa.micro.form.view.AppFormEV;
import com.moxa.micro.form.view.AppFormPermission;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/form")
public class AppFormController extends BaseController<IAppFormService, AppFormEV, AppFormModel> {
    public AppFormController() {
        super("应用表单");
    }

    @ResponseBody
    @GetMapping(value = "/getPermission")
    public Result getPermission(@RequestParam(name = "id") Long id) {
        AppFormPermission result = service.getPermission(id);
        return Result.ok(result);
    }

    @ResponseBody
    @PostMapping(value = "/savePermission")
    public Result savePermission(@RequestBody AppFormPermission formPermission) {
        if (retBool(service.savePermission(formPermission))) {
            return Result.ok(null, "添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @ResponseBody
    @GetMapping(value = "/getFormJson")
    public Result getFormJson(@RequestParam(name = "id") Long id,@RequestParam(name="password",required = false)String password) {
        Result result = service.getFormJson(id,password);
        return result;
    }

    @ResponseBody
    @PostMapping(value = "/submit")
    public Result submit(@RequestBody Map<String,Object>map) {
        return Result.ok();
    }
}
