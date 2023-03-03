package com.ag.bta.main.fragments;

import android.content.res.Resources;

import com.ag.bta.constants.Global;
import com.ag.bta.main.models.home.SubSubTab;
import com.ag.bta.main.models.home.SubTab;
import com.ag.bta.main.wavelayout.models.ItemCard;
import com.ag.bta.utils.Log;

import java.util.ArrayList;
import java.util.List;

public class FragmentUtils {

//    private int mainTabPosition =0;
//    private int subTabPosition =0;
//    private int subSubTabPosition =-1;
private static FragmentUtils fragUtils = null;
private ArrayList<ItemCard> argItemlist = null;
private FragmentUtils(){

}
public static FragmentUtils getInstance(){
    if(fragUtils == null){
        fragUtils = new FragmentUtils();
    }
    return fragUtils;
}

//    private ArrayList<ItemCard> itemlist =  null;
//    public  void setCards(ArrayList<ItemCard> argItemlist) {
//        itemlist = argItemlist;
//    }
//    public   List<ItemCard> getCards() {
//
//        return itemlist;
//    }


    public SubTab getSubFragment(int mainPosition, int subPosition){

        return Global.getInstance().getAppsModel().getHome().getTabs().get(mainPosition).getSubTabs().get(subPosition);
    }
    public SubSubTab getSubSubFragment(int mainPosition, int subPosition, int subsubPosition){
        //Global.getInstance().getAppsModel().getHome().getTabs().get(mainPosition).getSubTabs().
        return Global.getInstance().getAppsModel().getHome().getTabs().get(mainPosition).getSubTabs().get(subPosition).getSubSubTab().get(subsubPosition);

    }
    public void getFragment(int mainPosition, int subPosition, int subsubPosition){
        switch(mainPosition){
            case 0:
                break;
            case 1:
                break ;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                break;

        }
    }
}
