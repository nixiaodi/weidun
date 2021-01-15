package org.jiang.admin.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.jiang.common.config.BaseSwaggerConfig;
import org.jiang.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description Swagger API文档相关配置
 * @Author jiang
 * @Create 2021/1/6
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("org.jiang.admin.controller")
                .title("威盾后台管理系统")
                .description("威盾后台相关接口文档")
                .contactName("jiang")
                .version("1.0")
                .enableSecurity(false)
                .build();
    }
}
