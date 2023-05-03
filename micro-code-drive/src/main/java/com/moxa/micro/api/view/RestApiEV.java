package com.moxa.micro.api.view;

import com.moxa.dream.system.annotation.Column;
import com.moxa.dream.system.annotation.Id;
import com.moxa.dream.system.annotation.View;
import com.moxa.dream.template.annotation.Wrap;
import com.moxa.dream.template.wrap.ZeroWrapper;
import com.moxa.micro.api.table.RestApi;
import com.moxa.micro.core.entity.BaseEntity;
import lombok.Data;

import java.sql.Types;

@Data
@View(RestApi.class)
public class RestApiEV extends BaseEntity {
    /**
     * id
     */
    private Long id;
    /**
     * 函数名称
     */
    private String name;
    /**
     * 数据源
     */
    private Long datasourceId;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 超时时长
     */
    private Integer timeout;
    /**
     * 函数备注
     */
    private String remark;
    /**
     * 请求头
     */
    private String header;
    /**
     * 请求参数
     */
    private String param;
    /**
     * 请求参数
     */
    private String body;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @Wrap(ZeroWrapper.class)
    private Integer delFlag;
    /**
     * 请求成功处理函数
     */
    private String successFunction;
    /**
     * 请求失败处理函数
     */
    private String failFunction;
    /**
     * 表单id
     */
    private Long formId;
}
