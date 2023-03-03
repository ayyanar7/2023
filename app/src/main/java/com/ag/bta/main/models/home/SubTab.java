package com.ag.bta.main.models.home;

import java.util.ArrayList;

public class SubTab  {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String title = "";
    String drawable = "";
    ArrayList <SubSubTab> options = null;
     private boolean isSubSubTabExist = false;
     private String pagerContentJsonId = null;
     public  SubTab(){

    }

    public String getPagerContent() {
        return pagerContentJsonId;
    }

    public void setPagerContent(String pagerContent) {
        this.pagerContentJsonId = pagerContent;
    }



    public void add(SubSubTab sstab){
        if(sstab != null){
            isSubSubTabExist = true;
        }
         options.add(sstab);
    }

    public ArrayList<SubSubTab> getSubSubTab() {
        return options;
    }

    public void setSubSubTab(ArrayList<SubSubTab> options) {
        if(options != null &&  options.size() >0){
            isSubSubTabExist = true;
        }
         this.options = options;
    }

    public void setDrawables(String title) {
        drawable = title;
    }

    public String getDrawable() {
       return drawable ;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }

    public ArrayList<SubSubTab> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<SubSubTab> options) {
        this.options = options;
    }

    public boolean isSubSubTabExist() {



         return isSubSubTabExist;
    }

    public void setSubSubTabExist(boolean subSubTabExist) {
         if(subSubTabExist)
    options = new ArrayList<SubSubTab>();
        isSubSubTabExist = subSubTabExist;
    }
}
