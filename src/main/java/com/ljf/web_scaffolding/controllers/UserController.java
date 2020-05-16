package com.ljf.web_scaffolding.controllers;

import com.ljf.web_scaffolding.request.LoginRequest;
import com.ljf.web_scaffolding.request.SignUpRequest;
import com.ljf.web_scaffolding.services.UserService;
import com.ljf.web_scaffolding.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jef
 * @since 2020-05-15
 */
@Api(tags = "账号")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @ApiOperation(value = "登录", notes = "接口描述")
    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @ApiOperation(value = "登出", notes = "接口描述")
    @PostMapping("/logout")
    public ResultVo logout(String token) {
        return userService.logout(token);
    }

    @ApiOperation(value = "注册", notes = "接口描述")
    @PostMapping("/signUp")
    public ResultVo signUp(@RequestBody SignUpRequest request) {
        // TODO: 2020/5/16 mybatis 默认字段赋值
        return userService.signUp(request);
    }

    @ApiOperation(value = "获取用户信息", notes = "接口描述")
    @PostMapping("/getUserInfo")
    public ResultVo getUserInfo(String token) {
        return userService.getUserInfo(token);
    }

}
