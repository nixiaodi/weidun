package org.jiang.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @Description 用户登录参数
 * @Author jiang
 * @Create 2021/1/7
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UwdUserLoginParam {
    @NotEmpty
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
