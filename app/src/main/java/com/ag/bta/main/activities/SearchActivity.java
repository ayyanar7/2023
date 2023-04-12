package com.ag.bta.main.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.ag.bta.utils.constant.Global;
import com.ag.bta.utils.constant.database.ColumnName;
import com.ag.bta.utils.constant.database.DBConstants;
import com.ag.bta.main.R;
import com.ag.bta.main.models.ApplicationModel;
import com.ag.bta.main.models.Login;
import com.ag.bta.main.models.home.Container;
import com.ag.bta.main.models.home.PagerContent;
import com.ag.bta.test.jsongenerator.ApplicationJsongenerator;
import com.ag.bta.ui.searchrecycler.lib.SearchConfiguration;
import com.ag.bta.ui.searchrecycler.lib.SearchPreference;
import com.ag.bta.ui.searchrecycler.lib.SearchPreferenceActionView;
import com.ag.bta.ui.searchrecycler.lib.SearchPreferenceResult;
import com.ag.bta.ui.searchrecycler.lib.SearchPreferenceResultListener;
import com.ag.bta.utils.GsonUtils;
import com.ag.bta.utils.Log;
import com.ag.bta.utils.database.sqlite.DesignTable;

import java.util.HashMap;

public class SearchActivity extends AppCompatActivity  implements   SearchPreferenceResultListener {
     // protected FloatingSearchView mSearchView = null;
    //CustomViewPager mainViewPager  = null;
    FrameLayout framelayout = null;
    private static final String KEY_SEARCH_QUERY = "search_query";
    private static final String KEY_SEARCH_ENABLED = "search_enabled";
    private SearchPreferenceActionView searchPreferenceActionView;
    private MenuItem searchPreferenceMenuItem;
    private String savedInstanceSearchQuery;
    private boolean savedInstanceSearchEnabled;
   // private SearchViewExample.PrefsFragment prefsFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_main);
       // mSearchView = findViewById(R.id.search_view);
        framelayout = findViewById(R.id.homeframelayout);
        //mainViewPager = (CustomViewPager) findViewById(R.id.mainViewPager);
init();
        if (savedInstanceState != null) {
            savedInstanceSearchQuery = savedInstanceState.getString(KEY_SEARCH_QUERY);
            savedInstanceSearchEnabled = savedInstanceState.getBoolean(KEY_SEARCH_ENABLED);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        searchPreferenceMenuItem = menu.findItem(R.id.search);
        searchPreferenceActionView = (SearchPreferenceActionView) searchPreferenceMenuItem.getActionView();
        SearchConfiguration searchConfiguration = searchPreferenceActionView.getSearchConfiguration();
        searchConfiguration.index(R.xml.preferences);

        searchConfiguration.useAnimation(
                findViewById(android.R.id.content).getWidth() - getSupportActionBar().getHeight()/2,
                -getSupportActionBar().getHeight()/2,
                findViewById(android.R.id.content).getWidth(),
                findViewById(android.R.id.content).getHeight(),
                getResources().getColor(R.color.colorPrimary));

        searchPreferenceActionView.setActivity(this);

        final MenuItem searchPreferenceMenuItem = menu.findItem(R.id.search);
        searchPreferenceMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                searchPreferenceActionView.cancelSearch();
                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }
        });

        if (savedInstanceSearchEnabled) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    // If we do not use a handler here, it will not be possible
                    // to use the menuItem after dismissing the searchView
                    searchPreferenceMenuItem.expandActionView();
                    searchPreferenceActionView.setQuery(savedInstanceSearchQuery, false);
                }
            });
        }
        return true;
    }

    @Override
    public void onSearchResultClicked(@NonNull final SearchPreferenceResult result) {
        searchPreferenceActionView.cancelSearch();
        searchPreferenceMenuItem.collapseActionView();
       // result.highlight(prefsFragment);
    }

    @Override
    public void onBackPressed() {
        if (!searchPreferenceActionView.cancelSearch()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_SEARCH_QUERY, searchPreferenceActionView.getQuery().toString());
        outState.putBoolean(KEY_SEARCH_ENABLED, !searchPreferenceActionView.isIconified());
        searchPreferenceActionView.cancelSearch();
        super.onSaveInstanceState(outState);
    }

    public static class PrefsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.preferences);

            SearchPreference searchPreference = (SearchPreference) findPreference("searchPreference");
            searchPreference.setVisible(false);
        }
    }
    private void init(){
        Global.APPLICATION_PACKAGE =  getApplicationContext().getPackageName();
        Global.APPLICATION_CONTEXT = getApplicationContext();
        Log.d(" Global.APPLICATION_PACKAGE: "+ Global.APPLICATION_PACKAGE);
        Login useraccount = new Login("ayyanar","password","description","abc@email.com");
        String json_account = GsonUtils.toJson(useraccount);
        Log.d("json_account: ");
        Log.d(json_account);
        //AccountTable accountTable =  new AccountTable(this);

        ApplicationJsongenerator appjson = new ApplicationJsongenerator();
        String appson = GsonUtils.toJson(appjson.getApplicationModel());
        Container contaner =  new Container();
        String allpages = GsonUtils.toJson(contaner.generatePageContents(appjson.getApplicationModel().getHome()));
        //String appson = GsonUtils.toJson(appjson);
        Log.d("Application Jison: ");
        android.util.Log.d("JSOn: ", appson);
        android.util.Log.d("pages Json: ", allpages);
        //Log.d(appson);

        //DATABASE UPDATION

        DesignTable design = new DesignTable(this);
        boolean isAvailable = design.checkTable();
        //check db version ...and than delete or update
        if(!isAvailable){
            Log.d("First time insert into DB.."+appson);
            design.insert(DBConstants.MAIN_TAB_OPTIONS,   appson);
            Container pagesContainer = GsonUtils.fromJson(allpages, Container.class);
            HashMap<String, PagerContent> pages =  pagesContainer.getAllPages();
            for(String key:  pages.keySet()){
                Log.d("Key.."+key);
                PagerContent pc = pages.get(key);
                design.insert(key,   GsonUtils.toJson(pc));
            }

        }else{
            Log.d("Before Db Update...");
            design.update(ColumnName.JSON_OBJECT,  appson, ColumnName.TABS, DBConstants.MAIN_TAB_OPTIONS);
        }
        Log.d("INSERTED ....");
        String jsono = design.queryJson(DBConstants.MAIN_TAB_OPTIONS);
        Log.d("retrived Json: "+jsono);

        Global.getInstance().setAppsModel(GsonUtils.fromJson(jsono, ApplicationModel.class));


    }


}

