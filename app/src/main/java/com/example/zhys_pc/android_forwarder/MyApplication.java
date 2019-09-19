package com.example.zhys_pc.android_forwarder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import com.cfbx.framework.http.HttpClient;
import com.cfbx.framework.http.config.HttpConfig;
import com.example.zhys_pc.android_forwarder.api.ApiService;
import com.example.zhys_pc.android_forwarder.config.C;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;
import java.util.HashMap;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import okhttp3.Cache;


/**
 * Created by 71033 on 2017/10/9.
 */

public class MyApplication extends MultiDexApplication {
    public static MyApplication instance;
    private static Context context;
    @Environment.ENV
    public static int environment = Environment.DEBUG;
    public static ApiService apiService;

    public static ApiService downLoadApiService;


    @Override
    public void onCreate() {
        super.onCreate();
        if (environment == Environment.DEBUG) {
            //release包的时候主动改为生产环境
            if (!BuildConfig.DEBUG) {
                environment = Environment.PRODUCT;
            }
        }

        context = getApplicationContext();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        MultiDex.install(this);
        //初始化网络请求
        initHttpConfig();

    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
    }


    public static MyApplication getInstance() {
        if (instance == null) {
            synchronized (MyApplication.class) {
                if (instance == null) {
                    instance = new MyApplication();
                }
            }
        }
        return instance;
    }


    /**
     * 初始化HTTP设置
     */
    private void initHttpConfig() {
        String baseUrl = "";
        //其它url，如果有更多，需要在这里配置
        String posUrl = "";
        switch (environment) {
            case Environment.DEBUG:
                baseUrl = C.TEST_BASE_URL;
                posUrl = C.TEST_Pos_URL;
                break;
            case Environment.TOBE_PRODUCT:
                baseUrl = C.TEST_BASE_URL;
                posUrl = C.TEST_Pos_URL;
                break;
            case Environment.PRODUCT:
                baseUrl = C.PRODUCT_BASE_URL;
                posUrl = C.PRODUCT_Pos_URL;
                break;
            default:
                baseUrl = C.TEST_BASE_URL;
                posUrl = C.TEST_Pos_URL;
                break;
        }
        HashMap<String, String> otherUlrs = new HashMap<>();
        otherUlrs.put(C.POSURL, posUrl);
//        otherUlrs.put(C.DOWNLOADURL,donwLoadUrl);
        Cache cache = new Cache(new File(getCacheDir(), "httpCache"), C.httpCacheSize);

        HttpConfig httpConfig = new HttpConfig()
                .setContext(this)
                .setHttpCache(cache)
                .setTimeout(C.httpTimeOut)
                .setBaseUrl(baseUrl)
                .setOtherUrls(otherUlrs)
                .setUrlHeadreTag(C.HEADER_KEY_URL);//默认为url_name

        apiService = HttpClient
                .getInstance(httpConfig)
                .getRetrofit()
                .create(ApiService.class);

    }

    public static ApiService getApiService() {
        if (apiService == null) {
            return null;
        }
        return apiService;
    }


    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        return context;
    }


}
