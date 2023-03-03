package com.ag.bta.main.models.home;

import java.util.ArrayList;

public class NavBar {
    int drawable_logo = -1;

    public int getDrawable_logo() {
        return drawable_logo;
    }

    public void setDrawable_logo(int drawable_logo) {
        this.drawable_logo = drawable_logo;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public ArrayList<NavDrawerSection> getSectionList() {
        return sectionList;
    }

    public void setSectionList(ArrayList<NavDrawerSection> sectionList) {
        this.sectionList = sectionList;
    }

    String businessName = "";
    String tagLine = "";
    ArrayList<NavDrawerSection> sectionList = null;
    public NavBar (){
        sectionList =  new ArrayList<NavDrawerSection>();
    }

    public void addSection(NavDrawerSection sec){
        sectionList.add(sec);
    }


}
