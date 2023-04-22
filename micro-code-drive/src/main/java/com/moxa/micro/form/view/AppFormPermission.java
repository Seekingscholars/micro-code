package com.moxa.micro.form.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moxa.dream.system.annotation.View;
import com.moxa.micro.core.entity.BaseEntity;
import com.moxa.micro.form.table.AppForm;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@View(AppForm.class)
public class AppFormPermission extends BaseEntity {
    private Long id;
    /**
     * 是否分享
     */
    private Boolean open;
    /**
     * 密码
     */
    private String password;
    /**
     * 有效日期起
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date startExpireTime;
    /**
     * 有效日期止
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date endExpireTime;
}
