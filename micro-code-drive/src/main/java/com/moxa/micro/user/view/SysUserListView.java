package com.moxa.micro.user.view;

import com.moxa.dream.system.annotation.View;
import com.moxa.micro.user.table.$SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@View($SysUser.class)
public class SysUserListView {

    /**
     * id
     */
    private Long id;

    /**
     * 登录账号
     */

    private String username;

    /**
     * 真实姓名
     */

    private String realname;


    private Integer sex;

    /**
     * 电话
     */

    private String phone;
}
