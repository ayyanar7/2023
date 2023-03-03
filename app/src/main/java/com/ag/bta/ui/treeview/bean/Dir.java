package com.ag.bta.ui.treeview.bean;

import com.ag.bta.main.R;
import com.ag.bta.ui.treeview.treeviewlib.LayoutItemType;



public class Dir implements LayoutItemType {
    public String dirName;

    public Dir(String dirName) {
        this.dirName = dirName;
    }

    @Override
    public int getLayoutId() {
        return R.layout.treeview_item_dir;
    }
}
