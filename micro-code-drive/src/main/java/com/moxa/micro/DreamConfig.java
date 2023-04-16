package com.moxa.micro;

import com.moxa.dream.antlr.invoker.Invoker;
import com.moxa.dream.boot.bean.ConfigurationBean;
import com.moxa.dream.mate.block.inject.BlockInject;
import com.moxa.dream.mate.block.invoker.BlockInvoker;
import com.moxa.dream.mate.logic.inject.LogicInject;
import com.moxa.dream.mate.logic.invoker.LogicInvoker;
import com.moxa.dream.mate.permission.invoker.PermissionGetInvoker;
import com.moxa.dream.mate.permission.invoker.PermissionInjectInvoker;
import com.moxa.dream.system.inject.Inject;
import com.moxa.dream.template.sequence.Sequence;
import com.moxa.micro.core.dream.sequence.SnowFlakeSequence;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DreamConfig {
    private List<String> tableList = new ArrayList<>();
    private List<String> mapperList = new ArrayList<>();
    DreamConfig(){
        String name = this.getClass().getPackage().getName();
        tableList.add(name);
        mapperList.add(name);
    }
    @Bean
    public ConfigurationBean configurationBean() {

        return new ConfigurationBean(tableList, mapperList);
    }

    @Bean
    public Sequence sequence() {
        return new SnowFlakeSequence();
    }
    @Bean
    public Invoker[] invokers() {
        return new Invoker[]{ new BlockInvoker("key.txt"), new LogicInvoker()};
    }
    @Bean
    public Inject[] injects() {
        return new Inject[]{new BlockInject(), new LogicInject(() -> "del_flag")};
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource dataSource() {
        return new HikariDataSource();
    }
}
