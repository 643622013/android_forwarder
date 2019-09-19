package com.example.zhys_pc.android_forwarder.config;

import android.os.Environment;

import com.cfbx.framework.util.FileUtils;

import java.io.File;

/**
 * <p> 常量 </p>
 * Created by 71033 on 2018/4/19.
 */
public interface C {


    /**
     * 网络相关
     */

    //  正式
    String PRODUCT_BASE_URL = "http://api.yipurse.com/";
    String PRODUCT_Pos_URL = "http://api.merchant.yipurse.com/";//pos

    //  测试
    String TEST_BASE_URL = "https://stg.share1949.com";// http://192.168.11.61:8004/  http://192.168.11.211:9527/
    String TEST_Pos_URL = "";//pos


    /**
     * 用于匹配api层传入的header的值
     * <p>
     * 多url需配置多个
     */
    String POSURL = "posUrl";

    //http缓存大小
    int httpCacheSize = 10 * 1024 * 1024;

    //http超时时间
    long httpTimeOut = 5000L;

    /**
     * 用于匹配api层传入的header的键
     * <p>
     * 设置一次即可
     */

    String HEADER_KEY_URL = "url_name";

    //未登录状态下的token
    String NO_LOGIN_TOKEN = "10000";
    //登录状态下的token
    String LOGIN_TOKEN = "10001";




    //压缩图片的路径
    String compressedPicturesUrl = FileUtils.getRootDir()+ "/forwarder/compressedPictures/";



}
