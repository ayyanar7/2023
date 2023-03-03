package com.ag.bta.main.wavelayout.utils;

 import android.content.Context;
 import android.content.res.Resources;
 import android.util.Log;

 import androidx.annotation.StringRes;


 import androidx.recyclerview.widget.GridLayoutManager;
 import androidx.recyclerview.widget.LinearLayoutManager;


 import com.ag.bta.constants.FragmentConstants;
 import com.ag.bta.main.wavelayout.models.ItemCard;



 import com.ag.bta.main.R;

 import org.json.JSONArray;
 import org.json.JSONObject;

 import java.io.IOException;
 import java.io.InputStream;
 import java.util.ArrayList;
 import java.util.List;

 public class BaseUtils {


     public static final int[] HOME_SCREEN_ITEMLIST = {R.string.list_title1,R.string.list_title2,R.string.list_title3,R.string.list_title4,R.string.list_title5,R.string.list_title6};
     public static final int[] HOME_SCREEN_IMAGELIST = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
    // public static final int[] SALES_SCREEN_COMPANYLIST = {R.string.company_one,R.string.company_two,R.string.company_three,R.string.company_four,R.string.company_five,R.string.company_six};
    // public static final int[] SALES_SCREEN_COMPANY_IMAGELIST = {R.drawable.luminous_logo,R.drawable.amaron_logo,R.drawable.exide_logo,R.drawable.microtek_logo,R.drawable.sfsonic_logo,R.drawable.massimo_logo};

     // public static final int[] SALES_SCREEN_COMPIMAGELIST = {R.drawable.company_one_img,R.drawable.company_two_img,R.drawable.company_three_img,R.drawable.company_four_img,R.drawable.company_five_img,R.drawable.company_six_img};
     //public static final int[] SALES_SCREEN_ITEM_LIST = {R.string.item_one,R.string.item_two,R.string.item_three};//,R.string.item_four,R.string.item_five,R.string.item_six};
     //public static final int[] SALES_SCREEN_ITEM_IMAGELIST = {R.drawable.item_one_img,R.drawable.item_two_img,R.drawable.item_three_img,R.drawable.item_four_img,R.drawable.item_five_img,R.drawable.item_six_img};
    // public static final int[] SALES_SCREEN_ITEM_IMAGELIST = {R.drawable.invertor,R.drawable.battery,R.drawable.ups};//,R.drawable.solar,R.drawable.water_purifier,R.drawable.scrap};
     public static ArrayList<String> resourcse_name =  new ArrayList<String> ();



     public static List<ItemCard> getHomeCards(Resources resources) {
         ArrayList<ItemCard> items =  new ArrayList<ItemCard> ();
         ItemCard itemCard = null;
         for(int i=0 ; i<HOME_SCREEN_ITEMLIST.length;i++) {
              itemCard = createItemCard(resources, HOME_SCREEN_ITEMLIST[i], HOME_SCREEN_IMAGELIST[i],0,0);
              items.add(itemCard);
         }
              return items;
     }

     private ArrayList<ItemCard> itemlist =  null;
     public ArrayList<ItemCard> getCards(){
return itemlist;
     }
     public  void setCards(ArrayList<ItemCard> argItemlist) {
         itemlist = argItemlist;
     }

//     public static List<ItemCard> getSales2Cards(Resources resources) {
//         ArrayList<ItemCard> items =  new ArrayList<ItemCard> ();
//         ItemCard itemCard = null;
//         for(int i=0 ; i<SALES_SCREEN_ITEM_LIST.length;i++) {
//              itemCard = createItemCard(resources, SALES_SCREEN_ITEM_LIST[i], SALES_SCREEN_ITEM_IMAGELIST[i]);
//              items.add(itemCard);
//         }
//              return items;
//     }

     public static List<ItemCard> getSalesItemDispalyCards(Resources resources,Context cont) {
         ArrayList<ItemCard> items =  new ArrayList<ItemCard> ();

         ItemCard itemCard = null;
         try {
               //JSONUtil jutil = new JSONUtil();
                 String strjson = loadJSONFromAsset(cont,"Amaron_Battery");
                 JSONObject json =  new JSONObject(strjson);
                 JSONArray jarray= json.getJSONArray("atteryitems");
                 String filename ;
                 JSONObject jsonitem =null;
                 if(jarray != null) {
                     for(int i=0;i<jarray.length();i++) {
                         jsonitem = (JSONObject) jarray.get(i);
                         resourcse_name.add(jsonitem.getString("id"));
                          itemCard = createItemCard(resources,jsonitem.getString("strTitle") ,0,jsonitem.getString("strPrice"),jsonitem.getString("strCurrentrating")+" \n"+jsonitem.getString("strDescription1") );
                          items.add(itemCard);

                     }
                 }


         }catch (Exception e) {

  Log.d("", "Exception: getsalesItemdisplay: "+e.toString());
  e.printStackTrace();
         }

               return items;
     }


     public static ModelConfiguration getModelConfiguration(int configurationType, Context context, int title) {
         ModelConfiguration demoConfiguration;

         switch (configurationType) {
             case FragmentConstants.LAYOUT_TYPE_LIST_ONE:
                 demoConfiguration = new ModelConfiguration();
                 demoConfiguration.setStyleResource(R.style.ListTheme);
                 demoConfiguration.setLayoutResource(R.layout.wavelayout_list_recyclerview);
                 demoConfiguration.setLayoutManager(new LinearLayoutManager(context));
                 demoConfiguration.setTitleResource(title);
                 break;
             case FragmentConstants.LAYOUT_TYPE_LIST_TWO:
                 demoConfiguration = new ModelConfiguration();
                 demoConfiguration.setStyleResource(R.style.ListTheme);
                 demoConfiguration.setLayoutResource(R.layout.wavelayout_list2_recyclerview);
                 demoConfiguration.setLayoutManager(new LinearLayoutManager(context));
                 demoConfiguration.setTitleResource(title);
                 demoConfiguration.setItemDecoration(new CardDecoration(context));

                 break;
             case FragmentConstants.LAYOUT_TYPE_GRID_ONE:
                 demoConfiguration = new ModelConfiguration();
                 demoConfiguration.setStyleResource(R.style.GridTheme);
                 demoConfiguration.setLayoutResource(R.layout.wavelayout_grid_recyclerview);
                 demoConfiguration.setLayoutManager(new GridLayoutManager(context, FragmentConstants.GRIDLAYOUT_NO_OF_ITEMS_PER_ROW));
                 demoConfiguration.setTitleResource(title);
                                 break;
             case FragmentConstants.LAYOUT_TYPE_GRID_TWO:
                 demoConfiguration = new ModelConfiguration();
                 demoConfiguration.setStyleResource(R.style.GridTheme);
                 demoConfiguration.setLayoutResource(R.layout.wavelayout_grid2_recyclerview);
                 demoConfiguration.setLayoutManager(new GridLayoutManager(context, FragmentConstants.GRIDLAYOUT_NO_OF_ITEMS_PER_ROW));
                 demoConfiguration.setTitleResource(title);
                 break;
             default:
                 demoConfiguration = null;
         }

         return demoConfiguration;
     }

     private static ItemCard createItemCard(Resources resources, @StringRes int title, @StringRes int imageUrl,
                                            @StringRes int description, @StringRes int summary) {
         ItemCard itemCard = new ItemCard();

         itemCard.setTitle(resources.getString(title));
         itemCard.setThumbnailUrl(resources.getString(imageUrl));
         itemCard.setDescription(resources.getString(description));
         itemCard.setSummaryText(resources.getString(summary));

         return itemCard;
     }


     private static ItemCard createItemCard(Resources resources, String title, @StringRes int imageUrl, String description, String summary) {
         ItemCard itemCard = new ItemCard();

         itemCard.setTitle(title);
         itemCard.setThumbnailUrl(resources.getString(imageUrl));
         itemCard.setDescription(description);
         itemCard.setSummaryText(summary);

         return itemCard;
         }


     public static String loadJSONFromAsset(Context cnt,String filename) {
         String json = null;
         try {

             InputStream is = cnt.getAssets().open(filename);
             int size = is.available();
             byte[] buffer = new byte[size];
             is.read(buffer);
             is.close();
             json = new String(buffer, "UTF-8");
         } catch (IOException ex) {
             ex.printStackTrace();
             return null;
         }
         return json;
     }
 }
