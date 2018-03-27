package com.lanfanxing.goodsapplication.app;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.lanfanxing.goodsapplication.app.utils.Utils;

public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Utils.init(this);
//        CrashHandler.getInstance().init(this);
        SDKInitializer.initialize(mInstance);
    }


    public static MyApplication getInstance()
    {
        return mInstance;
    }

}
