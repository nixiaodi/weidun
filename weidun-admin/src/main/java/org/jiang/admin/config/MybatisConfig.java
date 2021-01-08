package org.jiang.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description mybatis相关配置
 * @Author jiang
 * @Create 2021/1/6
 * @Version 1.0
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"org.jiang.mapper","org.jiang.admin.dao"})
public class MybatisConfig {
}
