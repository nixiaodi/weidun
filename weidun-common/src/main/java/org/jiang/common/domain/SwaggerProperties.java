package org.jiang.common.domain;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/1/4
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class SwaggerProperties {
    /**
     * API文档生成基础路径
     */
    private String apiBasePackage;
    /**
     * 是否需要登录认证
     */
    private boolean enableSecurity;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档描述
     */
    private String description;
    /**
     * 文档版本
     */
    private String version;
    /**
     * 文档联系人
     */
    private String contactName;
    /**
     * 文档联系人URL
     */
    private String contactUrl;
    /**
     * 文档联系人邮箱
     */
    private String contactEmail;
}
