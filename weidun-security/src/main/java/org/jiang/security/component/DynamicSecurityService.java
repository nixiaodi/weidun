package org.jiang.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @Description 动态权限相关业务类
 * @Author jiang
 * @Create 2020/12/29
 * @Version 1.0
 */
public interface DynamicSecurityService {
    /**
     * 加载资源ANT匹配符和资源对应MAP
     * @return
     */
    Map<String, ConfigAttribute> loadDataSource();
}
