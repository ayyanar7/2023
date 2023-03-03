package com.ag.bta.main.models;


import com.ag.bta.utils.Log;;

public class Customer {  
	private String name= null;
	private String gender = null;
	private String address1= null;
	private String address2= null;
	private String address3= null;
	private String  Pincode= null;
	private String  State= null;
	private String  mobile= null;
	private String landline= null;
	private String fax= null;
	private String email= null;
	private String  GSTN= null;

	private String strDescription= null;
	
	public Customer() {
		
	}
	public void display() {
		Log.d("Customer: name "+name);		 
		Log.d("Customer: gender "+gender);
		Log.d("Customer: address1 "+address1);
		Log.d("Customer: address2 "+address2);
		Log.d("Customer: address3 "+address3);
		Log.d("Customer:  Pincode "+ Pincode);
		Log.d("Customer:  State "+ State);
		Log.d("Customer:  mobile "+ mobile);
		Log.d("Customer: landline "+landline);
		Log.d("Customer: fax "+fax);
		Log.d("Customer: email "+email);
		Log.d("Customer:  GSTN "+ GSTN);

	}
	
	public String getFullAddress() {
		return address1+"\n"+address2+"\n "+address3+"-"+ Pincode;
	}
	
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getPincode() {
		return  Pincode;
	}
	public void setPincode(String  Pincode) {
		this. Pincode =  Pincode;
	}
	public String getaddress3() {
		return address3;
	}
	public void setaddress3(String address3) {
		this.address3 = address3;
	}
	public String getaddress2() {
		return address2;
	}
	public void setaddress2(String address2) {
		this.address2 = address2;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getaddress1() {
		return address1;
	}
	public void setaddress1(String address1) {
		this.address1 = address1;
	}
	 
	public String getStrDescription() {
		return strDescription;
	}
	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}
	 
	 
	public String getGSTN() {
		return  GSTN;
	}
	public void setGSTN(String  GSTN) {
		this. GSTN =  GSTN;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getState() {
		return  State;
	}
	public void setState(String  State) {
		this. State =  State;
	}
	public String getmobile() {
		return  mobile;
	}
	public void setmobile(String  mobile) {
		this. mobile =  mobile;
	}
	public String getfax() {
		return fax;
	}
	public void setfax(String fax) {
		this.fax = fax;
	}
	public String getlandline() {
		return landline;
	}
	public void setlandline(String landline) {
		this.landline = landline;
	}


}
