package com.moxa.micro.app.table;

import com.moxa.dream.system.annotation.Column;
import com.moxa.dream.system.annotation.Id;
import com.moxa.dream.system.annotation.Table;
import com.moxa.micro.core.entity.BaseEntity;
import lombok.Data;

import java.sql.Types;

@Data
@Table("app")
public class App extends BaseEntity {
    @Id
    @Column(value = "id", jdbcType = Types.BIGINT)
    private Long id;
    @Column(value = "app_name", jdbcType = Types.VARCHAR)
    private String apName;
    @Column(value = "image_name", jdbcType = Types.VARCHAR)
    private String imageName;
    @Column(value = "color", jdbcType = Types.VARCHAR)
    private String color;
    @Column(value = "category_id", jdbcType = Types.BIGINT)
    private Long categoryId;

}
