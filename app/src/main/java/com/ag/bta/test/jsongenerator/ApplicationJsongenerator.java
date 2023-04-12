package com.ag.bta.test.jsongenerator;

import com.ag.bta.main.models.ApplicationModel;
import com.ag.bta.main.models.home.BottomBar;
import com.ag.bta.main.models.home.Home;
import com.ag.bta.main.models.home.NavBar;
import com.ag.bta.main.models.home.NavDrawerSection;
import com.ag.bta.main.models.home.Option;
import com.ag.bta.main.models.home.SubSubTab;
import com.ag.bta.main.models.home.SubTab;
import com.ag.bta.main.models.home.Tab;
import com.ag.bta.utils.Log;

import java.util.ArrayList;

public class ApplicationJsongenerator {


    public static final String[] mainTabList = {"Inventories", "Purchase", "Sale", "Ledger", "Trade Receivable","Trade Payable","Tax Statement", "Reconcilation"};
    public static final String[] subTabList_inventories = {"Stock-in-Trade", "Add Product", "Remove Product", "Reconcile"};
    public static final String[] subTabList_purchase = {"Purchase", "Purchase-Returns", "Goods-in-Transit", "Statement"};
    public static final String[] subTabList_sale = {"Sales", "Sales-Returns", "Goods-in-Transit", "Statements"};
    public static final String[] subTabList_ledger = {"Daybook", "Date-wise", "Month-wise", "Year-wise", "Overview"};

    public static final String[] subTabList_TradeRecievable = {"Table", "Chart"};
    public static final String[] subTabList_TradePayable = {"Table", "Chart"};
    public static final String[] subTabList_tax_statement = {"ITC details", "GSTR-1", "GSTR-3B", "Overview"};
    public static final String[] subTabList_tax_Reconcilation = {"Search", "Serach by Barcode"};

    public static final String[] subTabList_common = {"Add", "Remove", "View"};

    ArrayList<String[]> subtabList = new ArrayList<String[]>();

  public   ApplicationJsongenerator(){
      subtabList.add(subTabList_inventories);
      subtabList.add(subTabList_purchase);
      subtabList.add(subTabList_sale);
      subtabList.add(subTabList_ledger);
      subtabList.add(subTabList_TradeRecievable);
      subtabList.add(subTabList_TradePayable);
      subtabList.add(subTabList_tax_statement);
      subtabList.add(subTabList_tax_Reconcilation);



    }
    public ApplicationModel getApplicationModel(){
        ApplicationModel app = new ApplicationModel();
        Home hom = new Home();
        app.setTitle("Business Agent");

        Log.d("Generate Tabs");
        generateTabs(hom);

        Log.d("====================");
        Log.d("End of Generate Tabs");
        Log.d("====================");
        hom.setNavbar(generateNavBar());
        hom.setBottomBar(generateBottomBar());

        app.setHome(hom);
       // generatePageContents(hom);

return app;

    }


private void generateTabs(Home home){
      int count =-1;
    for(String tabname: mainTabList){
        ++count;
        home.addTab(  generateTab(tabname,   count));
    }
}


    private SubTab generateSubTab(String maintab, String title, int pos){
        SubTab subtab = new SubTab();
        subtab.setTitle(title);
        subtab.setDrawables(title);
        subtab.setSubSubTabExist(true);
       String [] lists = subTabList_common ;
       int count = -1;

        for (String tabname : lists) {
            Log.d(tabname  );
            ++count;

                subtab.setSubSubTabExist(true);
              //  int[] iarray = new int[] {0,2,1,3,0};
            int[] iarray = new int[] {0};
                subtab.add(generateSubSubTab(tabname, iarray, generatePageJSonName(maintab, title,tabname)));

        }
return subtab;
    }

    private SubSubTab generateSubSubTab(String title, int[] secTypeList, String jsonName){
      SubSubTab sstab = new SubSubTab();
        sstab.setDrawable(title);
        sstab.setTitle(title);

        sstab.setSubSubSubTabExist(false);
           // int[] iarray = new int[] {0};
        sstab.setPagerContent( jsonName);

        return  sstab;
    }
    private Tab generateTab(String tabname, int position){
        int pos = -1;
          Tab tab = new Tab();
          tab.setTitle(tabname);
          tab.setDrawable(tabname);
          String[] sublists = subtabList.get(position);
        Log.d("====================");
        Log.d("Inside Generate Tabs");
        Log.d("====================");
        int count =-1;
        for (String subtabtitle: sublists ) {
            Log.d(subtabtitle);
++count;
            if(count %2 ==0) {
               // tab.setSubSubTabExist(true);
                int[] iarray = new int[] {0,2,1,3,0};
                tab.add(generateSubTab(tabname,subtabtitle, pos));
            }else{
                SubTab stab = new SubTab();
                stab.setTitle(subtabtitle);
                stab.setSubSubTabExist(false);
              //  int[] iarray = new int[] {0};

                stab.setPagerContent(generatePageJSonName(tabname, subtabtitle));
                tab.add(stab);
            }

            Log.d(subtabtitle +" Created");
        }

    return tab;
    }

    private String generatePageJSonName(String tab, String subtab) {
 return tab+"_"+subtab;
    }
    private String generatePageJSonName(String maintab, String subtab, String subsubtab) {
        return maintab+"_"+subtab+"_"+subsubtab;
    }



    private BottomBar generateBottomBar(){
        BottomBar bottomBar = new BottomBar();
        bottomBar.addOption(new Option("Home", 0));
        bottomBar.addOption(new Option("Fav", 0));
        bottomBar.addOption(new Option("Cart", 0));
      return bottomBar;
    }

    private NavBar generateNavBar(){
        NavBar nb = new NavBar();
        nb.setBusinessName("Business Agent");
        nb.setDrawable_logo(0);
        nb.setTagLine("All Good");
        NavDrawerSection section1 =  new NavDrawerSection();
        section1.addOption(new Option("Section1", 0));
        nb.addSection(section1);
        NavDrawerSection section2 =  new NavDrawerSection();
        section2.addOption(new Option("Section2", 0));
        nb.addSection(section2);
        NavDrawerSection section3 =  new NavDrawerSection();
        section3.addOption(new Option("Admin1", 0));
        nb.addSection(section3);

        return nb;
    }
}
