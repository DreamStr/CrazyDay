package com.lanfanxing.goodsapplication.ui.activity.driver;

import android.app.Dialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.indicator.LoadMoreType;
import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;
import com.lanfanxing.goodsapplication.mvp.view.GetOrderDialog;
import com.lanfanxing.goodsapplication.mvp.view.HintDialogTv;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GetOrderActivity extends BaseActivity implements BaseQuickAdapter.OnRecyclerViewItemClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.view_top)
    View viewTop;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.activity_login)
    RelativeLayout activityLogin;

    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;
    private List<String> datas;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_get_order);
    }

    @Override
    public void initView() {
        datas = new ArrayList<>();
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        rvList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_get_order,datas) {
            @Override
            protected void convert(BaseViewHolder helper,  String item) {

            }
        };

        mAdapter.setOnRecyclerViewItemClickListener(this);

        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        swipeLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeLayout.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.setLoadMoreType(LoadMoreType.BALL_SCALE);
        mAdapter.setEnableLoadMore(true);
        rvList.setAdapter(mAdapter);
    }

    @Override
    public void initController() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onItemClick(View view, int position) {
        new GetOrderDialog(this, R.style.dialog, new HintDialogTv.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {

            }
        }).show();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
