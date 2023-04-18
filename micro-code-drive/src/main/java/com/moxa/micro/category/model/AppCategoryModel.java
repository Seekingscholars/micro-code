package com.moxa.micro.category.model;

import com.moxa.dream.system.annotation.View;
import com.moxa.dream.template.annotation.Sort;
import com.moxa.micro.category.table.AppCategory;
import lombok.Data;

@Data
@View(AppCategory.class)
public class AppCategoryModel {
    @Sort
    private Integer orderNo;
}
