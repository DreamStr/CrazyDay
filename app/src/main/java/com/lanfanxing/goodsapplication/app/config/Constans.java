package com.lanfanxing.goodsapplication.app.config;

/**
 * Created by lanfanxing on 2018/3/9.
 */

public class Constans {
        public static final String HOST = "http://192.168.2.107:8080/speech/appuser/";

    //上传图片
    public static final String URL_UPLOAD_IMG ="http://192.168.2.107:8080/speech/appupload/upload";
    //下载图片
    public static final String URL_DOWNLOAD_IMG =  "http://192.168.2.107:8080/speech/appdownload/download/";

    public static final String SDF_USER_USERTYPE = "usertype";//0 货主  1司机
    public static final String SDF_USER_USERID = "userid";
    public static final String SDF_USER_TOKEN = "apptoken";
    public static final String SDF_USER_NAME = "name";
    public static final String SDF_USER_SIGN = "sign";
    public static final String SDF_USER_PHONE = "phone";
    public static final String SDF_USER_FACE = "face";
    public static final String SDF_USER_JOB = "job";//职位
    public static final String SDF_USER_ACOUNT = "acount";//账户余额
    public static final String SDF_USER_ADDRESS = "address";//详细地址
    public static final String SDF_USER_PROVINCE = "province";//省
    public static final String SDF_USER_CITY = "city";//城市
    public static final String SDF_USER_LON = "lon";//经度
    public static final String SDF_USER_LAT = "lat";//纬度
    public static final String SDF_DENSITY = "density";//设备的像素密度
    public static final String SDF_USER_OPENID = "openid";

    //注册
    public static final String regist = HOST+"regist";
    //登录
    public static final String login = HOST+"login";
}
