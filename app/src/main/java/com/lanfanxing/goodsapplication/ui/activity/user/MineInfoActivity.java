package com.lanfanxing.goodsapplication.ui.activity.user;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineInfoActivity extends BaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_face)
    LinearLayout llFace;
    @BindView(R.id.ll_nickname)
    LinearLayout llNickname;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.iv_face)
    ImageView ivFace;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_mine_info);
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


    @OnClick({R.id.ll_face, R.id.ll_nickname, R.id.ll_sex, R.id.ll_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_face:
                PictureSelector.create(MineInfoActivity.this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                break;
            case R.id.ll_nickname:
                openActivity(InputNickNameActivity.class);
                break;
            case R.id.ll_sex:
                break;
            case R.id.ll_phone:
                openActivity(ChangePhoneActivity.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    final List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    Glide.with(MineInfoActivity.this)
                            .load(selectList.get(0).getPath())
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(ivFace);
//                    showLoadingDialog("正在上传头像", false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            updataImg(selectList.get(0).getPath());
                        }
                    }, 1000);
                    break;
            }
        }
    }

}
