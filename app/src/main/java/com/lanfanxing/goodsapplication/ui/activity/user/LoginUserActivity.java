package com.lanfanxing.goodsapplication.ui.activity.user;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;
import com.lanfanxing.goodsapplication.app.config.Constans;
import com.lanfanxing.goodsapplication.app.utils.SharedPrefusUtil;
import com.lanfanxing.goodsapplication.mvp.view.ILoginView;
import com.lanfanxing.goodsapplication.ui.activity.driver.DriverMainActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginUserActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.tv_btn)
    TextView tvBtn;
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.tv_forgetPwd)
    TextView tvForgetPwd;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_hackman)
    TextView tvHackman;
    @BindView(R.id.tv_callkf)
    TextView tvCallkf;
    @BindView(R.id.ll_layout)
    LinearLayout llLayout;
    @BindView(R.id.tv_user_type)
    TextView tvUserType;
    private String userType;

    private String permissionInfo;
    private final int SDK_PERMISSION_REQUEST = 127;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_login);

    }

    @Override
    public void initView() {
        userType = (String) SharedPrefusUtil.getParam(this, Constans.SDF_USER_USERTYPE, "");
        if (userType.equals("1")) {
            tvUserType.setText("司机");
            tvRegister.setText("注册司机");
            tvHackman.setText("我是货主");
        } else {
            tvUserType.setText("货主");
            tvRegister.setText("注册货主");
            tvHackman.setText("我是司机");
        }
    }

    @Override
    public void initController() {
        getPersimmions();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onLoginResult(Boolean result, String msg) {

    }

    @OnClick({R.id.tv_btn, R.id.tv_forgetPwd, R.id.tv_register, R.id.tv_hackman, R.id.tv_callkf})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn:
                if(SharedPrefusUtil.getParam(this, Constans.SDF_USER_USERTYPE, "").equals("1")){
                    openActivity(DriverMainActivity.class);
                }else{
                    openActivity(UserMainActivity.class);
                }
                break;
            case R.id.tv_forgetPwd:
                break;
            case R.id.tv_register:
                openActivity(RegisterUserActivity.class);
                break;
            case R.id.tv_hackman:
                if(SharedPrefusUtil.getParam(this, Constans.SDF_USER_USERTYPE, "").equals("1")){
                    SharedPrefusUtil.setParam(this,Constans.SDF_USER_USERTYPE,"0");
                    tvUserType.setText("货主");
                    tvRegister.setText("注册货主");
                    tvHackman.setText("我是司机");

                }else{
                    SharedPrefusUtil.setParam(this,Constans.SDF_USER_USERTYPE,"1");
                    tvUserType.setText("司机");
                    tvRegister.setText("注册司机");
                    tvHackman.setText("我是货主");
                }
                break;
            case R.id.tv_callkf:
                break;
        }
    }

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();

             //定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }

//            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//                permissions.add(Manifest.permission.RECORD_AUDIO);
//            }

            /*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */

            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.CAMERA);
            }
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }
//
//            // 打电话状态权限
//            if (addPermission(permissions, Manifest.permission.CALL_PHONE)) {
//                permissionInfo += "Manifest.permission.CALL_PHONE Deny \n";
//            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }
    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }
        } else {
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

}
