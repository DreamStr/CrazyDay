package com.lanfanxing.goodsapplication.mvp.presenter;

import android.content.Context;

/**
 * Created by lanfanxing on 2018/3/3.
 */

public interface ILoginPresenter {
    /**
     * 忘记密码
     */
    void forgetPwd();

    /**
     * 登录
     * @param name
     * @param passwd
     */
    void doLogin(String name, String passwd, Context context);

    /**
     * 注册
     */
    void regist();

    /**
     * 我是司机
     */
    void imCar();

    /**
     * 联系客服
     */
    void callService();
}
