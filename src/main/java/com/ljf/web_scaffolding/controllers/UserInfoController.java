package com.ljf.web_scaffolding.controllers;

import com.ljf.web_scaffolding.entity.User;
import com.ljf.web_scaffolding.enums.ResultEnum;
import com.ljf.web_scaffolding.utils.ResultUtil;
import com.ljf.web_scaffolding.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mr.lin on 2020/4/27
 */
@Api(value = "user", tags = "显示名")
@RestController("/user")
public class UserInfoController {

    @ApiOperation(value = "接口名", notes = "接口描述")
    @PostMapping("/user/login")
    @Cacheable(value = {"user", "animal"})  //cacheNames区分不同的cache空间，可设置数组
    public ResultVo<User> login(@RequestParam("姓名") String name) {                   // ；缓存对象必须序列化
        User user = new User();
        user.setName(name);
        user.setSex(111);
        return ResultUtil.success(user);
    }

    @PostMapping("/logout")
    public ResultVo logout(@RequestBody User user) {
        return ResultUtil.resultVoFromEnum(ResultEnum.SUCCESS);
    }

}
