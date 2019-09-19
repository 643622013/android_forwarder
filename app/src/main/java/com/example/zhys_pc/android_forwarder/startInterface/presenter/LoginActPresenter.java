package com.example.zhys_pc.android_forwarder.startInterface.presenter;

import android.os.Build;
import android.util.Log;

import com.cfbx.framework.exception.ApiException;
import com.cfbx.framework.exception.MyException;
import com.cfbx.framework.local_storage.SP;
import com.example.zhys_pc.android_forwarder.MyApplication;
import com.example.zhys_pc.android_forwarder.config.C;
import com.example.zhys_pc.android_forwarder.config.MethodConfig;
import com.example.zhys_pc.android_forwarder.http.body.LoginBody;
import com.example.zhys_pc.android_forwarder.http.body.NoDataBody;
import com.example.zhys_pc.android_forwarder.http.body.RequestJson;
import com.example.zhys_pc.android_forwarder.http.body.TokenBody;
import com.example.zhys_pc.android_forwarder.api.AutoConfigDataRepository;
import com.example.zhys_pc.android_forwarder.api.AutoConfigDataSource;
import com.example.zhys_pc.android_forwarder.http.consumer.HttpRxObserver;
import com.example.zhys_pc.android_forwarder.http.entity.LoginEntity;
import com.example.zhys_pc.android_forwarder.http.entity.TokenEntity;
import com.example.zhys_pc.android_forwarder.http.entity.UnreadMessageEntity;
import com.example.zhys_pc.android_forwarder.startInterface.contract.LoginActContract;
import com.example.zhys_pc.android_forwarder.tools.CommonUtils;

public class LoginActPresenter implements LoginActContract.Presenter {

    private AutoConfigDataSource autoConfigDataSource;
    private LoginActContract.View view;

    public LoginActPresenter(LoginActContract.View v) {
        autoConfigDataSource = AutoConfigDataRepository.getInstance();
        view = v;
    }


    @Override
    public void getVisitorToken() {

        view.showLoading();

        RequestJson<TokenBody> requestJson = new RequestJson<>();
        requestJson.setMethod(MethodConfig.GET_VISITOR_TOKEN);

        TokenBody tokenBody = new TokenBody();
        tokenBody.setP(2);
        tokenBody.setUserSource(12);
        tokenBody.setOs("android");
        tokenBody.setChannel("吕洞宾");
        tokenBody.setCanal(Build.MODEL);
        tokenBody.setMemo(Build.MANUFACTURER);
        tokenBody.setCver("1.0");
        tokenBody.setMid("123");

        requestJson.setDataBody(tokenBody);

        autoConfigDataSource.getVisitorToken(requestJson)
                .subscribe(new HttpRxObserver<TokenEntity>() {
                    @Override
                    protected void onHandleSuccess(TokenEntity tokenBean) {
                        view.hideLoading();
                        view.getNoLoginTokenSuccess(tokenBean.getToken());
                    }

                    @Override
                    protected void onServerError(ApiException e) {
                    }

                    @Override
                    protected void onHttpError(MyException e) {
                    }

                    @Override
                    protected void onTokenClose() {
                        Log.e("tag", "token失效");
                    }

                    @Override
                    protected void fail(String failMsg) {

                    }
                });
    }

    @Override
    public void loginUser(String phoneNum, String password) {


        RequestJson<LoginBody> requestJson = new RequestJson<>();
        requestJson.setMethod("login");
        requestJson.setToken(SP.getInstance(MyApplication.getContext()).getString(C.NO_LOGIN_TOKEN));
        LoginBody loginBody = new LoginBody();
        loginBody.setUserName(phoneNum);
        loginBody.setPassword(password);
        loginBody.setUserSource(12);
        loginBody.setUserType(1);
        requestJson.setDataBody(loginBody);

        autoConfigDataSource.loginUser(requestJson)
                .subscribe(new HttpRxObserver<LoginEntity>() {
                               @Override
                               protected void onHandleSuccess(LoginEntity loginEntity) {
                                   view.loginSuccess(loginEntity.getToken() + "");
                               }

                               @Override
                               protected void onServerError(ApiException e) {
                               }

                               @Override
                               protected void onHttpError(MyException e) {
                               }

                               @Override
                               protected void onTokenClose() {
                                   Log.e("tag", "token失效");
                               }

                               @Override
                               protected void fail(String failMsg) {

                               }
                           }
                );
    }

    @Override
    public void getMsgNum() {
        RequestJson<NoDataBody> req = new CommonUtils<NoDataBody>().geneRequestParams(MethodConfig.UNREAD_MESSAGE);
        NoDataBody noDataBody = new NoDataBody();
        req.setDataBody(noDataBody);
        autoConfigDataSource.getSupplyHallUnreadMessage(req)
                .subscribe(new HttpRxObserver<UnreadMessageEntity>() {
                               @Override
                               protected void onHandleSuccess(UnreadMessageEntity unreadMessageEntity) {
                                   view.getMsgNum(unreadMessageEntity.getTotalNotReadedNum() + "");
                               }

                               @Override
                               protected void onServerError(ApiException e) {
                               }

                               @Override
                               protected void onHttpError(MyException e) {
                               }

                               @Override
                               protected void onTokenClose() {
                                   Log.e("tag", "token失效");
                                   view.reLogin(false);
                               }

                               @Override
                               protected void fail(String failMsg) {

                               }
                           }
                );
    }
}
