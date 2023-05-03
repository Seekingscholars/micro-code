package com.moxa.micro.api.view;

import com.moxa.dream.system.annotation.View;
import com.moxa.micro.api.table.RestApi;
import lombok.Data;

@Data
@View(RestApi.class)
public class RestApiLV {
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
