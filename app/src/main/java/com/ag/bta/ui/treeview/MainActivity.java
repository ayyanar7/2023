package com.ag.bta.ui.treeview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ag.bta.ui.treeview.bean.Dir;
import com.ag.bta.ui.treeview.bean.File;
import com.ag.bta.ui.treeview.viewbinder.DirectoryNodeBinder;
import com.ag.bta.ui.treeview.viewbinder.FileNodeBinder;

import com.ag.bta.main.R;
import com.ag.bta.ui.treeview.treeviewlib.TreeNode;
import com.ag.bta.ui.treeview.treeviewlib.TreeViewAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private TreeViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.treeview_activity_main);
        initView();
        initData();
    }

    private void initData() {
        @DrawableRes
        int dirIcon =   R.drawable.about_ads;
        @DrawableRes
        int fileIcon =   R.drawable.about_help;

        List<TreeNode> nodes = new ArrayList<>();
        TreeNode<Dir> about = new TreeNode<>(new Dir("About", fileIcon, true)).lock();
        nodes.add(about);
        TreeNode<Dir> settings = new TreeNode<>(new Dir("Settings", fileIcon, false));
        nodes.add(settings);
        settings.addChild( new TreeNode<>(new File("General Settings", fileIcon)) );
        settings.addChild( new TreeNode<>(new File("Application Settings", fileIcon)) );

        TreeNode<Dir> configuration = new TreeNode<>(new Dir("Configuration", fileIcon, false));
        nodes.add(configuration);
        configuration.addChild(new TreeNode<>(new File("Add Product", fileIcon)))
                .addChild(new TreeNode<>(new File("Delete Product", fileIcon)))
                .addChild(new TreeNode<>(new File("View", fileIcon)));

        TreeNode<Dir> pref = new TreeNode<>(new Dir("Prefrences", fileIcon, true)).lock();
        nodes.add(pref);

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TreeViewAdapter(nodes, Arrays.asList(new FileNodeBinder(), new DirectoryNodeBinder()));
        // whether collapse child nodes when their parent node was close.
//        adapter.ifCollapseChildWhileCollapseParent(true);
        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener() {
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                if (!node.isLeaf()) {
                    //Update and toggle the node.
                    onToggle(!node.isExpand(), holder);
//                    if (!node.isExpand())
//                        adapter.collapseBrotherNode(node);
                }
                return false;
            }

            @Override
            public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {
                DirectoryNodeBinder.ViewHolder dirViewHolder = (DirectoryNodeBinder.ViewHolder) holder;
                final ImageView ivArrow = dirViewHolder.getIvArrow();
                int rotateDegree = isExpand ? 90 : -90;
                ivArrow.animate().rotationBy(rotateDegree)
                        .start();
            }
        });
        rv.setAdapter(adapter);
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.treeview_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.id_action_close_all:
                adapter.collapseAll();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
