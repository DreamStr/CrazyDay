package com.lanfanxing.goodsapplication.ui.activity.driver;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterInfoActivity extends BaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_city)
    LinearLayout llCity;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.ll_layout)
    LinearLayout llLayout;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_register_info);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initController() {

    }

    @Override
    public void initListener() {

    }


    @OnClick({R.id.ll_city, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_city:
                break;
            case R.id.tv_next:
                openActivity(RegisterCarInfoActivity.class);
                break;
        }
    }
}
