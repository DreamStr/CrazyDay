package com.lanfanxing.goodsapplication.ui.activity.driver;

import android.hardware.SensorManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;
import com.lanfanxing.goodsapplication.app.utils.JfUtility;
import com.lanfanxing.goodsapplication.mvp.bean.MenuBean;
import com.lanfanxing.goodsapplication.ui.activity.user.MineInfoActivity;
import com.lanfanxing.goodsapplication.ui.activity.user.MoreSetActivity;
import com.lanfanxing.goodsapplication.ui.activity.user.OrderActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DriverMainActivity extends BaseActivity implements BaseQuickAdapter.OnRecyclerViewItemClickListener {

    @BindView(R.id.map_path_view)
    MapView mapPathView;
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.titleBar)
    RelativeLayout titleBar;
    @BindView(R.id.nv_menu)
    NavigationView nvMenu;
    @BindView(R.id.nav_dra)
    DrawerLayout navDra;
    @BindView(R.id.iv_order)
    ImageView ivOrder;

    private BaiduMap baiduMap;
    private GeoCoder geoCoder;
    private ReverseGeoCodeOption op;
    private SensorManager mSensorManager;
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private float mCurrentAccracy;
    private MyLocationData locData;
    private int mCurrentDirection = 0;
    boolean isFirstLoc = true; // 是否首次定位
    private String address;

    private List<MenuBean.Items> datas = new ArrayList<>();
    private BaseQuickAdapter<MenuBean.Items, BaseViewHolder> mAdapter;
    private int[] imgs = new int[]{R.drawable.icon_my_orderrecord, R.drawable.icon_my_youhuijuan, R.drawable.icon_my_kefuzhongxin
            , R.drawable.icon_my_moresel};
    private String[] titles = new String[]{"订单记录", "我的钱包", "客服中心", "更多设置"};

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_driver_main);
    }

    @Override
    public void initView() {
        topView.setLayoutParams(new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, JfUtility.getStateBarHeight(this)));

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


        baiduMap = mapPathView.getMap();
        //隐藏百度logo
        mapPathView.removeViewAt(1);
        //不显示缩放
        mapPathView.showZoomControls(false);
        //设置是否显示比例尺控件
        mapPathView.showScaleControl(false);
        //实例化一个地理编码查询对象
        geoCoder = GeoCoder.newInstance();
        //设置反地理编码位置坐标
        op = new ReverseGeoCodeOption();

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
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
//        option.setIsNeedAltitude(true);//海拔信息
        option.setIsNeedLocationPoiList(true);//poi检索信息
        option.setIsNeedAddress(true);//需要地址信息
        option.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position){
            case 0:
                openActivity(OrderActivity.class);
                break;
            case 1:
                openActivity(WalletActivity.class);
                break;
            case 2:
                break;
            case 3:
                openActivity(MoreSetActivity.class);
                break;
        }
    }


    @OnClick({R.id.iv_left, R.id.iv_right, R.id.iv_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                navDra.openDrawer(Gravity.LEFT);
                break;
            case R.id.iv_right:
                break;
            case R.id.iv_order:
                openActivity(GetOrderActivity.class);
                break;
        }
    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mapPathView == null) {
                return;
            }

            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locData);

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(17.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//                添加marker
//                MarkerOptions ooA = new MarkerOptions().position(ll).icon(bdA)
//                        .zIndex(9).draggable(true);
//                ooA.animateType(MarkerOptions.MarkerAnimateType.grow);
//                mMarkerA = (Marker) (baiduMap.addOverlay(ooA));
//                initNearShop(ll);

                //实例化一个地理编码查询对象
                GeoCoder geoCoder = GeoCoder.newInstance();
                //设置反地理编码位置坐标
                ReverseGeoCodeOption op = new ReverseGeoCodeOption();
                op.location(ll);
                //发起反地理编码请求(经纬度->地址信息)
                geoCoder.reverseGeoCode(op);
                geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

                    @Override
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
                        //获取点击的坐标地址
//                        tvLocation.setText(arg0.getAddress());
                        address = arg0.getAddress();
                        //获取附近的技师
//                        nearbyRequest();
                    }

                    @Override
                    public void onGetGeoCodeResult(GeoCodeResult arg0) {
                    }
                });

            }
        }

        public void onConnectHotSpotMessage(String s, int i) {

        }


    }


}
