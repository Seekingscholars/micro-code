package com.moxa.micro.app.model;

import com.moxa.dream.template.annotation.Conditional;
import com.moxa.dream.template.condition.InCondition;
import lombok.Data;

import java.util.List;

@Data
public class AppModel {
    @Conditional(value = InCondition.class, nullFlag = false)
    private List<Long> categoryId;
}
