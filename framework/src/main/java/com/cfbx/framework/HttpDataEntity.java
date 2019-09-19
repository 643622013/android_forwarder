package com.cfbx.framework;

import com.google.gson.annotations.SerializedName;

public class HttpDataEntity<T> {

    private String responseTime;
    private int code;
    private String message;


    @SerializedName("data")
    private T data;
}
