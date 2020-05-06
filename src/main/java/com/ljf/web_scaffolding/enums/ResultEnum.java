package com.ljf.web_scaffolding.enums;

import lombok.Getter;

/**
 * Created by mr.lin on 2020/5/6
 * 返回结果枚举
 */
@Getter
public enum ResultEnum {

    SUCCESS(0,"成功"),
    LOGIN_FAIL(1, "登录失败"),
    ROLE_ERROR(2, "角色权限有误"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
