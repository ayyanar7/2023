package com.ag.bta.main.models.home;

import java.util.ArrayList;

public class SubSubTab  {
    String title = "";

    String drawable = "";
   //private boolean

    ArrayList<SubSubTab> options = null;
    private boolean isSubSubSubTabExist = false;
    private String pagerContentJsonId = null;

    public ArrayList<SubSubTab> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<SubSubTab> options) {
        this.options = options;
    }

    public boolean isSubSubTabExist() {
        return isSubSubSubTabExist;
    }

    public void setSubSubSubTabExist(boolean subSubTabExist) {
        isSubSubSubTabExist = subSubTabExist;
    }

    public String getPagerContent() {
        return pagerContentJsonId;
    }

    public void setPagerContent(String pagerContent) {
        this.pagerContentJsonId = pagerContent;
    }

    public SubSubTab(){
    super( );
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDrawable() {
        return drawable;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }


}
