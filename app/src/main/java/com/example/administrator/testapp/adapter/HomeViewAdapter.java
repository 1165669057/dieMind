package com.example.administrator.testapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/9/23.
 */
public class HomeViewAdapter extends FragmentPagerAdapter{
    private List<Fragment> myFragments;
    public HomeViewAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.myFragments=fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);
    }

    @Override
    public int getCount() {
        return myFragments.size();
    }
}
