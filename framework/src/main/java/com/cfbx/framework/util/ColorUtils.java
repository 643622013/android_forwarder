package com.cfbx.framework.util;

import android.graphics.Color;

import androidx.annotation.ColorInt;

public class ColorUtils {

    /**
     * 透明度转换
     * @param color
     * @param alpha 0-255
     * @return
     */
    private static int calculateColor(@ColorInt int color, int alpha) {

        String aaa = Integer.toHexString(color);
        aaa = aaa.toUpperCase().substring(2,aaa.length());

        String bbb = Integer.toHexString(alpha);
        if (bbb.length() < 2)
            bbb = "0" + bbb;
        String fiColor = bbb+aaa;

        return Color.parseColor("#"+fiColor);

    }
}
