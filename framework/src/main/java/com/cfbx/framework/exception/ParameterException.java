package com.cfbx.framework.exception;

import androidx.annotation.IntDef;

/**
 * 参数异常
 */
public class ParameterException extends Exception {
    public static final int ISNOTLOGIN = 75000;
    public static final int PARAMISNULL_ERROR = 75001;
    public static final int PARAMFORMAT_ERROR = 75002;

    @IntDef({ISNOTLOGIN, PARAMISNULL_ERROR, PARAMFORMAT_ERROR})
    public @interface  PARAEXCEPTION {}



    private int code;//错误码
    private String msg;//错误信息


    public ParameterException(@PARAEXCEPTION int code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public ParameterException(@PARAEXCEPTION int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

