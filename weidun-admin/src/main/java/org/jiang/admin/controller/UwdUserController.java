package org.jiang.admin.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/1/6
 * @Version 1.0
 */
@RestController
@Api(tags = "UwdUserController",description = "后台用户管理")
public class UwdUserController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

}
