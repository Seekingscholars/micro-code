package com.moxa.micro.app.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.moxa.dream.boot.impl.ServiceImpl;
import com.moxa.micro.app.service.IAppService;
import com.moxa.micro.app.view.AppCategoryBean;
import com.moxa.micro.app.view.AppEV;
import com.moxa.micro.app.view.AppLV;
import com.moxa.micro.category.service.IAppCategoryService;
import com.moxa.micro.category.table.AppCategory;
import com.moxa.micro.form.model.AppFormModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppServiceImpl extends ServiceImpl<AppLV, AppEV> implements IAppService {
    @Autowired
    private IAppCategoryService appCategoryService;

    @Override
    public List<AppCategoryBean> listApp() {
        List<AppCategory> appCategoryLVList = appCategoryService.selectList(null);
        List<AppCategoryBean> appCategoryBeanList = new ArrayList<>();
        if (CollUtil.isNotEmpty(appCategoryLVList)) {
            for (AppCategory appCategory : appCategoryLVList) {
                AppFormModel appFormModel = new AppFormModel();
                appFormModel.setCategoryId(appCategory.getId());
                List<AppLV> appLVList = this.selectList(appFormModel);
                AppCategoryBean appCategoryBean = new AppCategoryBean();
                appCategoryBean.setCategory(appCategory);
                appCategoryBean.setAppList(appLVList);
                appCategoryBeanList.add(appCategoryBean);
            }
        }
        return appCategoryBeanList;
    }
}
