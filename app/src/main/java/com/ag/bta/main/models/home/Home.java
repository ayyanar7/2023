package com.ag.bta.main.models.home;

import com.ag.bta.utils.Log;

import java.util.ArrayList;

public class Home {
   NavBar navbar = null;

 BottomBar bottomBar = null;
 ArrayList <Tab> tabs = null;
   public Home(){
        navbar = new NavBar();
       tabs =  new ArrayList<Tab>();
        bottomBar = new BottomBar();
   }

    public NavBar getNavbar() {
        return navbar;
    }

    public void setNavbar(NavBar navbar) {
        this.navbar = navbar;
    }



    public void addTab(Tab tab){
        tabs.add(tab);
    }
    public void setMainTab(ArrayList<Tab> tabs) {
        this.tabs = tabs;
    }

    public BottomBar getBottomBar() {
        return bottomBar;
    }

    public void setBottomBar(BottomBar bottomBar) {
        this.bottomBar = bottomBar;
    }

    public ArrayList<Tab> getTabs() {
        Log.d("get Tabs"+tabs);
        Log.d(tabs);
        return tabs;
    }

    public void setTabs(ArrayList<Tab> tabs) {
        this.tabs = tabs;
    }
}
