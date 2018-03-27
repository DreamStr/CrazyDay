package com.lanfanxing.goodsapplication.app.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.lanfanxing.goodsapplication.app.utils.SnackbarUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import net.lemonsoft.lemonbubble.LemonBubble;
import net.lemonsoft.lemonbubble.LemonBubbleInfo;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLayoutStyle;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLocationStyle;

import butterknife.ButterKnife;


public abstract class BaseActivity extends RxAppCompatActivity implements IBaseView {
    private Dialog LoadingDialog;
    private ProgressDialog pd;


    public String TAG = this.getClass().getSimpleName();

    /**
     * 初始化布局
     */
    public abstract void initContentView();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化控制中心
     */
    public abstract void initController();

    /**
     * 初始化监听事件
     */
    public abstract void initListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        initContentView();
        // 将该Activity加入堆栈
        ButterKnife.bind(this);

        initView();
        initController();
        initListener();
    }

    @Override
    protected void onDestroy() {
        // TODO 该方法运行在子线程,可能导致Activity回收时Context被持有,导致内存泄露
        // 防止dialog关闭时 context已提前关闭 所有在销毁时执行
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
        super.onDestroy();
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (pd == null) {
            pd = new ProgressDialog(this);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(flag);
            pd.setCanceledOnTouchOutside(false);
            pd.setMessage(message);
            pd.show();
        } else {
            pd.show();
        }
    }

    @Override
    public void hideProgress() {
        if (pd == null)
            return;

        new AsyncTask<Void, Integer, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);

                if (pd != null && pd.isShowing()) {
                    pd.dismiss();
                }
            }
        }.execute();
    }

    @Override
    public void showToast(int resId) {
        showToast(getString(resId));
    }

    @Override
    public void showToast(final String msg) {
        if (!isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void showNetError() {
        showToast("网络异常,请检查你的网络");
    }

    @Override
    public void showParseError() {
        showToast("服务器数据异常");
    }

    public void back(View v) {
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showLoadingDialog(String msg, boolean cancelable) {
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View v = inflater.inflate(R.layout.loading_view, null);
//        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
//        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img_dialog);
//        TextView tvText = (TextView) v.findViewById(R.id.tv_msg);
//
//        Glide.with(BaseActivity.this).load(R.drawable.loding).into(spaceshipImage);
//
//        tvText.setText(msg);
//        LoadingDialog = new Dialog(BaseActivity.this, R.style.dialog);
//        LoadingDialog.setCancelable(cancelable);
//        LoadingDialog.show();
//        LoadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // 获取默认的正确信息的泡泡信息对象
        LemonBubbleInfo myInfo = LemonBubble.getRoundProgressBubbleInfo();
        // 设置图标在左侧，标题在右侧
        myInfo.setLayoutStyle(LemonBubbleLayoutStyle.ICON_LEFT_TITLE_RIGHT);
        // 设置泡泡控件在底部
        myInfo.setLocationStyle(LemonBubbleLocationStyle.CENTER);
        // 设置泡泡控件的动画图标颜色为蓝色
        myInfo.setIconColor(Color.BLUE);
        // 设置泡泡控件的尺寸，单位dp
        myInfo.setBubbleSize(200, 80);
        // 设置泡泡控件的偏移比例为整个屏幕的0.01,
        myInfo.setProportionOfDeviation(0.01f);
        // 设置泡泡控件的标题
        myInfo.setTitle(msg);
        // 展示自定义的泡泡控件，并显示2s后关闭

        LemonBubble.showBubbleInfo(this, myInfo);
    }

    @Override
    public void showRightDialog(String msg, int delay) {
//        LemonBubble.getRightBubbleInfo()// 增加无限点语法修改bubbleInfo的特性
//                .setTitle(msg)
//                .setTitleFontSize(12)// 修改字体大小
//                .setTitleColor(Color.RED)
//                .setMaskColor(Color.argb(100, 0, 0, 0))// 修改蒙版颜色
//                .show(this, delay);
//                LemonBubble.showRight(UserMainActivity.this, "这是一个成功的提示", 2000);
        LemonBubble.showRight(this,msg,delay);

    }

    @Override
    public void showRightDialog(String msg) {
//        LemonBubble.getRightBubbleInfo()// 增加无限点语法修改bubbleInfo的特性
//                .setTitle(msg)
//                .setTitleFontSize(12)// 修改字体大小
//                .setTitleColor(Color.RED)
//                .setMaskColor(Color.argb(100, 0, 0, 0))// 修改蒙版颜色
//                .show(this, delay);
//                LemonBubble.showRight(UserMainActivity.this, "这是一个成功的提示", 2000);
        LemonBubble.showRight(this,msg,2000);
    }

    @Override
    public void showErrorDialog(String msg, int delay) {
        LemonBubble.showError(this,msg,delay);
    }

    @Override
    public void showErrorDialog(String msg) {
        LemonBubble.showError(this,msg,2000);
    }

    @Override
    public void showRightSnack(View view, String msg) {
        SnackbarUtil.ShortSnackbar(view,msg, SnackbarUtil.Confirm).show();
    }

    @Override
    public void showInfoSnack(View view, String msg) {
        SnackbarUtil.ShortSnackbar(view,msg, SnackbarUtil.Info).show();
    }

    @Override
    public void showErrorSnack(View view, String msg) {
        SnackbarUtil.ShortSnackbar(view,msg, SnackbarUtil.Alert).show();
    }

    @Override
    public void dissLoadingDialog() {
//        if (LoadingDialog != null && LoadingDialog.isShowing()) {
//            LoadingDialog.dismiss();
//        }
        LemonBubble.hide();
    }

    /**
     * 打开一个新的activity
     *
     * @param pClass
     */
    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 打开一个新的activity并传递数据
     *
     * @param pClass
     * @param pBundle
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

}
