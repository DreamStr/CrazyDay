package com.lanfanxing.goodsapplication.app.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

public class ActivityManagerUtil {
    private List<Activity> activityList = new LinkedList<Activity>();
    private static ActivityManagerUtil instance;

    private ActivityManagerUtil() {

    }

    // 单例模式中获取唯一的MyApplication实例
    public static ActivityManagerUtil getInstance() {
        if (null == instance) {
            instance = new ActivityManagerUtil();
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }
}
