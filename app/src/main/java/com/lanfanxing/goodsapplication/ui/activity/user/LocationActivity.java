package com.lanfanxing.goodsapplication.ui.activity.user;

import android.hardware.SensorManager;
import android.view.View;
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
import com.lanfanxing.goodsapplication.R;
import com.lanfanxing.goodsapplication.app.base.BaseActivity;

import butterknife.BindView;

public class LocationActivity extends BaseActivity {

    @BindView(R.id.map_path_view)
    MapView mapPathView;
    @BindView(R.id.tv_city)
    TextView tvCity;

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

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_location);
    }

    @Override
    public void initView() {
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
        option.setIsNeedAddress(true);//需要地址信息
        option.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    @Override
    public void initListener() {
        tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(AddressActivity.class);
            }
        });
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
