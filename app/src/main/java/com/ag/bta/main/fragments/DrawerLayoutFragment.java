package com.ag.bta.main.fragments;

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
import androidx.recyclerview.widget.RecyclerView;

import com.ag.bta.constants.FragmentConstants;
import com.ag.bta.main.R;
import com.ag.bta.main.models.home.PagerContent;
import com.ag.bta.main.models.home.SectionBar;
import com.ag.bta.main.wavelayout.models.ItemCard;
import com.ag.bta.main.wavelayout.utils.BaseUtils;
import com.ag.bta.main.wavelayout.utils.CardAdapter;
import com.ag.bta.main.wavelayout.utils.ModelConfiguration;
import com.ag.bta.main.wavelayout.view.WaveLayoutRecyclerView;
import com.ag.bta.utils.GsonUtils;
import com.ag.bta.utils.Log;

import java.util.ArrayList;

public class DrawerLayoutFragment implements AdapterView.OnItemClickListener {


    private   int layoutType = 0;

    private WaveLayoutRecyclerView WaveRecycler;
    private CardAdapter mAdapter;
    ArrayList<ItemCard> itemlist = null;
    ArrayList<SectionBar> secList =  null;
   public DrawerLayoutFragment(){
       itemlist = new ArrayList<ItemCard>();
       itemlist.add(new ItemCard("Title1 ","",0));
       itemlist.add(new ItemCard("Title2 ","",0));
       itemlist.add(new ItemCard("Title3","",0));
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

        mAdapter = new CardAdapter();
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

        mAdapter.setCards(itemlist);
        WaveRecycler.hideWaveAdapter();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("On item click listerner .."+i);
        Toast.makeText(cnt,"Item click called.. "+i,Toast.LENGTH_SHORT);
    }
}
