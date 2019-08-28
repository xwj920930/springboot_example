package com.xwj.exception.config;

/**
 * @Description 自定义异常
 * runtimeException不用try/catch
 * @Author yuki
 * @Date 2019/4/25 10:24
 * @Version 1.0
 **/
public class LyException extends RuntimeException {
    private ExceptionEnum exceptionEnum;

    public LyException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }
}
