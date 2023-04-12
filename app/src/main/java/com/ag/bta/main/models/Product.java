package com.ag.bta.main.models;

import com.ag.bta.utils.Log;
import com.ag.bta.utils.constant.Constants;


import java.text.DecimalFormat;

public class Product {

	String strMake= null;
	Object img = null;


    private   int id = 0;
	
    protected String strManufacturerName= "";
    protected String strDealerName= "";
    protected boolean  flagIGSTapplicable = false;

    protected String strDealerAddress1= "";
    protected String strDealerAddress2= "";
    protected String strDealerAddress3= "";
    protected String strDealerPincode= "";
    protected String strDealerState= "";
    protected String strContactMoile= "";
    protected String strContactLandline= "";
    protected String strContactFax= "";
    protected String strContactEmail= "";
    protected String strGSTN= "";
   
    private String strVoltageRating= "";
	private String strCurrentrating= "";
	private String strPowerRating= "";
	private String strPrice= "";
	private String strNoOfUnits = "";
	private String strDescription1= "";
	private String strDescription2= "";
private int type= 0;
 private static Product oitem = null;
     
public Product() {
	//dplease dont change the position; destroy has to the 1st;
	destroy();

}
	public int getType() {
		return type;
	}
	public void setType(int typ) {
		type = typ;
	}
public  int getId() {
	return id;
}
public  void setId(int id) {
	  this.id= id;
}
 public void destroy() {
	id = -1;
    strManufacturerName= "";
    strDealerName= ""; 
    flagIGSTapplicable = false;
    strDealerAddress1= "";
    strDealerAddress2= "";
    strDealerAddress3= "";
    strDealerPincode= "";
    strDealerState= "";
    strContactMoile= "";
    strContactLandline= "";
    strContactFax= "";
    strContactEmail= "";
    strGSTN= "";
   }

public static Product getInstance() {

//	if(itemTtype >-1) {
//			Log.d("Itemcosntructor: Invalid Item Type: "+itemTtype);
//			return null;
//	}
	 
	return oitem;
		 	
}
public void display() {
     
	Log.d("Item: Productid "+id);
	Log.d("Item: strManufacturerName"+strManufacturerName);
	Log.d("Item: strDealerName "+strDealerName);
	Log.d("Item: flagIGSTapplicable "+flagIGSTapplicable);
	Log.d("Item: strDealerAddress1 "+strDealerAddress1);
	Log.d("Item: strDealerAddress2 "+strDealerAddress2);
	Log.d("Item: strDealerAddress3 "+strDealerAddress3);

	Log.d("Item: pincode "+strDealerPincode);
	Log.d("Item: strDealerState "+strDealerState);
	Log.d("Item: strContactMoile "+strContactMoile);
	Log.d("Item:  strContactLandline "+strContactLandline);
	Log.d("Item: strContactFax "+strContactFax);
	Log.d("Item: strContactEmail "+strContactEmail);
	Log.d("Item: strGSTN "+strGSTN);
	Log.d("Item: strVoltageRating "+strVoltageRating);
	Log.d("Item: strCurrentrating "+strCurrentrating);
	Log.d("Item: strPowerRating "+strPowerRating);
	Log.d("Item: strPowerRating "+strPowerRating);
	Log.d("Item: strPrice "+strPrice);
	Log.d("Item: strNoOfUnits "+strNoOfUnits);
	Log.d("Item: strDescription1 "+strDescription1);
	Log.d("Item: strDescription2 "+strDescription2);


}
public  String getID() {

	if(id<0) {
			Log.d("Itemcosntructor: Invalid Item Type: "+id);
			return null;
	}



           return ""+id;

	
}
public double getGSTRate() {
	if(id<0) {
		Log.d("Itemcosntructor: Invalid Item Type: "+id);
		return 0;
}
	return 0;
	
}
public String getHSNCode() {
	if(id<0) {
		Log.d("Itemcosntructor: Invalid Item Type: "+id);
		return "----";
}

	return "----";
}


private String strTaxableAmountUnit ="";
private String strCGSTaxAmountUnit ="";
private String strIGSTaxAmountUnit ="";
private String strTotalTaxableAmount ="";
private String strTotalCGSTaxAmount ="";
private String strTotalIGSTaxAmount ="";

private String strTotalAmount ="";
 

public String[] calculateTax() {
	String strtaxdetails[] = new String[8];
	//price, taxa,tax
	double dprice =0.00, dtaxrate = 0.00, dtaxable = 0.00,dtax=0.00;
	double dtotalAmout =0.00, dtotaltaxable = 0.00, dtotaltax = 0.00;
	double dquantity = 0;

	if(strPrice != null && strPrice.trim().length()>0)
	  dprice = Double.valueOf(strPrice);
	  dtaxrate = getGSTRate();
	  if(strNoOfUnits != null && strNoOfUnits.trim().length()>0)
		  dquantity = Double.valueOf(strNoOfUnits);
	  Log.d("gst rate ...."+dtaxrate);
  
	if(dtaxrate !=0 ) {
		  dtaxable = (100*dprice)/(100+dtaxrate);
		  dtax = dprice- dtaxable;
		  dtotalAmout = dquantity*dprice;
		  dtotaltaxable = (100*dtotalAmout)/(100+dtaxrate);
		  dtotaltax = dtotalAmout - dtotaltaxable;
		}
	
	  DecimalFormat df = new DecimalFormat("#.00");
	  strTaxableAmountUnit = df.format(dtaxable);
	  if(!isFlagIGSTapplicable()) {
		  dtax = dtax/2;
		  strCGSTaxAmountUnit = df.format(dtax);
		  strIGSTaxAmountUnit ="0";

	  }else {
		  strIGSTaxAmountUnit = df.format(dtax);
		  strCGSTaxAmountUnit = "0";
	  }
	  strTotalAmount = df.format(dtotalAmout);
	  strTotalTaxableAmount = df.format(dtotaltaxable);
      
      if(!isFlagIGSTapplicable()) {
    	  dtotaltax = dtotaltax/2;
	      strTotalCGSTaxAmount = df.format(dtotaltax);
	      strTotalIGSTaxAmount ="0";

	  }else {
		  strTotalIGSTaxAmount = df.format(dtotaltax);
		  strTotalCGSTaxAmount = "0";
	  }

      
      
      strtaxdetails[0] = df.format(dprice);
      strtaxdetails[1] = strTaxableAmountUnit;
      strtaxdetails[2] = strCGSTaxAmountUnit;
      strtaxdetails[3] = strIGSTaxAmountUnit;
      strtaxdetails[4] = strTotalAmount;
      strtaxdetails[5] = strTotalTaxableAmount;
      strtaxdetails[6] = strTotalCGSTaxAmount;
      strtaxdetails[7] = strTotalIGSTaxAmount;

      for(String strdata:strtaxdetails) {
    	  Log.d("Taxdetails ...."+strdata);
      }
      
return       strtaxdetails;
}

public String getTaxableAmountperunit() {
	return strTaxableAmountUnit;
}
public String getCGSTaxAmountUnit() {
	return strCGSTaxAmountUnit;

}
public String getIGSTaxAmountUnit() {
	return strIGSTaxAmountUnit;

}

public String getTotalTaxableAmount() {
	return strTotalTaxableAmount;

}
public String getTotalCGSTaxAmount() {
	return strTotalCGSTaxAmount;

}
public String getTotalIGSTaxAmount() {
	return strTotalIGSTaxAmount;

}

public String getTotalAmount() {
	return strTotalAmount;

}


 public String getStrManufacturerName() {
	return strManufacturerName;
}
public void setStrManufacturerName(String strManufacturerName) {
	this.strManufacturerName = strManufacturerName;
}
public String getStrDealerName() {
	return strDealerName;
}
public void setStrDealerName(String strDealerName) {
	this.strDealerName = strDealerName;
}
public String getStrDealerAddress1() {
	return strDealerAddress1;
}
public void setStrDealerAddress1(String strDealerAddress1) {
	this.strDealerAddress1 = strDealerAddress1;
}
public String getStrDealerAddress2() {
	return strDealerAddress2;
}
public void setStrDealerAddress2(String strDealerAddress2) {
	this.strDealerAddress2 = strDealerAddress2;
}
public String getStrDealerAddress3() {
	return strDealerAddress3;
}
public void setStrDealerAddress3(String strDealerAddress3) {
	this.strDealerAddress3 = strDealerAddress3;
}
public String getStrDealerPincode() {
	return strDealerPincode;
}
public void setStrDealerPincode(String strDealerPincode) {
	this.strDealerPincode = strDealerPincode;
}
public String getStrDealerState() {
	return strDealerState;
}
public void setStrDealerState(String strDealerState) {
	this.strDealerState = strDealerState;
}
public String getStrContactMoile() {
	return strContactMoile;
}
public void setStrContactMoile(String strContactMoile) {
	this.strContactMoile = strContactMoile;
}
public String getStrContactLandline() {
	return strContactLandline;
}
public void setStrContactLandline(String strContactLandline) {
	this.strContactLandline = strContactLandline;
}
public String getStrContactFax() {
	return strContactFax;
}
public void setStrContactFax(String strContactFax) {
	this.strContactFax = strContactFax;
}
public String getStrContactEmail() {
	return strContactEmail;
}
public void setStrContactEmail(String strContactEmail) {
	this.strContactEmail = strContactEmail;
}
public String getStrGSTN() {
	return strGSTN;
}
public void setStrGSTN(String strGSTN) {
	this.strGSTN = strGSTN;
}
public String getStrVoltageRating() {
	return strVoltageRating;
}
public void setStrVoltageRating(String strVoltageRating) {
	this.strVoltageRating = strVoltageRating;
}
public String getStrCurrentrating() {
	return strCurrentrating;
}
public void setStrCurrentrating(String strCurrentrating) {
	this.strCurrentrating = strCurrentrating;
}
public String getStrPowerRating() {
	return strPowerRating;
}
public void setStrPowerRating(String strPowerRating) {
	this.strPowerRating = strPowerRating;
}
public String getStrPrice() {
	return strPrice;
}
public void setStrPrice(String strPrice) {
	this.strPrice = strPrice;
}
public String getStrNoOfUnits() {
	return strNoOfUnits;
}
public void setStrNoOfUnits(String strNoOfUnits) {
	this.strNoOfUnits = strNoOfUnits;
}
public String getStrDescription1() {
	String strdesc = strPowerRating+"VA "+strManufacturerName+" ";
	return strdesc;
}
public void setStrDescription1(String strDescription) {
	this.strDescription1 = strDescription;
}
public String getStrDescription2() {
	String strdesc = strVoltageRating+"V "+strCurrentrating+"A Capacity";

	return strdesc;
}
public void setStrDescription2(String strDescription) {
	this.strDescription2 = strDescription;
}

public boolean isFlagIGSTapplicable() {
	return flagIGSTapplicable;
}
public void setFlagIGSTapplicable(boolean flagIGSTapplicable) {
	this.flagIGSTapplicable = flagIGSTapplicable;
}
	
}
