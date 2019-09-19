package com.cfbx.framework;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luyang on 2017/11/13.
 */

public class ApiRespons<T>{

    public static boolean isNeedQuit = false;


    private String msg;
    private String cause;
    private int code;
    private String version;

    @SerializedName("result")
    private ResponseData<T> result;

    public ApiRespons(String msg, int code, String cause, String version, ResponseData<T> result) {
        this.msg = msg;
        this.code = code;
        this.cause = cause;
        this.version = version;
        this.result = result;
    }



    public String getMsg() {
        return msg;
    }

    public ApiRespons<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getCause() {
        return cause;
    }

    public ApiRespons<T> setCause(String cause) {
        this.cause = cause;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ApiRespons<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public ApiRespons<T> setVersion(String version) {
        this.version = version;
        return this;
    }



    public ResponseData<T> getResult() {
        return result;
    }

    public ApiRespons<T> setResult(ResponseData<T> result) {
        this.result = result;
        return this;
    }

    // 成功状态
    public boolean isSuccess() {
        isNeedQuit = false;
        return 0 == getCode();
    }

}
