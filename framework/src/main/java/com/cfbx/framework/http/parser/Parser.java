package com.cfbx.framework.http.parser;

import java.net.URL;

/**
 * <p>Url解析器</p>
 * Created by zqiang94 on 2018/5/25.
 */
public interface Parser {

    /**
     *替换Url
     * @param domainUrl
     * @param url
     * @return
     */
    URL parseUrl(URL domainUrl, URL url);
}
