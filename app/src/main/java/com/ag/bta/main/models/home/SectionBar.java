package com.ag.bta.main.models.home;

import com.ag.bta.main.wavelayout.models.ItemCard;

import java.util.ArrayList;

public class SectionBar {
    private int itemsType =-1; // 0- Otype  1 for 0ne type,
    ArrayList<ItemCard> itemslist = null;
    public SectionBar(){


    }

    public int getItemsType() {
        return itemsType;
    }

    public void setItemsType(int itemsType) {
        this.itemsType = itemsType;

        }




    public void addItem(ItemCard item){
        itemslist.add(item) ;
    }




    public ArrayList<ItemCard> getItemslist() {
        return itemslist;
    }


    public void setItemslist(ArrayList<ItemCard> itemslist) {
        this.itemslist = itemslist;
    }

}
