package com.ag.bta.main.wavelayout.view.innerwaverecycler;

import android.util.Log;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.ag.bta.main.R;
import com.ag.bta.main.wavelayout.models.ItemCard;
import com.ag.bta.main.wavelayout.utils.ModelConfiguration;
import com.ag.bta.main.wavelayout.view.WaveLayoutRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InnerRecyclerAdapter extends Adapter<RecyclerHolder> {

    private List<WaveLayoutRecyclerView> wRecyclerlist = new ArrayList();
    private int mType = -1;
   // private OnItemClickListener listener ;
    public interface OnItemClickListener {
        void onItemClick(ItemCard item);
    }


    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent,  int viewType) {
       // if(viewType)

        ModelConfiguration innerconfig = new ModelConfiguration();
        innerconfig.setStyleResource(R.style.ListTheme);
        innerconfig.setLayoutResource(R.layout.wavelayout_list_recyclerview);
        innerconfig.setLayoutManager(new LinearLayoutManager(parent.getContext()));
        // innerconfig.setTitleResource(title);
        return RecyclerHolder.newInstance(parent,innerconfig, mType);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder,  int position) {
        Log.d("","mType: "+mType);
        holder.bind(wRecyclerlist.get(position),  position);
        Log.d("","item position set: "+position);
         //holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return wRecyclerlist.size();
    }

    public void setCards(List<WaveLayoutRecyclerView> cards) {
        if (cards == null) {
            return;
        }
        wRecyclerlist = cards;
    }

    public void setType(int type) {
        this.mType = type;
    }


}
