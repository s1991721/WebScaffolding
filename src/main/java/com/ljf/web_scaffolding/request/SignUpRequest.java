package com.ljf.web_scaffolding.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by mr.lin on 2020/5/16
 */
@ApiModel(value = "注册请求")
@Data
public class SignUpRequest {
    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String name;
}
