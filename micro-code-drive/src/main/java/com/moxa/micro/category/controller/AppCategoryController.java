package com.moxa.micro.category.controller;

import com.moxa.micro.category.model.AppCategoryModel;
import com.moxa.micro.category.service.IAppCategoryService;
import com.moxa.micro.category.table.AppCategory;
import com.moxa.micro.category.view.AppCategoryBean;
import com.moxa.micro.core.controller.BaseController;
import com.moxa.micro.core.entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")
public class AppCategoryController extends BaseController<IAppCategoryService, AppCategory, AppCategoryModel> {
    public AppCategoryController() {
        super("应用分类");
    }

    @ResponseBody
    @GetMapping(value = "/listApp")
    public Result listApp() {
        List<AppCategoryBean> result = service.listApp();
        return Result.ok(result);
    }

    @ResponseBody
    @PostMapping(value = "/saveBatch")
    public Result saveBatch(@RequestBody List<AppCategory> categoryList) {
        service.saveBatch(categoryList);
        return Result.ok(null, "添加成功");
    }

}
