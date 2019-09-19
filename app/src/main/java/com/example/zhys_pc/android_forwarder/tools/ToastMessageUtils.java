package com.example.zhys_pc.android_forwarder.tools;


import android.widget.Toast;

import com.example.zhys_pc.android_forwarder.MyApplication;

import androidx.annotation.NonNull;
import es.dmoral.toasty.Toasty;

/**
 * @desc 二次封装toast
 * @author lilei
 * create at 2019/6/4 10:42
 */

public class ToastMessageUtils {

   /**
    * @desc 弹出成功消息
    * @author lilei
    * create at 2019/5/27 17:01
    */
   

    public static void toastSuccess(@NonNull String text, Boolean isShowIcon) {

        if (isShowIcon == null) {

            isShowIcon = true;

        }

        Toasty.success(MyApplication.getContext(), text, Toast.LENGTH_SHORT, isShowIcon).show();

    }

    /**
     * 弹出错误消息
     *
     * @param text       需要显示的消息
     * @param isShowIcon 是否需要显示图标 默认显示
     */

    public static void toastError(@NonNull String text, Boolean isShowIcon) {

        if (isShowIcon == null) {

            isShowIcon = true;

        }

        Toasty.error(MyApplication.getContext(), text, Toast.LENGTH_SHORT, isShowIcon).show();

    }

    /**
     * 弹出一般消息
     *
     * @param text 需要显示的消息
     */

    public static void toastNormal(@NonNull String text) {

        Toasty.normal(MyApplication.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    /**
     * 弹出警告消息
     *
     * @param text       需要显示的消息
     * @param isShowIcon 是否需要显示图标 默认显示
     */

    public static void toastWarn(@NonNull String text, Boolean isShowIcon) {

        if (isShowIcon == null) {

            isShowIcon = true;

        }

        Toasty.warning(MyApplication.getContext(), text, Toast.LENGTH_SHORT, isShowIcon).show();

    }

}