package com.cfbx.framework.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class URLintercepter implements Interceptor {
    private HashMap<String, String> urls = null;
    private String tag = "url_name";

    public URLintercepter(HashMap<String, String> urls, String tag) {
        if (urls != null) {
            this.urls = urls;
        }
        this.tag = tag;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Headers headers = request.headers();
        Set<String> names = headers.names();
        Request.Builder builder = request.newBuilder();
        HttpUrl newBaseUrl = null;
        Request newRequest = null;
        if (urls != null && names.contains(tag)) {
            String value = headers.get(tag);
            newBaseUrl = HttpUrl.parse(urls.get(value));
            //从request中获取原有的HttpUrl实例oldHttpUrl
            HttpUrl oldHttpUrl = request.url();
            //重建新的HttpUrl，修改需要修改的url部分
            HttpUrl newFullUrl = oldHttpUrl
                    .newBuilder()
                    .scheme(newBaseUrl.scheme())
                    .host(newBaseUrl.host())

                    .port(newBaseUrl.port())
                    .build();
            newRequest = builder.headers(headers).url(newFullUrl).build();
        } else {
            newRequest = builder.headers(headers).build();
        }
        return chain.proceed(newRequest);
    }
}
