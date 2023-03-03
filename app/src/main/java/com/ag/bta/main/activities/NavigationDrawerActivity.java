package com.ag.bta.main.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ag.bta.main.R;
import com.ag.bta.main.fragments.AdminAboutFragment;
import com.ag.bta.ui.inventories.AddItemFragment;
import com.ag.bta.ui.inventories.DeleteItemsFragment;
import  com.ag.bta.ui.navigationdrawer.Utils.Common;
import com.ag.bta.ui.navigationdrawer.model.ChildModel;
import com.ag.bta.ui.navigationdrawer.model.HeaderModel;
import com.ag.bta.ui.navigationdrawer.ui.NavigationListView;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class NavigationDrawerActivity extends SearchActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private NavigationListView listView;
    private Context context;

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

        listView = findViewById(R.id.expandable_navigation);

        navigationView.setNavigationItemSelectedListener(this);

        listView.init(this)
                .addHeaderModel(new HeaderModel("About"))
                .addHeaderModel(new HeaderModel("Cart",  R.drawable.navdrawer_cardbackgroud, true,true, false, Color.WHITE))
                .addHeaderModel(
                        new HeaderModel("Configure", -1,true)
                                .addChildModel(new ChildModel("Add Inventries"))
                                .addChildModel(new ChildModel("Edit Inventries"))
                                .addChildModel(new ChildModel("Delete Inventries"))
                                .addChildModel(new ChildModel("Trash"))
                )
                .addHeaderModel(new HeaderModel("Orders"))
                .addHeaderModel(new HeaderModel("Wishlist"))
                .addHeaderModel(new HeaderModel("Notifications"))
                .build()
                .addOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        mainViewPager.setVisibility(View.GONE);
                        framelayout.setVisibility(View.VISIBLE);
                        listView.setSelected(groupPosition);

                        //drawer.closeDrawer(GravityCompat.START);
                        if (id == 0) {
                            //Home Menu
                            Common.showToast(context, "Home Select");
                            AdminAboutFragment about = new AdminAboutFragment();
                            loadFragment(about);

                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 1) {
                            //Cart Menu
                            Common.showToast(context, "Cart Select");
                            drawer.closeDrawer(GravityCompat.START);
                        } /*else if (id == 2) {
                            //Categories Menu
                            Common.showToast(context, "Configure  Select");
                        }*/else if (id == 3) {
                            //Orders Menu
                            Common.showToast(context, "Orders");
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 4) {
                            //Wishlist Menu
                            Common.showToast(context, "Wishlist Selected");
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 5) {
                            //Notifications Menu
                            Common.showToast(context, "Notifications");
                            drawer.closeDrawer(GravityCompat.START);
                        }
                        return false;
                    }
                })
                .addOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        listView.setSelected(groupPosition, childPosition);
                        if (id == 0) {

                            AddItemFragment addProduct = new AddItemFragment();
                            loadFragment(addProduct);
                            Common.showToast(context, "Add");
                        } else if (id == 1) {
                            DeleteItemsFragment deleteProduct = new DeleteItemsFragment();
                            loadFragment(deleteProduct);
                            Common.showToast(context, "Delete");
                        } else if (id == 2) {
                            Common.showToast(context, "Babies and Family");
                        } else if (id == 3) {
                            Common.showToast(context, "Health");
                        }

                        drawer.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });
        //listView.expandGroup(2);

        mSearchView.attachNavigationDrawerToMenuButton(drawer);
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
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       /* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragTrans = fm.beginTransaction();
        fragTrans.replace(R.id.homeframelayout, fragment);
        fragTrans.commit();

    }
}
