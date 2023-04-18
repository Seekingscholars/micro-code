package com.moxa.micro.category.controller;

import com.moxa.micro.category.model.AppCategoryModel;
import com.moxa.micro.category.service.IAppCategoryService;
import com.moxa.micro.category.table.AppCategory;
import com.moxa.micro.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/category")
public class AppCategoryController extends BaseController<IAppCategoryService, AppCategory, AppCategoryModel> {
    public AppCategoryController() {
        super("应用分类");
    }
}
