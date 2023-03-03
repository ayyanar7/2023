package com.ag.bta.main.models.home;

import java.util.ArrayList;

public class BottomBar {
    ArrayList<Option> bottomList = null;
    public BottomBar (){
        bottomList =  new ArrayList<Option>();
    }

    public void addOption(Option option){
        bottomList.add(option);
    }

    public ArrayList<Option> getBottomList() {
        return bottomList;
    }

    public void setBottomList(ArrayList<Option> bottomList) {
        this.bottomList = bottomList;
    }
}
