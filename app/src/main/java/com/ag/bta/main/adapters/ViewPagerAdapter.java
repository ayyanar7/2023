package com.ag.bta.main.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ag.bta.utils.lazyloading.FragmentFactory;
import com.ag.bta.utils.lazyloading.LazyLoadFragment;


import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        //Fragment contentFragment = SimpleFragment.newInstance(position);
//        if (position == 0) {
//            return contentFragment;
//        } else if (position == 1) {
//            return LazyLoadFragment.newInstance(SimpleFragment.class);
//        } else {

            return LazyLoadFragment.newInstance(new FragmentFactory() {
                @Override
                public Fragment createFragment() {
                    return mFragmentList.get(position);
                }
            });
        //}

       // return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}