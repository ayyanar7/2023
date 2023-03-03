package com.ag.bta.main.models;

import java.util.ArrayList;

public class BusinessEntity {
	public  static final String SHOP_NAME = "Business Agent";
	public  static final String GSTIN= "34AOSPT1398E2ZE";

	
Proprietor prop = null;//new Proprietor();
ArrayList<Product> stockList = new ArrayList<Product>();
ArrayList<OrderDetails> orderList = new ArrayList<OrderDetails>();
 public Proprietor getPropreitorObject() {
	if(prop == null)
		prop = new Proprietor();
	return prop;
}

public void getPropreitorObject(Proprietor proprietor) {
	 prop = proprietor;
}
public void setInvoiceArrayList(ArrayList<OrderDetails> invoiceslist) {
	orderList = invoiceslist;
}

public void setstockArrayList(ArrayList<Product> argstockList) {
	stockList = argstockList;
}

public ArrayList<OrderDetails> getInvoiceArrayList() {
	return orderList ;
}

public ArrayList<Product> getstockArrayList() {
return stockList;
}

}
