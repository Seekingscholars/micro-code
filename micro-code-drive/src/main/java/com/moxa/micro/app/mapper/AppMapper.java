package com.moxa.micro.app.mapper;

import com.moxa.dream.system.annotation.Mapper;
import com.moxa.dream.system.annotation.Param;
import com.moxa.dream.system.annotation.Sql;

import java.util.List;

@Mapper
public interface AppMapper {
    @Sql("update app set category_id=@?(categoryId) where id in(@foreach(appIds))")
    Integer resetAppCategory(@Param("appIds") List<Long> appIds, @Param("categoryId") Long categoryId);
}
