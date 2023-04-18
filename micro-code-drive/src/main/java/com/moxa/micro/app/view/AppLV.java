package com.moxa.micro.app.view;

import com.moxa.dream.system.annotation.View;
import com.moxa.micro.app.table.App;
import lombok.Data;

@Data
@View(App.class)
public class AppLV {
    private Long id;
    private Long categoryId;
    private String appName;
    private String imageName;
    private String color;
}
