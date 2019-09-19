package com.example.zhys_pc.android_forwarder.tools;

import com.cfbx.framework.local_storage.SP;
import com.example.zhys_pc.android_forwarder.MyApplication;
import com.example.zhys_pc.android_forwarder.config.C;
import com.example.zhys_pc.android_forwarder.http.body.RequestJson;

import androidx.annotation.Keep;

/**
 * @desc 网络请求的公共工具类
 * @author lilei
 * create at 2019/6/4 10:42
 */

@Keep
public class CommonUtils<T> {


    public CommonUtils() {

    }


    public RequestJson<T> geneRequestParams(String requestMethod) {
        RequestJson<T> requestJson = new RequestJson<>();
        requestJson.setMethod(requestMethod);
        requestJson.setVersion("");
        requestJson.setToken(SP.getInstance(MyApplication.getContext()).getString(C.LOGIN_TOKEN));
        return requestJson;
    }


}
