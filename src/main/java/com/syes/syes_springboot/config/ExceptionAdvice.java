package com.syes.syes_springboot.config;


import com.syes.syes_springboot.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public Result handleBussinessException(Exception e) {
        return Result.error("401", "业务异常消息：" + e.getMessage());
    }
}
