package com.moxa.micro.form.view;

import com.moxa.dream.system.annotation.View;
import com.moxa.micro.core.config.App;
import lombok.Data;

@Data
@View(App.class)
public class AppFormLV {
    private Long id;
    private Long categoryId;
    private String appName;
    private String imageName;
    private String color;
}