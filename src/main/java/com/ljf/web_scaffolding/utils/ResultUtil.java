package com.ljf.web_scaffolding.utils;

import com.ljf.web_scaffolding.enums.ResultEnum;
import com.ljf.web_scaffolding.vo.ResultVo;

/**
 * Created by mr.lin on 2020/5/6
 * 构造返回结果工具类
 */
public class ResultUtil {

    public static ResultVo success(Object data) {
        ResultVo resultVo = resultVoFromEnum(ResultEnum.SUCCESS);
        resultVo.setData(data);
        return resultVo;
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo error(Integer code, String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }

    public static ResultVo resultVoFromEnum(ResultEnum resultEnum) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(resultEnum.getCode());
        resultVo.setMsg(resultEnum.getMsg());
        return resultVo;
    }

}
