package com.cfbx.framework.exception;

/**
 * 接口异常
 */
public class ApiException extends RuntimeException {
    private int code;
    private String msg;

    public ApiException(int code, String msg) {
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
