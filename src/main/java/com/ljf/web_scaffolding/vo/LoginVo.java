package com.ljf.web_scaffolding.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by mr.lin on 2020/5/15
 * 登录输出Vo
 */
@ApiModel(value = "登录输出Vo", description = "描述信息")
@Data
public class LoginVo {

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "token")
    private String token;
}
