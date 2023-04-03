package com.ag.bta.main.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ag.bta.main.R;
import com.ag.bta.main.fragments.AdminAboutFragment;
import com.ag.bta.ui.inventories.AddItemFragment;
import com.ag.bta.ui.inventories.DeleteItemsFragment;

import com.ag.bta.ui.treeview.bean.Dir;
import com.ag.bta.ui.treeview.bean.File;
import com.ag.bta.ui.treeview.treeviewlib.TreeNode;
import com.ag.bta.ui.treeview.treeviewlib.TreeViewAdapter;
import com.ag.bta.ui.treeview.viewbinder.DirectoryNodeBinder;
import com.ag.bta.ui.treeview.viewbinder.FileNodeBinder;
import com.ag.bta.utils.Log;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NavigationDrawerActivity extends SearchActivity
        {

    private DrawerLayout drawer;

    private Context context;
    private RecyclerView rv;
    private TreeViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // Toolbar toolbar = findViewById(R.id.toolbar);
       //setSupportActionBar(toolbar);

        context = NavigationDrawerActivity.this;
         drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,
                null,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

        de.hdodenhof.circleimageview.CircleImageView profileImageView = navigationView.getHeaderView(0).findViewById(R.id.profile_image);
        Glide.with(this)
                .load(R.drawable.logo)
                .into(profileImageView);

        initView();
        initData();
        //listView.expandGroup(2);

        mSearchView.attachNavigationDrawerToMenuButton(drawer);
    }




    private void initData() {
        @DrawableRes
        int dirIcon =   R.drawable.about_ads;
        @DrawableRes
        int fileIcon =   R.drawable.about_help;
        List<TreeNode> nodes = new ArrayList<>();

        TreeNode<Dir> about = new TreeNode<>(new Dir("About", dirIcon, true)).lock();
        nodes.add(about);
        TreeNode<Dir> settings = new TreeNode<>(new Dir("Settings", dirIcon, false));
        nodes.add(settings);
        settings.addChild( new TreeNode<>(new File("General Settings", fileIcon)) );
        settings.addChild( new TreeNode<>(new File("Application Settings", fileIcon)) );

        TreeNode<Dir> configuration = new TreeNode<>(new Dir("Configuration", dirIcon, false));
        nodes.add(configuration);
        configuration.addChild(new TreeNode<>(new File("Add Product", fileIcon)))
                .addChild(new TreeNode<>(new File("Delete Product", fileIcon)))
                .addChild(new TreeNode<>(new File("View", fileIcon)));

        TreeNode<Dir> pref = new TreeNode<>(new Dir("Prefrences", dirIcon, true)).lock();
        nodes.add(pref);
        TreeNode<Dir> export = new TreeNode<>(new Dir("Export Product details", dirIcon, true)).lock();
        nodes.add(export);
        TreeNode<Dir> imprt = new TreeNode<>(new Dir("Import Product details", dirIcon, true)).lock();
        nodes.add(imprt);
        TreeNode<Dir> orderDetails = new TreeNode<>(new Dir("Order details", dirIcon, true)).lock();
        nodes.add(orderDetails);

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TreeViewAdapter(nodes, Arrays.asList(new FileNodeBinder(), new DirectoryNodeBinder()));
        // whether collapse child nodes when their parent node was close.
//        adapter.ifCollapseChildWhileCollapseParent(true);
        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener() {
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                Log.d(node.toString());
                Log.d(holder.toString());
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
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navdrawer_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragTrans = fm.beginTransaction();
        fragTrans.replace(R.id.homeframelayout, fragment);
        fragTrans.commit();

    }
}
