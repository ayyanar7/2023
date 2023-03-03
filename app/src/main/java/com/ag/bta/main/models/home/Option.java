package com.ag.bta.main.models.home;

public class Option {
    String title = "";
    int drawableIcon = -1;
    public Option(String argTitle, int argDrawable){
        String title = argTitle;
        int drawableIcon = argDrawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDrawableIcon() {
        return drawableIcon;
    }

    public void setDrawableIcon(int drawableIcon) {
        this.drawableIcon = drawableIcon;
    }
}
