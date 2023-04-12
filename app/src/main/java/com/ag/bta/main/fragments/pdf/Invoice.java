package com.ag.bta.main.fragments.pdf;

import android.content.Context;


import com.ag.bta.main.models.BusinessEntity;
import com.ag.bta.utils.constant.Constants;
import com.ag.bta.utils.DateUtils;
import com.ag.bta.utils.Log;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
 
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Invoice {

	String FILEROOT = null;

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	BaseColor mColorAccent = new BaseColor(0, 153, 204, 255);
	BaseFont fBaseFont = null;
	BaseFont ftitleFont = null;

	private BaseFont getFontFromAsset()   {
		// TODO Auto-generated method stub
		try {
			if (fBaseFont == null)
				fBaseFont = BaseFont.createFont("assets/fonts/brandon_medium.otf", "UTF-8", BaseFont.EMBEDDED);

		} catch (Exception e) {

			// TODO: handle exceptionger
			Log.d("Unable to create font. Hence creating Times New roman");
			// need to write logic
			Log.d(getAppContext(), "Font creation failed. Times new roman creted.");
			e.printStackTrace();
		}
		return fBaseFont;
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private void generateTitleSection(Document document) throws Exception {

//    Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD
//			| Font.UNDERLINE, BaseColor.GRAY);
		PdfPTable table = new PdfPTable(1);

			ftitleFont = BaseFont.createFont("assets/fonts/fontpdf/XeroxSerifNarrowBold.ttf", "UTF-8", BaseFont.EMBEDDED);
		table.setWidthPercentage(100.0f);
		table.spacingAfter();
		Font mOrderTitleFont = new Font(ftitleFont, 25.0f, Font.BOLD | Font.UNDERLINE, BaseColor.BLACK);
		Chunk mOrderDetailsTitleChunk = new Chunk("VT Power Solutions\n", mOrderTitleFont);
		Paragraph mOrderDetailsTitleParagraph = new Paragraph(mOrderDetailsTitleChunk);
		addEmptyLine(mOrderDetailsTitleParagraph, 1);
		mOrderDetailsTitleParagraph.setAlignment(Element.ALIGN_CENTER);
		PdfPCell ccell = new PdfPCell(mOrderDetailsTitleParagraph);
		ccell.setUseVariableBorders(true);
		ccell.setHorizontalAlignment(Element.ALIGN_CENTER);
		ccell.setBorder(Rectangle.NO_BORDER);
		table.addCell(ccell);

 			 
		
		Font mOrderTitleFont1 = new Font(getFontFromAsset(), 10.0f, Font.NORMAL | Font.UNDERLINE, BaseColor.BLACK);
		Chunk mOrderDetailsTitleChunk1 = new Chunk("Online UPS, Invertor & Battery Sales & Service\n",
				mOrderTitleFont1);
		Paragraph mOrderDetailsTitleParagraph1 = new Paragraph(mOrderDetailsTitleChunk1);
		// addEmptyLine(mOrderDetailsTitleParagraph1, 1);
		mOrderDetailsTitleParagraph1.setAlignment(Element.ALIGN_CENTER);
		PdfPCell ccell1 = new PdfPCell(mOrderDetailsTitleParagraph1);
		ccell1.setBorder(Rectangle.NO_BORDER);
		ccell1.setUseVariableBorders(true);
		ccell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(ccell1);

		// Font mOrderTitleFont2 = new Font(getFontFromAsset(), 10.0f, Font.BOLD |
		// Font.UNDERLINE, BaseColor.BLACK);

		Chunk mOrderDetailsTitleChunk2 = new Chunk(
				"No.285/5, Thiruvalluvar Salai, Pillaithottam(Back Side Manimegalai School), Puducherry-605013",
				mOrderTitleFont1);
		Paragraph mOrderDetailsTitleParagraph2 = new Paragraph(mOrderDetailsTitleChunk2);
		mOrderDetailsTitleParagraph2.setAlignment(Element.ALIGN_CENTER);
		// addEmptyLine(mOrderDetailsTitleParagraph2, 1);
		PdfPCell ccell2 = new PdfPCell(mOrderDetailsTitleParagraph2);
		ccell2.setBorder(Rectangle.NO_BORDER);
		ccell2.setUseVariableBorders(true);
		ccell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(ccell2);

		Font mOrderTitleFont3 = new Font(getFontFromAsset(), 7.0f, Font.BOLD | Font.UNDERLINE, BaseColor.RED);

		Chunk mOrderDetailsTitleChunk3 = new Chunk("Contact: 9787431714, 9043110402 | Email: vtpowersln@gmail.com",
				mOrderTitleFont3);
		Paragraph mOrderDetailsTitleParagraph3 = new Paragraph(mOrderDetailsTitleChunk3);
		mOrderDetailsTitleParagraph3.setAlignment(Element.ALIGN_CENTER);
		mOrderDetailsTitleParagraph3.setSpacingAfter(5.0f);

		// addEmptyLine(mOrderDetailsTitleParagraph3, 1);
		PdfPCell ccell3 = new PdfPCell(mOrderDetailsTitleParagraph3);
		ccell3.setBorder(Rectangle.NO_BORDER);
		ccell3.setUseVariableBorders(true);
		ccell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(ccell3);

		document.add(table);

	}

	private void generateAddressSection(Document document, String invoice, String name, String streetnlocality,
			String posttaluk, String distnpincode, String state, String mobile, String email, String strGSTIN)
			throws Exception {

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100.0f);
		table.spacingAfter();
		Log.d("address section");
		Font fontgray = new Font(getFontFromAsset(), 10.0f, Font.NORMAL, BaseColor.DARK_GRAY);
		Font fontlackold = new Font(getFontFromAsset(), 10.0f, Font.BOLD | Font.UNDERLINE, BaseColor.BLACK);
		Font fontlack = new Font(getFontFromAsset(), 9.0f, Font.NORMAL, BaseColor.BLACK);
		Paragraph newline = new Paragraph("\n");

		// Chunk mOrderDetailsTitleChunk = new Chunk("Customer Address\n", fontlack);
		Paragraph mOrderDetailsTitleParagraph = new Paragraph("");
		mOrderDetailsTitleParagraph.setAlignment(Element.ALIGN_LEFT);
		// addEmptyLine(mOrderDetailsTitleParagraph, 1);
		Paragraph tempPara0 = new Paragraph("Customer Address:\n", fontlackold);
		tempPara0.setAlignment(Element.ALIGN_LEFT);
		mOrderDetailsTitleParagraph.add(tempPara0);
		// mOrderDetailsTitleParagraph.add(newline);

		Paragraph tempPara = new Paragraph("  \n", fontlack);
		tempPara.setAlignment(Element.ALIGN_LEFT);
		mOrderDetailsTitleParagraph.add(tempPara);
		// mOrderDetailsTitleParagraph.add(newline);

		Paragraph namePara = new Paragraph("Mr/Mrs. " + name, fontgray);
		namePara.setAlignment(Element.ALIGN_LEFT);
		mOrderDetailsTitleParagraph.add(namePara);
		mOrderDetailsTitleParagraph.add(newline);

		Paragraph adr1Para = new Paragraph(streetnlocality, fontgray);
		adr1Para.setAlignment(Element.ALIGN_LEFT);
		mOrderDetailsTitleParagraph.add(adr1Para);
		mOrderDetailsTitleParagraph.add(newline);

		Paragraph adr2para = new Paragraph(posttaluk, fontgray);
		adr2para.setAlignment(Element.ALIGN_LEFT);
		mOrderDetailsTitleParagraph.add(adr2para);
		mOrderDetailsTitleParagraph.add(newline);

		Paragraph adr3para = new Paragraph(distnpincode + ", " + state, fontgray);
		adr3para.setAlignment(Element.ALIGN_LEFT);
		mOrderDetailsTitleParagraph.add(adr3para);
		mOrderDetailsTitleParagraph.add(newline);

		Paragraph contactPara = new Paragraph("\n \t" + "Contact: " + mobile + "\t Email: " + email, fontgray);
		contactPara.setAlignment(Element.ALIGN_LEFT);
		mOrderDetailsTitleParagraph.add(contactPara);
		mOrderDetailsTitleParagraph.add(newline);

		Paragraph gstinPara = new Paragraph("GSTIN:" + strGSTIN.toUpperCase(), fontgray);
		gstinPara.setAlignment(Element.ALIGN_LEFT);
		mOrderDetailsTitleParagraph.add(gstinPara);

		PdfPCell ccell = new PdfPCell(mOrderDetailsTitleParagraph);
		ccell.setUseVariableBorders(true);
		ccell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(ccell);
		Log.d("address middlesection");

		Paragraph mOrderDetailsTitleParagraph2 = new Paragraph(" \n");
		mOrderDetailsTitleParagraph2.setAlignment(Element.ALIGN_LEFT);
		Paragraph GSTIN = new Paragraph("GSTIN: " + BusinessEntity.GSTIN + "\n", fontlack);
		GSTIN.setAlignment(Element.ALIGN_LEFT);
		// addEmptyLine(mOrderDetailsTitleParagraph2, 1);
		mOrderDetailsTitleParagraph2.add(GSTIN);
		mOrderDetailsTitleParagraph2.add(newline);


		Paragraph tempPara2 = new Paragraph("Date: " + DateUtils.getDisplayDateTime() + "\n", fontlack);
		tempPara2.setAlignment(Element.ALIGN_LEFT);
		mOrderDetailsTitleParagraph2.add(tempPara2);
		mOrderDetailsTitleParagraph2.add(newline);

		Paragraph tempPara3 = new Paragraph("Invoice No: " + invoice + "\n", fontlack);
		tempPara3.setAlignment(Element.ALIGN_LEFT);
		mOrderDetailsTitleParagraph2.add(tempPara3);
		mOrderDetailsTitleParagraph2.add(newline);

		PdfPCell ccell1 = new PdfPCell(mOrderDetailsTitleParagraph2);
		// ccell.setBorder(Rectangle.NO_BORDER);
		ccell1.setUseVariableBorders(true);
		ccell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(ccell1);
		Log.d("address section");

		// table.setHeaderRows(1);
		document.add(table);

	}

	private void generateInvoiceContent(Document dcoument, ArrayList<HashMap<String, String>> argitemlist) throws Exception {
		// TODO Auto-generated method stub

		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100.0f);
		int[] clmwidths = { 8, 85, 8, 20 };
		table.setWidths(clmwidths);
		table.setSpacingBefore(5.0f);
		table.setSpacingAfter(5.0f);

		Log.d("address section");
		// Font fontgray = new Font(getFontFromAsset(), 10.0f, Font.NORMAL ,
		// BaseColor.DARK_GRAY);
		Font fontlackold = new Font(getFontFromAsset(), 10.0f, Font.NORMAL | Font.UNDERLINE, BaseColor.BLACK);
		Font fontlack = new Font(getFontFromAsset(), 10.0f, Font.NORMAL, BaseColor.BLACK);
		// Font fontalignRight = new Font(getFontFromAsset(), 9.0f, Font.NORMAL,
		// BaseColor.BLACK);

		Paragraph newline = new Paragraph("\n");

		Paragraph mOrderDetailsTitleParagraph = new Paragraph("");
		mOrderDetailsTitleParagraph.setAlignment(Element.ALIGN_CENTER);
		Paragraph cloumnTitle1 = new Paragraph("S.NO\n", fontlackold);
		cloumnTitle1.setAlignment(Element.ALIGN_CENTER);
		mOrderDetailsTitleParagraph.add(cloumnTitle1);
		// mOrderDetailsTitleParagraph.add(newline);
		PdfPCell ccell1 = new PdfPCell(mOrderDetailsTitleParagraph);

		ccell1.setUseVariableBorders(true);
		ccell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(ccell1);
		Log.d("address section");

		Paragraph cloumnTitleorder = new Paragraph("OrderDetails\n", fontlackold);
		Paragraph cloumnTitletax = new Paragraph("Tax%\n", fontlackold);

		cloumnTitleorder.setAlignment(Element.ALIGN_LEFT);
		cloumnTitletax.setAlignment(Element.ALIGN_RIGHT);

		PdfPCell ccelltiteleorder = new PdfPCell(cloumnTitleorder);
		PdfPCell ccelltiteletax = new PdfPCell(cloumnTitletax);
		ccelltiteleorder.setBorder(Rectangle.NO_BORDER);
		ccelltiteletax.setBorder(Rectangle.NO_BORDER);
		ccelltiteleorder.setUseVariableBorders(true);
		ccelltiteletax.setUseVariableBorders(true);
		ccelltiteleorder.setHorizontalAlignment(Element.ALIGN_LEFT);
		ccelltiteletax.setHorizontalAlignment(Element.ALIGN_RIGHT);

		PdfPTable titleinnertable = new PdfPTable(2);
		titleinnertable.addCell(ccelltiteleorder);
		titleinnertable.addCell(ccelltiteletax);
		table.addCell(titleinnertable);

		Log.d("address section");

		Paragraph cloumntitleqty = new Paragraph("Qty\n", fontlackold);
		cloumntitleqty.setAlignment(Element.ALIGN_CENTER);
		// mOrderDetailsTitleParagraph.add(cloumnTitle2);
		// mOrderDetailsTitleParagraph.add(newline);
		PdfPCell ccellqty2 = new PdfPCell(cloumntitleqty);
		ccellqty2.setUseVariableBorders(true);
		ccellqty2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(ccellqty2);
		Log.d("address section");

		Paragraph cloumnTitle3 = new Paragraph("Unit Price\n\n", fontlackold);
		cloumnTitle3.setAlignment(Element.ALIGN_CENTER);
		// mOrderDetailsTitleParagraph.add(cloumnTitle3);
		// mOrderDetailsTitleParagraph.add(newline);
		PdfPCell ccell3 = new PdfPCell(cloumnTitle3);
		ccell3.setUseVariableBorders(true);
		ccell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(ccell3);
		Log.d("address section");
		table.setHeaderRows(1);

		// inertale
		PdfPTable innertable = null;
		float[] clmwidthsinner = { 2f, 12f, 3f };

 		if(argitemlist != null) {
			int size = argitemlist.size();
		for (int i = 0; i < size; i++) {
			HashMap<String, String> itemmap = argitemlist.get(i);
			innertable = new PdfPTable(3);
			innertable.setWidths(clmwidthsinner);
			Log.d("inner tale section");
			String imgfile = null;
			imgfile = getIconLocation(itemmap.get(Constants.STR_JSON_PRODUCT_TYPE));
			Log.d("imgfile location: "+imgfile);

			table.addCell((i + 1) + "");// sno Co

			Log.d("price per unit in invoice generT: "+itemmap.get(Constants.STR_JSON_PRICE_PER_UNIT));
			Log.d("Taxale per unit in invoice generT: "+itemmap.get(Constants.STR_JSON_TAXABLE_PER_UNIT));

			// create list View items
			createListViewItems(table, innertable, fontlack, imgfile, itemmap.get(Constants.STR_JSON_1_DESCRIPTION),
					itemmap.get(Constants.STR_JSON_2_DESCRIPTION), "("+getGSTRate(itemmap.get(Constants.STR_JSON_PRODUCT_TYPE))+"%)",
					itemmap.get(Constants.STR_JSON_TAXABLE_PER_UNIT));
 			table.addCell(getRightAlignPara(itemmap.get(Constants.STR_JSON_QUANTITY), fontlack));// qty

			Paragraph tempamt = new Paragraph(itemmap.get(Constants.STR_JSON_PRICE_PER_UNIT), fontlack);
			tempamt.setAlignment(Element.ALIGN_RIGHT);
			table.addCell(tempamt);// totalamt

		}
		}
		/*
		 * table.addCell(newline); table.addCell(newline); table.addCell(newline);
		 * table.addCell(newline); table.addCell(newline); table.addCell(newline);
		 */
		dcoument.add(table);

	}
	public double getGSTRate(String strType) {
		int itemTtype =-1;

		if(itemTtype<0) {
			Log.d("Itemcosntructor: Invalid Item Type: "+itemTtype);
			return 0;
	}

		return 18.00;
//		switch(itemTtype) {
//		case Item.ITEM_BATTERY:
//			return Battery.IGST_RATE;
//			case Item.ITEM_INVERTOR:
//				return Invertor.IGST_RATE ;
//			case Item.ITEM_SCRAP:
//					return Scrap.IGST_RATE ;
//			case Item.ITEM_UPS:
//			return UPS.IGST_RATE;
//				default:
//				Log.d("no oject created");
//				return 0;
//		}
		
	}

	private String getIconLocation(String stritem) {
		
//		for(String str: Constants.sItemList) {
//			if(stritem.trim().equalsIgnoreCase(str.trim())) {
//				return str.toLowerCase()+"_icon.png";
//			}
//		}
		return "other_icon.png";
		 	
	}

	private Image createImage(String filename) {
		Image img = null;
		try {

			// String imgfile = "file:///assets/img/icon/invertor_icon.png";
			byte[] imData = null;
			InputStream instrm = getAppContext().getAssets().open("img/icon/"+filename);
			Log.d("instrm: " + instrm);

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nread = 0;
			byte[] byteDatain = new byte[16384];
			while ((nread = instrm.read(byteDatain, 0, byteDatain.length)) != -1) {
				Log.d("data read: " + nread);

				buffer.write(byteDatain, 0, nread);
			}

			imData = buffer.toByteArray();
			Log.d("Img byteData: " + imData);
			img = Image.getInstance(imData);
			Log.d("Img created: " + img);

		} catch (Exception e) {
			e.printStackTrace();
			Log.d(getAppContext(), "Unale to open the Image File: " + e.getMessage());
		}
		return img;
	}

	private void createListViewItems(PdfPTable outertable, PdfPTable innertable, Font fontlackold, String imgfile,
			String desc1, String desc2, String percentage, String amount) throws Exception {
		// image , description1, desc2. percetage

		Paragraph newline = new Paragraph("\n");
		Paragraph cloumnTitle2 = new Paragraph("image\n", fontlackold);
		cloumnTitle2.setAlignment(Element.ALIGN_CENTER);
		// Image img = Image.getInstance(imgfile);
		Image img = createImage(imgfile);

		PdfPCell ccell2 = new PdfPCell(img);
		ccell2.setRowspan(2);
		ccell2.setBorder(Rectangle.NO_BORDER);
		ccell2.setUseVariableBorders(true);
		ccell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		innertable.addCell(ccell2);

		PdfPCell ccelldesc1 = new PdfPCell(new Paragraph(desc1, fontlackold));
		ccelldesc1.setBorder(Rectangle.NO_BORDER);
		innertable.addCell(ccelldesc1);

		PdfPCell ccellpercent = new PdfPCell(new Paragraph(percentage, fontlackold));
		ccellpercent.setHorizontalAlignment(Element.ALIGN_RIGHT);
		ccellpercent.setBorder(Rectangle.NO_BORDER);
		innertable.addCell(ccellpercent);

		/*
		 * PdfPCell ccellempty = new PdfPCell(new Paragraph(" ",fontlackold));
		 * ccellempty.setBorder(Rectangle.NO_BORDER); innertable.addCell(ccellempty);
		 */
		PdfPCell ccelldesc2 = new PdfPCell(new Paragraph(desc2, fontlackold));
		ccelldesc2.setBorder(Rectangle.NO_BORDER);
		innertable.addCell(ccelldesc2);

		PdfPCell ccellamount = new PdfPCell(new Paragraph(amount, fontlackold));
		ccellamount.setHorizontalAlignment(Element.ALIGN_RIGHT);
		ccellamount.setBorder(Rectangle.NO_BORDER);
		innertable.addCell(ccellamount);

		outertable.addCell(innertable);
		Log.d("address section");

	}

	private void generateCalculationContent(Document dcument, Font fontlackold,ArrayList<HashMap<String, String>> argItemList, HashMap<String, String> argInvoiceDetails) throws DocumentException {
		// TODO Auto-generated method stub

		float[] clmwidthsinner = { 2f, 3f, 1f, 2f, 1f, 2f, 1f, 2f, 3f, 3f };

		PdfPTable tabletaxCalculation = new PdfPTable(10);
		tabletaxCalculation.setWidthPercentage(100.0f);
		tabletaxCalculation.setWidths(clmwidthsinner);
		tabletaxCalculation.setSpacingBefore(3.0f);
		tabletaxCalculation.setSpacingAfter(3.0f);
		Paragraph newline = new Paragraph("\n");
		Paragraph particulatTitle = new Paragraph("Particulars(HSN Code)\n", fontlackold);
		particulatTitle.setAlignment(Element.ALIGN_CENTER);
		PdfPCell particulatTitleccell = new PdfPCell(particulatTitle);
		particulatTitleccell.setRowspan(2);
		particulatTitleccell.setUseVariableBorders(true);
		particulatTitleccell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabletaxCalculation.addCell(particulatTitleccell);

		Paragraph taxableValuetTitle = new Paragraph("Taxable Value\n", fontlackold);
		taxableValuetTitle.setAlignment(Element.ALIGN_CENTER);
		PdfPCell taxalevalueTitle = new PdfPCell(taxableValuetTitle);
		taxalevalueTitle.setRowspan(2);
		taxalevalueTitle.setUseVariableBorders(true);
		taxalevalueTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabletaxCalculation.addCell(taxalevalueTitle);

		Paragraph cgstTitlepara = new Paragraph("CGST\n", fontlackold);
		cgstTitlepara.setAlignment(Element.ALIGN_CENTER);
		PdfPCell cgstTitle = new PdfPCell(cgstTitlepara);
		cgstTitle.setColspan(2);
		cgstTitle.setUseVariableBorders(true);
		cgstTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabletaxCalculation.addCell(cgstTitle);

		Paragraph sgstTitlepara = new Paragraph("SGST\n", fontlackold);
		sgstTitlepara.setAlignment(Element.ALIGN_CENTER);
		PdfPCell sgstTitle = new PdfPCell(sgstTitlepara);
		sgstTitle.setColspan(2);
		sgstTitle.setUseVariableBorders(true);
		sgstTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabletaxCalculation.addCell(sgstTitle);

		Paragraph igstTitlepara = new Paragraph("IGST\n", fontlackold);
		igstTitlepara.setAlignment(Element.ALIGN_CENTER);
		PdfPCell igstTitle = new PdfPCell(igstTitlepara);
		igstTitle.setColspan(2);
		igstTitle.setUseVariableBorders(true);
		igstTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabletaxCalculation.addCell(igstTitle);

		Paragraph totaltaxTitle = new Paragraph("Total Tax\n", fontlackold);
		totaltaxTitle.setAlignment(Element.ALIGN_CENTER);
		PdfPCell totalTaxTitle = new PdfPCell(totaltaxTitle);
		totalTaxTitle.setRowspan(2);
		totalTaxTitle.setUseVariableBorders(true);
		totalTaxTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabletaxCalculation.addCell(totalTaxTitle);

		Paragraph totalAmountTitle = new Paragraph("Total Amount", fontlackold);
		totalAmountTitle.setAlignment(Element.ALIGN_CENTER);
		PdfPCell totalAmtTitle = new PdfPCell(totalAmountTitle);
		totalAmtTitle.setRowspan(2);
		totalAmtTitle.setUseVariableBorders(true);
		totalAmtTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabletaxCalculation.addCell(totalAmtTitle);

		Paragraph rateTitle = new Paragraph("Rate", fontlackold);
		Paragraph amountTitle = new Paragraph("Amount(Rs)", fontlackold);
		rateTitle.setAlignment(Element.ALIGN_CENTER);
		amountTitle.setAlignment(Element.ALIGN_CENTER);
		tabletaxCalculation.addCell(rateTitle);
		tabletaxCalculation.addCell(amountTitle);
		tabletaxCalculation.addCell(rateTitle);
		tabletaxCalculation.addCell(amountTitle);
		tabletaxCalculation.addCell(rateTitle);
		tabletaxCalculation.addCell(amountTitle);
		if(argItemList != null) {
			double grantCgsttax=0, grantIgsttax = 0,grantTaxable =0, grantTotalTax=0, GrantTotal=0;

			for(HashMap<String, String> itmMap: argItemList) {
				if(itmMap != null) {
					double totalTaxPerProduct = 0;
					String IgstApplica = itmMap.get(Constants.BL_JSON_GST_APPLICABILITY) ;
					String strcgstRate = "0", strigstRate= "0", cgstAmount="0",igstAmount="0";
					
					if(!IgstApplica.equalsIgnoreCase("YES")) {
						totalTaxPerProduct = 2* Double.valueOf(itmMap.get(Constants.STR_JSON_CGSTAX_PRODUCT_TOTAL));
					}else {
						totalTaxPerProduct = Double.valueOf(itmMap.get(Constants.STR_JSON_CGSTAX_PRODUCT_TOTAL));
					}
					grantTotalTax = grantTotalTax+ totalTaxPerProduct;
					grantTaxable = grantTaxable + Double.valueOf(itmMap.get(Constants.STR_JSON_TAXABLE_PRODUCT_TOTAL));
					grantCgsttax = grantCgsttax + Double.valueOf(itmMap.get(Constants.STR_JSON_CGSTAX_PRODUCT_TOTAL));
					grantIgsttax  =grantIgsttax +Double.valueOf(itmMap.get(Constants.STR_JSON_IGSTAX_PRODUCT_TOTAL));	
					GrantTotal = GrantTotal +Double.valueOf(itmMap.get(Constants.STR_JSON_PRICE_PRODUCT_TOTAL));	
					
					tabletaxCalculation.addCell(getCenterAlignPara(itmMap.get(Constants.STR_JSON_HSN_CODE), fontlackold));
					tabletaxCalculation.addCell(getCenterAlignPara(itmMap.get(Constants.STR_JSON_TAXABLE_PRODUCT_TOTAL), fontlackold));
					tabletaxCalculation.addCell(getCenterAlignPara(itmMap.get(Constants.STR_JSON_CGST_RATE), fontlackold));
					tabletaxCalculation.addCell(getCenterAlignPara(itmMap.get(Constants.STR_JSON_CGSTAX_PRODUCT_TOTAL), fontlackold));
					tabletaxCalculation.addCell(getCenterAlignPara(itmMap.get(Constants.STR_JSON_CGST_RATE), fontlackold));
					tabletaxCalculation.addCell(getCenterAlignPara(itmMap.get(Constants.STR_JSON_CGSTAX_PRODUCT_TOTAL), fontlackold));
					tabletaxCalculation.addCell(getCenterAlignPara(itmMap.get(Constants.STR_JSON_IGST_RATE), fontlackold));
					tabletaxCalculation.addCell(getCenterAlignPara(itmMap.get(Constants.STR_JSON_IGSTAX_PRODUCT_TOTAL), fontlackold));
					tabletaxCalculation.addCell(getCenterAlignPara(""+totalTaxPerProduct, fontlackold));
					tabletaxCalculation.addCell(getCenterAlignPara(itmMap.get(Constants.STR_JSON_PRICE_PRODUCT_TOTAL), fontlackold));

				}
			}
			tabletaxCalculation.addCell(getCenterAlignPara("Total", fontlackold));
			tabletaxCalculation.addCell(getCenterAlignPara(String.valueOf(grantTaxable), fontlackold));
			tabletaxCalculation.addCell(getCenterAlignPara("--", fontlackold));
			tabletaxCalculation.addCell(getCenterAlignPara( String.valueOf(grantCgsttax), fontlackold));
			tabletaxCalculation.addCell(getCenterAlignPara("--", fontlackold));
			tabletaxCalculation.addCell(getCenterAlignPara(String.valueOf(grantCgsttax), fontlackold));
			tabletaxCalculation.addCell(getCenterAlignPara("--", fontlackold));
			tabletaxCalculation.addCell(getCenterAlignPara(String.valueOf(grantIgsttax) , fontlackold));
			tabletaxCalculation.addCell(getCenterAlignPara(""+grantTotalTax, fontlackold));
			tabletaxCalculation.addCell(getCenterAlignPara(""+GrantTotal, fontlackold));

		}
		
		dcument.add(tabletaxCalculation);

	}

	private Paragraph getCenterAlignPara(String str, Font font) {
		Paragraph oPara = new Paragraph(str, font);
		oPara.setAlignment(Element.ALIGN_CENTER);
		return oPara;
	}

	private Paragraph getLeftAlignPara(String str, Font font) {
		Paragraph oPara = new Paragraph(str, font);
		oPara.setAlignment(Element.ALIGN_LEFT);
		return oPara;
	}

	private Paragraph getRightAlignPara(String str, Font font) {
		Paragraph oPara = new Paragraph(str, font);
		oPara.setAlignment(Element.ALIGN_RIGHT);
		return oPara;
	}

	private void generateSignatureSection(Section subCatPart) throws Exception {
		// TODO Auto-generated method stub

	}

	private void generateDisclaimerSection(Document docum) throws DocumentException, IOException {
		// TODO Auto-generated method stub
		PdfPTable disclaimSectionTable = new PdfPTable(2);
		disclaimSectionTable.setWidthPercentage(100.0f);
		disclaimSectionTable.setSpacingBefore(6.0f);
		disclaimSectionTable.setSpacingAfter(3.0f);

		Font disclaimerTitleFont = new Font(getFontFromAsset(), 10.0f, Font.BOLD | Font.UNDERLINE, BaseColor.BLACK);
		Font disclaimerListFont = new Font(getFontFromAsset(), 10.0f, Font.NORMAL, BaseColor.BLACK);
		Font signatoryFont = new Font(getFontFromAsset(), 12.0f, Font.BOLD, BaseColor.BLACK);
		Font computergenertFont = new Font(getFontFromAsset(), 8.0f, Font.NORMAL | Font.UNDERLINE, BaseColor.BLACK);

		Paragraph newline = new Paragraph("\n");
		docum.add(newline);

		Paragraph disclaimerTitle = new Paragraph("Terms & Conditions\n", disclaimerTitleFont);
		disclaimerTitle.setAlignment(Element.ALIGN_LEFT);
		PdfPCell disclaimerTitlecell = new PdfPCell(disclaimerTitle);
		disclaimerTitlecell.setBorder(Rectangle.NO_BORDER);
		disclaimerTitlecell.setUseVariableBorders(true);
		disclaimerTitlecell.setHorizontalAlignment(Element.ALIGN_LEFT);
		disclaimSectionTable.addCell(disclaimerTitlecell);

		Paragraph signatoryTitle = new Paragraph("for VT Power Solutions\n", signatoryFont);
		signatoryTitle.setAlignment(Element.ALIGN_CENTER);
		PdfPCell signatoryTitlecell = new PdfPCell(signatoryTitle);
		signatoryTitlecell.setBorder(Rectangle.NO_BORDER);
		signatoryTitlecell.setUseVariableBorders(true);
		signatoryTitlecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		disclaimSectionTable.addCell(signatoryTitlecell);

		/*
		 * List list = new List(true, false, 12); list.add(new
		 * ListItem("Goods once sold will not be taken back.", disclaimerListFont));
		 * list.add(new ListItem("Goods received are in good condition.",
		 * disclaimerListFont)); list.add(new
		 * ListItem("Subject to Puducherry Jurisdiction only.",disclaimerListFont));
		 */
		Paragraph disclaimPhrase = new Paragraph(
				"1. Goods once sold will not be taken back.\n2. Goods received are in good condition.\n3. Subject to Puducherry Jurisdiction only.",
				disclaimerListFont);
		disclaimPhrase.setAlignment(Element.ALIGN_LEFT);
		PdfPCell discalimerList = new PdfPCell(disclaimPhrase);
		discalimerList.setBorder(Rectangle.NO_BORDER);
		discalimerList.setUseVariableBorders(true);
		discalimerList.setHorizontalAlignment(Element.ALIGN_LEFT);
		disclaimSectionTable.addCell(discalimerList);

		Paragraph signatorymsgTitle = new Paragraph("\n\nAuthorised Signatory\n", disclaimerListFont);
		signatorymsgTitle.setAlignment(Element.ALIGN_CENTER);
		PdfPCell signatorymsgTitlecell = new PdfPCell(signatorymsgTitle);
		signatorymsgTitlecell.setBorder(Rectangle.NO_BORDER);
		signatorymsgTitlecell.setUseVariableBorders(true);
		signatorymsgTitlecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		disclaimSectionTable.addCell(signatorymsgTitlecell);
		docum.add(disclaimSectionTable);

		docum.add(newline);
		Paragraph computerGeneraed = new Paragraph(
				"**This is a Computer/Mobile generated invoice and requires no signature.\n", disclaimerListFont);
		computerGeneraed.setAlignment(Element.ALIGN_CENTER);
		docum.add(computerGeneraed);

	}

	private Context cntxt = null;

	private Context getAppContext() {
		return this.cntxt;
	}

	private void setAppContext(Context octn) {
		this.cntxt = octn;
	}

	public void generateItemisedInvoice(Context context, String dest,final ArrayList<HashMap<String, String>> argitemlist, final HashMap<String, String> arginvoiceDetails) {
		Document document = null;
		try {
		setAppContext(context);
		Log.d("PDFReader:PDFMain " + new File(dest));
		File file = new File(dest);
		Log.d("file  " + file);

		FileOutputStream fileos = new FileOutputStream(file);
		Log.d("fileos  " + fileos);

		// Create New Blank Document
		  document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, fileos);
		Log.d("Pdf Document Open.");
		// Open Document for Writting into document
		document.open();
		Log.d("To Set Meta Data");
		document.addTitle("VTPOWER SOLUTIONS");
		document.addSubject("Invoice");
		document.addKeywords("invertor,	solar, cell");
		document.addAuthor("Properator,VTpowerSln");
		document.addCreator("Tamilarasan");
		document.addCreationDate();
		Log.d("Set Margin");
		document.right(10.0f);
		document.setMargins(20.0f, 20.0f, 20.0f, 20.0f);
 
		fBaseFont = BaseFont.createFont("assets/fonts/brandon_medium.otf", "UTF-8", BaseFont.EMBEDDED);
		// LINE SEPARATOR
		LineSeparator lineSeparator = new LineSeparator();
		lineSeparator.setLineColor(new BaseColor(0, 0, 0, 68));
		Paragraph newline = new Paragraph("\n");

		generateTitleSection(document);
		document.add(newline);
}catch(Exception e) {
	Log.d("Invoice: Exception; "+e.toString());
	e.printStackTrace();
}
		/*
		 * invoice, name, streetnlocality, String posttaluk, String distnpincode,String
		 * state,String mobile, String email, String strGSTIN
		 */
		try {

		generateAddressSection(document, arginvoiceDetails.get(Constants.STR_JSON_INVOICE_NUM),
				arginvoiceDetails.get(Constants.STR_JSON_CUSTOMER_NAME),
				arginvoiceDetails.get(Constants.STR_JSON_1_ADDRESS), 
				arginvoiceDetails.get(Constants.STR_JSON_2_ADDRESS),
				arginvoiceDetails.get(Constants.STR_JSON_3_ADDRESS)+"-"+arginvoiceDetails.get(Constants.STR_JSON_PINCODE_ADDRESS),
				  "", arginvoiceDetails.get(Constants.STR_JSON_CONTACT_NUM),arginvoiceDetails.get(Constants.STR_JSON_CONTACT_EMAIL), "");
		}catch(Exception e) {
			Log.d("Invoice: Exception at address Section; "+e.toString());
			e.printStackTrace();
		}

		
		Font fontlackoldtitle = new Font(getFontFromAsset(), 8.0f, Font.BOLD | Font.UNDERLINE, BaseColor.BLACK);
		Font fontlackold = new Font(getFontFromAsset(), 10.0f, Font.NORMAL, BaseColor.BLACK);
		Font fontlacksmall = new Font(getFontFromAsset(), 9.0f, Font.NORMAL, BaseColor.BLACK);
try {
		Paragraph cashpara = new Paragraph("Cash/Credit Invoice\n", fontlackoldtitle);
		cashpara.setAlignment(Element.ALIGN_CENTER);
		document.add(cashpara);

 		generateInvoiceContent(document, argitemlist);
}catch(Exception e) {
	Log.d("Invoice: Exception at generate Inovice Content; "+e.toString());
	e.printStackTrace();
}

try {

		Paragraph taxCalculation = new Paragraph("Tax Calculation\n", fontlackoldtitle);
		taxCalculation.setAlignment(Element.ALIGN_CENTER);
		document.add(taxCalculation);
		generateCalculationContent(document, fontlacksmall,argitemlist,arginvoiceDetails);
		generateDisclaimerSection(document);
		
}catch(Exception e) {
	Log.d("Invoice: Exception end section; "+e.toString());
	e.printStackTrace();
}

		// generateSignatureSection(subCatPart);
		// system genrted no sign req and authorised dealer.
		// generateFooterSection(subCatPart);

		// Close Document after writting all content
		// document.add(catPart);

		document.close();

	}

	private void generateFooterSection(Section subCatPart) {
		// TODO Auto-generated method stub

	}

	public void createPdf(String dest) throws DocumentException, IOException {

		if (new File(dest).exists()) {
			new File(dest).delete();
		}

		// try {
		/**
		 * Creating Document
		 */
		Document document = new Document();

		// Location to save
		PdfWriter.getInstance(document, new FileOutputStream(dest));

		// Open to write
		document.open();

		// Document Settings
		document.setPageSize(PageSize.A4);
		document.addCreationDate();
		document.addAuthor("Android School");
		document.addCreator("Pratik Butani");

		/***
		 * Variables for further use....
		 */
		BaseColor mColorAccent = new BaseColor(0, 153, 204, 255);
		float mHeadingFontSize = 20.0f;
		float mValueFontSize = 26.0f;

		/**
		 * How to USE FONT....
		 */
		BaseFont urName = BaseFont.createFont("assets/fonts/brandon_medium.otf", "UTF-8", BaseFont.EMBEDDED);

		// LINE SEPARATOR
		LineSeparator lineSeparator = new LineSeparator();
		lineSeparator.setLineColor(new BaseColor(0, 0, 0, 68));

		// Title Order Details...
		// Adding Title....
		Font mOrderDetailsTitleFont = new Font(urName, 36.0f, Font.NORMAL, BaseColor.BLACK);
		Chunk mOrderDetailsTitleChunk = new Chunk("Order Details", mOrderDetailsTitleFont);
		Paragraph mOrderDetailsTitleParagraph = new Paragraph(mOrderDetailsTitleChunk);
		mOrderDetailsTitleParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(mOrderDetailsTitleParagraph);

		// Fields of Order Details...
		// Adding Chunks for Title and value
		Font mOrderIdFont = new Font(urName, mHeadingFontSize, Font.NORMAL, mColorAccent);
		Chunk mOrderIdChunk = new Chunk("Order No:", mOrderIdFont);
		Paragraph mOrderIdParagraph = new Paragraph(mOrderIdChunk);
		document.add(mOrderIdParagraph);

		Font mOrderIdValueFont = new Font(urName, mValueFontSize, Font.NORMAL, BaseColor.BLACK);
		Chunk mOrderIdValueChunk = new Chunk("#123123", mOrderIdValueFont);
		Paragraph mOrderIdValueParagraph = new Paragraph(mOrderIdValueChunk);
		document.add(mOrderIdValueParagraph);

		// Adding Line Breakable Space....
		document.add(new Paragraph(""));
		// Adding Horizontal Line...
		document.add(new Chunk(lineSeparator));
		// Adding Line Breakable Space....
		document.add(new Paragraph(""));

		// Fields of Order Details...
		Font mOrderDateFont = new Font(urName, mHeadingFontSize, Font.NORMAL, mColorAccent);
		Chunk mOrderDateChunk = new Chunk("Order Date:", mOrderDateFont);
		Paragraph mOrderDateParagraph = new Paragraph(mOrderDateChunk);
		document.add(mOrderDateParagraph);

		Font mOrderDateValueFont = new Font(urName, mValueFontSize, Font.NORMAL, BaseColor.BLACK);
		Chunk mOrderDateValueChunk = new Chunk("06/07/2017", mOrderDateValueFont);
		Paragraph mOrderDateValueParagraph = new Paragraph(mOrderDateValueChunk);
		document.add(mOrderDateValueParagraph);

		document.add(new Paragraph(""));
		document.add(new Chunk(lineSeparator));
		document.add(new Paragraph(""));

		// Fields of Order Details...
		Font mOrderAcNameFont = new Font(urName, mHeadingFontSize, Font.NORMAL, mColorAccent);
		Chunk mOrderAcNameChunk = new Chunk("Account Name:", mOrderAcNameFont);
		Paragraph mOrderAcNameParagraph = new Paragraph(mOrderAcNameChunk);
		document.add(mOrderAcNameParagraph);

		Font mOrderAcNameValueFont = new Font(urName, mValueFontSize, Font.NORMAL, BaseColor.BLACK);
		Chunk mOrderAcNameValueChunk = new Chunk("Pratik Butani", mOrderAcNameValueFont);
		Paragraph mOrderAcNameValueParagraph = new Paragraph(mOrderAcNameValueChunk);
		document.add(mOrderAcNameValueParagraph);

		document.add(new Paragraph(""));
		document.add(new Chunk(lineSeparator));
		document.add(new Paragraph(""));

		document.close();

		// Toast.makeText(mContext, "Created... :)", Toast.LENGTH_SHORT).show();

		// FileUtils.openFile(mContext, new File(dest));

		/*
		 * } catch (IOException | DocumentException ie) { // LOGE("createPdf: Error " +
		 * ie.getLocalizedMessage()); } catch (ActivityNotFoundException ae) {
		 * //Toast.makeText(mContext, "No application found to open this file.",
		 * Toast.LENGTH_SHORT).show();
		 */ }

	private void createDoc(String dest) throws Exception {
		Document document = new Document();
		// Location to save
		PdfWriter.getInstance(document, new FileOutputStream(dest));

		// Open to write
		document.open();

		document.setPageSize(PageSize.A4);
		document.addCreationDate();
		document.addAuthor("Android School");
		document.addCreator("VTPower Sln");

		// urName = BaseFont.createFont("assets/fonts/NexaLight.otf", "UTF-8",
		// BaseFont.EMBEDDED);

	}

}
