package com.ag.bta.ui.treeview.bean;

import com.ag.bta.main.R;
import com.ag.bta.ui.treeview.treeviewlib.LayoutItemType;



public class File implements LayoutItemType {
    public String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int getLayoutId() {
        return R.layout.treeview_item_file;
    }
}
