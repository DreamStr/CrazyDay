package com.lanfanxing.goodsapplication.ui.activity.user;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MoneyDetailActivity extends BaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rc_list)
    RecyclerView rcList;
    @BindView(R.id.ll_layout)
    LinearLayout llLayout;

    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;
    private List<String> datas;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_money_detail);
    }

    @Override
    public void initView() {
        datas = new ArrayList<>();
        datas.add("");
        datas.add("");
        datas.add("");
        rcList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_price_detail,datas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {

            }
        };
        rcList.setAdapter(mAdapter);
    }

    @Override
    public void initController() {

    }

    @Override
    public void initListener() {

    }

}
