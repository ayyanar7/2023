package com.ag.bta.main.activities.other;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ag.bta.main.R;
import com.ag.bta.utils.Log;
import com.ag.bta.utils.constant.Global;
import com.ag.bta.utils.customfonts.MyEditText;
import com.ag.bta.utils.customfonts.MyTextView;

//import android.support.v7.app.AppCompatActivity;
public class SalesDetailsGetterFragment extends Fragment {

	 MyTextView txtViewproceed = null;
	 MyEditText txttotal = null;
	 MyEditText txtgstrate = null;
	 MyEditText txtquantity = null;
	ToggleButton gstApplicale = null;
	ImageView imgView = null;
	MyEditText imgViewpower = null;
	MyEditText imgViewVolt = null;
	MyEditText imgViewCurrent = null;
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = View.inflate(getContext(), R.layout.sales_child_3, null);

		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
  
		imgView = (ImageView) view.findViewById(R.id.item_logo_img);
		imgView.setImageResource(getImageResourceId());
		imgViewpower = (MyEditText) view.findViewById(R.id.powerrating);
		imgViewVolt = (MyEditText) view.findViewById(R.id.voltage_rating);
		imgViewCurrent = (MyEditText) view.findViewById(R.id.currentrating);

		txttotal = ( MyEditText) view.findViewById(R.id.sales2_amountTotal);
		txtgstrate = ( MyEditText) view.findViewById(R.id.sales2_gstrate);
		txtquantity = ( MyEditText) view.findViewById(R.id.sales2_quantity);
		gstApplicale = (ToggleButton) view.findViewById(R.id.sales_toggleButton);

		txtViewproceed = ( MyTextView) view.findViewById(R.id.sales_sNext);
		txtViewproceed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("onclick " + SalesDetailsGetterFragment.this);
				if (validate()) {
					// Item.getInstance(Item.getType(),
					// false).setStrManufacturerName(tvmassimo.getText().toString().trim());;
					//Intent it = new Intent(SalesDetailsGetterActivity.this, OrderDetailsActivity.class);
					//startActivity(it);
				}

			}
		});
		
		gstApplicale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {

					Log.d("Toggle enaled: IGST is applicale");
					// The toggle is enabled
					Global.getInstance().getProduct().setFlagIGSTapplicable(true);
				} else {
					Log.d("Toggle enaled: IGST is not applicale");

					// The toggle is disabled
					Global.getInstance().getProduct().setFlagIGSTapplicable(false);

				}
			}
		});


	}



	private boolean validate() {
		
		String strpower = imgViewpower.getText().toString();
		String strVoltage = imgViewVolt.getText().toString();
		String strCurrent = imgViewCurrent.getText().toString();
		
		if (TextUtils.isEmpty(strpower)) {
			imgViewpower.setError("Power rating cannot be empty.");
			return false;
		} else if (!TextUtils.isDigitsOnly(strpower)) {
			imgViewpower.setError("Only digits allowed in Power rating field.");
			return false;
		}
		
		if (TextUtils.isEmpty(strVoltage)) {
			imgViewVolt.setError("Voltage rating cannot be empty.");
			return false;
		} else if (!TextUtils.isDigitsOnly(strVoltage)) {
			imgViewVolt.setError("Only digits allowed in Voltage rating field.");
			return false;
		}
		if (TextUtils.isEmpty(strCurrent)) {
			imgViewCurrent.setError("Current rating cannot be empty.");
			return false;
		} else if (!TextUtils.isDigitsOnly(strCurrent)) {
			imgViewCurrent.setError("Only digits allowed in Current rating field.");
			return false;
		}
		Log.d("sales detail activity: imgViewpower "+strpower);
		Log.d("sales detail activity: imgViewVolt "+strVoltage);
		Log.d("sales detail activity: imgViewCurrent "+strCurrent);
		Global.getInstance().getProduct().setStrPowerRating(strpower);
		Global.getInstance().getProduct().setStrVoltageRating(strVoltage);
		Global.getInstance().getProduct().setStrCurrentrating(strCurrent);

		
		String strtotal = txttotal.getText().toString().trim();
		String strgstrate = txtgstrate.getText().toString().trim();
		String strquantity = txtquantity.getText().toString().trim();
		Log.d("sales detail activity: strtotal "+strtotal);
		Log.d("sales detail activity: strgstrate "+strgstrate);
		Log.d("sales detail activity: strquantity "+strquantity);
		Log.d("sales detail activity: gstApplicale "+gstApplicale);

		// String gstApplicale = gstApplicale.
		if (TextUtils.isEmpty(strtotal)) {
			txttotal.setError("Amount cannot be empty.");
			return false;
		} else if (!TextUtils.isDigitsOnly(strtotal)) {
			txttotal.setError("Only digits allowed in Amount field.");
			return false;
		}
		if (TextUtils.isEmpty(strquantity)) {
			txtquantity.setError("Quantity cannot be empty.");
			return false;
		} else if (strquantity.equals("0")) {
			txtquantity.setError("Quantity cannot be zero.");
			return false;
		}

		/*
		 * if(TextUtils.isEmpty(strgstrate)) {
		 * txtgstrate.setError("Gst Rate cannot be empty."); return; }else
		 * if(!TextUtils.isDigitsOnly(strgstrate)) {
		 * txtgstrate.setError("Only digits allowed as GST Rate."); return; }
		 */
		Global.getInstance().getProduct().setStrNoOfUnits(strquantity);
		Global.getInstance().getProduct().setStrPrice(strtotal);

		Log.d("strpower : " + strpower);
		Log.d("strVoltage : " + strVoltage);

		Log.d("strCurrent : " + strCurrent);
return true;
	}

	private int getImageResourceId() {


			return R.drawable.ic_launcher;




	}

	private boolean validate(String strarg) {
		if (strarg.trim().length() == 0) {
			return false;
		}
		return true;
	}
}
