package com.ag.bta.main.models;


import com.ag.bta.utils.DateUtils;

public class Login {

    private String mAccountName;
    private String mPass;
    private String mDescription;
    private String memail;


    private String mlogindate;
    private String mlogintime;
    public Login(String accountName, String pass, String description, String email) {
        mAccountName = accountName;
        mPass = pass;
        mDescription = description;
        memail = email;
        mlogindate = DateUtils.getDate();
         mlogintime = DateUtils.getTime();
    }



    public String getmAccountName() {
        return mAccountName;
    }

    public void setmAccountName(String mAccountName) {
        this.mAccountName = mAccountName;
    }

    public String getmPass() {
        return mPass;
    }

    public void setmPass(String mPass) {
        this.mPass = mPass;
    }


    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmLink() {
        return memail;
    }

    public void setmLink(String mLink) {
        this.memail= mLink;
    }
    public void setMlogindate(String mlogindate) {
        this.mlogindate = mlogindate;
    }

    public void setMlogintime(String mlogintime) {
        this.mlogintime = mlogintime;
    }

    public String getMlogindate() {
        return mlogindate;
    }

    public String getMlogintime() {
        return mlogintime;
    }
}