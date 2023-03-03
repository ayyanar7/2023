package com.ag.bta.main.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ag.bta.constants.FragmentConstants;
import com.ag.bta.constants.Global;
import com.ag.bta.main.R;
import com.ag.bta.main.adapters.ViewPagerAdapter;
import com.ag.bta.main.models.home.Tab;
import com.ag.bta.main.viewPager.CustomViewPager;
import com.ag.bta.utils.Log;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DashBoard extends Fragment {
    private String title;
private  CustomViewPager mViewPager = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get title
      //  title = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.dashboard, null);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
          mViewPager = (CustomViewPager) view.findViewById(R.id.dashboardPager);
       // mViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        setupViewPager();
        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorGray));
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d("111111MainActivity ..Viewpager listerner.."+position);


                if(tabLayout != null) {
                    TabLayout.Tab defaultTab = tabLayout.getTabAt(position);
                    if (defaultTab != null) {
                        View viewdef = defaultTab.view;
                        Log.d("Default Tab  "+viewdef);
                        if (viewdef != null) {
                            viewdef.setSelected(true);
                        } else {
                            Log.d("Default Tab not set");
                        }
                    }
                }
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("111111MainActivity ..onTabSelected.."+tab.getPosition());
                //FragmentUtils.getInstance().setMainTabPosition(tab.getPosition());
//                int pos = tab.getPosition();
//                Log.d("11111 Tab selection position..."+pos);
//                viewPager.setCurrentItem(pos);
//                Log.d("11111111subviewPager.getVisibility().."+viewPager.getVisibility());
//                viewPager.getVisibility();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("111111MainActivity ..onTabUnselected.."+tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("11111MainActivity ..onTabReselected.."+tab.getPosition());
            }
        });
    }


    private void setupViewPager( ) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        ArrayList<Tab> tabs= Global.getInstance().getAppsModel().getHome().getTabs();
        Log.d("+++++++++++++++++++++");
        Log.d("======setupViewPager =====");
        //Resources res = getResources();
        // String[] tablist =  res.getStringArray(R.array.tab_items);
        try {
            if (tabs != null & tabs.size() > 0) {
                for (int i = 0; i < tabs.size(); i++) {
                    Tab tab = tabs.get(i);
                    if (tab != null ) {
                        Log.d("Added Tab  "+tab.getTitle());
                        SubTabMainFragment tabmain =   new SubTabMainFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt(FragmentConstants.KEY_MAIN_TAB_POS,i);
                        tabmain.setArguments(bundle);
                        adapter.addFragment(tabmain, tab.getTitle());
                    }
                }
            }
        }catch ( Resources.NotFoundException e){
            // nothing doing. Its away of exit.
            Log.d("Resource Not found Exception : "+e.toString());
            e.printStackTrace();
        }

        mViewPager.setAdapter(adapter);
        Log.d("======setupViewPager End=====");
        Log.d("+++++++++++++++++++++");

    }

}
