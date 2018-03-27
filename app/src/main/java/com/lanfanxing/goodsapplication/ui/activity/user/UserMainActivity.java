package com.lanfanxing.goodsapplication.ui.activity.user;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;
import com.lanfanxing.goodsapplication.app.utils.JfUtility;
import com.lanfanxing.goodsapplication.mvp.bean.MenuBean;
import com.lanfanxing.goodsapplication.mvp.view.widget.OnWheelChangedListener;
import com.lanfanxing.goodsapplication.mvp.view.widget.WheelView;
import com.lanfanxing.goodsapplication.mvp.view.widget.adapters.ArrayWheelAdapter;
import com.lanfanxing.goodsapplication.ui.adapter.UserHomePagerAdapter;
import com.lanfanxing.goodsapplication.ui.fragment.CarTypeFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserMainActivity extends BaseActivity implements BaseQuickAdapter.OnRecyclerViewItemClickListener, OnWheelChangedListener {


    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.titleBar)
    RelativeLayout titleBar;
    @BindView(R.id.tab_slid)
    TabLayout tabSlid;
    @BindView(R.id.iv_pre)
    ImageView ivPre;
    @BindView(R.id.vp_pager)
    ViewPager vpPager;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_time)
    LinearLayout llTime;
    @BindView(R.id.tv_start_address)
    TextView tvStartAddress;
    @BindView(R.id.tv_end_address)
    TextView tvEndAddress;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.nv_menu)
    NavigationView nvMenu;
    @BindView(R.id.nav_dra)
    DrawerLayout navDra;

    //日期选择
    private Dialog dialog;
    private WheelView twv;
    private WheelView twv1;
    private WheelView twv2;
    private List<String> yearList;
    private List<String> monthList;
    private List<String> dayList;
    private List<String> hList;
    private List<String> mList;
    private Calendar calendar;
    private HashMap<String, List<String>> yAndm;
    private HashMap<String, List<String>> mAndd;
    private String mCurrentYear;
    private String mCurrentMonth;
    private String mCurrentDay;
    private View timeView;
    private int year;
    private int month;
    private int day;
    private int h;
    private int m;
    private WheelView whShi;
    private WheelView whFen;

    private String startTime;

    private List<Fragment> fragments;
    private UserHomePagerAdapter adapter;
    private String tabTitles[] = new String[]{"小面包车", "中面包车", "小型厢货", "小型平板", "中货车"};
    private int current = 0;

    private List<MenuBean.Items> datas = new ArrayList<>();
    private BaseQuickAdapter<MenuBean.Items, BaseViewHolder> mAdapter;
    private int[] imgs = new int[]{R.drawable.icon_my_orderrecord, R.drawable.icon_my_youhuijuan, R.drawable.icon_my_kefuzhongxin
            , R.drawable.icon_my_moresel};
    private String[] titles = new String[]{"订单记录", "优惠劵", "客服中心", "更多设置"};


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        fragments = new ArrayList<>();
        for (int i = 0; i < tabTitles.length; i++) {
            Fragment fragment = new CarTypeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("test", tabTitles[i]);
            fragment.setArguments(bundle);

            fragments.add(fragment);
        }

        adapter = new UserHomePagerAdapter(getSupportFragmentManager(), this, fragments);
        vpPager.setAdapter(adapter);
        tabSlid.setupWithViewPager(vpPager);

        View headerView = nvMenu.getHeaderView(0);
        LinearLayout llMine = (LinearLayout) headerView.findViewById(R.id.ll_mine);
        TextView textView = (TextView) headerView.findViewById(R.id.nav_name);
        ImageView ivFace = (ImageView) headerView.findViewById(R.id.nav_header_img);
        View menuTop = headerView.findViewById(R.id.top_view_menu);

        ivFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(MineInfoActivity.class);
                navDra.closeDrawers();
            }
        });

        menuTop.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, JfUtility.getStateBarHeight(this)));

        RecyclerView rcMenu = (RecyclerView) headerView.findViewById(R.id.rc_menu);
        rcMenu.setLayoutManager(new LinearLayoutManager(this));
        getdatas();
        mAdapter = new BaseQuickAdapter<MenuBean.Items, BaseViewHolder>(R.layout.item_menu, datas) {
            @Override
            protected void convert(BaseViewHolder helper, MenuBean.Items item) {
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setImageDrawable(R.id.iv_icon, getResources().getDrawable(item.getResouse()));
            }
        };

        mAdapter.setOnRecyclerViewItemClickListener(this);

        rcMenu.setAdapter(mAdapter);
    }

    private void getdatas() {
        for (int i = 0; i < imgs.length; i++) {
            MenuBean.Items items = new MenuBean.Items();
            items.setTitle(titles[i]);
            items.setResouse(imgs[i]);
            datas.add(items);
        }
    }

    @Override
    public void initController() {
        getTimeData();
    }

    @Override
    public void initListener() {
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                current = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.iv_left, R.id.tv_right, R.id.iv_pre, R.id.iv_next, R.id.ll_time, R.id.tv_start_address, R.id.tv_end_address, R.id.tv_detail, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                navDra.openDrawer(Gravity.LEFT);
                break;
            case R.id.tv_right:
                break;
            case R.id.iv_pre:
                if (current != 0) {
                    vpPager.setCurrentItem(current - 1);
                }
                break;
            case R.id.iv_next:
                if (current != tabTitles.length - 1) {
                    vpPager.setCurrentItem(current + 1);
                }
                break;
            case R.id.ll_time:
                showTD();
                break;
            case R.id.tv_start_address:
                openActivity(SendAddressActivity.class);
                break;
            case R.id.tv_end_address:
                openActivity(AcceptAddressActivity.class);
                break;
            case R.id.tv_detail:
                openActivity(MoneyDetailActivity.class);
                break;
            case R.id.tv_next:
                openActivity(ConfirmOrderActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position){
            case 0:
                openActivity(OrderActivity.class);
                break;
            case 1:
                openActivity(CouponActivity.class);
                break;
            case 2:
                break;
            case 3:
                openActivity(MoreSetActivity.class);
                break;
        }
    }

    private void getTimeData() {
        //日期
        yAndm = new HashMap<>();
        mAndd = new HashMap<>();

        yearList = new ArrayList<>();
        monthList = new ArrayList<>();
        dayList = new ArrayList<>();

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);
        int index = 0;
        int daysCountOfMonth = 0;

        for (int i = year; i <= year + 3; i++) {
            yearList.add(i + "年");
            calendar.set(Calendar.YEAR, i);//先指定年份
            List<String> test = new ArrayList<>();

            for (int j = 0; j < 12; j++) {

                if (i == year) {
                    if (j >= month) {
                        monthList.add((j + 1) + "月");
                    }
                } else {
                    monthList.add((j + 1) + "月");
                }

                if ((j + 1) == 12) {
                    test.addAll(monthList);
                    yAndm.put(yearList.get(index), test);
                    index++;
                    monthList.clear();
                }
                calendar.set(Calendar.MONTH, j);//再指定月份
                daysCountOfMonth = calendar.getActualMaximum(Calendar.DATE);//获取指定年份中指定月份有几天
                List<String> strings = new ArrayList<>();

                for (int k = 1; k <= daysCountOfMonth; k++) {
                    if (i == year && j == month) {

                        if (k >= day) {    //当天 小于当前日期才添加
                            dayList.add(k + "日");
                        }
                    } else {
                        dayList.add(k + "日");
                    }

                    if (k == daysCountOfMonth) {
                        strings.addAll(dayList);
                        mAndd.put((i) + "年" + (j + 1) + "月", strings);
                        dayList.clear();
                    }
                }
            }
        }

        //时间
        hList = new ArrayList<>();
        mList = new ArrayList<>();

        h = calendar.get(Calendar.HOUR_OF_DAY);
        m = calendar.get(Calendar.MINUTE);

        for (int i = 0; i < 24; i++) {
            hList.add(i + "");
        }

        for (int i = 0; i < 60; i++) {
            mList.add(i + "");
        }
    }

    /**
     * 日期选择器
     */
    private void showTD() {
        dialog = new Dialog(this, R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        timeView = getLayoutInflater().inflate(R.layout.choose_five, null);

        loadTimeWheel(timeView);

        dialog.setContentView(timeView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围取消
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void loadTimeWheel(final View view) {

        twv = (WheelView) view.findViewById(R.id.id_one);
        twv1 = (WheelView) view.findViewById(R.id.id_two);
        twv2 = (WheelView) view.findViewById(R.id.id_three);

        whShi = (WheelView) view.findViewById(R.id.id_shi);
        whFen = (WheelView) view.findViewById(R.id.id_fen);

        twv2.setVisibility(View.VISIBLE);

        twv.setViewAdapter(new ArrayWheelAdapter<String>(this, yearList));
        twv1.setViewAdapter(new ArrayWheelAdapter<String>(this, yAndm.get(year + "年")));
        twv2.setViewAdapter(new ArrayWheelAdapter<String>(this, mAndd.get(year + "年" + (month + 1) + "月")));
        whShi.setViewAdapter(new ArrayWheelAdapter<String>(this, hList));
        whFen.setViewAdapter(new ArrayWheelAdapter<String>(this, mList));

        whShi.setVisibleItems(7);
        whFen.setVisibleItems(7);
        twv.setVisibleItems(7);
        twv1.setVisibleItems(7);
        twv2.setVisibleItems(7);


        TextView btnConfirm = (TextView) view.findViewById(R.id.btn_confirm);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);

        twv.addChangingListener(this);
        twv1.addChangingListener(this);
        twv2.addChangingListener(this);
        whShi.addChangingListener(this);
        whFen.addChangingListener(this);

        mCurrentYear = yearList.get(0);
        mCurrentMonth = yAndm.get(yearList.get(0)).get(0);
        mCurrentDay = mAndd.get(yearList.get(0) + yAndm.get(yearList.get(0)).get(0)).get(0);

        if (mCurrentYear.equals(year + "年")
                && mCurrentMonth.equals(month + 1 + "月")
                && mCurrentDay.equals(day + "日")) {
            if (Integer.parseInt(hList.get(whShi.getCurrentItem())) < h) {
                for (int i = 0; i < hList.size(); i++) {
                    if (hList.get(i).equals(h + "")) {
                        whShi.setCurrentItem(i);
                    }

                }
            }

            if (Integer.parseInt(mList.get(whFen.getCurrentItem())) < m) {
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).equals(m + "")) {
                        whFen.setCurrentItem(i);
                    }

                }
            }
        } else {
            whShi.setCurrentItem(0);
            whFen.setCurrentItem(0);
        }

        for (int i = 0; i < yearList.size(); i++) {
            if (yearList.get(i).contains(year + "")) {
                twv.setCurrentItem(i);
            }

        }
        for (int i = 0; i < yAndm.get(year + "年").size(); i++) {
            if (yAndm.get(year + "年").get(i).equals((month + 1) + "月")) {
                twv1.setCurrentItem(i);
            }
        }

        for (int i = 0; i < mAndd.get(year + "年" + (month + 1) + "月").size(); i++) {
            if (mAndd.get(year + "年" + (month + 1) + "月").get(i).contains(day + "")) {
                twv2.setCurrentItem(i);
                break;
            }
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentYear = mCurrentYear.replace("年", "");
                mCurrentMonth = mCurrentMonth.replace("月", "");
                mCurrentDay = mCurrentDay.replace("日", "");
                String strMonth = String.format("%02d", Integer.parseInt(mCurrentMonth));
                String strDay = String.format("%02d", Integer.parseInt(mCurrentDay));
                String shi = String.format("%02d", Integer.parseInt(hList.get(whShi.getCurrentItem())));
                String fen = String.format("%02d", Integer.parseInt(mList.get(whFen.getCurrentItem())));

                tvTime.setText(mCurrentYear + "-" + strMonth + "-" + strDay + "  " + shi + ":" + fen);
                startTime = mCurrentYear + "-" + strMonth + "-" + strDay + " " + shi + ":" + fen;

                dialog.cancel();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == twv) {
            mCurrentYear = yearList.get(twv.getCurrentItem());
            twv1.setViewAdapter(new ArrayWheelAdapter<String>(this, yAndm.get(mCurrentYear)));
            twv1.setCurrentItem(0);
            mCurrentMonth = yAndm.get(mCurrentYear).get(twv1.getCurrentItem());
            twv2.setViewAdapter(new ArrayWheelAdapter<String>(this, mAndd.get(mCurrentYear + mCurrentMonth)));
            twv2.setCurrentItem(0);
        } else if (wheel == twv1) {
            mCurrentMonth = yAndm.get(mCurrentYear).get(twv1.getCurrentItem());
            twv2.setViewAdapter(new ArrayWheelAdapter<String>(this, mAndd.get(mCurrentYear + mCurrentMonth)));
            twv2.setCurrentItem(0);
        } else if (wheel == twv2) {
            mCurrentDay = mAndd.get(mCurrentYear + mCurrentMonth).get(twv2.getCurrentItem());
        }

        if (yearList.get(twv.getCurrentItem()).equals(year + "年")
                && yAndm.get(mCurrentYear).get(twv1.getCurrentItem()).equals(month + 1 + "月")
                && mAndd.get(mCurrentYear + mCurrentMonth).get(twv2.getCurrentItem()).equals(day + "日")) {

            if (Integer.parseInt(hList.get(whShi.getCurrentItem())) < h) {
                for (int i = 0; i < hList.size(); i++) {
                    if (hList.get(i).equals(h + "")) {
                        whShi.setCurrentItem(i);
                    }
                }
            }
            if (hList.get(whShi.getCurrentItem()).equals(h + "")) {
                if (Integer.parseInt(mList.get(whFen.getCurrentItem())) < m) {
                    for (int i = 0; i < mList.size(); i++) {
                        if (mList.get(i).equals(m + "")) {
                            whFen.setCurrentItem(i);
                        }
                    }
                }
            }

        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
