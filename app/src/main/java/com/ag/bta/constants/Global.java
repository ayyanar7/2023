package com.ag.bta.constants;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.ag.bta.main.models.ApplicationModel;
import com.ag.bta.utils.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class Global {
    public static String APPLICATION_PACKAGE = null;
    public static Context APPLICATION_CONTEXT = null;
    private ApplicationModel appsModel = null;
private static Global instance = null;
    private Global(){
        instance = this;
    }
    public static Global getInstance(){
        if(instance == null){
            instance = new Global();
        }
      return   instance;
    }

    public ApplicationModel getAppsModel() {
        return appsModel;
    }

    public void setAppsModel(ApplicationModel appsModel) {
        this.appsModel = appsModel;
    }

    public Drawable resize(Resources res, Drawable img, int xdimen, int ydimen){
    Bitmap b= ((BitmapDrawable) img).getBitmap();
    Bitmap bitmapresized = Bitmap.createScaledBitmap(b,xdimen, ydimen,false);
    return new BitmapDrawable(res, bitmapresized);
    }
    public static int getLayout(String  layoutName){
        Objects.requireNonNull(Global.APPLICATION_CONTEXT);
       // Log.d(" APPLICATION_PACKAGE .. "+Global.APPLICATION_PACKAGE);
        int layoutId =  Global.APPLICATION_CONTEXT.getResources().getIdentifier(layoutName, "layout", Global.APPLICATION_PACKAGE);
        return layoutId;
    }

    public static String getLayoutName( int mainTabPosition, int subTabposition){
        String layoutName =  null;
      if(mainTabPosition <0 || subTabposition <0){
          return "";
      }
        layoutName =  "fragment"+"_"+mainTabPosition+"_"+subTabposition;
        Log.d("Layout Name : "+layoutName);
        return layoutName;
    }
    public static String getLayoutName( int mainTabPosition){
        String layoutName =  null;
        if(mainTabPosition <0  ){
            return "";
        }
        layoutName =  "fragment"+"_"+mainTabPosition ;
        Log.d("Layout Name : "+layoutName);
        return layoutName;
    }

    protected Boolean parseJSON(Context cntxt) {
        try {
            String data =loadJSONFromAsset( cntxt, "UIDesign.txt");
            Log.d("kkk",data);

//            JSONObject jsono = new JSONObject(jsonData);
//            JSONArray jarray = jsono.getJSONArray("users");
//
//            System.out.print("jsono: "+jsono);
//            System.out.print("jsono: "+jsono);
//            for (int i = 0; i < jarray.length(); i++) {
//                JSONObject object = jarray.getJSONObject(i);
//                //Inner object is their using below code:                    /*                        //create another object create and get dat                       JSONObject businessObject = object.getJSONObject("currency");                        String nameValue = businessObject.getString("code");                        String currencyname = businessObject.getString("currencyname");                        System.out.println(nameValue);                        System.out.println(currencyname);                    */                        Details actor = new Details();
//                Details user = new Details();
//                System.out.print("jsono: "+jsono);
//                user.setId(object.getString("id"));
//                user.setName(object.getString("name"));
//                user.setEmail(object.getString("email"));
//                user.setGender(object.getString("gender"));
//                System.out.print("jsono: "+jsono);
//                System.out.print("jsono: "+jsono);
//                System.out.print("jsono: "+jsono);
//                System.out.print("jsono: "+jsono);
//                JSONObject jcontact= object.getJSONObject("contact");
//                System.out.print("jsono: "+jsono);
//                user.setContacthome(jcontact.getString("home"));
//                user.setContactmo(jcontact.getString("mobile"));
//                user.setContactoffice(jcontact.getString("office"));
//                System.out.print("jsono: "+jsono);
//                System.out.print("jsono: "+jsono);
//                System.out.print("jsono: "+jsono);
//                parsedList.add(user);
//
//            }
            return true;
            //}

            //------------------>>
        } catch (Exception e) {
            e.printStackTrace();
        } /*catch (JSONException e) {
              e.printStackTrace();
          }*/
        return false;
    }
    public String loadJSONFromAsset(Context cnt,String filename) {
        String json = null;
        try {

            InputStream is = cnt.getAssets().open(filename);
            Reader targetReader = new InputStreamReader(is);
           int size = is.available();

            //GsonUtils.fromJson(targetReader, UIDesign.class) ;
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
