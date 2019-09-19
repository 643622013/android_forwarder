package com.example.zhys_pc.android_forwarder.api;


import com.cfbx.framework.ApiRespons;
import com.cfbx.framework.http.RxSchedulers;
import com.example.zhys_pc.android_forwarder.MyApplication;
import com.example.zhys_pc.android_forwarder.http.body.LoginBody;
import com.example.zhys_pc.android_forwarder.http.body.NoDataBody;
import com.example.zhys_pc.android_forwarder.http.body.RequestJson;
import com.example.zhys_pc.android_forwarder.http.body.TokenBody;
import com.example.zhys_pc.android_forwarder.http.entity.LoginEntity;
import com.example.zhys_pc.android_forwarder.http.entity.TokenEntity;
import com.example.zhys_pc.android_forwarder.http.entity.UnreadMessageEntity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * Created by wpf on 2018/1/1311.
 */

public class AutoConfigDataRepository implements AutoConfigDataSource {


    private ApiService apiService;

    public AutoConfigDataRepository() {
        this.apiService = MyApplication.getApiService();
    }

    public static AutoConfigDataSource getInstance() {
        return new AutoConfigDataRepository();
    }


    /**
     * @desc 获取未登录状态下的token
     * @author lilei
     * create at 2019/5/24 11:28
     */

    @Override
    public Observable getVisitorToken(RequestJson<TokenBody> requestJson) {
        return apiService.getVisitorToken(requestJson).compose(RxSchedulers.<ApiRespons<TokenEntity>>io_main());
    }

    /**
     * @desc 用户登录
     * @author lilei
     * create at 2019/5/24 11:28
     */

    @Override
    public Observable loginUser(RequestJson<LoginBody> requestJson) {
        return apiService.loginUser(requestJson).compose(RxSchedulers.<ApiRespons<LoginEntity>>io_main());
    }

    /**
     * @desc 获取货源大厅订单未读数量
     * @author lilei
     * create at 2019/5/24 14:36
     */


    @Override
    public Observable getSupplyHallUnreadMessage(RequestJson<NoDataBody> requestJson) {
        return apiService.getSupplyHallUnreadMessage(requestJson).compose(RxSchedulers.<ApiRespons<UnreadMessageEntity>>io_main());
    }
}
