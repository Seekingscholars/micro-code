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
    private String appName;
    @Column(value = "image_name", jdbcType = Types.VARCHAR)
    private String imageName;
    @Column(value = "color", jdbcType = Types.VARCHAR)
    private String color;
    @Column(value = "category_id", jdbcType = Types.BIGINT)
    private Long categoryId;
    /**
     * 创建人
     */
    @Column(value = "create_by", jdbcType = Types.VARCHAR)
    private String createBy;
    /**
     * 创建时间
     */
    @Column(value = "create_time", jdbcType = Types.DATE)
    private java.util.Date createTime;
    /**
     * 更新人
     */
    @Column(value = "update_by", jdbcType = Types.VARCHAR)
    private String updateBy;
    /**
     * 更新时间
     */
    @Column(value = "update_time", jdbcType = Types.DATE)
    private java.util.Date updateTime;

}
