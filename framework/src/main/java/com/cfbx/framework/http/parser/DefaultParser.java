package com.cfbx.framework.http.parser;


import java.net.URL;

/**
 * <p>默认的一个Url解析器，可重新实现</p>
 * Created by zqiang94 on 2018/5/25.
 */
public class DefaultParser implements Parser {
    @Override
    public URL parseUrl(URL domainUrl, URL url) {
        if (null == domainUrl) return url;
        return domainUrl;
    }
}
