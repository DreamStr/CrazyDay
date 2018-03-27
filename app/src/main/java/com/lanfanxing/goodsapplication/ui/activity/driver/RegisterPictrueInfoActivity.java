package com.lanfanxing.goodsapplication.ui.activity.driver;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterPictrueInfoActivity extends BaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.ll_layout)
    LinearLayout llLayout;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_register_pictrue_info);
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


    @OnClick(R.id.tv_submit)
    public void onViewClicked() {
        openActivity(DriverMainActivity.class);
    }
}
