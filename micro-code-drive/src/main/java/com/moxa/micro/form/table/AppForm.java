package com.moxa.micro.form.table;

import com.moxa.dream.system.annotation.Column;
import com.moxa.dream.system.annotation.Id;
import com.moxa.dream.system.annotation.Table;
import com.moxa.micro.core.entity.BaseEntity;
import lombok.Data;

import java.sql.Types;

@Data
@Table("app_form")
public class AppForm extends BaseEntity {
    @Id
    @Column(value = "id", jdbcType = Types.BIGINT)
    private Long id;
    @Column(value = "form_name", jdbcType = Types.VARCHAR)
    private String formName;
    @Column(value = "form_json", jdbcType = Types.VARCHAR)
    private String formJson;
    @Column(value = "app_id", jdbcType = Types.BIGINT)
    private Long appId;
}
