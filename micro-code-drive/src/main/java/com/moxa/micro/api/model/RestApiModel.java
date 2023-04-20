package com.moxa.micro.api.model;

import com.moxa.dream.template.annotation.Conditional;
import com.moxa.dream.template.condition.EqCondition;
import lombok.Data;

@Data
public class RestApiModel {
    @Conditional(EqCondition.class)
    private Long formId;
}
