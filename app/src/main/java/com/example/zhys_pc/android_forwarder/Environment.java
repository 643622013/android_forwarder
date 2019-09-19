package com.example.zhys_pc.android_forwarder;
import androidx.annotation.IntDef;

public class Environment {

    public static final int DEBUG = 0; //测试
    public static final int TOBE_PRODUCT = 1; //准生产
    public static final int PRODUCT = 2; //生产

    @IntDef({DEBUG,TOBE_PRODUCT, PRODUCT})
    public @interface  ENV {}

}
