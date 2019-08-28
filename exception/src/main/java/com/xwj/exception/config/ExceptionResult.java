package com.xwj.exception.config;

/**
 * @Description 通用异常返回格式
 * @Author yuki
 * @Date 2019/4/25 10:25
 * @Version 1.0
 **/
public class ExceptionResult {
    private int status;
    private String message;
    private long timestamp;

    public ExceptionResult(ExceptionEnum enums) {
        this.status=enums.getCode();
        this.message=enums.getMsg();
        this.timestamp=System.currentTimeMillis();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
