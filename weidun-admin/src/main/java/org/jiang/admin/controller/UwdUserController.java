package org.jiang.admin.controller;

import cn.hutool.core.collection.CollUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jiang.admin.dto.UwdUserLoginParam;
import org.jiang.admin.dto.UwdUserParam;
import org.jiang.admin.service.UwdRoleService;
import org.jiang.admin.service.UwdUserService;
import org.jiang.common.api.CommonResult;
import org.jiang.model.UwdRole;
import org.jiang.model.UwdUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public CommonResult login(@Validated @RequestBody UwdUserLoginParam uwdUserLoginParam) {
        String token = uwdUserService.login(uwdUserLoginParam.getUsername(), uwdUserLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "刷新token")
    @GetMapping("/refreshToken")
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = uwdUserService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期!");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "提取当前登录用户信息")
    @GetMapping("/info")
    public CommonResult getUserInfo(Principal principal) {
        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        UwdUser user = uwdUserService.getUserByUsername(username);
        Map<String,Object> data = new HashMap<>();
        data.put("username",user.getUsername());
        data.put("menus",uwdRoleService.getMenuList(user.getId()));
        data.put("icon",user.getIcon());
        List<UwdRole> roleList = uwdUserService.getRoleList(user.getId());
        if (CollUtil.isNotEmpty(roleList)) {
            List<String> roles = roleList.stream().map(UwdRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return CommonResult.success(data);
    }

    @ApiOperation(value = "登出功能")
    @PostMapping("/logout")
    public CommonResult logout() {
        return CommonResult.success(null);
    }
}
