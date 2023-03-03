package com.ag.bta.ui.treeview.viewbinder;

import android.view.View;
import android.widget.TextView;

import com.ag.bta.main.R;
import com.ag.bta.ui.treeview.bean.File;
import com.ag.bta.ui.treeview.treeviewlib.TreeNode;
import com.ag.bta.ui.treeview.treeviewlib.TreeViewBinder;



public class FileNodeBinder extends TreeViewBinder<FileNodeBinder.ViewHolder> {
    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void bindView(ViewHolder holder, int position, TreeNode node) {
        File fileNode = (File) node.getContent();
        holder.tvName.setText(fileNode.fileName);
    }

    @Override
    public int getLayoutId() {
        return R.layout.treeview_item_file;
    }

    public class ViewHolder extends TreeViewBinder.ViewHolder {
        public TextView tvName;

        public ViewHolder(View rootView) {
            super(rootView);
            this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
        }

    }
}
