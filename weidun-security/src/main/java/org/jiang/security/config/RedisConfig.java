package org.jiang.security.config;

import org.jiang.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Redis配置类
 * @Author jiang
 * @Create 2021/1/4
 * @Version 1.0
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {
}
