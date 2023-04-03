package com.ag.bta.ui.treeview.bean;

import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;

import com.ag.bta.main.R;
import com.ag.bta.ui.treeview.treeviewlib.LayoutItemType;



public class Dir implements LayoutItemType {
    public String dirName;
    @DrawableRes
    public int  iconRes;
    public boolean disableArrow = false;
    public Dir(String dirName, @DrawableRes int iconRes, boolean disableArrow) {
        this.dirName = dirName;
         this.iconRes =  iconRes;
         this.disableArrow = disableArrow;
    }

    @Override
    public int getLayoutId() {
        return R.layout.treeview_item_dir;
    }
}
