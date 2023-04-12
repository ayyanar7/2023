package com.ag.bta.main.activities.other;

import com.ag.bta.main.models.BusinessEntity;
import com.ag.bta.main.models.Customer;
import com.ag.bta.main.models.Product;
import com.ag.bta.utils.Log;


import java.util.ArrayList;

public class InvoiceDetails {

	private String strDate =  null;
	private String strTime = null;
	private String strInvoiceNo = null;
     private static InvoiceDetails invoicedetail= null;
	private Customer oCust = null;
	ArrayList<Product> Productslist =new ArrayList<Product>();
	private InvoiceDetails(){
		Productslist = new ArrayList<Product>();
		oCust = new Customer();
	}
	public void display() {
		 
		Log.d("Invoice Details: strDate "+strDate);
		Log.d("Invoice Details: strTime "+strTime);
		Log.d("Invoice Details: strInvoiceNo "+strInvoiceNo);
		Log.d("Invoice Details: oCust "+oCust);
		Log.d("Invoice Details: Productslist "+Productslist);
		for(Product Product:Productslist ) {
			Log.d("Invoice Details: Product "+Product);
			if(Product != null) {
			Product.display();
			}
		}
	}
	
 	public void destroy() {
		    strDate =  null;
		    strTime = null;
		    strInvoiceNo = null;
		    oCust = null;
		    Productslist =null;
		    Productslist = new ArrayList<Product>();

	}
	public void instantiateCustomer() {
		 
			oCust = null;
			oCust =  new Customer();
			Productslist = new ArrayList<Product>();
}
	public static InvoiceDetails getInstance() {
		if(invoicedetail == null) {
			invoicedetail =  new InvoiceDetails();
		}
			return invoicedetail;

	}
	
	public String getStrSellerGSTN() {
		return BusinessEntity.GSTIN;
	}
	public void addProduct(Product Product) {
		if(Product == null) {
			Log.d("Null value passed..not added to the list.");
			return;
		}
		if(Productslist==null) {
			Log.d("Product container arraylits null ...please instatiace the Invoice details");
			Productslist = new ArrayList<Product>();
			return;
		}
		

		Productslist.add(Product);
		Log.d("Product added to List.");
 	}
	
	protected void removeProduct(Product Product) {
		if(Productslist==null) {
			Log.d("revomeProduct: Product container arraylits null ...please instatiace the Invoice details");
			return;
		}
		if(Product == null) {
			Log.d("Null value passed..not removed from the list.");
			return;
		}

		Productslist.remove(Product);
	}

	
	
	public ArrayList<Product> getProductList() {
		return Productslist;
	}
	public void setProductList(ArrayList<Product> Productlist) {
		this.Productslist = Productlist;
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
		return strInvoiceNo;
	}
	public void setStrInvoiceNo(String strInvoiceNo) {
		this.strInvoiceNo = strInvoiceNo;
	}
	public Customer getoCust() {
		return oCust;
		 
	}
	public void setoCust(Customer oCust) {
		this.oCust = oCust;
	}

}
