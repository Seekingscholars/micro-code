package com.moxa.micro.form.view;

import com.moxa.dream.system.annotation.View;
import com.moxa.micro.form.table.AppForm;
import lombok.Data;

@Data
@View(AppForm.class)
public class AppFormLV {
    private Long id;
    private Long appId;
    private String formName;

}
