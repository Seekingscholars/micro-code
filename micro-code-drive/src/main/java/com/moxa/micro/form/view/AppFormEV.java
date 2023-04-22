package com.moxa.micro.form.view;

import com.moxa.dream.system.annotation.View;
import com.moxa.dream.template.annotation.Wrap;
import com.moxa.dream.template.wrap.ZeroWrapper;
import com.moxa.micro.core.entity.BaseEntity;
import com.moxa.micro.form.table.AppForm;
import lombok.Data;


@Data
@View(AppForm.class)
public class AppFormEV extends BaseEntity {
    private Long id;
    private String formName;
    private Long appId;
    private String formJson;
    @Wrap(ZeroWrapper.class)
    private Integer delFlag;
}
