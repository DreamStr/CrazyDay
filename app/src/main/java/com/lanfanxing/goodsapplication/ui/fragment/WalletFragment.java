package com.lanfanxing.goodsapplication.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.indicator.LoadMoreType;
import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseFragment;
import com.lanfanxing.goodsapplication.app.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lanfanxing on 2018/3/8.
 */

public class WalletFragment extends BaseFragment implements BaseQuickAdapter.OnRecyclerViewItemClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rc_list)
    RecyclerView rcList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.tv_time_day)
    TextView tvTimeDay;
    @BindView(R.id.tv_sum_money)
    TextView tvSumMoney;
    @BindView(R.id.ll_top)
    LinearLayout llTop;

    private BaseQuickAdapter<WalletBean, BaseViewHolder> mAdapter;
    private List<WalletBean> datas;

    private int mSuspensionHeight;
    private LinearLayoutManager linearLayoutManager;
    private int mCurrentPosition = 0;

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_wallet, null);
    }

    @Override
    protected void initFindViewById(View view) {
        Bundle mBundle = getArguments();

        //随意添加的一些数据，其中用top字段判断是否存在悬浮条，用日期分类，每个日期的第一条top为true
        datas = new ArrayList<>();
        datas.add(new WalletBean("20180101",100,false));
        datas.add(new WalletBean("20180101",100,false));
        datas.add(new WalletBean("20180101",100,false));
        datas.add(new WalletBean("20180102",100,false));
        datas.add(new WalletBean("20180102",100,false));
        datas.add(new WalletBean("20180102",100,false));
        datas.add(new WalletBean("20180102",100,false));
        datas.add(new WalletBean("20180103",100,false));
        datas.add(new WalletBean("20180103",100,false));
        datas.add(new WalletBean("20180103",100,false));
        datas.add(new WalletBean("20180103",100,false));
        datas.add(new WalletBean("20180104",100,false));
        datas.add(new WalletBean("20180105",100,false));

        linearLayoutManager = new LinearLayoutManager(getActivity());
        rcList.setLayoutManager(linearLayoutManager);

        //BaseQuickAdapter是我使用的一个快速适配器框架，把代码放入Recycler的adapter中都可以使用
        mAdapter = new BaseQuickAdapter<WalletBean, BaseViewHolder>(R.layout.item_wallet, datas) {
            @Override
            protected void convert(BaseViewHolder helper, WalletBean item) {

                int pos = helper.getLayoutPosition();
                if(pos>=1){
                    if(item.getTime().equals(datas.get(pos-1).getTime())){
                        helper.setVisible(R.id.ll_top,false);
                        item.setTop(false);
                    }else{
                        helper.setVisible(R.id.ll_top,true);
                        item.setTop(true);
                    }
                }else{
                    helper.setVisible(R.id.ll_top,true);
                    item.setTop(true);
                }
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

        //重点是这里，给Recycler添加一个crollListener其中llTop是写在布局文件里的
        rcList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                mSuspensionHeight = llTop.getHeight();//悬浮条最底部的y
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取最顶部的item
                View view = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);

                    //获取到的view顶部坐标小于悬浮条最底部的y轴坐标就是要重叠了
                    //datas.get(mCurrentPosition+1).isTo 表示下个item存在悬浮条
                    if(view.getTop() <= mSuspensionHeight&&datas.get(mCurrentPosition+1).isTop()){
                            llTop.setY(-(mSuspensionHeight - view.getTop()));
                    }else{
                        llTop.setY(0);

                    }

                //mCurrentPosition 不是 当前显示最顶部的position
                if(mCurrentPosition != linearLayoutManager.findFirstVisibleItemPosition()){
                    mCurrentPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    llTop.setY(0);
                }
            }
        });

        llTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/27 点击事件处理
                ToastUtils.show("SHOW");
            }
        });

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

    class WalletBean{
        public WalletBean(String time, int money,boolean top) {
            this.time = time;
            this.money = money;
        }

        String time;
        int money;
        boolean top;

        public boolean isTop() {
            return top;
        }

        public void setTop(boolean top) {
            this.top = top;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
}
