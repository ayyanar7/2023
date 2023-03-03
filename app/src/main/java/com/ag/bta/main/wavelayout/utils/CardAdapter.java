package com.ag.bta.main.wavelayout.utils;

import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.util.Log;
import android.view.ViewGroup;


import com.ag.bta.main.wavelayout.models.ItemCard;
import com.ag.bta.main.wavelayout.view.ItemHolder;
import java.util.ArrayList;
import java.util.List;

 public class CardAdapter extends Adapter<ItemHolder> {

     private List<ItemCard> mCards = new ArrayList();
     private int mType = -1;
    // private OnItemClickListener listener ;
     public interface OnItemClickListener {
         void onItemClick(ItemCard item);
     }


     @Override
     public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         return ItemHolder.newInstance(parent, mType);
     }

     @Override
     public void onBindViewHolder(ItemHolder holder, int position) {
         Log.d("","mType: "+mType);
         holder.bind(mCards.get(position), mType,position);
         Log.d("","item position set: "+position);
          holder.itemView.setTag(position);

     }

     @Override
     public int getItemCount() {
         return mCards.size();
     }

     public void setCards(List<ItemCard> cards) {
         if (cards == null) {
             return;
         }
          mCards = cards;
     }

     public void setType(int type) {
         this.mType = type;
     }


 }
