package com.ljf.web_scaffolding.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by mr.lin on 2020/5/6
 * 返回结果类
 */
@Data
public class ResultVo<T> implements Serializable {


    private Integer code;

    private String msg;

    private T data;

}
