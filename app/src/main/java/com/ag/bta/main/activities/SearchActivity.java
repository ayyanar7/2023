package com.ag.bta.main.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ag.bta.constants.Global;
import com.ag.bta.constants.database.ColumnName;
import com.ag.bta.constants.database.DBConstants;
import com.ag.bta.main.R;
import com.ag.bta.main.models.ApplicationModel;
import com.ag.bta.main.models.Login;
import com.ag.bta.main.models.home.Container;
import com.ag.bta.main.models.home.PagerContent;
import com.ag.bta.main.viewPager.CustomViewPager;
import com.ag.bta.test.jsongenerator.ApplicationJsongenerator;
import com.ag.bta.utils.GsonUtils;
import com.ag.bta.utils.Log;
import com.ag.bta.utils.database.sqlite.DesignTable;
import com.arlib.floatingsearchview.FloatingSearchView;

import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {
      protected FloatingSearchView mSearchView = null;
    CustomViewPager mainViewPager  = null;
    FrameLayout framelayout = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_main);
        mSearchView = findViewById(R.id.search_view);
        framelayout = findViewById(R.id.homeframelayout);
        mainViewPager = (CustomViewPager) findViewById(R.id.mainViewPager);
init();
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

            }
        });


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

