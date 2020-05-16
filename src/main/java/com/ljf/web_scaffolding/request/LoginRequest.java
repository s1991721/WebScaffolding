package com.ljf.web_scaffolding.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by mr.lin on 2020/5/15
 */
@ApiModel(value = "登录请求")
@Data
public class LoginRequest {

    private String account;

    private String password;

}
