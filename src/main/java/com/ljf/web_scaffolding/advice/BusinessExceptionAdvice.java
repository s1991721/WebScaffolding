package com.ljf.web_scaffolding.advice;

import com.ljf.web_scaffolding.enums.ResultEnum;
import com.ljf.web_scaffolding.utils.ResultUtil;
import com.ljf.web_scaffolding.vo.ResultVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by mr.lin on 2020/5/23
 * 拦截业务异常
 */
@RestControllerAdvice
public class BusinessExceptionAdvice {

    @ExceptionHandler
    public ResultVo handleBusinessException(BusinessException exception) {
        ResultVo resultVo = ResultUtil.resultVoFromEnum(ResultEnum.BUSINESS_EXCEPTION);
        resultVo.setMsg(exception.getMessage());
        return resultVo;
    }

}
