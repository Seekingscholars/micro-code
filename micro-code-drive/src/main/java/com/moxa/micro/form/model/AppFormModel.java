package com.moxa.micro.form.model;

import com.moxa.dream.system.annotation.View;
import com.moxa.dream.template.annotation.Conditional;
import com.moxa.dream.template.condition.EqCondition;
import com.moxa.micro.form.table.AppForm;
import lombok.Data;

@Data
@View(AppForm.class)
public class AppFormModel {
    @Conditional(value = EqCondition.class,nullFlag = false)
    private Long appId;
}
