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
    ACCOUNT_EXIST(2, "账号已存在"),
    ROLE_ERROR(3, "角色权限有误"),
    BUSINESS_EXCEPTION(999, "业务处理异常"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
