package com.moxa.micro.datasource.table;

import com.moxa.dream.system.annotation.Column;
import com.moxa.dream.system.annotation.Id;
import com.moxa.dream.system.annotation.Table;
import lombok.Data;

import java.sql.Types;

@Data
@Table("datasource")
public class Datasource {
    /**
     * id
     */
    @Id
    @Column(value="id",jdbcType = Types.BIGINT)
    private Long id;
    /**
     * 数据源名称
     */
    @Column(value="name",jdbcType = Types.VARCHAR)
    private String name;
    /**
     * 数据源类型
     */
    @Column(value="type",jdbcType = Types.VARCHAR)
    private String type;
    /**
     * 数据源地址
     */
    @Column(value="url",jdbcType = Types.VARCHAR)
    private String url;
    /**
     * 超时时长
     */
    @Column(value="timeout",jdbcType = Types.INTEGER)
    private Integer timeout;
    /**
     * 请求头
     */
    @Column(value="header",jdbcType = Types.VARCHAR)
    private String header;
    /**
     * 用户名
     */
    @Column(value="username",jdbcType = Types.VARCHAR)
    private String username;
    /**
     * 密码
     */
    @Column(value="password",jdbcType = Types.VARCHAR)
    private String password;
    /**
     * 数据源备注
     */
    @Column(value="remark",jdbcType = Types.VARCHAR)
    private String remark;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @Column(value="del_flag",jdbcType = Types.SMALLINT)
    private Integer delFlag;
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
