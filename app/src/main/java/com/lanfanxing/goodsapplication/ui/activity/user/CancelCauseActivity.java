package com.lanfanxing.goodsapplication.ui.activity.user;

import android.view.View;
import android.widget.TextView;

import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CancelCauseActivity extends BaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_btn)
    TextView tvBtn;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_cancel_cause);

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

    @OnClick(R.id.tv_btn)
    public void onViewClicked() {
        openActivity(OrderCancelActivity.class);
    }
}
