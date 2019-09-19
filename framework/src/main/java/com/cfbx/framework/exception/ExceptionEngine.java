package com.cfbx.framework.exception;


import android.util.MalformedJsonException;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.util.concurrent.TimeoutException;

/**
 * 错误/异常处理工具
 */
public class ExceptionEngine {

    public static Exception handleException(Throwable e) {
        if (e instanceof ParameterException) {                       //参数异常
            ParameterException paramExc = (ParameterException) e;
            return paramExc;
        } else if (e instanceof HttpException) {                     //HTTP错误
            return new MyException(MyException.HTTP_ERROR,e,"网络异常，请稍后重试");
        } else if (e instanceof ApiException) {                   //服务器返回的错误
            ApiException serverExc = (ApiException) e;
            return serverExc;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
                || e instanceof MalformedJsonException) {            //解析数据错误
            return new MyException(MyException.ANALYTIC_DATA_ERROR, e, "网络异常，请稍后重试");
        } else if (e instanceof ConnectException) {                  //连接网络错误
            return new MyException(MyException.CONNECT_ERROR, e, "网络异常，请检查并设置网络连接后重试");
        } else if (e instanceof SocketTimeoutException               //网络超时
                || e instanceof TimeoutException) {
            return new MyException(MyException.TIMEOUT_ERROR, e, "网络超时，请重试");
        } else {                                                     //未知错误
            return new MyException(MyException.UNKNOWN_ERROR, e, "未知错误");

        }
    }

}
