package com.lanfanxing.goodsapplication.mvp.presenter;

import android.content.Context;

/**
 * Created by lanfanxing on 2018/3/3.
 */

public class LoginPresenterCompl implements ILoginPresenter {

    public LoginPresenterCompl() {

    }


    @Override
    public void forgetPwd() {

    }

    @Override
    public void doLogin(String name, String passwd, Context context) {
//        IdeaApi.getApiService()
//                .getMezi()
//                .compose(this.<BasicResponse<List<MeiZi>>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DefaultObserver<BasicResponse<List<MeiZi>>>(this) {
//                    @Override
//                    public void onSuccess(BasicResponse<List<MeiZi>> response) {
//                        List<MeiZi> results = response.getResults();
//                        showToast("请求成功，妹子个数为"+results.size());
//                    }
//                });
    }


    @Override
    public void regist() {

    }

    @Override
    public void imCar() {

    }

    @Override
    public void callService() {

    }


}
