package com.moxa.micro.app.view;

import com.moxa.dream.system.annotation.View;
import com.moxa.micro.app.table.App;
import com.moxa.micro.core.entity.BaseEntity;
import lombok.Data;

@Data
@View(App.class)
public class AppEV extends BaseEntity {
    private Long id;
    private Long categoryId;
    private String appName;
    private String imageName;
    private String color;
}
