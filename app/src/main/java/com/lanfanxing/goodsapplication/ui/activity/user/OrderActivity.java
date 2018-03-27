package com.lanfanxing.goodsapplication.ui.activity.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;
import com.lanfanxing.goodsapplication.ui.adapter.OrderPagerAdapter;
import com.lanfanxing.goodsapplication.ui.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tab_slid)
    TabLayout tabSlid;
    @BindView(R.id.vp_pager)
    ViewPager vpPager;

    private List<Fragment> fragments;
    private OrderPagerAdapter adapter;
    private String tabTitles[] = new String[]{"进行中","已完成","已取消"};

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_order);

    }

    @Override
    public void initView() {
        fragments = new ArrayList<>();
        for (int i = 0; i < tabTitles.length; i++) {
            Fragment fragment = new OrderFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", tabTitles[i]);
            fragment.setArguments(bundle);

            fragments.add(fragment);
        }

        adapter = new OrderPagerAdapter(getSupportFragmentManager(), this, fragments);
        vpPager.setAdapter(adapter);
        tabSlid.setupWithViewPager(vpPager);
    }

    @Override
    public void initController() {

    }

    @Override
    public void initListener() {

    }

}
