package com.moxa.micro.appCategory.table;

import com.moxa.dream.system.annotation.Column;
import com.moxa.dream.system.annotation.Id;
import com.moxa.dream.system.annotation.Table;
import lombok.Data;

import java.sql.Types;

@Data
@Table("app_category")
public class AppCategory {
    @Id
    @Column(value = "id", jdbcType = Types.BIGINT)
    private Long id;
    @Column(value = "category_name", jdbcType = Types.VARCHAR)
    private String categoryName;
    @Column(value = "order_no", jdbcType = Types.INTEGER)
    private Integer orderNo;
}
