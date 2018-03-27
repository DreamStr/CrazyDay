package com.lanfanxing.goodsapplication.ui.activity.user;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;
import com.lanfanxing.goodsapplication.app.config.Constans;
import com.lanfanxing.goodsapplication.app.utils.SharedPrefusUtil;
import com.lanfanxing.goodsapplication.ui.activity.driver.RegisterInfoActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterUserActivity extends BaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.tv_btn)
    TextView tvBtn;
    @BindView(R.id.tv_callkf)
    TextView tvCallkf;
    @BindView(R.id.ll_layout)
    LinearLayout llLayout;
    private String userType;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_register_user);

    }

    @Override
    public void initView() {
        userType = (String) SharedPrefusUtil.getParam(this, Constans.SDF_USER_USERTYPE, "");
    }

    @Override
    public void initController() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.tv_send, R.id.tv_btn, R.id.tv_callkf})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send:
                break;
            case R.id.tv_btn:
                if(userType.equals("1")){
                    openActivity(RegisterInfoActivity.class);
                }else{
                    openActivity(UserMainActivity.class);
                }
                break;
            case R.id.tv_callkf:
                break;
        }
    }
}
