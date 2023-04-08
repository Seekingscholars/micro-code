package com.moxa.micro.user.mapper;

import com.moxa.dream.system.annotation.Mapper;
import com.moxa.dream.system.annotation.Param;
import com.moxa.dream.system.annotation.Sql;
import com.moxa.micro.user.view.SysUser;


@Mapper
public interface SysUserMapper {
    @Sql("select @all() from sys_user where username=@?(username)")
    SysUser selectOneUser(String username);


    @Sql("update sys_user set password=@?(password) where id=@?(id)")
    int updatePassword(@Param("id") Long id, @Param("password") String password);
}
