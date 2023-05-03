package com.moxa.micro.api.table;

import com.moxa.dream.system.annotation.Column;
import com.moxa.dream.system.annotation.Id;
import com.moxa.dream.system.annotation.Table;
import lombok.Data;

import java.sql.Types;

@Data
@Table("rest_api")
public class RestApi {
    /**
     * id
     */
    @Id
    @Column(value="id",jdbcType = Types.BIGINT)
    private Long id;
    /**
     * 函数名称
     */
    @Column(value="name",jdbcType = Types.VARCHAR)
    private String name;
    /**
     * 数据源
     */
    @Column(value="datasource_id",jdbcType = Types.BIGINT)
    private Long datasourceId;
    /**
     * 请求地址
     */
    @Column(value="url",jdbcType = Types.VARCHAR)
    private String url;
    /**
     * 请求方法
     */
    @Column(value="method",jdbcType = Types.VARCHAR)
    private String method;
    /**
     * 超时时长
     */
    @Column(value="timeout",jdbcType = Types.INTEGER)
    private Integer timeout;
    /**
     * 函数备注
     */
    @Column(value="remark",jdbcType = Types.VARCHAR)
    private String remark;
    /**
     * 请求头
     */
    @Column(value="header",jdbcType = Types.VARCHAR)
    private String header;
    /**
     * 请求参数
     */
    @Column(value="param",jdbcType = Types.VARCHAR)
    private String param;
    /**
     * 请求参数
     */
    @Column(value="body",jdbcType = Types.VARCHAR)
    private String body;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @Column(value="del_flag",jdbcType = Types.SMALLINT)
    private Integer delFlag;
    /**
     * 请求成功处理函数
     */
    @Column(value="success_function",jdbcType = Types.VARCHAR)
    private String successFunction;
    /**
     * 请求失败处理函数
     */
    @Column(value="fail_function",jdbcType = Types.VARCHAR)
    private String failFunction;
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

    /**
     * 表单id
     */
    @Column(value = "form_id", jdbcType = Types.BIGINT)
    private Long formId;

}
