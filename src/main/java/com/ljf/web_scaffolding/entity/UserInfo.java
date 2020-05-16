package com.ljf.web_scaffolding.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by mr.lin on 2020/4/27
 */
@ApiModel(value = "用户实体类", description = "描述信息")
@Data
public class UserInfo implements Serializable {

    @ApiModelProperty(value = "用户名")
    public String name;

    @ApiModelProperty(value = "性别", allowableValues = "1,0")
    public Integer sex;

}
