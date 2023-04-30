package com.moxa.micro.appCategory.service;

import com.moxa.dream.template.service.IService;
import com.moxa.micro.appCategory.table.AppCategory;
import com.moxa.micro.appCategory.view.AppCategoryBean;

import java.util.List;

public interface IAppCategoryService extends IService<AppCategory, AppCategory> {
    List<AppCategoryBean> listApp();

    void saveBatch(List<AppCategory> categoryList);

    void removeBatch(List<Long> categoryIds);
}
