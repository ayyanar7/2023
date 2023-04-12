package com.ag.bta.main.activities.other;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import com.ag.bta.main.R;
import com.ag.bta.main.activities.HomeActivity;
import com.ag.bta.main.fragments.pdf.Invoice;
import com.ag.bta.main.models.BusinessEntity;
import com.ag.bta.main.models.Customer;
import com.ag.bta.main.models.Product;
import com.ag.bta.utils.DateUtils;
import com.ag.bta.utils.JSONUtil;
import com.ag.bta.utils.JsonUtils;
import com.ag.bta.utils.Log;
import com.ag.bta.utils.constant.Constants;
import com.ag.bta.utils.constant.EncryptConstant;
import com.ag.bta.utils.constant.Global;
import com.ag.bta.utils.database.SalesTable;
import com.ag.bta.utils.security.EncryptionDecryptionAES;

public class CartFragment extends Fragment {
	private TableLayout mTableLayout;
	ProgressDialog mProgressBar;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = View.inflate(getContext(), R.layout.orderdetails, null);

		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
 //for testing purpose.. to be removed
		Global.getInstance().setProduct(new Product());

		Log.d("TL", "1in on create");
		mProgressBar = new ProgressDialog(getContext());
		mTableLayout = (TableLayout) view.findViewById(R.id.tableInvoices);
		Log.d("TL", "2layour createa" + mTableLayout);

		mTableLayout.setStretchAllColumns(true);
		Log.d("TL", "3After setstretch colums1");

		startLoadData();
	}

	public void startLoadData() {
		Log.d("TL", "4 loading data");

		mProgressBar.setCancelable(false);
		mProgressBar.setMessage("Fetching Invoices..");
		mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressBar.show();
		new LoadDataTask().execute(0);
	}

	int textSize = 0, smallTextSize = 0, mediumTextSize = 0;

	public void loadData(ArrayList<HashMap<String, String>> itemlist, HashMap<String, String> invoiceDetails) {
		int leftRowMargin = 0;
		int topRowMargin = 0;
		int rightRowMargin = 0;
		int bottomRowMargin = 0;
		Log.d("TL", "inside load data funtion");

		textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
		smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
		mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);
		Log.d("TL", "1111111111111111111111111111111");

		Log.d("TL", "22222222222222222 date format");

		int rows = 4;
		// getActionBar().setTitle("Invoices (" + String.valueOf(rows) + ")");
		Log.d("TL", " 33333333333333333333 rows " + rows);
		mTableLayout.removeAllViews();
		Log.d("TL", " after remove all viewss..");

		// drawTitleRow(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
		drawContentRow(0, Constants.STR_ORDER_TITLE_1_Order_DETAILS, "Number",
				invoiceDetails.get(Constants.STR_JSON_INVOICE_NUM), invoiceDetails.get(Constants.STR_JSON_DATE),
				invoiceDetails.get(Constants.STR_JSON_TIME));
		drawLine(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
		drawContentRow(1, Constants.STR_ORDER_TITLE_2_CUSTOMER_DETAILS,
				invoiceDetails.get(Constants.STR_JSON_CUSTOMER_NAME),
				invoiceDetails.get(Constants.STR_JSON_FULL_ADDRESS), invoiceDetails.get(Constants.STR_JSON_GENDER),
				invoiceDetails.get(Constants.STR_JSON_AGE));
		drawLine(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

		drawContentRow(2, Constants.STR_ORDER_TITLE_3_GSTIN_DETAIL, "Customer's",
				invoiceDetails.get(Constants.STR_JSON_CUSTOMER_GSTIN), "Seller's",
				invoiceDetails.get(Constants.STR_JSON_SELLER_GSTIN));
		drawLine(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

		boolean isIgst = false;
		int size = itemlist.size();
		for (int i = 0; i < size; i++) {
			HashMap<String, String> item = itemlist.get(i);
			drawContentRow(3 + i, Constants.STR_ORDER_TITLE_4_ITEM_DETAILS, item.get(Constants.STR_JSON_1_DESCRIPTION),
					item.get(Constants.STR_JSON_2_DESCRIPTION), item.get(Constants.STR_JSON_PRODUCT_TYPE),
					"Qty: " + item.get(Constants.STR_JSON_QUANTITY));

			String ratetemp = null;
			String strTotalTaxProduct = "";

			if (item.get(Constants.BL_JSON_GST_APPLICABILITY).toLowerCase().equals("yes")) {
				isIgst = true;
				ratetemp = "Rate: " + item.get(Constants.STR_JSON_IGST_RATE) + "(IGST)";
				strTotalTaxProduct = item.get(Constants.STR_JSON_IGSTAX_PRODUCT_TOTAL);

			} else {
				ratetemp = "Rate: " + item.get(Constants.STR_JSON_CGST_RATE) + "% CGST "
						+ item.get(Constants.STR_JSON_CGST_RATE) + "% SGST";

				strTotalTaxProduct = "(" + item.get(Constants.STR_JSON_CGSTAX_PRODUCT_TOTAL) + " + "
						+ item.get(Constants.STR_JSON_CGSTAX_PRODUCT_TOTAL) + ")";

			}
			drawContentRow((4 + i), Constants.STR_ORDER_TITLE_5_PRICE_DETAILS,
					item.get(Constants.STR_JSON_PRICE_PRODUCT_TOTAL),
					"(Taxable: " + item.get(Constants.STR_JSON_TAXABLE_PRODUCT_TOTAL) + ")", ratetemp,
					strTotalTaxProduct);

			drawLine(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

		}
		// drawLine(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

		String ratetemp1 = null, strGrantTax = "";
		if (isIgst) {
			ratetemp1 = "Rate: IGST";

		} else {
			ratetemp1 = "Rate: (CGST + SGST)";
		}

		strGrantTax = "" + (Double.valueOf(invoiceDetails.get(Constants.STR_JSON_PRICE_GRANT_TOTAL))
				- Double.valueOf(invoiceDetails.get(Constants.STR_JSON_TAXABLE_GRANT_TOTAL)));

		drawContentRow((5 + size), Constants.STR_ORDER_TITLE_6_TOTAL_DETAILS,
				invoiceDetails.get(Constants.STR_JSON_PRICE_GRANT_TOTAL),
				"(Taxable: " + invoiceDetails.get(Constants.STR_JSON_TAXABLE_GRANT_TOTAL) + ")", ratetemp1,
				strGrantTax);

		drawLine(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

		addButton((6 + size), itemlist, invoiceDetails);
	}

	private void addButton(int rowId, final ArrayList<HashMap<String, String>> itemlist,
			final HashMap<String, String> invoiceDetails) {
//	       Button myButton = new Button(this);
//	       myButton.setText("Button :");
//	       myButton.setId(100);
//	       final int id_ = myButton.getId();

		// LinearLayout layout = (LinearLayout) findViewById(R.id.myDynamicLayout);
		TableRow.LayoutParams tparam1clm = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
				TableRow.LayoutParams.MATCH_PARENT, 3.5f);
		TableRow.LayoutParams tparam2clm = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
				TableRow.LayoutParams.MATCH_PARENT, 3f);

		TableRow.LayoutParams tparam3clm = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
				TableRow.LayoutParams.MATCH_PARENT, 3.5f);

		final TextView tv1clm = new TextView(getContext());
		tv1clm.setLayoutParams(tparam1clm);
		tv1clm.setText(" ");

		final TextView tv3clm = new TextView(getContext());
		tv3clm.setLayoutParams(tparam3clm);
		tv3clm.setText(" ");

		final LinearLayout layColumn2 = new LinearLayout(getContext());
		layColumn2.setOrientation(LinearLayout.HORIZONTAL);
		layColumn2.setPadding(0, 10, 0, 10);
		layColumn2.setBackgroundColor(Color.parseColor("#f8f8f8"));
		final TextView tv2clm = new TextView(getContext());
		tv2clm.setLayoutParams(tparam2clm);
		tv2clm.setPadding(5, 5, 5, 5);

		tv2clm.setClickable(true);
		// tv2clm.setBackgroundDrawable(background);
		tv2clm.setGravity(Gravity.CENTER);
		tv2clm.setText("Generate Invoice");
		tv2clm.setTextColor(Color.BLACK);
		tv2clm.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.angle_white));

		layColumn2.setHorizontalGravity(Gravity.CENTER);
		tv2clm.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Toast.makeText(getContext(), "Generate initiated ", Toast.LENGTH_SHORT).show();
				 
				Invoice invo = new Invoice();
				try {

					Log.d("Order Details .getApplicationContext() " + getContext());

					invo.generateItemisedInvoice(getContext(), getFileLocation(), itemlist, invoiceDetails);
					generateJSONUpdateDB(itemlist, invoiceDetails);
					// popup message..
					// alertDialog(getApplicationContext());
				} catch (Exception e) {
					// TODO: handle exception
					Log.d("Error while generating pdf " + e.toString());
					e.printStackTrace();
				}
			}
		});

		layColumn2.addView(tv2clm);
		TableRow.LayoutParams tparRow = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
				TableRow.LayoutParams.MATCH_PARENT, 10f);

		final TableRow tr = new TableRow(getContext());
		tr.setId(rowId);
		tr.setPadding(0, 0, 0, 0);
		tr.setLayoutParams(tparRow);
		tr.addView(tv1clm);
		tr.addView(layColumn2);
		tr.addView(tv3clm);

		mTableLayout.addView(tr);
	}

	private void alertDialog(Context ocnt) {
		final Context ocntf = ocnt;
		AlertDialog.Builder dialog = new AlertDialog.Builder(ocnt);
		dialog.setMessage("Invoice generated Successfully.");
		dialog.setTitle("Success!");
		dialog.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(ocntf, "directing to sales menu", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(getContext(), HomeActivity.class);
				startActivity(intent);
			}
		});
		/*
		 * dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) {
		 * Toast.makeText(ocntf,"cancel is clicked",Toast.LENGTH_LONG).show(); } });
		 */

	}

	public String getFileLocation() {
		String FILEROOT = Environment.getExternalStorageDirectory().toString() + "/PDF/";
		String FILE = null;

		// Create Directory in External Storage
		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/PDF");
		myDir.mkdirs();
		Log.d("PDFReader:PDFMain", "root: " + root);

		FILE = FILEROOT + DateUtils.getDateTime() + ".pdf";
		Log.d("PDFReader:PDFMain", "createpdf: " + FILE);

		return FILE;
	}

	private void drawContentRow(int rowId, String rowTitle, String detail1, String detail1Sub, String detail2,
			String detail2Sub) {
		/*************** Invoice Details ******************/
		/***************** Colum 1 ******************/
		TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
				TableLayout.LayoutParams.WRAP_CONTENT, 10f);
		trParams.setMargins(0, 0, 0, 0);

		TableRow.LayoutParams params1clm = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
				TableRow.LayoutParams.WRAP_CONTENT, 2f);
		TableRow.LayoutParams params2clm = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
				TableRow.LayoutParams.WRAP_CONTENT, 5.5f);
		TableRow.LayoutParams params3clm = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
				TableRow.LayoutParams.WRAP_CONTENT, 2.5f);

		Log.d("TL", "55555555555555555555555555555555");
		final TextView tv1clm = new TextView(getContext());
		/*
		 * tv1clm.setLayoutParams( new
		 * TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
		 * TableRow.LayoutParams.MATCH_PARENT));
		 */
		tv1clm.setLayoutParams(params1clm);
		tv1clm.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		Log.d("TL", "333333333333333333333333");
		tv1clm.setGravity(Gravity.LEFT);
		tv1clm.setPadding(5, 15, 0, 15);
		tv1clm.setBackgroundColor(Color.parseColor("#ffffff"));
		tv1clm.setTextColor(Color.parseColor("#000000"));
		tv1clm.setText(rowTitle);
		Log.d("TL", "5555555555555555555555555555555");

		/**************** column2 *******************************/
		final LinearLayout layColumn2 = new LinearLayout(getContext());
		layColumn2.setOrientation(LinearLayout.VERTICAL);
		layColumn2.setPadding(0, 10, 0, 10);
		layColumn2.setBackgroundColor(Color.parseColor("#f8f8f8"));
		final TextView tv2clm = new TextView(getContext());
		tv2clm.setLayoutParams(params2clm);
		/*
		 * tv2clm.setLayoutParams( new
		 * TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
		 * TableRow.LayoutParams.MATCH_PARENT));
		 */
		tv2clm.setPadding(5, 0, 0, 5);
		tv2clm.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
		Log.d("TL", "7777777777777777777777777777");
		tv2clm.setGravity(Gravity.TOP);
		tv2clm.setBackgroundColor(Color.parseColor("#f8f8f8"));
		tv2clm.setTextColor(Color.parseColor("#000000"));
		tv2clm.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
		tv2clm.setText(detail1);
		Log.d("TL", "999999999999999999999999999");
		layColumn2.addView(tv2clm);
		final TextView tv2bclm = new TextView(getContext());
		tv2bclm.setLayoutParams(
				new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
		tv2bclm.setGravity(Gravity.CENTER);
		tv2bclm.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		tv2bclm.setPadding(5, 1, 0, 5);
		if (rowId > 3) {
			tv2bclm.setTextColor(Color.parseColor("#00afff"));

		} else {
			tv2bclm.setTextColor(Color.parseColor("#aaaaaa"));
		}
		tv2bclm.setBackgroundColor(Color.parseColor("#f8f8f8"));
		tv2bclm.setText(detail1Sub);
		layColumn2.addView(tv2bclm);
		Log.d("TL", "88888888888888888888888888");

		/************************ column3 *******************************/
		// dateFormat.format(row.invoiceDate)
		final LinearLayout layColumn3 = new LinearLayout(getContext());
		layColumn3.setOrientation(LinearLayout.VERTICAL);
		layColumn3.setGravity(Gravity.RIGHT);
		layColumn3.setPadding(0, 5, 0, 5);
		layColumn3.setLayoutParams(params3clm);
		/*
		 * layColumn3.setLayoutParams( new
		 * TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
		 * TableRow.LayoutParams.MATCH_PARENT));
		 */
		Log.d("TL", "9999999999999999999999999999999");

		final TextView tv3 = new TextView(getContext());
		tv3.setLayoutParams(
				new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
		tv3.setPadding(3, 0, 1, 3);
		layColumn3.setBackgroundColor(Color.parseColor("#ffffff"));

		tv3.setGravity(Gravity.RIGHT);
		Log.d("TL", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		tv3.setBackgroundColor(Color.parseColor("#ffffff"));
		tv3.setTextColor(Color.parseColor("#000000"));
		tv3.setText(detail2);
		tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		Log.d("TL", "ccccccccccccccccccccccccccccc");

		layColumn3.addView(tv3);
		final TextView tv3b = new TextView(getContext());
		tv3b.setLayoutParams(
				new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
		tv3b.setGravity(Gravity.RIGHT);
		tv3b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		tv3b.setPadding(2, 2, 1, 5);
		tv3b.setTextColor(Color.parseColor("#00afff"));
		tv3b.setBackgroundColor(Color.parseColor("#ffffff"));

		tv3b.setText(detail2Sub);
		layColumn3.addView(tv3b);

		// add table row
		final TableRow tr = new TableRow(getContext());
		tr.setId(rowId);
		tr.setPadding(0, 0, 0, 0);
		tr.setLayoutParams(trParams);
		// tr.addView(tv);
		tr.addView(tv1clm);
		tr.addView(layColumn2);
		tr.addView(layColumn3);
		Log.d("TL", "dddddddddddddddddddddddddddddddddd");

		mTableLayout.addView(tr, trParams);

	}

	private void drawTitleRow(int leftRowMargin, int topRowMargin, int rightRowMargin, int bottomRowMargin) {
		final TextView tv1Title = new TextView(getContext());
		tv1Title.setLayoutParams(
				new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 2f));
		tv1Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
		tv1Title.setGravity(Gravity.LEFT);
		tv1Title.setPadding(5, 5, 0, 5);
		tv1Title.setText("Particulars");
		tv1Title.setBackgroundColor(Color.parseColor("#f7f7f7"));

		final TextView tv2Title = new TextView(getContext());
		final LinearLayout layColumn2Title = new LinearLayout(getContext());
		final LinearLayout layColumn3Title = new LinearLayout(getContext());

		tv2Title.setLayoutParams(
				new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 8f));
		tv2Title.setPadding(5, 5, 0, 5);
		tv2Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
		Log.d("TL", "66666666666666666666666666666");
		tv2Title.setText("Details");
		tv2Title.setBackgroundColor(Color.parseColor("#f0f0f0"));
		Log.d("TL", "88888888888888888888888888");
		layColumn2Title.setGravity(Gravity.CENTER);
		layColumn2Title.addView(tv2Title);
		/*
		 * final TextView tv3Title = new TextView(getContext()); tv3Title.setLayoutParams(new
		 * TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
		 * TableRow.LayoutParams.MATCH_PARENT)); tv3Title.setPadding(5, 5, 1, 5);
		 * tv3Title.setBackgroundColor(Color.parseColor("#f7f7f7"));
		 * 
		 * tv3Title.setText("Details");
		 * tv3Title.setBackgroundColor(Color.parseColor("#f7f7f7"));
		 * tv3Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
		 * layColumn3Title.addView(tv3Title);
		 */
		final TableRow trtitle = new TableRow(getContext());
		trtitle.setId(0);
		TableLayout.LayoutParams trParamstitle = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
				TableLayout.LayoutParams.WRAP_CONTENT, 10f);
		trParamstitle.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

		trtitle.setPadding(0, 0, 0, 0);
		trtitle.setLayoutParams(trParamstitle);
		trtitle.addView(tv1Title);
		trtitle.addView(layColumn2Title);
		trtitle.addView(layColumn3Title);

		mTableLayout.addView(trtitle, trParamstitle);

	}

	private void drawLine(int leftRowMargin, int topRowMargin, int rightRowMargin, int bottomRowMargin) {
		final TableRow trSep = new TableRow(getContext());
		TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
				TableLayout.LayoutParams.WRAP_CONTENT);
		trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
		trSep.setLayoutParams(trParamsSep);
		TextView tvSep = new TextView(getContext());
		TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
				TableRow.LayoutParams.WRAP_CONTENT);
		tvSepLay.span = 4;
		tvSep.setLayoutParams(tvSepLay);
		tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
		tvSep.setHeight(1);
		trSep.addView(tvSep);
		mTableLayout.addView(trSep, trParamsSep);
		Log.d("TL", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

	}

	protected void preloadData() {

		HashMap<String, String> invoiceDetails = new HashMap<String, String>();
		InvoiceDetails.getInstance().display();
		InvoiceDetails.getInstance().getoCust().display();
		Customer ocust = InvoiceDetails.getInstance().getoCust();
		invoiceDetails.put(Constants.STR_JSON_INVOICE_NUM, InvoiceDetails.getInstance().getStrInvoiceNo());
		invoiceDetails.put(Constants.STR_JSON_DATE, InvoiceDetails.getInstance().getStrDate());
		invoiceDetails.put(Constants.STR_JSON_TIME, InvoiceDetails.getInstance().getStrTime());

		invoiceDetails.put(Constants.STR_JSON_CUSTOMER_NAME, ocust.getname());
		invoiceDetails.put(Constants.STR_JSON_1_ADDRESS, ocust.getaddress1());
		invoiceDetails.put(Constants.STR_JSON_2_ADDRESS, ocust.getaddress2());
		invoiceDetails.put(Constants.STR_JSON_3_ADDRESS, ocust.getaddress3());
		invoiceDetails.put(Constants.STR_JSON_PINCODE_ADDRESS, ocust.getPincode());
		invoiceDetails.put(Constants.STR_JSON_STATE_ADDRESS, ocust.getState());
		invoiceDetails.put(Constants.STR_JSON_FULL_ADDRESS, ocust.getFullAddress());
		invoiceDetails.put(Constants.STR_JSON_CONTACT_NUM, ocust.getmobile());
		invoiceDetails.put(Constants.STR_JSON_CONTACT_EMAIL, ocust.getemail());
		invoiceDetails.put(Constants.STR_JSON_CONTACT_FAX, ocust.getfax());

		invoiceDetails.put(Constants.STR_JSON_GENDER, ocust.getGender());
		// invoiceDetails.put("Age",);
		invoiceDetails.put(Constants.STR_JSON_CUSTOMER_GSTIN, ocust.getGSTN());
		invoiceDetails.put(Constants.STR_JSON_SELLER_GSTIN, BusinessEntity.GSTIN);
		double dgrandtotalAmout = 0;
		double dgrandtotaltaxable = 0;
		double dgrandtotalCGStax = 0, dgrandtotalIGStax = 0;

		ArrayList<HashMap<String, String>> itemmapList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> itemmap = new HashMap<String, String>();
		ArrayList<Product> itemarray = InvoiceDetails.getInstance().getProductList();
		int size = itemarray.size();

		for (int i = 0; i < size; i++) {
			Product itm = itemarray.get(i);
			itemmap = new HashMap<String, String>();
			itemmap.put(Constants.STR_JSON_HSN_CODE, itm.getHSNCode());

			itemmap.put(Constants.STR_JSON_1_DESCRIPTION, itm.getStrDescription1());
			itemmap.put(Constants.STR_JSON_2_DESCRIPTION, itm.getStrDescription2());
			itemmap.put(Constants.STR_JSON_PRODUCT_TYPE, itm.getType()+"");
			itemmap.put(Constants.STR_JSON_QUANTITY, itm.getStrNoOfUnits());
			// itemmap.put(Constants.STR_JSON_PRICE_PER_UNIT, itm.getStrPrice());
			itm.calculateTax();// dont remove.
			// itemmap.put("TaxableAmountperunit", itm.getTaxableAmountperunit());
			// itemmap.put("TaxAmountperunit", itm.getTaxAmountUnit());
			if (itm.isFlagIGSTapplicable()) {
				itemmap.put(Constants.BL_JSON_GST_APPLICABILITY, "YES");
				itemmap.put(Constants.STR_JSON_IGST_RATE, String.valueOf(itm.getGSTRate()));
				itemmap.put(Constants.STR_JSON_CGST_RATE, "0");

			} else {
				itemmap.put(Constants.BL_JSON_GST_APPLICABILITY, "NO");
				itemmap.put(Constants.STR_JSON_CGST_RATE, String.valueOf(itm.getGSTRate() / 2));
				itemmap.put(Constants.STR_JSON_IGST_RATE, "0");

			}
			itemmap.put(Constants.STR_JSON_PRICE_PER_UNIT, itm.getStrPrice());
			itemmap.put(Constants.STR_JSON_CGSTAX_PER_UNIT, itm.getCGSTaxAmountUnit());
			itemmap.put(Constants.STR_JSON_IGSTAX_PER_UNIT, itm.getIGSTaxAmountUnit());
			itemmap.put(Constants.STR_JSON_TAXABLE_PER_UNIT, itm.getTaxableAmountperunit());

			itemmap.put(Constants.STR_JSON_PRICE_PRODUCT_TOTAL, itm.getTotalAmount());
			itemmap.put(Constants.STR_JSON_CGSTAX_PRODUCT_TOTAL, itm.getTotalCGSTaxAmount());
			itemmap.put(Constants.STR_JSON_IGSTAX_PRODUCT_TOTAL, itm.getTotalIGSTaxAmount());
			itemmap.put(Constants.STR_JSON_TAXABLE_PRODUCT_TOTAL, itm.getTotalTaxableAmount());

			dgrandtotalAmout = dgrandtotalAmout + Double.valueOf(itm.getTotalAmount());
			dgrandtotaltaxable = dgrandtotaltaxable + Double.valueOf(itm.getTotalTaxableAmount());
			dgrandtotalCGStax = dgrandtotalCGStax + Double.valueOf(itm.getTotalCGSTaxAmount());
			dgrandtotalIGStax = dgrandtotalIGStax + Double.valueOf(itm.getTotalIGSTaxAmount());

			itemmapList.add(itemmap);

		}
		invoiceDetails.put(Constants.STR_JSON_PRICE_GRANT_TOTAL, String.valueOf(dgrandtotalAmout));
		invoiceDetails.put(Constants.STR_JSON_TAXABLE_GRANT_TOTAL, String.valueOf(dgrandtotaltaxable));
		invoiceDetails.put(Constants.STR_JSON_CGSTAX_GRANT_TOTAL, String.valueOf(dgrandtotalCGStax));
		invoiceDetails.put(Constants.STR_JSON_IGSTAX_GRANT_TOTAL, String.valueOf(dgrandtotalIGStax));

		loadData(itemmapList, invoiceDetails);

	}

	public void generateJSONUpdateDB(ArrayList<HashMap<String, String>> itemmapList,
			HashMap<String, String> invoiceDetails) throws Exception {
		// invoiceDetails.put(Constants.STR_JSON_ITEM_LIST, itemmapList);
		Log.d("generateJSONUpdateDB Json..");

		JSONObject jsono = JSONUtil.generateJSON(itemmapList, invoiceDetails);
		Log.d(" AFTER generateJSONUpdateDB Json..");

		if (jsono != null) {

			// update Dataase.
			Log.d(" FEROE UpdateDB Json..");
			EncryptionDecryptionAES aesen = new EncryptionDecryptionAES();
			HashMap<String, byte[]> strencypt = aesen.encryptBytes(jsono.toString().getBytes(), "PASSWORD");

			Log.d("Encrypted string..." + strencypt);
			byte[] data = aesen.decryptData(strencypt.get(EncryptConstant.ENCRYPT),strencypt.get(EncryptConstant.SALT),strencypt.get(EncryptConstant.IV), "PASSWORD");
			Log.d("Decrypted String..." +new String(data) );
			SalesTable saleTable = new SalesTable(getContext());

			saleTable.open();
			saleTable.insert(2020, "JULY", 19, strencypt.get(EncryptConstant.SALT),strencypt.get(EncryptConstant.IV),strencypt.get(EncryptConstant.ENCRYPT));
			Cursor curs = saleTable.fetch();
			Log.d("colun count:" + curs.getColumnCount());
			Log.d("AFTER DATAASE UPDATE Json..");

		} else {
			Log.d("Null Json..");
		}

	}

	class LoadDataTask extends AsyncTask<Integer, Integer, String> {
		@Override
		protected String doInBackground(Integer... params) {
			try {
				Log.d("TL", "inside load run");

				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Task Completed.";
		}

		@Override
		protected void onPostExecute(String result) {
			Log.d("TL", "post excetution of load run thread.");

			mProgressBar.hide();
			// generate
			preloadData();
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
		}
	}
}
