package com.demo01.common.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobleExceptionAop {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String runtimeException(Exception exception) {
        return "Globle Exception Aop!" + exception.toString();
    }
}
