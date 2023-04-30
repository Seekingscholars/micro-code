package com.moxa.micro.datasource.view;

import com.moxa.dream.system.annotation.View;
import com.moxa.micro.datasource.table.Datasource;
import lombok.Data;

@Data
@View(Datasource.class)
public class DatasourceLV {
    /**
     * id
     */
    private Long id;
    /**
     * 数据源名称
     */
    private String name;
    /**
     * 数据源类型
     */
    private String type;
    /**
     * 数据源地址
     */
    private String url;
    /**
     * 数据源备注
     */
    private String remark;
}
