package com.ag.bta.main.wavelayout.models;

public class ItemCard {

    private String mTitle;
    private String mDescription;
    private String mThumbnailUrl;
    private String mSummaryText;
    int drawableMain = -1;
    int drawableoptions = -1;
    private String subTitle ="";
    private int type = 0; //special , normal
    String actiontext ="";
    String numerictext = "";
    String numerictext2 = "";

    public String getNumerictext2() {
        return numerictext2;
    }

    public void setNumerictext2(String numerictext2) {
        this.numerictext2 = numerictext2;
    }

    public ItemCard(){

    }
    public ItemCard(String title, String desc, int type){
        mTitle = title;
        mDescription = desc;
        this.type = type;
    }
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public void setThumbnailUrl(String mThumbnailUrl) {
        this.mThumbnailUrl = mThumbnailUrl;
    }

    public String getSummaryText() {
        return mSummaryText;
    }

    public void setSummaryText(String mSummaryText) {
        this.mSummaryText = mSummaryText;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmThumbnailUrl() {
        return mThumbnailUrl;
    }

    public void setmThumbnailUrl(String mThumbnailUrl) {
        this.mThumbnailUrl = mThumbnailUrl;
    }

    public String getmSummaryText() {
        return mSummaryText;
    }

    public void setmSummaryText(String mSummaryText) {
        this.mSummaryText = mSummaryText;
    }


    public int getDrawableMain() {
        return drawableMain;
    }


    public void setDrawableMain(int drawableMain) {
        this.drawableMain = drawableMain;
    }


    public int getDrawableoptions() {
        return drawableoptions;
    }


    public void setDrawableoptions(int drawableoptions) {
        this.drawableoptions = drawableoptions;
    }


    public String getSubTitle() {
        return subTitle;
    }


    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }


    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }


    public String getActiontext() {
        return actiontext;
    }


    public void setActiontext(String actiontext) {
        this.actiontext = actiontext;
    }


    public String getNumerictext() {
        return numerictext;
    }


    public void setNumerictext(String numerictext) {
        this.numerictext = numerictext;
    }
}
