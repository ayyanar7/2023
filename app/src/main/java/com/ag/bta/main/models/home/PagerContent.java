package com.ag.bta.main.models.home;

import java.util.ArrayList;

public class PagerContent {
    ArrayList< SectionBar> sectionlist = null;

private int pageViewType = 0; // list , grit view
    public PagerContent(){

        sectionlist = new ArrayList<SectionBar>();

 }

    public int getPageType() {
        return pageViewType;
    }

    public void setPageType(int pageType) {
        this.pageViewType = pageType;
    }

    public ArrayList<SectionBar> getSectionlist() {
        return sectionlist;
    }

    public void setSectionlist(ArrayList<SectionBar> sectionlist) {
        this.sectionlist = sectionlist;
    }
    public void addSection(SectionBar sec){

        sectionlist.add(sec) ;
    }
}
