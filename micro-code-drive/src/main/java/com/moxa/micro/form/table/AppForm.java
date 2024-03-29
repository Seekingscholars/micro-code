package com.moxa.micro.form.table;

import com.moxa.dream.system.annotation.Column;
import com.moxa.dream.system.annotation.Id;
import com.moxa.dream.system.annotation.Table;
import lombok.Data;

import java.sql.Types;

@Data
@Table("app_form")
public class AppForm {
    @Id
    @Column(value = "id", jdbcType = Types.BIGINT)
    private Long id;
    @Column(value = "form_name", jdbcType = Types.VARCHAR)
    private String formName;
    @Column(value = "form_json", jdbcType = Types.VARCHAR)
    private String formJson;
    @Column(value = "app_id", jdbcType = Types.BIGINT)
    private Long appId;
    @Column(value = "del_flag", jdbcType = Types.SMALLINT)
    private Integer delFlag;
    /**
     * 是否分享
     */
    @Column(value="open",jdbcType = Types.SMALLINT)
    private Boolean open;
    /**
     * 密码
     */
    @Column(value="password",jdbcType = Types.VARCHAR)
    private String password;
    /**
     * 有效日期起
     */
    @Column(value = "start_expire_time", jdbcType = Types.DATE)
    private java.util.Date startExpireTime;
    /**
     * 有效日期止
     */
    @Column(value = "end_expire_time", jdbcType = Types.DATE)
    private java.util.Date endExpireTime;
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
