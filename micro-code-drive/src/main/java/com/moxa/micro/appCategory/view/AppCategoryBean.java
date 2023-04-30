package com.moxa.micro.appCategory.view;

import com.moxa.micro.app.view.AppLV;
import com.moxa.micro.appCategory.table.AppCategory;
import lombok.Data;

import java.util.List;

@Data
public class AppCategoryBean {
    private AppCategory category;
    private List<AppLV> appList;
}
