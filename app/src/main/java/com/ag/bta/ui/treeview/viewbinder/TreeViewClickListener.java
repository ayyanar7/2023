package com.ag.bta.ui.treeview.viewbinder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ag.bta.main.R;
import com.ag.bta.ui.treeview.bean.Dir;
import com.ag.bta.ui.treeview.treeviewlib.TreeNode;
import com.ag.bta.ui.treeview.treeviewlib.TreeViewBinder;
import com.ag.bta.utils.Log;


public class TreeViewClickListener     {

    public void dirItemClick(View view){
        Log.d("========== Dir TreeViewItemClick : "+view);
    }

    public void fileItemClick(View view){
        Log.d("==========File TreeViewItemClick : "+view);
    }
}
