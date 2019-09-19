package com.example.zhys_pc.android_forwarder.mainInterface;

import android.content.Context;
import android.os.Bundle;

import com.cfbx.framework.BaseActivity;
import com.cfbx.framework.adapter.MyPagerAdapter;
import com.example.zhys_pc.android_forwarder.R;
import com.example.zhys_pc.android_forwarder.base.MyBaseActivity;
import com.example.zhys_pc.android_forwarder.http.entity.TabEntity;
import com.example.zhys_pc.android_forwarder.mainInterface.fragment.FourFragment;
import com.example.zhys_pc.android_forwarder.mainInterface.fragment.OneFragment;
import com.example.zhys_pc.android_forwarder.mainInterface.fragment.ThreeFragment;
import com.example.zhys_pc.android_forwarder.mainInterface.fragment.TwoFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;

public class MainActivity extends MyBaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.tab_main_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.commonTabLayout)
    CommonTabLayout commonTabLayout;

    private MyPagerAdapter mAdapter;


    private ArrayList<Fragment> fragments;

    private String[] mTitles = {"首页","首页","首页"};
    private int[] mIconUnselectIds = {
            R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};
    private int[] mIconSelectIds = {
            R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initFragment();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mAdapter.setFragments(fragments);
        mViewPager.setAdapter(mAdapter);
        //设置相邻页面的N个页面进行缓存，防止切换过程中fragment销毁重建
        mViewPager.setOffscreenPageLimit(3);
        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(this);
        commonTabLayout.setCurrentTab(0);
        mViewPager.addOnPageChangeListener(this);

    }

    @Override
    public void initTopBar() {

    }


    @Override
    public void initParams(Bundle bundle) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }


    //初始化fragment
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
    }

    @Override
    public void onTabSelect(int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        commonTabLayout.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
