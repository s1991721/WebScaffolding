package com.ljf.web_scaffolding.controllers;

import com.ljf.web_scaffolding.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mr.lin on 2020/4/27
 */
@Api(value = "user", tags = "显示名")
@RestController("/user")
public class UserInfoController {

    @ApiOperation(value = "接口名", notes = "接口描述")
    @PostMapping("/user/login")
    public User login(@ApiParam("参数") String name) {
        return new User();
    }

    @PostMapping("/logout")
    public void logout(@RequestBody User user) {

    }

}
