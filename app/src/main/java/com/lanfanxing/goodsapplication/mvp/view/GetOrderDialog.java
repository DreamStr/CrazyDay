package com.lanfanxing.goodsapplication.mvp.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanfanxing.goodsapplication.R;

/**
 * Created by lanfanxing on 2018/3/9.
 */

public class GetOrderDialog extends Dialog implements View.OnClickListener {
    private HintDialogTv.OnCloseListener listener;
    private Context mContext;

    public GetOrderDialog(Context context, int themeResId, HintDialogTv.OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_get_order);
        setCancelable(true);
        initView();
    }

    private void initView(){
        TextView tvBtnOk =  findViewById(R.id.tv_ok);
        TextView tvBtnCancel =  findViewById(R.id.tv_cancel);
        LinearLayout llDaoHang = findViewById(R.id.ll_daohang);
        tvBtnOk.setOnClickListener(this);
        tvBtnCancel.setOnClickListener(this);
        llDaoHang.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_ok:
                if (listener != null) {
                    listener.onClick(this, true);
                }
                this.dismiss();
                break;
            case R.id.tv_cancel:
                if (listener != null) {
                    listener.onClick(this, false);
                }
                this.dismiss();
                break;
        }
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm);
    }
}
