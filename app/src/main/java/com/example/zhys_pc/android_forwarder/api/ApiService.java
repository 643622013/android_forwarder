package com.example.zhys_pc.android_forwarder.api;


import com.cfbx.framework.ApiRespons;
import com.example.zhys_pc.android_forwarder.http.body.RequestJson;
import com.example.zhys_pc.android_forwarder.http.entity.LoginEntity;
import com.example.zhys_pc.android_forwarder.http.entity.TokenEntity;
import com.example.zhys_pc.android_forwarder.http.entity.UnreadMessageEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * <p>
 * 所有请求相关的接口
 * </p>
 * Created by wpf on 2017/12/21.
 */

public interface ApiService {

    final String httpUrl = "/gapi/commonGateway/api/";

    /**
     * 登录模块
     */
    /**
     * @desc 获取未登录状态下的token
     * @author lilei
     * create at 2019/5/24 11:22
     */

    @POST(httpUrl)
    Observable<ApiRespons<TokenEntity>> getVisitorToken(@Body RequestJson requestJson);

    /**
     * @desc 用户登录
     * @author lilei
     * create at 2019/5/24 11:18
     */

    @POST(httpUrl)
    Observable<ApiRespons<LoginEntity>> loginUser(@Body RequestJson requestJson);

    /**
     * @desc 获取货源大厅订单未读数量
     * @author lilei
     * create at 2019/5/24 14:36
     */
    
    @POST(httpUrl)
    Observable<ApiRespons<UnreadMessageEntity>> getSupplyHallUnreadMessage(@Body RequestJson requestJson);
}
