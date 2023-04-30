package com.moxa.micro.datasource.model;

import com.moxa.dream.template.annotation.Conditional;
import com.moxa.dream.template.condition.ContainsCondition;
import com.moxa.dream.template.condition.InCondition;
import lombok.Data;

@Data
public class DatasourceModel {
    @Conditional(ContainsCondition.class)
    private String name;
    @Conditional(InCondition.class)
    private String[] type;
}
