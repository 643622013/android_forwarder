package com.cfbx.framework.exception;

import androidx.annotation.IntDef;

/**
 * 自定义异常
 */
public class MyException extends Exception {


    public static final int HTTP_ERROR = 45001;//http异常
    public static final int TIMEOUT_ERROR = 45002;//超时
    public static final int CONNECT_ERROR = 45003;//连接异常
    public static final int ANALYTIC_DATA_ERROR = 55001;//数据解析异常
    public static final int UNKNOWN_ERROR = 65000;//未知错误

    private int code;//错误码
    private String msg;//错误信息


    @IntDef({HTTP_ERROR, TIMEOUT_ERROR,CONNECT_ERROR,ANALYTIC_DATA_ERROR,UNKNOWN_ERROR})
    public @interface  MYEXCEPTION {}


    public MyException(@MYEXCEPTION int code,Throwable throwable,String myMessage) {
        super(throwable);
        this.code = code;
    }

    public MyException(@MYEXCEPTION int code, String msg) {
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

