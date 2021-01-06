package org.jiang.admin.config;

import org.jiang.admin.service.UwdResourceService;
import org.jiang.admin.service.UwdUserService;
import org.jiang.model.UwdResource;
import org.jiang.security.component.DynamicSecurityService;
import org.jiang.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description admin模块security配置
 * @Author jiang
 * @Create 2021/1/5
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdminSecurityConfig extends SecurityConfig {
    @Autowired
    private UwdUserService uwdUserService;
    @Autowired
    private UwdResourceService uwdResourceService;

    @Bean
    public UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return username -> uwdUserService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return () -> {
            Map<String,ConfigAttribute> map = new ConcurrentHashMap<>();
            List<UwdResource> resourceList = uwdResourceService.listAll();
            for (UwdResource resource : resourceList) {
                map.put(resource.getUrl(),new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
            }
            return map;
        };
    }
 }
