package com.ag.bta.ui.treeview.bean;

import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;

import com.ag.bta.main.R;
import com.ag.bta.ui.treeview.treeviewlib.LayoutItemType;



public class File implements LayoutItemType {
    public String fileName;
@DrawableRes
    public int iconRes;
    public File(String fileName,  @DrawableRes int iconRes) {
        this.fileName = fileName;
        this.iconRes =  iconRes;
    }

    @Override
    public int getLayoutId() {
        return R.layout.treeview_item_file;
    }
}
