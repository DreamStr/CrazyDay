package com.lanfanxing.goodsapplication.ui.activity.user;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AcceptAddressActivity extends BaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_btn)
    TextView tvBtn;
    @BindView(R.id.ll_layout)
    LinearLayout llLayout;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_accept);

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

    @OnClick({R.id.tv_right, R.id.tv_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                openActivity(LocationActivity.class);
                break;
            case R.id.tv_btn:
                break;
        }
    }
}
