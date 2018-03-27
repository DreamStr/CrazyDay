package com.lanfanxing.goodsapplication.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.indicator.LoadMoreType;
import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lanfanxing on 2018/3/8.
 */

public class OrderFragment extends BaseFragment implements BaseQuickAdapter.OnRecyclerViewItemClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rc_list)
    RecyclerView rcList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;
    private List<String> datas;

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_order, null);
    }

    @Override
    protected void initFindViewById(View view) {
        Bundle mBundle = getArguments();

        datas = new ArrayList<>();
        datas.add("");
        datas.add("");
        datas.add("");
        rcList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_order, datas) {
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
        rcList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

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
