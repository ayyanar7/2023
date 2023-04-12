package com.ag.bta.main.activities.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ag.bta.main.models.Customer;
import com.ag.bta.utils.DateUtils;
import com.ag.bta.utils.Log;
import com.ag.bta.utils.constant.Constants;
import com.ag.bta.utils.customfonts.MyEditText;
import com.ag.bta.utils.customfonts.MyTextView;
import com.ag.bta.main.R;

public class CustomerDetailsFragment extends Fragment {
	ImageView footerexit;
	MyTextView txtproceed = null;
	MyEditText txtname = null;
	MyEditText txtHouseStreet = null;
	MyEditText txtvillagelocality = null;
	MyEditText txtdistrict = null;
	MyEditText txtpincode = null;
	MyEditText txtemail = null;
	MyEditText txtContactno = null;
	MyEditText txtgstin = null;
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = View.inflate(getContext(), R.layout.customer, null);

		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);   
		txtproceed = (MyTextView) view.findViewById(R.id.CustNext);
		footerexit = (ImageView) view.findViewById(R.id.footersales_abort);
		footerexit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(validate(v)) {
				//Intent it = new Intent(CustomerDetailsActivity.this, SalesFragmentsActivity.class);
				//startActivity(it);
				}

			}
		});

		txtproceed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(validate(view)) {
				//Intent it = new Intent(CustomerDetailsActivity.this, SalesFragmentsActivity.class);
				//startActivity(it);
				}

			}
		});
	}
	
	private boolean validate(View view) {
		txtname = (MyEditText)  view.findViewById(R.id.sales_fname);
		txtHouseStreet = (MyEditText)  view.findViewById(R.id.sales_addr1);
		txtvillagelocality = (MyEditText)  view.findViewById(R.id.sales_addr2);
		txtdistrict = (MyEditText)  view.findViewById(R.id.sales_addr3);
		txtpincode = (MyEditText)  view.findViewById(R.id.sales_pincode);
		txtemail = (MyEditText)  view.findViewById(R.id.sales_email);
		txtContactno = (MyEditText)  view.findViewById(R.id.sales_contact);
		txtgstin = (MyEditText)  view.findViewById(R.id.sales_custGSTIN);

		String strname = txtname.getText().toString().trim();
		String strHouseStreet = txtHouseStreet.getText().toString().trim();
		String strvillagelocality = txtvillagelocality.getText().toString().trim();
		String strdistrict = txtdistrict.getText().toString().trim();
		String strpincode = txtpincode.getText().toString().trim();
		String stremail = txtemail.getText().toString().trim();
		String strcontactno = txtContactno.getText().toString().trim();
		String strgstin = txtgstin.getText().toString().trim();
		
		Log.d("Customer activity: strname " + strname);

		Log.d("Customer activity: strHouseStreet " + strHouseStreet);
		Log.d("Customer activity: strvillagelocality " + strvillagelocality);
		Log.d("Customer activity: strdistrict " + strdistrict);
		Log.d("Customer activity: strpincode " + strpincode);
		Log.d("Customer activity: stremail " + stremail);
		Log.d("Customer activity: strcontactno " + strcontactno);
		Log.d("Customer activity: strgstin " + strgstin);

		if (TextUtils.isEmpty(strname)) {
			txtname.setError("Please Enter Customer Name.");
			return false;
		} else if (strname.length() < 3) {
			txtname.setError("Customer Name Cannot be less than 3 char.");
			return false;
		}

		if (TextUtils.isEmpty(strHouseStreet)) {
			txtHouseStreet.setError("House No/Street cannot be empty.");
			return false;
		} else if (strHouseStreet.length() < 3) {
			txtHouseStreet.setError("House No/Street Cannot be less than 1 char.");
			return false;
		}

		if (TextUtils.isEmpty(strvillagelocality)) {
			txtvillagelocality.setError("Village/Locality cannot be empty.");
			return false;
		} else if (strvillagelocality.length() < 3) {
			txtvillagelocality.setError("Village/Locality Cannot be less than 3 char.");
			return false;
		}
		if (TextUtils.isEmpty(strdistrict)) {
			txtdistrict.setError("District cannot be empty.");
			return false;
		} else if (strdistrict.length() < 3) {
			txtdistrict.setError("District cannot be less than 3 char.");
			return false;
		}
		InvoiceDetails.getInstance().destroy();

		InvoiceDetails.getInstance().setStrInvoiceNo(Constants.INVOICE_NUM);
		InvoiceDetails.getInstance().setStrDate(DateUtils.getDate());
		InvoiceDetails.getInstance().setStrTime(DateUtils.getTime());
			Customer cust = new Customer();
		cust.setname(strname);
		cust.setemail(stremail);
		cust.setmobile(strcontactno);
		cust.setaddress1(strHouseStreet);
		cust.setaddress2(strvillagelocality);
		cust.setaddress3(strdistrict);
		cust.setGSTN(strgstin);
		cust.setPincode(strpincode);
		InvoiceDetails.getInstance().setoCust(cust);
		InvoiceDetails.getInstance().getoCust().display();
		// InvoiceDetails.getInstance().getoCust().setStrCustomerState(strCustomerState);
		// InvoiceDetails.getInstance().getoCust().setStrDescription(strDescription);
		// InvoiceDetails.getInstance().getoCust().setStrContactLandline(strContactLandline);
		/// nvoiceDetails.getInstance().getoCust().setStrContactFax(strContactFax);
		// InvoiceDetails.getInstance().getoCust().setGender(gender);
return true;
	}

}
