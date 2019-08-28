package com.xwj.exception.config;

/**
 * @Description 异常枚举
 * @Author yuki
 * @Date 2019/4/25 10:22
 * @Version 1.0
 **/
public enum  ExceptionEnum {
    //tips:错误码有规定，当以100开头前台不会显示错误
    PRICE_CANNOT_BE_NULL(400,"价格不能为空");
    private int code;
    private String msg;

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
