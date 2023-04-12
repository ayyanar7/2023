package com.ag.bta.main.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ag.bta.main.activities.other.CartFragment;
import com.ag.bta.main.activities.other.CustomerDetailsFragment;
import com.ag.bta.main.activities.other.OrderDetailsFragment;
import com.ag.bta.main.activities.other.SalesDetailsGetterFragment;
import com.ag.bta.main.fragments.DashBoard;
import com.ag.bta.ui.custombottombar.bottomnavigationview.BaseFragment;
import com.ag.bta.ui.custombottombar.library.custombottomnavview.BottomNavigationViewEx;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ag.bta.main.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends NavigationDrawerActivity{
   // private HomeActivity.VpAdapter adapter;

    BottomNavigationViewEx bnve_bottom = null;
    private List<Fragment> fragments;// used for ViewPager adapter
    protected FloatingActionButton fab = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragments = new ArrayList<>();
        initBottomBarData();

        initView();
        initEvent();
   //mainViewPager.setCurrentItem(2, false);
        bnve_bottom.setCurrentItem(2);
        //displayFrgament(2);
    }

    private void initBottomBarData() {


        // create music fragment and add it
        CustomerDetailsFragment bottom_fragment_one = new CustomerDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.bottombar_item_left));
        bottom_fragment_one.setArguments(bundle);

        // create backup fragment and add it
        OrderDetailsFragment bottom_fragment_two = new OrderDetailsFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.bottombar_item_leftbutone));
        bottom_fragment_two.setArguments(bundle);

        // create friends fragment and add it
        SalesDetailsGetterFragment bottom_fragment_four = new SalesDetailsGetterFragment();
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
        dashboard.setArguments(bundle);


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
        //bnve_bottom.setCurrentItem(2);

        // set listener to change the current item of view pager when click bottom nav item
        bnve_bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;
            int position = 0;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               //mainViewPager.setVisibility(View.VISIBLE);
               //framelayout.setVisibility(View.GONE);
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


displayFrgament(position);
                return true;
            }
        });



        // center item click listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bnve_bottom.setCurrentItem(2);
                fab.setImageResource(R.drawable.dashboard_sel);

                Toast.makeText(HomeActivity.this, "Center", Toast.LENGTH_SHORT).show();
            }
        });
    }

private void displayFrgament(int pos){
        if(pos== 2){
            fab.setImageResource(R.drawable.dashboard_sel);
            fab.refreshDrawableState();
            //bnve_bottom.setCurrentItem(pos);
        }
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    Fragment fragment = fragments.get(pos);
    if (fragment != null) {
        // mainViewPager.setVisibility(View.GONE);
        framelayout.setVisibility(View.VISIBLE);
        transaction.replace(R.id.homeframelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

}
