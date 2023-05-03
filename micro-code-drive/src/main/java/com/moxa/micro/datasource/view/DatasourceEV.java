package com.moxa.micro.datasource.view;

import com.moxa.dream.system.annotation.Extract;
import com.moxa.dream.system.annotation.View;
import com.moxa.dream.template.annotation.Wrap;
import com.moxa.dream.template.annotation.WrapType;
import com.moxa.dream.template.wrap.ZeroWrapper;
import com.moxa.micro.core.dream.extract.PasswordExtractor;
import com.moxa.micro.core.dream.wrap.PasswordWrapper;
import com.moxa.micro.core.entity.BaseEntity;
import com.moxa.micro.datasource.table.Datasource;
import lombok.Data;


@Data
@View(Datasource.class)
public class DatasourceEV extends BaseEntity {
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
     * 超时时长
     */
    private Integer timeout;
    /**
     * 请求头
     */
    private String header;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    @Extract(PasswordExtractor.class)
    @Wrap(PasswordWrapper.class)
    private String password;
    /**
     * 数据源备注
     */
    private String remark;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @Wrap(value = ZeroWrapper.class,type = WrapType.INSERT)
    private Integer delFlag;
}
