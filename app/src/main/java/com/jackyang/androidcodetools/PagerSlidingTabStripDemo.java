/*
  * JackYang   2017-08-04 11:08
  * Copyright (c)2017 7see Co.Ltd. All right reserved. 
  *
  */
package com.jackyang.androidcodetools;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.jackyang.codetools.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Viewpager标签栏@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-08-04 11:08
 */
public class PagerSlidingTabStripDemo extends BaseActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_pagerslidingtabstrip);
        initToolBar(false,R.string.viewpager_PagerSlidingTab);
        initialization();
    }

    private void initialization() {
        PagerSlidingTabStrip pagerTitle = (PagerSlidingTabStrip) findViewById(R.id.topic_viewpager_title);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_fragment);

        FragmentOne oneF = new FragmentOne();
        FragmentTwo twoF = new FragmentTwo();
        FragmentManager fragmentTransaction = getSupportFragmentManager();
        FragmentViewPagerAdapter pagerAdapter = new FragmentViewPagerAdapter(fragmentTransaction);
        pagerAdapter.add(oneF, getString(R.string.viewpager_one_text));
        pagerAdapter.add(twoF, getString(R.string.viewpager_two_text));
        viewPager.setAdapter(pagerAdapter);
        pagerTitle.setViewPager(viewPager);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Adapter
    ///////////////////////////////////////////////////////////////////////////
    private static class FragmentViewPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
        private List<Fragment> mFragmentList = new ArrayList<>();
        private List<CharSequence> mTitleList = new ArrayList<>();

        FragmentViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return getCount() > position ? mFragmentList.get(position) : null;
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }

        void add(Fragment fragment, CharSequence title) {
            mFragmentList.add(fragment);
            mTitleList.add(title);
        }
    }
}