package com.ag.bta.main.wavelayout.sample;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ag.bta.constants.FragmentConstants;
import com.ag.bta.main.R;
import com.ag.bta.main.models.home.PagerContent;
import com.ag.bta.main.models.home.SectionBar;
import com.ag.bta.main.wavelayout.utils.BaseUtils;
import com.ag.bta.main.wavelayout.view.innerwaverecycler.InnerRecyclerAdapter;
import com.ag.bta.main.wavelayout.utils.ModelConfiguration;
import com.ag.bta.main.wavelayout.view.WaveLayoutRecyclerView;
import com.ag.bta.utils.GsonUtils;
import com.ag.bta.utils.Log;

import java.util.ArrayList;

public class WaveLayoutInnerRecyclerFragment extends Fragment implements AdapterView.OnItemClickListener {

    private int subSubposition = -1;
    private int mainTabposition = -1;
    private int subposition = -1;

    private   int layoutType = 0;

    private WaveLayoutRecyclerView WaveRecycler;
    private InnerRecyclerAdapter mAdapter;
    ArrayList<WaveLayoutRecyclerView> rlist = null;
    ArrayList<SectionBar> secList =  null;
   public WaveLayoutInnerRecyclerFragment(Context context){
       rlist = new ArrayList<WaveLayoutRecyclerView>();
       //new ItemCard(  );
       rlist.add(new WaveLayoutRecyclerView(context));
       rlist.add(new WaveLayoutRecyclerView(context));
       rlist.add(new WaveLayoutRecyclerView(context));
       rlist.add(new WaveLayoutRecyclerView(context));


      // rlist.add(new ItemCard("Title2 ","",0));
      // rlist.add(new ItemCard("Title3","",0));
  }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            Bundle bundle =  this.getArguments();
            PagerContent pageContent = null;
            Log.d("onCreateView. .WaveLayout..");
            if(bundle != null){
                this.mainTabposition = bundle.getInt(FragmentConstants.KEY_MAIN_TAB_POS);
                this.subposition = bundle.getInt(FragmentConstants.KEY_SUB_TAB_POS);
                this.subSubposition = bundle.getInt(FragmentConstants.KEY_SUB_SUB_TAB_POS);

                String pageString = bundle.getString(FragmentConstants.KEY_JSON_STRING);
                if(pageString != null && pageString.trim().length() >0) {
                    pageContent = GsonUtils.fromJson(pageString, PagerContent.class);
                    if (pageContent != null) {

                        Log.d("pgcontent.getPageType() " + pageContent.getPageType());
                        secList = pageContent.getSectionlist();
                        layoutType = pageContent.getPageType();

                        if (secList != null) {
                            for (SectionBar sec : secList) {
                              //  itemlist = sec.getItemslist();
                            }

                        } else {
                            Log.d("Section list is null..");
                        }
                    }
                }else{
                    Log.d("Page Content is Null @ Wavelayout");
                }
            }else{
                Log.d("Bundle is null .SubTabMainFragment...");
            }



            //final int type = getType();
            RecyclerView.LayoutManager layoutManager;
            Log.d("Activity ...is.."+getActivity());
            View view =null;
        layoutType = FragmentConstants.LAYOUT_NAVIGATION_DRAWER;
            final ModelConfiguration modelConfiguration = BaseUtils.getModelConfiguration(layoutType, getActivity(), R.string.nav_header_title);
            //setTheme(modelConfiguration.getStyleResource());
            int layoutId =  modelConfiguration.getLayoutResource();
            view = inflater.inflate(layoutId, container, false);
            layoutManager = modelConfiguration.getLayoutManager();
            // setTitle(modelConfiguration.getTitleResource());

            WaveRecycler = view.findViewById(R.id.wavelayout_outer_recycler);

            if (modelConfiguration.getItemDecoration() != null) {
                WaveRecycler.addItemDecoration(modelConfiguration.getItemDecoration());
            }


        mAdapter = new InnerRecyclerAdapter();
            mAdapter.setType(layoutType);

            WaveRecycler.setLayoutManager(layoutManager);
            WaveRecycler.setAdapter(mAdapter);
          WaveRecycler.showWaveAdapter();

            WaveRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadCards();
                }
            }, FragmentConstants.LOADING_DELAY_TIME);

            WaveRecycler.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    Toast.makeText(v.getContext(), "Wave recyler view clicked..", Toast.LENGTH_LONG).show();
                }

            });
            return view;
    }

    Context cnt ;
    public View createView (Context actContect, WaveLayoutRecyclerView argwaveRecycler) {
        cnt = actContect;
        WaveRecycler = argwaveRecycler;
        //final int type = getType();
        RecyclerView.LayoutManager layoutManager ;
       // Log.d("Activity ...is.."+getActivity());
        View view =null;
        final ModelConfiguration modelConfiguration = BaseUtils.getModelConfiguration(layoutType, actContect, R.string.nav_header_title);
       //setTheme(modelConfiguration.getStyleResource());
     // int layoutId =  modelConfiguration.getLayoutResource();
       //   view = inflater.inflate(layoutId, container, false);

        // setTitle(modelConfiguration.getTitleResource());



        if (modelConfiguration.getItemDecoration() != null) {
            WaveRecycler.addItemDecoration(modelConfiguration.getItemDecoration());
        }



        mAdapter = new InnerRecyclerAdapter(innerconfig);
        mAdapter.setType(layoutType);

        WaveRecycler.setAdapter(mAdapter);
        WaveRecycler.showWaveAdapter();

        WaveRecycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadCards();
            }
        }, FragmentConstants.LOADING_DELAY_TIME);

        WaveRecycler.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                Toast.makeText(v.getContext(), "Wave recyler view clicked..", Toast.LENGTH_LONG).show();
            }

        });

        return view;

        //return super.onCreateView(inflater, container, savedInstanceState);
    }



    private void loadCards() {

        mAdapter.setCards(rlist);
        WaveRecycler.hideWaveAdapter();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("On item click listerner .."+i);
        Toast.makeText(cnt,"Item click called.. "+i,Toast.LENGTH_SHORT);
    }
}
