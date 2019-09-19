package com.example.zhys_pc.android_forwarder.http.body;


import androidx.annotation.Keep;

/**
 * Created by Administrator on 2017/9/27.
 */
@Keep
public class RequestJson<T> {

    private String method;

    private String token;
    private String ver;
    private Integer p;
    private String channel;

    private String version;
    private T dataBody;

    public RequestJson() {
    }

    public RequestJson(String method, T dataBody) {
        this.method = method;
        this.dataBody = dataBody;
    }

    public RequestJson(String method, String token, T dataBody) {
        this.method = method;
        this.token = token;
        this.dataBody = dataBody;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getDataBody() {
        return dataBody;
    }

    public void setDataBody(T dataBody) {
        this.dataBody = dataBody;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
