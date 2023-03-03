package com.ag.bta.main.models;

import com.ag.bta.main.models.home.Home;

public class ApplicationModel {
    String title =null;
    Admin admin = null;
    Home home = null;
    public ApplicationModel(){
          admin = new Admin();
          home = new Home();
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
