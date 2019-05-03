package com.examine.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class SystemRealmConfig {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(SystemRealmConfig.class);

    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        logger.info("shiro filter factory bean");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //设置静态数据
        filterChainDefinitionMap.put("/static/**", "anon");
        //设置退出
        filterChainDefinitionMap.put("/logout", "logout");

        //教师的URL权限设置

        //学生的URL权限设置

        //管理员的URL权限设置

        //test
        filterChainDefinitionMap.put("/success","perms[/test20]");

        shiroFilterFactoryBean.setLoginUrl("/login");

        shiroFilterFactoryBean.setSuccessUrl("/index");

        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean(name = "systemRealm")
    public SystemRealm getSystemRealm() {
        return new SystemRealm();
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("systemRealm") SystemRealm systemRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(systemRealm);
        return securityManager;
    }

    /*@Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");
        mappings.setProperty("UnauthorizedException", "403");
        r.setExceptionMappings(mappings);
        r.setDefaultErrorView("error");
        r.setExceptionAttribute("ex");
        return r;
    }*/
}