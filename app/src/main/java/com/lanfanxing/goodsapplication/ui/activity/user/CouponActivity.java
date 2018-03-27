package com.lanfanxing.goodsapplication.ui.activity.user;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.indicator.LoadMoreType;
import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CouponActivity extends BaseActivity implements BaseQuickAdapter.OnRecyclerViewItemClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

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
        setContentView(R.layout.activity_coupon);
    }

    @Override
    public void initView() {
        datas = new ArrayList<>();
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        rvList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_coupon,datas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {

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
        addHeaderView();
    }

    private void addHeaderView() {
        View headerView = getLayoutInflater().inflate(R.layout.item_coupon_top, null);
        headerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mAdapter.addHeaderView(headerView);

    }
    @Override
    public void initController() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
