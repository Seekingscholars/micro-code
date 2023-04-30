package com.moxa.micro.appCategory.model;

import com.moxa.dream.system.annotation.View;
import com.moxa.dream.template.annotation.Sort;
import com.moxa.micro.appCategory.table.AppCategory;
import lombok.Data;

@Data
@View(AppCategory.class)
public class AppCategoryModel {
    @Sort
    private Integer orderNo;
}
