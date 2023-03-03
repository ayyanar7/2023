package com.ag.bta.main.models.home;

import java.io.Serializable;
import java.util.ArrayList;

public class Tab {
    String title = "";
    String drawable = "";
    ArrayList<SubTab> options = null;
    private boolean issubTabExist = false;
    public Tab(){
        options = new ArrayList<SubTab>();
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public ArrayList<SubTab> getSubTabs() {
        return options;
    }

    public void setSubTabs(ArrayList<SubTab> options) {
        if(options != null &&  options.size() >0){
            issubTabExist = true;
        }
        this.options = options;
    }

    public void add(SubTab stab){

        if(stab != null){
            issubTabExist = true;
        }
        options.add(stab);
    }

    public void setDrawable(String argdrawable) {
        drawable = argdrawable;
    }
    public String getDrawables() {
        return drawable;
    }

    public boolean isIssubTabExist() {
        return issubTabExist;
    }

    public void setIssubTabExist(boolean issubTabExist) {
        this.issubTabExist = issubTabExist;
    }

}
