package com.example.zhys_pc.android_forwarder.mainInterface.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cfbx.framework.exception.MyException;
import com.cfbx.framework.rxbus.RxBus;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhys_pc.android_forwarder.MyApplication;
import com.example.zhys_pc.android_forwarder.R;
import com.example.zhys_pc.android_forwarder.base.MyBaseFragment;
import com.example.zhys_pc.android_forwarder.constant.RxBusCode;
import com.example.zhys_pc.android_forwarder.customview.ToolBarBuilder;
import com.example.zhys_pc.android_forwarder.customview.requestError.RequestErrorController;
import com.example.zhys_pc.android_forwarder.customview.requestError.RequestErrorInterface;
import com.example.zhys_pc.android_forwarder.http.entity.MsgEventEntity;
import com.example.zhys_pc.android_forwarder.mainInterface.adapter.DemoAdapter;
import com.example.zhys_pc.android_forwarder.startInterface.LoginAct;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

import static com.cfbx.framework.exception.MyException.HTTP_ERROR;

public class OneFragment extends MyBaseFragment implements OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private ToolBarBuilder toolBarBuilder;

    @BindView(R.id.swipeRefresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRefreshView;
    private DemoAdapter demoAdapter;
    private List<String> dataList = new ArrayList<>();

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_demo;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void initView(View rootView) {
        initAdapter();

        initRefresh();

        initError();
    }

    @Override
    public void initData(Bundle arguments) {

    }

    //初始化刷新
    private void initAdapter() {
        demoAdapter = new DemoAdapter(dataList);
        demoAdapter.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.empty_interface, (ViewGroup) mRefreshView.getParent(), false));//这里展示空数据界面
        demoAdapter.setOnLoadMoreListener(this, mRefreshView);
        demoAdapter.loadMoreComplete();
    }


    //初始化错误页面
    private void initError() {
        //初始化请求异常
        RequestErrorController.Builder builder = initRequestError(mRefreshView);
        requestErrorController = builder
                .setOnNetworkErrorRetryClickListener("刷新", new RequestErrorInterface.OnClickListener() {
                    @Override
                    public void onClick() {
                        requestErrorController.showView(mRefreshView);
//                        //触发自动刷新
                        refreshLayout.autoRefresh();
                    }
                })
                .setOnErrorRetryClickListener("返回", new RequestErrorInterface.OnClickListener() {
                    @Override
                    public void onClick() {
                        mActivity.finish();
                    }
                }).build();
    }

    @SuppressLint("WrongConstant")
    private void initRefresh() {
        refreshLayout.setDragRate(0.25f);
        // 设置头尾 Height
        refreshLayout.setHeaderHeight(60f);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(this);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRefreshView.setAdapter(demoAdapter);
        mRefreshView.setLayoutManager(linearLayoutManager);

        //触发自动刷新
        refreshLayout.autoRefresh();
    }

    @Override
    public void initTopBar() {
        new ToolBarBuilder(mRootView)
                .setLeftImageView(R.drawable.ic_launcher_background)
                .setBackgroundColor(getResources().getColor(R.color.colorAccent), 100)
                .setTitle("测试1");
    }


    @OnClick({R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                RxBus.get().send(RxBusCode.DEMO_CODE, new MsgEventEntity("ceshi"));
                myBaseActivity.finish();
                break;
        }
    }


    @Override
    public void onLazyLoad() {

    }

    //刷新
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

        new Handler().postDelayed(new Runnable() {
            public void run() {
                //execute the task
                if (dataList.size() > 30) {
                    dataList.clear();
                    demoAdapter.setNewData(dataList);
                    refreshLayout.finishRefresh();

                    showError(true, new MyException(HTTP_ERROR, "测试"));
                } else {

                    for (int i = 0; i < 10; i++) {
                        dataList.add("");
                    }
                    demoAdapter.setNewData(dataList);
                    refreshLayout.finishRefresh();

                }

            }
        }, 2000);

    }

    //加载
    @Override
    public void onLoadMoreRequested() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //execute the task
                for (int i = 0; i < 100; i++) {
                    dataList.add("");
                }
                demoAdapter.setNewData(dataList);
                demoAdapter.loadMoreEnd(false);
            }
        }, 2000);
    }
}
