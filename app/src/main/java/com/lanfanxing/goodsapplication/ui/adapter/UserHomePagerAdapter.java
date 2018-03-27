package com.lanfanxing.goodsapplication.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lanfanxing on 2018/3/5.
 */

public class UserHomePagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"小面包车","中面包车","小型厢货","小型平板","中货车"};

    private Context context;
    private List<Fragment> fragmentList;

    public UserHomePagerAdapter(FragmentManager fm, Context context, List<Fragment> list_fragment) {
        super(fm);
        this.context = context;
        this.fragmentList = list_fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return  fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


}
