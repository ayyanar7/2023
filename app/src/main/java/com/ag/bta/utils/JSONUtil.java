package com.ag.bta.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

 

import android.content.Context;

import com.ag.bta.main.activities.other.InvoiceDetails;
import com.ag.bta.main.models.BusinessEntity;
import com.ag.bta.main.models.Customer;
import com.ag.bta.main.models.Product;
import com.ag.bta.utils.Log;

import com.ag.bta.utils.constant.Constants;

public class JSONUtil {

	String jsonData = "{\r\n" + 
			"    \"users\": [\r\n" + 
			"        {\r\n" + 
			"                \"id\": \"1087\",\r\n" + 
			"                \"name\": \"Abhishek Saini\",\r\n" + 
			"                \"email\": \"info@abhiandroid.com\",\r\n" + 
			"                \"gender\" : \"male\",\r\n" + 
			"                \"contact\": {\r\n" + 
			"                    \"mobile\": \"+91 0000000000\",\r\n" + 
			"                    \"home\": \"00 000000\",\r\n" + 
			"                    \"office\": \"00 000000\"\r\n" + 
			"                }\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"                \"id\": \"1088\",\r\n" + 
			"                \"name\": \"Gourav\",\r\n" + 
			"                \"email\": \"gourav9188@gmail.com\",\r\n" + 
			"                \"gender\" : \"male\",\r\n" + 
			"                \"contact\": {\r\n" + 
			"                    \"mobile\": \"+91 0000000000\",\r\n" + 
			"                    \"home\": \"00 000000\",\r\n" + 
			"                    \"office\": \"00 000000\"\r\n" + 
			"                }\r\n" + 
			"        }\r\n" + 
			"  ]\r\n" + 
			"}";
	
    
    
    public static JSONObject generateJSON(ArrayList<HashMap<String, String>> itemmapList,HashMap<String, String> invoiceDetails) {
       	JSONObject jsono = null;

  try {
	jsono = new JSONObject(invoiceDetails);
    JSONArray jarray = new JSONArray();
    		//jsono.getJSONArray("ItemsList");
  if(itemmapList != null) {
	  int size = itemmapList.size();
	  for(int i=0; i<size;i++) {
 		  if(itemmapList.get(i) != null) {
			  jarray.put(i, itemmapList.get(i));
		  }
	  }
	   
	  jsono.put(Constants.STR_JSON_ITEM_LIST, jarray);
  }
       }catch (Exception e) {
		Log.d("Exception while generating JSON "+e.toString());
		e.printStackTrace();
		return null;
		}


 Log.d("Generated Json ..:"+ jsono.toString());
// jsono.put
    	return jsono; 
    }


	  public String loadJSONFromAsset(Context cnt) {
	        String json = null;
	        try {
	            InputStream is = cnt.getAssets().open("users_list.json");
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
	  
	  
	  
	  public void generateInvoiceJSON(ArrayList<InvoiceDetails> invoicelist)   {
		  InvoiceDetails invoice = null;

		 JSONObject joson =  null, custjoson = null; 
		 Customer cust = null;

		 JSONArray jsonarray = null;
		 try {
		   jsonarray = new JSONArray("Invoices");
		 for (int i=0; i< invoicelist.size(); i++) {
			 joson = null; custjoson= null;
			 cust = null;
			 invoice =null;
			 invoice = invoicelist.get(i);
			 joson =  new JSONObject();
			 joson.put("Invoiceno", invoice.getStrInvoiceNo());
			 joson.put("Date", invoice.getStrDate());
			 joson.put("Time", invoice.getStrTime());
			 
			 cust = invoicelist.get(i).getoCust();
			 custjoson =  new JSONObject("Customer");
			 custjoson.put("Name", cust.getname());
			 custjoson.put("Gender", cust.getGender());
			 custjoson.put("Email", cust.getemail());
			 custjoson.put("Fax", cust.getfax());
			 custjoson.put("Landline", cust.getlandline());
			 custjoson.put("Mobile", cust.getmobile());
			 custjoson.put("Address1", cust.getaddress1());
			 custjoson.put("Address2", cust.getaddress2());
			 custjoson.put("Address3", cust.getaddress3());
			 custjoson.put("Pincode", cust.getPincode());
			 custjoson.put("GSTN", cust.getGSTN());
			 custjoson.put("State", cust.getState());
			 custjoson.put("Description", cust.getStrDescription());	 
			 joson.put("Customer", custjoson);
			 
			 //places json battery
			 joson.put("Item", generateItemJSON(invoice.getProductList()));
			 
			 jsonarray.put(i, joson);

		 }
	  }catch (Exception e) {
			e.printStackTrace();
			Log.d("Failed to generate Item Json. "+e.toString());
		}

		 
	}
	  
public JSONArray generateItemJSON(ArrayList<Product> itemlist)  {
	 Product oitem = null;

	 JSONArray jsonarray = null;
	 try {
	   jsonarray = new JSONArray("Items");
	 for (int i=0; i< itemlist.size(); i++) {

		  oitem = itemlist.get(i);
 			// obatery = oitem.getBatteryInstance();
			 jsonarray.put(i, generateBatteryJSON(oitem));
			 	System.out.println("error Item not elong to any type");
 		 }

	 
	 
}catch (Exception e) {
	e.printStackTrace();
	Log.d("Failed to generate Item Json. "+e.toString());
}

	return jsonarray;
}


public JSONObject generateBatteryJSON(Product oBatry) {
	JSONObject jsn = null;
	try {
	  jsn =  new JSONObject("Battery");
	jsn.put("GSTN", oBatry.getStrGSTN());
	jsn.put("ManufacturerName", oBatry.getStrManufacturerName());
	jsn.put("Price", oBatry.getStrPrice());
	jsn.put("NoOfUnits", oBatry.getStrNoOfUnits());
	jsn.put("PowerRating", oBatry.getStrPowerRating());
	jsn.put("VolateRating", oBatry.getStrVoltageRating());
	jsn.put("CurrentRating", oBatry.getStrCurrentrating());
	jsn.put("IsIGSTAppicable", oBatry.isFlagIGSTapplicable());
}catch (Exception e) {
	e.printStackTrace();
	Log.d("Failed to generate Battery Json. "+e.toString());
}
	
return jsn;
}

 
 public JSONObject generateProprietorJSON(BusinessEntity vtSln) {
	JSONObject jsn = null;
	try {
		
	  jsn =  new JSONObject("Proprietor");
	jsn.put("GSTN", BusinessEntity.GSTIN);
	jsn.put("Shopname", BusinessEntity.SHOP_NAME);
	jsn.put("Name", vtSln.getPropreitorObject().getStrName());
	jsn.put("Gender", vtSln.getPropreitorObject().getStrGender());
	jsn.put("ShopAddress1", vtSln.getPropreitorObject().getStrShopAddress1());
	jsn.put("ShopAddress2", vtSln.getPropreitorObject().getStrShopAddress2());
	jsn.put("ShopAddress3", vtSln.getPropreitorObject().getStrShopAddress3());
	jsn.put("ShopPincode", vtSln.getPropreitorObject().getStrShopPincode());
	jsn.put("ShopState", vtSln.getPropreitorObject().getStrShopState());
	jsn.put("ShopCountry", vtSln.getPropreitorObject().getStrShopCountry());
	jsn.put("ShopContactNo", vtSln.getPropreitorObject().getStrShopContactNo());
	jsn.put("PersonalContactNO", vtSln.getPropreitorObject().getStrPersonalContactNO());
	jsn.put("EmailId", vtSln.getPropreitorObject().getStrEmailId());
}catch (Exception e) {
	e.printStackTrace();
	Log.d("Failed to generate UPS Json. "+e.toString());
}

	return jsn;
}



}