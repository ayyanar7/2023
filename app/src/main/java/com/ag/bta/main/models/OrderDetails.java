package com.ag.bta.main.models;

import com.ag.bta.utils.Log;


import java.util.ArrayList;

public class OrderDetails {

	private String strDate =  null;
	private String strTime = null;
	private String strOrderNo = null;
     private static OrderDetails orderdetail= null;
	private Customer oCust = null;
	ArrayList<Product> productlist =new ArrayList<Product>();
	private OrderDetails(){
		productlist = new ArrayList<Product>();
		oCust = new Customer();
	}
	public void display() {
		Log.d("Invoice Details: strDate "+strDate);
		Log.d("Invoice Details: strTime "+strTime);
		Log.d("Invoice Details: strInvoiceNo "+strOrderNo);
		Log.d("Invoice Details: oCust "+oCust);
		Log.d("Invoice Details: productlist "+productlist);
		for(Product item:productlist ) {
			Log.d("Invoice Details: item "+item);
			if(item != null) {
			item.display();
			}
		}
	}
	
 	public void destroy() {
		    strDate =  null;
		    strTime = null;
		strOrderNo = null;
		    oCust = null;
		    productlist =null;
		    productlist = new ArrayList<Product>();

	}
	public void instantiateCustomer() {
		 
			oCust = null;
			oCust =  new Customer();
			productlist = new ArrayList<Product>();
}
	public static OrderDetails getInstance() {
		if(orderdetail == null) {
			orderdetail =  new OrderDetails();
		}
			return orderdetail;

	}
	
	public String getStrSellerGSTN() {
		return BusinessEntity.GSTIN;
	}
	public void addItem(Product item) {
		if(item == null) {
			Log.d("Null value passed..not added to the list.");
			return;
		}
		if(productlist==null) {
			Log.d("Item container arraylits null ...please instatiace the Invoice details");
			productlist = new ArrayList<Product>();
			return;
		}
		

		productlist.add(item);
		Log.d("Item added to List.");
 	}
	
	protected void removeItem(Product item) {
		if(productlist==null) {
			Log.d("revomeitem: Item container arraylits null ...please instatiace the Invoice details");
			return;
		}
		if(item == null) {
			Log.d("Null value passed..not removed from the list.");
			return;
		}

		productlist.remove(item);
	}

	
	
	public ArrayList<Product> getItemList() {
		return productlist;
	}
	public void setItemList(ArrayList<Product> itemlist) {
		this.productlist = itemlist;
	}
	
	public String getStrDate() {
		return strDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public String getStrTime() {
		return strTime;
	}
	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}
	public String getStrInvoiceNo() {
		return strOrderNo;
	}
	public void setStrInvoiceNo(String strInvoiceNo) {
		this.strOrderNo = strInvoiceNo;
	}
	public Customer getoCust() {
		return oCust;
	}
	public void setoCust(Customer oCust) {
		this.oCust = oCust;
	}

}
