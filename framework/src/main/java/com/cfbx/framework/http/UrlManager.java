package com.cfbx.framework.http;

import android.text.TextUtils;


import com.cfbx.framework.http.parser.DefaultParser;
import com.cfbx.framework.http.parser.Parser;
import com.cfbx.framework.util.LogUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>动态替换BaseUrl，保持单个OkHttp</p>
 * Created by zqiang94 on 2018/5/25.
 */
public class UrlManager {

    private Parser urlParser;   //Url解析器
    private final Interceptor mInterceptor;     //baseUrl拦截器
    private static final String DOMAIN_NAME = "Domain-Name";
    public static final String DOMAIN_NAME_HEADER = DOMAIN_NAME + ": ";
    private final Map<String, URL> domainMap = new HashMap<>();

    private static class UrlManagerHolder{
        private static final UrlManager INSTANCE = new UrlManager();
    }

    public static final UrlManager getInstance() {
        return UrlManagerHolder.INSTANCE;
    }


    public UrlManager() {
        setUrlParser(new DefaultParser());  //设置默认Url解析器
        mInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = processRequest(chain.request());
                return chain.proceed(newRequest);
            }
        };
    }


    /**
     * 对request进行包装加工切换BaseUrl
     * @param request
     * @return
     */
    private Request processRequest(Request request) {
        Request.Builder newBuilder = request.newBuilder();
        URL url = request.url().url();
        String domainName = obtainDomainNameFromHeaders(request);
        URL httpUrl = null;
        if (!TextUtils.isEmpty(domainName)) {
            httpUrl = fetchDomain(domainName);
            newBuilder.removeHeader(domainName);
        }else{
            //todo  domainName为空时的处理

        }
        if (null!=httpUrl) {
            URL newHttpUrl = urlParser.parseUrl(httpUrl, url);
            LogUtils.i("新的url为:" + newHttpUrl +"\n"+"旧的url为:"+request.url().toString());
            return newBuilder.url(newHttpUrl).build();
        }

        return newBuilder.build();
    }

    /**
     * 根据domainName获取HttpUrl
     * @param domainName
     * @return
     */
    private URL fetchDomain(String domainName) {
        return domainMap.get(domainName);
    }

    /**
     * 从header中获取domain名字
     * @param request
     * @return
     */
    private String obtainDomainNameFromHeaders(Request request) {
        List<String> headers = request.headers(DOMAIN_NAME);
        if (headers == null || headers.size() == 0)
            return null;
        if (headers.size() > 1)
            throw new IllegalArgumentException("只能有一个domain!!!");
        return request.header(DOMAIN_NAME);
    }

    /**
     * 存放 Domain(BaseUrl) 映射关系
     *
     * @param domainName
     * @param domainUrl
     */
    public void putDomain(String domainName, String domainUrl) {
        synchronized (domainMap) {
            domainMap.put(domainName, checkURL(domainUrl));
        }
    }

    private URL checkURL(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            LogUtils.e(e.getMessage());

        }
        return null;
    }


    public void setUrlParser(Parser urlParser) {
        this.urlParser = urlParser;
    }

    public Interceptor getInterceptor() {
        return mInterceptor;
    }
}

