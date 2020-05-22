package com.ljf.web_scaffolding.controllers;

import com.ljf.web_scaffolding.entity.UserInfo;
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
@Api(value = "userInfo", tags = "显示名")
@RestController("/userInfo")
public class UserInfoController {

    @ApiOperation(value = "接口名", notes = "接口描述")
    @PostMapping("/userInfo/login")
//    @Cacheable(value = {"userInfo", "animal"})  //cacheNames区分不同的cache空间，可设置数组
    public ResultVo login(@RequestParam("name") String name) {                   // ；缓存对象必须序列化
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setSex(111);
        return ResultUtil.success(userInfo);
    }

    @PostMapping("/logout")
//    @Cacheable(cacheNames = "product", key = "123")
    public ResultVo logout(@RequestBody UserInfo userInfo) {
        return ResultUtil.resultVoFromEnum(ResultEnum.SUCCESS);
    }

}
