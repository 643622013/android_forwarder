package com.example.zhys_pc.android_forwarder.api;


import com.example.zhys_pc.android_forwarder.http.body.LoginBody;
import com.example.zhys_pc.android_forwarder.http.body.NoDataBody;
import com.example.zhys_pc.android_forwarder.http.body.RequestJson;
import com.example.zhys_pc.android_forwarder.http.body.TokenBody;

import io.reactivex.Observable;

/**
 * <p>类说明</p>
 * Created by wpf on 2018/02/18.
 */

public interface AutoConfigDataSource {

    Observable getVisitorToken(RequestJson<TokenBody> requestJson);

    Observable loginUser(RequestJson<LoginBody> requestJson);

    Observable getSupplyHallUnreadMessage(RequestJson<NoDataBody> requestJson);


}
