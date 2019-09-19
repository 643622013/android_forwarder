package com.example.zhys_pc.android_forwarder.http.body;


import androidx.annotation.Keep;

/**
 * Des
 * <p>
 * Created by liujindong
 * <p>
 * createTime 2018/10/11
 */
@Keep
public class TokenBody {

    private int p;
    private String os;
    private String mid;
    private String cver;
    private String memo;
    private String canal;
    private String channel;
    private int userSource;

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public String getOs() {
        return os == null ? "" : os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getMid() {
        return mid == null ? "" : mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCver() {
        return cver == null ? "" : cver;
    }

    public void setCver(String cver) {
        this.cver = cver;
    }

    public String getMemo() {
        return memo == null ? "" : memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCanal() {
        return canal == null ? "" : canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getChannel() {
        return channel == null ? "" : channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getUserSource() {
        return userSource;
    }

    public void setUserSource(int userSource) {
        this.userSource = userSource;
    }
}
