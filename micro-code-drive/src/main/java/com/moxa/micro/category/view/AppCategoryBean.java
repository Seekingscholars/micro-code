package com.moxa.micro.category.view;

import com.moxa.micro.app.view.AppLV;
import com.moxa.micro.category.table.AppCategory;
import lombok.Data;

import java.util.List;

@Data
public class AppCategoryBean {
    private AppCategory category;
    private List<AppLV> appList;
}
