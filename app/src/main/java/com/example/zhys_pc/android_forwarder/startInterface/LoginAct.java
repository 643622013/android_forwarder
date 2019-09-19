package com.example.zhys_pc.android_forwarder.startInterface;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cfbx.framework.local_storage.SP;
import com.cfbx.framework.rxbus.Subscribe;
import com.example.zhys_pc.android_forwarder.MyApplication;
import com.example.zhys_pc.android_forwarder.R;
import com.example.zhys_pc.android_forwarder.base.MyBaseActivity;
import com.example.zhys_pc.android_forwarder.config.C;
import com.example.zhys_pc.android_forwarder.constant.RxBusCode;
import com.example.zhys_pc.android_forwarder.customview.ToolBarBuilder;
import com.example.zhys_pc.android_forwarder.http.entity.MsgEventEntity;
import com.example.zhys_pc.android_forwarder.mainInterface.MainActivity;
import com.example.zhys_pc.android_forwarder.startInterface.contract.LoginActContract;
import com.example.zhys_pc.android_forwarder.startInterface.presenter.LoginActPresenter;
import com.example.zhys_pc.android_forwarder.tools.ToastMessageUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginAct extends MyBaseActivity implements LoginActContract.View {
    public LoginActPresenter presenter;
    @BindView(R.id.login_btn)
    Button login_btn;
    @BindView(R.id.get_no_login_token_btn)
    Button get_no_login_token_btn;
    @BindView(R.id.get_msg_num_btn)
    Button get_msg_num_btn;

    /**
     * @desc 绑定布局 ，需放在第一个方法
     * @author lilei
     * create at 2019/5/27 15:38
     */

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        presenter = new LoginActPresenter(this);
    }

    @Override
    public void initTopBar() {
        setStatusBarMode(true);
        new ToolBarBuilder(LoginAct.this)
                .setLeftImageView(R.drawable.ic_launcher_background)
                .setLeftOnClickListener(this::onViewClicked)
                .setBackgroundColor(getResources().getColor(R.color.colorAccent), 100)
                .setTitle("登录");
    }

    /**
     * @desc 点击事件
     * @author lilei
     * create at 2019/5/27 15:37
     */

    @OnClick({R.id.get_no_login_token_btn, R.id.login_btn, R.id.get_msg_num_btn, R.id.go_main_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_no_login_token_btn:
                presenter.getVisitorToken();
                break;
            case R.id.login_btn:
                presenter.loginUser("18663927065", "123456");
                break;
            case R.id.get_msg_num_btn:
                presenter.getMsgNum();
                break;
            case R.id.go_main_btn:
                startActivity(MainActivity.class);
                break;
        }
    }

    @Override
    public void initParams(Bundle bundle) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }


    @Override
    public void loginSuccess(String token) {
        SP.getInstance(MyApplication.getContext()).put(C.LOGIN_TOKEN, token);
        login_btn.setText(token);
    }

    @Override
    public void getNoLoginTokenSuccess(String token) {
        get_no_login_token_btn.setText(token);
        SP.getInstance(MyApplication.getContext()).put(C.NO_LOGIN_TOKEN, token);
    }

    @Override
    public void getMsgNum(String num) {
        get_msg_num_btn.setText(num);
    }

    @Override
    public void error(String msg) {
        Toast.makeText(LoginAct.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(code = RxBusCode.DEMO_CODE)
    public void demo(MsgEventEntity msgEventEntity) {
        ToastMessageUtils.toastSuccess(msgEventEntity.getMsg(), true);
    }

}
