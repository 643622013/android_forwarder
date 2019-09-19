package com.cfbx.framework;


import androidx.annotation.Keep;

/**
 * Created by Administrator on 2017/9/21.
 */
@Keep
public class ResponseData<T> {

    /**
     * "code" : 0,  //业务状态码
     * "cause": null,
     * "msg": "请求成功",
     * "data" : {
     * "token" : "用户通过浏览器生成标识"
     * }
     */

    public static boolean isNeedQuit = false;

    private int code;
    private String cause;
    private String msg;
    private String version;
    private T data;

    public ResponseData() {
    }

    public ResponseData(int code, String cause, String msg, String version, T data) {
        this.code = code;
        this.cause = cause;
        this.msg = msg;
        this.version = version;
        this.data = data;
    }

    public static boolean isNeedQuit() {
        return isNeedQuit;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // 未实名认证
    public boolean isUnIdentify() {
        return 600005 == getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    // 运单不存在
    public boolean isNoExist() {
        return getCode() == 10000003;
    }

    // 成功状态
    public boolean isSuccess() {
        isNeedQuit = false;
        return 0 == getCode();
    }


    // token 异常
    public boolean isTokenException() {
        isNeedQuit = true;
        return isTokenExpired() | isTokenTopOffed() | isCheckFail();
    }

    // token 失效
    private boolean isTokenExpired() {
        return 409 == getCode();
    }

    // 登录被顶
    private boolean isTokenTopOffed() {
        return 408 == getCode();
    }

    // 权限校验失败
    private boolean isCheckFail() {
        return 405 == getCode();
    }

    /**
     * 设备未注册
     *
     * @return
     */
    public boolean isTokenInvoid() {
        return 450 == getCode();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "code=" + code +
                ", cause='" + cause + '\'' +
                ", msg='" + msg + '\'' +
                ", version='" + version + '\'' +
                ", data=" + data +
                '}';
    }
}
