package com.lanfanxing.goodsapplication.ui.activity.user;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.mapapi.map.MapView;
import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;
import com.lanfanxing.goodsapplication.app.utils.JfUtility;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderMapActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.map_path_view)
    MapView mapPathView;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_order_map);

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


    @OnClick(R.id.tv_right)
    public void onViewClicked() {
        showPopupWindow();
    }

    private void showPopupWindow(){
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_order_cancel, null);

        TextView tvCancel = contentView.findViewById(R.id.tv_cancel);
        TextView tvReset = contentView.findViewById(R.id.tv_reset);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(CancelCauseActivity.class);
            }
        });

        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        PopupWindow window = new PopupWindow(contentView, JfUtility.dip2px(this,120), LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.update();
        window.showAsDropDown(tvRight, 0, 20);
    }
}
