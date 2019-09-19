package com.cfbx.framework.util;

import android.util.Log;

import com.cfbx.framework.exception.ApiException;
import com.cfbx.framework.exception.MyException;
import com.cfbx.framework.exception.ParameterException;


/**
 * LOG工具类 默认tag-LOGUTIL
 * Created by wpf on 16/8/15.
 */
public class LogUtils {

    private static final String TAG = "LOGUTIL";
    private static boolean LOG_ENABLE = true;
    private static boolean DETAIL_ENABLE = true;

    private static String buildMsg(String msg) {
        StringBuilder buffer = new StringBuilder();

        if (DETAIL_ENABLE) {
            final StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];

            buffer.append("[ ");
            buffer.append(Thread.currentThread().getName());
            buffer.append(": ");
            buffer.append(stackTraceElement.getFileName());
            buffer.append(": ");
            buffer.append(stackTraceElement.getLineNumber());
            buffer.append(": ");
            buffer.append(stackTraceElement.getMethodName());
        }

        buffer.append("() ] _____ ");

        buffer.append(msg);

        return buffer.toString();
    }

    /**
     * 设置是否显示Log
     *
     * @param enable true-显示 false-不显示
     */
    public static void setLogEnable(boolean enable) {
        LOG_ENABLE = enable;
    }

//    /**
//     * 设置是否显示详细Log
//     * @param isdetail true-显示详细 false-不显示详细
//     */
//    public static void setLogDetail(boolean isdetail) {
//        DETAIL_ENABLE = isdetail;
//    }

    /**
     * verbose log
     *
     * @param msg log msg
     */
    public static void v(String msg) {
        if (LOG_ENABLE) {
            Log.v(TAG, buildMsg(msg));
        }
    }

    /**
     * verbose log
     *
     * @param tag tag
     * @param msg log msg
     */
    public static void v(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.v(tag, buildMsg(msg));
        }
    }

    /**
     * debug log
     *
     * @param msg log msg
     */
    public static void d(String msg) {
        if (LOG_ENABLE) {
            Log.d(TAG, buildMsg(msg));
        }
    }

    /**
     * debug log
     *
     * @param tag tag
     * @param msg log msg
     */
    public static void d(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.d(tag, buildMsg(msg));
        }
    }

    /**
     * info log
     *
     * @param msg log msg
     */
    public static void i(String msg) {
        if (LOG_ENABLE) {
            Log.i(TAG, buildMsg(msg));
        }
    }

    /**
     * info log
     *
     * @param tag tag
     * @param msg log msg
     */
    public static void i(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.i(tag, buildMsg(msg));
        }
    }

    /**
     * warning log
     *
     * @param msg log msg
     */
    public static void w(String msg) {
        if (LOG_ENABLE) {
            Log.w(TAG, buildMsg(msg));
        }
    }

    /**
     * warning log
     *
     * @param msg log msg
     * @param e   exception
     */
    public static void w(String msg, Exception e) {
        if (LOG_ENABLE) {
            Log.w(TAG, buildMsg(msg), e);
        }
    }

    /**
     * warning log
     *
     * @param tag tag
     * @param msg log msg
     */
    public static void w(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.w(tag, buildMsg(msg));
        }
    }

    /**
     * warning log
     *
     * @param tag tag
     * @param msg log msg
     * @param e   exception
     */
    public static void w(String tag, String msg, Exception e) {
        if (LOG_ENABLE) {
            Log.w(tag, buildMsg(msg), e);
        }
    }

    /**
     * error log
     *
     * @param msg log msg
     */
    public static void e(String msg) {
        if (LOG_ENABLE) {
            Log.e(TAG, buildMsg(msg));
        }
    }

    /**
     * error log
     *
     * @param e   exception
     */
    public static void e(Exception e) {
        if (LOG_ENABLE) {

            if (e instanceof ParameterException) {                       //参数异常
                ParameterException paramExc = (ParameterException) e;
                if (paramExc.getCode() == ParameterException.PARAMISNULL_ERROR) {
                    Log.e(TAG, buildMsg("参数为空"), paramExc);
                } else if (paramExc.getCode() == ParameterException.PARAMFORMAT_ERROR) {
                    Log.e(TAG, buildMsg("参数格式不正确"), paramExc);
                }
            } else if (e instanceof ApiException) {
                ApiException apiExc = (ApiException) e;
                Log.e(TAG, buildMsg("接口异常————" + apiExc.getMsg()), apiExc);
            } else if (e instanceof MyException) {
                MyException myExc = (MyException) e;
                switch (myExc.getCode()) {
                    case MyException.HTTP_ERROR:
                        Log.e(TAG, buildMsg("网络异常"), myExc);
                        break;
                    case MyException.ANALYTIC_DATA_ERROR:
                        Log.e(TAG, buildMsg("json解析异常"), myExc);
                        break;
                    case MyException.CONNECT_ERROR:
                        Log.e(TAG, buildMsg("网络连接异常"), myExc);
                        break;
                    case MyException.TIMEOUT_ERROR:
                        Log.e(TAG, buildMsg("网络连接超时"), myExc);
                        break;
                    case MyException.UNKNOWN_ERROR:
                        Log.e(TAG, buildMsg("未知异常"), myExc);
                        break;
                }
            }
        }
    }

    /**
     * error log
     *
     * @param tag tag
     * @param msg msg
     */
    public static void e(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.e(tag, buildMsg(msg));
        }
    }

    /**
     * error log
     *
     * @param tag tag
     * @param msg log msg
     * @param e   exception
     */
    public static void e(String tag, String msg, Exception e) {
        if (LOG_ENABLE) {
            Log.e(tag, buildMsg(msg), e);
        }
    }
}
