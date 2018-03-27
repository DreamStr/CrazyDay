package com.lanfanxing.goodsapplication.mvp.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lanfanxing.goodsapplication.R;


/**
 * Created by lanfanxing on 2017/7/4.
 */

public class HintDialogTv extends Dialog implements View.OnClickListener {
    private OnCloseListener listener;
    private Context mContext;
    private String title;
    private String content;
    private String positiveName;
    private String negateName;
    private Drawable posDrawable =null;
    private Drawable negDrawable = null;

    public HintDialogTv(Context context) {
        super(context);
        this.mContext = context;
    }

    public HintDialogTv(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public HintDialogTv(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;

    }

    protected HintDialogTv(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public HintDialogTv setTitle(String title) {
        this.title = title;
        return this;
    }

    public HintDialogTv setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    public HintDialogTv setNegateButton(String name) {
        this.negateName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tv_hint);
        setCancelable(true);
        initView();
    }

    private void initView() {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        TextView tvContent = (TextView) findViewById(R.id.tv_content);
        final TextView tvBtnOk = (TextView) findViewById(R.id.tv_ok);
        TextView tvBtnCancel = (TextView) findViewById(R.id.tv_cancel);

        View viewLine = findViewById(R.id.view_line);

        tvBtnOk.setOnClickListener(this);
        tvBtnCancel.setOnClickListener(this);

        if(!TextUtils.isEmpty(content)){
            tvContent.setText(content);
        }else{
            tvContent.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        } else {
            tvTitle.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(positiveName)) {
            tvBtnOk.setText(positiveName);
        }

        if (!TextUtils.isEmpty(negateName)) {
            tvBtnCancel.setText(negateName);
        }else{
            tvBtnCancel.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
