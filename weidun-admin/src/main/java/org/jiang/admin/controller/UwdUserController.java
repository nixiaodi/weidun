package org.jiang.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jiang.admin.dto.UwdUserParam;
import org.jiang.admin.service.UwdRoleService;
import org.jiang.admin.service.UwdUserService;
import org.jiang.common.api.CommonResult;
import org.jiang.model.UwdUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/1/6
 * @Version 1.0
 */
@RestController
@Api(tags = "UwdUserController",description = "后台用户管理")
@RequestMapping("/user")
public class UwdUserController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UwdUserService uwdUserService;
    @Autowired
    private UwdRoleService uwdRoleService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult<UwdUser> register(@Validated @RequestBody UwdUserParam userParam) {
        UwdUser uwdUser = uwdUserService.register(userParam);
        return uwdUser == null ? CommonResult.failed() : CommonResult.success(uwdUser);
    }
}
