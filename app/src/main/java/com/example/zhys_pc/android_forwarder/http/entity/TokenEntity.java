package com.example.zhys_pc.android_forwarder.http.entity;


import androidx.annotation.Keep;

/**
 * Des
 * <p>
 * Created by liujindong
 * <p>
 * createTime 2018/10/11
 */
@Keep
public class TokenEntity {

    private String token;

    public String getToken() {
        return token == null ? "" : token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
