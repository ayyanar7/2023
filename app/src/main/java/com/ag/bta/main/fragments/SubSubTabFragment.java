package com.ag.bta.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.ag.bta.utils.constant.FragmentConstants;
import com.ag.bta.utils.constant.Global;
import com.ag.bta.main.R;
import com.ag.bta.main.adapters.ViewPagerAdapter;
import com.ag.bta.main.viewPager.CustomViewPager;
import com.ag.bta.main.models.home.SubSubTab;
import com.ag.bta.main.models.home.SubTab;
import com.ag.bta.main.models.home.Tab;
import com.ag.bta.utils.Log;
import com.ag.bta.main.wavelayout.sample.WaveLayoutFragment;
import com.ag.bta.utils.database.sqlite.DesignTable;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SubSubTabFragment extends Fragment {
    private int subposition = -1;
    private int mainTabposition = -1;
    CustomViewPager viewPager = null;
    TabLayout subsubTabLayout = null;
    public SubSubTabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SUB TAB DESTROYED....."+this.mainTabposition+" "+this.subposition+" ");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate ********* SubSubTab Fragment ....");

    }
    @Override
    public void onResume() {
        super.onResume();
        if(subsubTabLayout != null) {
            TabLayout.Tab defaultTab = subsubTabLayout.getTabAt(0);
            if (defaultTab != null) {
                View viewdef = defaultTab.getCustomView();
                if (viewdef != null) {
                    viewdef.setSelected(true);
                } else {
                    Log.d("Default Tab not set");
                }
            }
        }
        if(viewPager != null){
            viewPager.setCurrentItem(0);
        }

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("onCreateView SubSubTab Fragment ....");
        Bundle bundle =  this.getArguments();
        if(bundle != null){
            Log.d("Bundle is not null ....");
            this.mainTabposition = bundle.getInt(FragmentConstants.KEY_MAIN_TAB_POS);
            this.subposition = bundle.getInt(FragmentConstants.KEY_SUB_TAB_POS);

        }else{
            Log.d("Bundle is null ....");
        }
        Log.d("SubSubTabFragment Inside create view  : " +mainTabposition+" "+ this.subposition);


        // String layoutName = Global.getInstance().getLayoutName(mainTabposition);
        View view = null;

        switch(subposition){
            case 0:
                view =  loadFragmentWithPager( inflater,  container);
                break;
            case 1:
                view =  loadFragmentWithPager( inflater,  container);
                break;

        }
        Log.d("SubSubTabFragment   view created : "+view);
        if(subsubTabLayout != null) {
            TabLayout.Tab defaultTab = subsubTabLayout.getTabAt(subposition);
            if (defaultTab != null) {
                View viewdef = defaultTab.getCustomView();
                if (viewdef != null) {
                    viewdef.setSelected(true);
                } else {
                    Log.d("Default Tab not set");
                }
            }
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    private View loadFragmentWithPager(LayoutInflater inflater, ViewGroup container){
        String layoutName = "fragment_subtab_0";
        int layoutId = Global.getInstance().getLayout(layoutName);
        Log.d("loadFragmentWithPager: ");
        // Inflate the layout for this fragment
        // View view = inflater.inflate(R.layout.fragment_top_charts, container, false);
        View view = inflater.inflate(layoutId, container, false);
          viewPager = view.findViewById(R.id.vp_app_list);
        setupViewPager();
        Log.d("loadFragmentWithPager2: ");
          subsubTabLayout = view.findViewById(R.id.tl_app_list);
        subsubTabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(subposition);
        viewPager.addOnPageChangeListener(new  ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        subsubTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("Sub Sub Tab .. onTabSelected: "+tab.getPosition());
               // FragmentUtils.getInstance().setSubSubTabPosition(tab.getPosition());
                int pos = tab.getPosition();
                Log.d("333333sub sub Tab selection position..."+pos);
                viewPager.setCurrentItem(pos);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("Sub Sub Tab .. onTabUnselected: "+tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("Sub Sub Tab .. onTabReselected: "+tab.getPosition());
            }
        });
        Log.d("loadFragmentWithPager3: ");
        return view;
    }

    private View loadFragmentRecyclerView(LayoutInflater inflater, ViewGroup container){
        String layoutName = "fragment_subtab_1";
        int layoutId = Global.getInstance().getLayout(layoutName);
        Log.d("loadFragmentRecyclerView: ");
      View  view = inflater.inflate(layoutId, container, false);
      RecyclerView    mainRecyclerView = view.findViewById(R.id.for_you_recycler_view);
        mainRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mainRecyclerView.setLayoutManager(layoutManager);
        Log.d("loadFragmentRecyclerView2: ");
        loadItemsinRecycler(mainRecyclerView);
        Log.d("loadFragmentRecyclerView3: ");
        return view;
    }
    private void  loadItemsinRecycler(RecyclerView mainRecyclerView){

       // ArrayList<HomeItemModel> mArrayList = new ArrayList<>();
        Log.d("loadItemsinRecycler: ");
        Tab tab = Global.getInstance().getAppsModel().getHome().getTabs().get(this.mainTabposition);

        if (tab != null) {
            SubTab subtab = tab.getSubTabs().get(this.subposition);
            if(subtab != null){
               // ArrayList<SectionBar>  sections = subtab.getSectionlist();
//                if(sections != null){
//                    if (getActivity() != null) {
//                    for(int i=0; sections.size() >0;i++){
//                        SectionBar secbar = sections.get(i);
//                       // ArrayList<TypeOItem> mArrayList = secbar.getItems0list();
//
//                           // ItemAdapter itemAdapter = new ItemAdapter(mArrayList, getContext());
//                         //   mainRecyclerView.setAdapter(itemAdapter);
//                        }
//                    }
//                }else{
//                    Log.d("List is null");
//                }
            }
        }
//        if (getActivity() != null) {
//            mArrayList.add(new HomeItemModel("", "", "special"));
//            mArrayList.add(new HomeItemModel("New + Updated Games", "Selected games of the week", "normal"));
//            mArrayList.add(new HomeItemModel("Recommended for you", "", "normal"));
//            mArrayList.add(new HomeItemModel("Get Stuff Done", "", "normal"));
//        }
//
//        HomeItemAdapter homeItemAdapter = new HomeItemAdapter(mArrayList, getContext());
//        mainRecyclerView.setAdapter(homeItemAdapter);
    }
    private void setupViewPager() {

        // ViewPagerAdapter adapter = new ViewPagerAdapter(requireActivity().getSupportFragmentManager());
//        Activity actv = getActivity();
//        Objects.requireNonNull(actv,"Fragment Activity cant be null");

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

Log.d("*********************");
        Log.d("Set View Pager");
        Tab tab = Global.getInstance().getAppsModel().getHome().getTabs().get(this.mainTabposition);

        if (tab != null) {
            SubTab subtab = tab.getSubTabs().get(this.subposition);

            if (subtab != null) {
                ArrayList<SubSubTab> subsubTabs = subtab.getSubSubTab();

                if (subsubTabs != null & subsubTabs.size() > 0) {
                    for (int i = 0; i < subsubTabs.size(); i++) {
                        SubSubTab sstab = subsubTabs.get(i);
                        if (sstab != null) {
                            String title = "";
                            if (sstab.getTitle() != null)
                                title = sstab.getTitle();
                            Log.d("Added Tab with title " + title);
                            Bundle bundle = new Bundle();
                            bundle.putInt(FragmentConstants.KEY_MAIN_TAB_POS,mainTabposition);
                            bundle.putInt(FragmentConstants.KEY_SUB_TAB_POS,subposition);
                            bundle.putInt(FragmentConstants.KEY_SUB_SUB_TAB_POS,i);
//                            if(i!=0) {
//                                ContainerFragment ssfrag =    new ContainerFragment();
//                                ssfrag.setArguments(bundle);
//                                adapter.addFragment(ssfrag, title);
//                            }else{
                            //String pageContent = GsonUtils.toJson(sstab.getPagerContent());


                            Log.d(" SubSubTab not exist  getContext "+getContext() );
                            DesignTable designTable = new DesignTable(getContext());
                            String json = designTable.queryJson(sstab.getPagerContent());
                            Log.d(" page content "+json );

                            bundle.putString(FragmentConstants.KEY_JSON_STRING,json);
                                WaveLayoutFragment wavefrag=   new WaveLayoutFragment();

                                wavefrag.setArguments(bundle);

                                adapter.addFragment(wavefrag, title);
                            //}
                            Log.d("SubSubTab added: " +this.mainTabposition+"  "+ this.subposition + " " + i);
                        }
                    }
                }


            }else{
                Log.d("Sub Tab is null @ position "+this.subposition);
            }
        }else{
            Log.d("Main Tab is null @ position "+this.mainTabposition);
        }
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);
        Log.d("Set View Pager End");
        Log.d("*********************");

//            adapter.addFragment(new TopFreeAppsFragment(), "TOP FREE APPS");
//        adapter.addFragment(new TopFreeAppsFragment(), "TOP FREE GAMES");
//        adapter.addFragment(new TopFreeAppsFragment(), "TOP GROSSING");
//        adapter.addFragment(new TopFreeAppsFragment(), "TRENDING");


    }


}
