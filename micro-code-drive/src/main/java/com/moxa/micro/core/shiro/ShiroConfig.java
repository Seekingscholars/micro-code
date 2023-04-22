package com.moxa.micro.core.shiro;

import cn.hutool.core.collection.CollUtil;
import com.moxa.micro.core.config.MicroProperties;
import com.moxa.micro.core.shiro.filters.JwtFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SimpleSessionFactory;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
public class ShiroConfig {
    @Autowired
    private MicroProperties microProperties;
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter(SessionsSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        List<String> excludeUrls = microProperties.getExcludeUrls();
        if(CollUtil.isNotEmpty(excludeUrls)){
            for(String excludeUrl:excludeUrls){
                filterChainDefinitionMap.put(excludeUrl, "anon");
            }
        }
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/sys/randomImage/**", "anon");
        filterChainDefinitionMap.put("/sys/checkCaptcha", "anon");
        filterChainDefinitionMap.put("/sys/login", "anon");
        filterChainDefinitionMap.put("/sys/logout", "anon");
        filterChainDefinitionMap.put("/form/getFormJson","anon");

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>(1);
        //如果cloudServer为空 则说明是单体 需要加载跨域配置【微服务跨域切换】
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        // <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        filterChainDefinitionMap.put("/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public Realm realm() {
        return new ShiroRealm();
    }

    @Bean
    public SessionsSecurityManager webSecurityManager(Realm realm) {
        return new DefaultWebSecurityManager(realm);
    }

    @Bean
    public SimpleSessionFactory simpleSessionFactory() {
        return new SimpleSessionFactory();
    }
}
