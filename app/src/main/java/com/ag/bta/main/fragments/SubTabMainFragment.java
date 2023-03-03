package com.ag.bta.main.fragments;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ag.bta.constants.FragmentConstants;
import com.ag.bta.constants.Global;
import com.ag.bta.main.R;
import com.ag.bta.main.adapters.ViewPagerAdapter;
import com.ag.bta.main.models.home.PagerContent;
import com.ag.bta.main.viewPager.CustomViewPager;
import com.ag.bta.main.models.home.SubTab;
import com.ag.bta.main.models.home.Tab;
import com.ag.bta.main.wavelayout.models.ItemCard;
import com.ag.bta.main.wavelayout.sample.WaveLayoutFragment;
import com.ag.bta.utils.GsonUtils;
import com.ag.bta.utils.Log;
import com.ag.bta.utils.database.sqlite.DesignTable;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

public class SubTabMainFragment extends Fragment {

    CustomViewPager viewPager = null;
    private TabLayout subTabLayout;
 private  int mainTabPosition = -1;
    public SubTabMainFragment() {
        // Required empty public constructor
    }


    Tab tabObj ;
    private Bundle savedState = null;
    private boolean createdStateInDestroyView;
    private static final String SAVED_BUNDLE_TAG = "saved_bundle";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate ********* SubTab Fragment ...."+mainTabPosition);

        if (savedInstanceState != null) {
            savedState = savedInstanceState.getBundle(SAVED_BUNDLE_TAG);
        }
    }
    private Bundle saveState() {
        Bundle state = new Bundle();
       // state.putSerializable(SAVED_BUNDLE_TAG, tabObj);
        return state;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MAIN TAB DESTROYED....."+this.mainTabPosition);
        super.onDestroyView();
        savedState = saveState();
        createdStateInDestroyView = true;
        tabObj = null;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {

        if (tabObj == null) {
            outState.putBundle(SAVED_BUNDLE_TAG, savedState);
        } else {
            outState.putBundle(SAVED_BUNDLE_TAG, createdStateInDestroyView ? savedState : saveState());
        }
        createdStateInDestroyView = false;
        super.onSaveInstanceState(outState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String layoutName =  "fragment_maintab_home";//same for all hopefully
        int layoutId =  Global.getInstance().getLayout(layoutName );
        Log.d("22222layoutName: "+layoutName);
        Log.d("22222layoutId: "+layoutId);
        Bundle bundle =  this.getArguments();
        if(bundle != null){
            Log.d("Bundle is not null .SubTabMainFragment...");
            this.mainTabPosition = bundle.getInt(FragmentConstants.KEY_MAIN_TAB_POS);
            tabObj = Global.getInstance().getAppsModel().getHome().getTabs().get(this.mainTabPosition);

        }else{
            Log.d("Bundle is null .SubTabMainFragment...");
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(layoutId, container, false);

           viewPager = view.findViewById(R.id.homeViewPager);

        subTabLayout = view.findViewById(R.id.sub_tabs);// not nto change the order.
        setupViewPager();
        subTabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        if(subTabLayout != null) {
            TabLayout.Tab defaultTab = subTabLayout.getTabAt(0);
            if (defaultTab != null) {
                View viewdef = defaultTab.getCustomView();
                if (viewdef != null) {
                    viewdef.setSelected(true);
                } else {
                    Log.d("Default Tab not set");
                }
            }
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("22222TabMain...PageChange Selected.. "+position);
                subTabLayout.getTabAt(position).select();


                }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        subTabLayout.setFocusable(true);
        subTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

               int pos = tab.getPosition();
               Log.d("2222222sub Tab selection position..."+pos);
               // viewPager.setCurrentItem(pos);
             //   Log.d("2222222subviewPager.getVisibility().."+viewPager.getVisibility());
               // viewPager.getVisibility();
                //subTabLayout.setFocusable(true);
               // subTabLayout.setSelected(true);
               //     tab.select();
              //  subTabLayout.setScrollPosition(pos, 0f, true);

               // FragmentUtils.getInstance().setSubTabPosition(tab.getPosition());
                //setupViewPager(viewPager);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                Log.d("22222sub Tab UNselection position..."+pos);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//its called when both current tab index and selected index
                int pos = tab.getPosition();
                Log.d("sub Tab Re selection position..."+pos);
            }
        });

Log.d("TabMainFragment onCreateView ends");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(subTabLayout != null) {
            TabLayout.Tab defaultTab = subTabLayout.getTabAt(0);
            if (defaultTab != null) {
                View view = defaultTab.getCustomView();
                if (view != null) {
                    view.setSelected(true);
                } else {
                    Log.d("Default Tab not set");
                }
            }
        }
        if(viewPager != null){
            viewPager.setCurrentItem(0);
        }


    }

    private void setupViewPager() {
        Activity actv = getActivity();
        Objects.requireNonNull(actv,"Activity cant be null");
        Log.d("---------------------------------");
        Log.d("Pager setup Start");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager() );

        Tab tab = Global.getInstance().getAppsModel().getHome().getTabs().get(this.mainTabPosition);
        Log.d("   tab "+tab);
        if(savedState != null) {
           // myObject = (MyObject) savedState.getSerializable(SAVED_BUNDLE_TAG);
        }

        try {
            if(tab != null) {

                ArrayList<SubTab> subTabs = tab.getSubTabs();
                Log.d("222subTabs  "+subTabs);
                if (subTabs != null & subTabs.size() > 0) {
                    for (int i = 0; i < subTabs.size(); i++) {
                        SubTab stab = subTabs.get(i);

                        if (stab != null ) {
                            String title = "";
                            if(stab.getTitle() != null)
                                title =  stab.getTitle();
                            Log.d( "Added Sub Tab with title " + title);

                            Bundle bundle = new Bundle();
                            bundle.putInt(FragmentConstants.KEY_MAIN_TAB_POS,mainTabPosition);
                            bundle.putInt(FragmentConstants.KEY_SUB_TAB_POS,i);
            if(!stab.isSubSubTabExist()){

                Log.d(" SubSubTab not exist  getContext "+getContext() );
                DesignTable designTable = new DesignTable(getContext());
                String json = designTable.queryJson(stab.getPagerContent());

                Log.d(" Page Content "+json );
               // String pageContent = GsonUtils.toJson(stab.getPagerContent());
                bundle.putString(FragmentConstants.KEY_JSON_STRING,json);
                WaveLayoutFragment   waveSubtab  = new  WaveLayoutFragment( )  ;
                waveSubtab.setArguments(bundle);
                adapter.addFragment(waveSubtab, title);
            }else{
                SubSubTabFragment subtab  =   new SubSubTabFragment();
                bundle.putString(FragmentConstants.KEY_JSON_STRING,"");
                subtab.setArguments(bundle);
                adapter.addFragment(subtab, title);
            }

          Log.d("2222SubTab added: "+this.mainTabPosition + " "+i);



                        }
                    }
                }
            }
        }catch ( Resources.NotFoundException e){
            // nothing doing. Its away of exit.
            Log.d("Resource Not found Exception : "+e.toString());
            e.printStackTrace();
        }


//        adapter.addFragment(new HomeForYouFragment(), "For You");
//        adapter.addFragment(new HomeTopChartsFragment(), "Top Charts");
//        adapter.addFragment(new HomeCategoriesFragment(), "Categories");
//        adapter.addFragment(new HomeForYouFragment(), "Genres");
//        adapter.addFragment(new HomeTopChartsFragment(), "Editor's Choice");
        Log.d("Pager setup end");
        Log.d("---------------------------------");
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        //Tab tab = Global.getInstance().getAppsModel().getHome().getTabs().get(this.position);
        ArrayList<SubTab> subTabs = Global.getInstance().getAppsModel().getHome().getTabs().get(this.mainTabPosition).getSubTabs();

        if(subTabs != null)
        for (int i = 0; i < subTabs.size(); i++) {
            SubTab stab = subTabs.get(i);
            //stab.getDrawable to be added
            Log.d("Tab Icons setup ..."+stab.getTitle());
            TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_sub_tab, null);
           tabOne.setText(stab.getTitle());
            Drawable icon = Global.getInstance().resize(getResources(), getResources().getDrawable(R.drawable.ic_launcher), 40,40);

            tabOne.setCompoundDrawablesWithIntrinsicBounds(null,icon, null, null);
           // tabOne.setBackgroundColor(R.color.colorGray);
           // tabOne.setText(R.color.textPrimary);

            Log.d(" tabone ");
            Objects.requireNonNull(subTabLayout.getTabAt(i)).setCustomView(tabOne);
        }
//        TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_sub_tab, null);
//       tabOne.setText(R.string.tab_one);
//        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_explorer, 0, 0);
//       Log.d(" tabone ");
//        Objects.requireNonNull(subTabLayout.getTabAt(0)).setCustomView(tabOne);
//
//        TextView tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_sub_tab, null);
//        //tabTwo.setText(R.string.tab_two);
//        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_star, 0, 0);
//        Log.d(" tabTwo ");
//        Objects.requireNonNull(subTabLayout.getTabAt(1)).setCustomView(tabTwo);
//
//        TextView tabThree = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_sub_tab, null);
//      //  tabThree.setText(R.string.tab_three);
//        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_category, 0, 0);
//        Log.d(" tabThree ");
//        Objects.requireNonNull(subTabLayout.getTabAt(2)).setCustomView(tabThree);
//
//        TextView tabFour = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_sub_tab, null);
//       // tabFour.setText(R.string.tab_four);
//        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_circle_star, 0, 0);
//        Log.d(" tabFour ");
//        Objects.requireNonNull(subTabLayout.getTabAt(3)).setCustomView(tabFour);
//
//        TextView tabFive = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_sub_tab, null);
//       // tabFive.setText(R.string.tab_five);
//        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_verified, 0, 0);
//        Log.d(" tabFive ");
//        Objects.requireNonNull(subTabLayout.getTabAt(4)).setCustomView(tabFive);
    }
}
