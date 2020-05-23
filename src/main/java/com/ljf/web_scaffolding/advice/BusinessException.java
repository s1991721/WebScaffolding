package com.ljf.web_scaffolding.advice;

/**
 * Created by mr.lin on 2020/5/23
 * 业务异常
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
