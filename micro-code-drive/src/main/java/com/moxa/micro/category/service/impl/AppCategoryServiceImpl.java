package com.moxa.micro.category.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.moxa.dream.boot.impl.ServiceImpl;
import com.moxa.micro.app.model.AppModel;
import com.moxa.micro.app.service.IAppService;
import com.moxa.micro.app.view.AppLV;
import com.moxa.micro.category.service.IAppCategoryService;
import com.moxa.micro.category.table.AppCategory;
import com.moxa.micro.category.view.AppCategoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppCategoryServiceImpl extends ServiceImpl<AppCategory, AppCategory> implements IAppCategoryService {
    @Autowired
    private IAppService appService;

    @Override
    public List<AppCategoryBean> listApp() {
        List<AppCategory> appCategoryLVList = this.selectList(null);
        List<AppCategoryBean> appCategoryBeanList = new ArrayList<>();
        if (CollUtil.isNotEmpty(appCategoryLVList)) {
            for (AppCategory appCategory : appCategoryLVList) {
                AppModel appModel = new AppModel();
                appModel.setCategoryId(Arrays.asList(appCategory.getId()));
                List<AppLV> appLVList = appService.selectList(appModel);
                AppCategoryBean appCategoryBean = new AppCategoryBean();
                appCategoryBean.setCategory(appCategory);
                appCategoryBean.setAppList(appLVList);
                appCategoryBeanList.add(appCategoryBean);
            }
        }
        return appCategoryBeanList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBatch(List<AppCategory> categoryList) {
        List<AppCategory> oldCategoryList = this.selectList(null);
        Map<Long, AppCategory> oldCategoryMap = new HashMap<>();
        if (CollUtil.isNotEmpty(oldCategoryList)) {
            for (AppCategory category : oldCategoryList) {
                oldCategoryMap.put(category.getId(), category);
            }
        }
        List<AppCategory> insertCategoryList = new ArrayList<>();
        List<Long> deleteCategoryIdList = new ArrayList<>();
        if (CollUtil.isNotEmpty(categoryList)) {
            for (AppCategory newCategory : categoryList) {
                Long id = newCategory.getId();
                if (id == null) {
                    insertCategoryList.add(newCategory);
                } else {
                    AppCategory oldCategory = oldCategoryMap.get(id);
                    if (oldCategory == null) {
                        deleteCategoryIdList.add(id);
                    } else {
                        if (!oldCategory.getCategoryName().equals(newCategory.getCategoryName()) || !oldCategory.getOrderNo().equals(newCategory.getOrderNo())) {
                            this.updateById(newCategory);
                        }
                    }
                }
            }
        }
        if (CollUtil.isNotEmpty(insertCategoryList)) {
            this.batchInsert(insertCategoryList);
        }
        if (CollUtil.isNotEmpty(deleteCategoryIdList)) {
            this.removeBatch(deleteCategoryIdList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeBatch(List<Long> categoryIds) {
        this.deleteByIds(categoryIds);
        AppModel appModel = new AppModel();
        appModel.setCategoryId(categoryIds);
        List<AppLV> appLVList = appService.selectList(appModel);
        if (CollUtil.isNotEmpty(appLVList)) {
            appService.resetAppCategory(appLVList.stream().map(AppLV::getId).collect(Collectors.toList()), null);
        }
    }
}
