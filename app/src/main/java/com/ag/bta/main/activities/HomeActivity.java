package com.ag.bta.main.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ag.bta.constants.Constants;
import com.ag.bta.main.fragments.DashBoard;
import com.ag.bta.ui.custombottombar.bottomnavigationview.BaseFragment;
import com.ag.bta.ui.custombottombar.library.custombottomnavview.BottomNavigationViewEx;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ag.bta.main.R;
import com.ag.bta.main.viewPager.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends NavigationDrawerActivity {
    private HomeActivity.VpAdapter adapter;

    BottomNavigationViewEx bnve_bottom = null;
    private List<Fragment> fragments;// used for ViewPager adapter
    protected FloatingActionButton fab = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.home_activity_main);
     //  init();
   // CustomViewPager mainViewPager = findViewById(R.id.mainViewPager);

        //setupViewPager(mainViewPager);
        fragments = new ArrayList<>();
        initBottomBarData();

        adapter = new HomeActivity.VpAdapter(getSupportFragmentManager(), fragments);

        mainViewPager.setAdapter(adapter);
        initView();

        initEvent();


    }

    private void initBottomBarData() {


        // create music fragment and add it
        BaseFragment bottom_fragment_one = new BaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.bottombar_item_left));
        bottom_fragment_one.setArguments(bundle);

        // create backup fragment and add it
        BaseFragment bottom_fragment_two = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.bottombar_item_leftbutone));
        bottom_fragment_two.setArguments(bundle);

        // create friends fragment and add it
        BaseFragment bottom_fragment_four = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.bottombar_item_rightb4one));
        bottom_fragment_four.setArguments(bundle);

        // create friends fragment and add it
        BaseFragment bottom_fragment_five = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.bottombar_item_right));
        bottom_fragment_five.setArguments(bundle);

        DashBoard dashboard = new DashBoard();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.bottombar_item_center));
        //bottom_fragment_center.setArguments(bundle);


        // add to fragments for adapter
        fragments.add(bottom_fragment_one);
        fragments.add(bottom_fragment_two);
        fragments.add(dashboard);
        fragments.add(bottom_fragment_four);
        fragments.add(bottom_fragment_five);


    }
    private void initView() {


        bnve_bottom = (BottomNavigationViewEx) findViewById(R.id.bnve_bottom);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        bnve_bottom.enableItemShiftingMode(true);
        bnve_bottom.enableShiftingMode(true);
        bnve_bottom.enableAnimation(true);

        // set adapter

    }

    private void initEvent() {
        bnve_bottom.setCurrentItem(2);
        // set listener to change the current item of view pager when click bottom nav item
        bnve_bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = 0;
                mainViewPager.setVisibility(View.VISIBLE);
                framelayout.setVisibility(View.GONE);
                fab.setImageResource(R.drawable.dashboard);
                android.util.Log.d("Business Agnet ", " position: "+position);
                switch (item.getItemId()) {
                    case R.id.item_one:
                        position = 0;
                        break;
                    case R.id.item_two:
                        position = 1;
                        break;
                    case R.id.item_three:
                        position = 2;
                        break;
                    case R.id.item_four:
                        position = 3;
                        break;
                    case R.id.item_five:
                        position = 4;
                        break;

                    default: {
                        return false;
                    }
                }
                if(previousPosition != position) {
                    mainViewPager.setCurrentItem(position, false);
                    previousPosition = position;
                    android.util.Log.i(Constants.STR_APP_ID, "-----bnve-------- previous item:" +   bnve_bottom.getCurrentItem() + " current item:" + position + " ------------------");
                }

                return true;
            }
        });



        // set listener to change the current checked item of bottom nav when scroll view pager
        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              //  android.util.Log.i(Constants.STR_APP_ID, "----top-ViewPager-------- previous item:" +   bnve_top.getCurrentItem() + " current item:" + position + " ------------------");
                Log.i(Constants.STR_APP_ID, "----bottom-ViewPager-------- previous item:" +   bnve_bottom.getCurrentItem() + " current item:" + position + " ------------------");
                     bnve_bottom.setCurrentItem(position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // center item click listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bnve_bottom.setCurrentItem(2);
              //  fab.setImageResource(R.drawable.about_android);
                Toast.makeText(HomeActivity.this, "Center", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private   class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }
}
