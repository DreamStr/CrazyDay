package com.lanfanxing.goodsapplication.ui.activity.user;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfirmOrderActivity extends BaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_orther_need)
    LinearLayout llOrtherNeed;
    @BindView(R.id.ll_rmk)
    LinearLayout llRmk;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.tv_go_pay)
    TextView tvGoPay;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_confirm_order);
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

    @OnClick({R.id.ll_orther_need, R.id.ll_rmk, R.id.tv_detail, R.id.tv_go_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_orther_need:
                openActivity(OrtherNeedActivity.class);
                break;
            case R.id.ll_rmk:
                openActivity(OrderRmkActivity.class);
                break;
            case R.id.tv_detail:
                openActivity(MoneyDetailActivity.class);
                break;
            case R.id.tv_go_pay:
                openActivity(OrderMapActivity.class);
                break;
        }
    }
}
