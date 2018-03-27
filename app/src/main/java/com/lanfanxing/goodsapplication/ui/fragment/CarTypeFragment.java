package com.lanfanxing.goodsapplication.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseFragment;

/**
 * Created by lanfanxing on 2018/3/5.
 */

public class CarTypeFragment extends BaseFragment{

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_cartype, null);
    }

    @Override
    protected void initFindViewById(View view) {
        Bundle mBundle = getArguments();
//        ToastUtils.show(mBundle.getString("test"));
    }

    @Override
    public void initData() {

    }
}
