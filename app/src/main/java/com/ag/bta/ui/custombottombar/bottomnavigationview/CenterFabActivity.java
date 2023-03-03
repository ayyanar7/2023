package com.ag.bta.ui.custombottombar.bottomnavigationview;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ag.bta.constants.Constants;
import com.ag.bta.main.viewPager.CustomViewPager;
import com.ag.bta.ui.custombottombar.library.custombottomnavview.BottomNavigationViewEx;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ag.bta.main.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.ag.bottomnavigationview.databinding.ActivityCenterFabBinding;

import java.util.ArrayList;
import java.util.List;

public class CenterFabActivity extends AppCompatActivity {
    private static final String TAG = CenterFabActivity.class.getSimpleName();
    //  private ActivityCenterFabBinding bind;
    private VpAdapter adapter;
   CustomViewPager mainViewPager  = null;
    BottomNavigationViewEx  bnve_bottom = null;
    BottomNavigationViewEx  bnve_top = null;
    FloatingActionButton fab = null;
    // collections
    private List<Fragment> fragments;// used for ViewPager adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_main);
        // bind = DataBindingUtil.setContentView(this, R.layout.activity_center_fab);
        fragments = new ArrayList<>();
        initBottomBarData();
        initTopBarData();
        adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        mainViewPager = (CustomViewPager) findViewById(R.id.mainViewPager);
        mainViewPager.setAdapter(adapter);


        initView();
        initEvent();
    }


    /**
     * create fragments
     */
    private void initTopBarData() {


        // create music fragment and add it
        BaseFragment top_fragment_one = new BaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.bottombar_item_left));
        top_fragment_one.setArguments(bundle);

        // create backup fragment and add it
        BaseFragment top_fragment_two = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.bottombar_item_leftbutone));
        top_fragment_two.setArguments(bundle);

        // create friends fragment and add it
        BaseFragment top_fragment_four = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.bottombar_item_rightb4one));
        top_fragment_four.setArguments(bundle);

        // create friends fragment and add it
        BaseFragment top_fragment_five = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.bottombar_item_right));
        top_fragment_five.setArguments(bundle);


        // add to fragments for adapter
        fragments.add(top_fragment_one);
        fragments.add(top_fragment_two);
        fragments.add(top_fragment_four);
        fragments.add(top_fragment_five);


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


        // add to fragments for adapter
        fragments.add(bottom_fragment_one);
        fragments.add(bottom_fragment_two);
        fragments.add(bottom_fragment_four);
        fragments.add(bottom_fragment_five);


    }



    /**
     *
     */
    private void initView() {


        bnve_bottom = (BottomNavigationViewEx) findViewById(R.id.bnve_bottom);
        //bnve_top = (BottomNavigationViewEx) findViewById(R.id.bnve_top);
        fab = (FloatingActionButton) findViewById(R.id.fab);


        bnve_bottom.enableItemShiftingMode(false);
        bnve_bottom.enableShiftingMode(false);
        bnve_bottom.enableAnimation(false);

        // set adapter

    }

    /**
     * set listeners
     */
    private void initEvent() {
        // set listener to change the current item of view pager when click bottom nav item
        bnve_bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = 0;
                Log.d("Business Agnet ", " position: "+position);
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
                    Log.i(TAG, "-----bnve-------- previous item:" +   bnve_bottom.getCurrentItem() + " current item:" + position + " ------------------");
                }

                return true;
            }
        });

        bnve_top.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = 5;
                Log.d("Business Agnet ", " position: "+position);
                switch (item.getItemId()) {
                    case R.id.item_one:
                        position += 0;
                        break;
                    case R.id.item_two:
                        position += 1;
                        break;
                    case R.id.item_three:
                        position += 2;
                        break;
                    case R.id.item_four:
                        position += 3;
                        break;
                    case R.id.item_five:
                        position += 4;
                        break;

                    default: {
                        return false;
                    }
                }
                if(previousPosition != position) {
                    mainViewPager.setCurrentItem(position, false);
                    previousPosition = position;
                    Log.i(TAG, "-----bnve-top------- previous item:" +   bnve_top.getCurrentItem() + " current item:" + position + " ------------------");
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
                Log.i(TAG, "----top-ViewPager-------- previous item:" +   bnve_top.getCurrentItem() + " current item:" + position + " ------------------");
                Log.i(TAG, "----bottom-ViewPager-------- previous item:" +   bnve_bottom.getCurrentItem() + " current item:" + position + " ------------------");
if(position > 4){
    bnve_top.setCurrentItem(position);
}else{
    bnve_bottom.setCurrentItem(position);
}

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // center item click listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CenterFabActivity.this, "Center", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
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