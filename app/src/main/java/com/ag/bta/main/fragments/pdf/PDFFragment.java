package com.ag.bta.main.fragments.pdf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ag.bta.main.activities.HomeActivity;
import com.ag.bta.main.viewPager.CustomViewPager;
import com.ag.bta.utils.DateUtils;
import com.ag.bta.utils.Log;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 import com.ag.bta.main.R;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

public class PDFFragment extends Fragment {
	Button btnCreate;
	EditText editText;
	Context ocontext = null;
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// get title
		//  title = getArguments().getString("title");
	}
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = View.inflate(getContext(), R.layout.pdf_main, null);


		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		boolean status = false;
		Log.d("PDFReader:PDFMain", " 00000000000");

		// navigate();
		// if(status) {

		Log.d("PDFReader:PDFMain", " 00000000000");
		;
		Log.d("PDFReader:PDFMain", "after contentview" + savedInstanceState);

		Log.d("PDFReader:PDFMain", "1111111111111111");
		btnCreate = (Button) view.findViewById(R.id.createpdf);
		editText = (EditText) view.findViewById(R.id.edittextpdf);
		Log.d("PDFReader:PDFMain", "2222222222222222222222222222222editText :" + editText);
		btnCreate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d("PDFReader:PDFMain", "onclick ");

				//Log.d("PDFReader:PDFMain", "onclick " + FileUtils.checkMemory(getApplicationContext()));

				String strfile = getFileLocation();
				Invoice oinv = new Invoice();
				try {
					// Log.d("getApplicationContext() "+getApplicationContext());
					Log.d("ocontext: " + ocontext);

					//oinv.generateItemisedInvoice(ocontext, strfile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				navigate();
				Log.d("after navigate...");

			}
		});
		Log.d("324253535" + btnCreate);

		// }
	}

	private void navigate() {
		try {
			//Intent main = new Intent(PDFMain.this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			//startActivity(main);
		} catch (Exception e) {
			// TODO: handle exception

			Log.d("Navigate exception: ", e);
		}

	}

	public String getFileLocation() {
		FILEROOT = Environment.getExternalStorageDirectory().toString() + "/";
		/* + "/PDF/" */;
		String FILE = FILEROOT + "invoice.pdf";
		Log.d("PDFReader:PDFMain", "createpdf: " + FILE);

		// Create Directory in External Storage
		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/PDF");
		myDir.mkdirs();
		Log.d("PDFReader:PDFMain", "root: " + root);

		FILE = FILEROOT + DateUtils.getDateTime() + ".pdf";

		return FILE;
	}

	String FILEROOT = null;
	String FILE = null;

	void createPdf(String msg) {

		// Create New Blank Document
		Document document = new Document(PageSize.A4);
		// Create Pdf Writer for Writting into New Created Document

		try {
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			Log.d("PDFReader:PDFMain", "after 111111111111");
			// Open Document for Writting into document
			document.open();
			Log.d("PDFReader:PDFMain", "after 22222222");
			// User Define Method
			addMetaData(document);
			Log.d("PDFReader:PDFMain", "after 333333");
			addTitlePage(document);
			Log.d("PDFReader:PDFMain", "after 444444444444444");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Close Document after writting all content
		document.close();

		Toast.makeText(getContext(), msg + " :PDF File is Created. Location : " + FILE, Toast.LENGTH_LONG).show();

	}

	// Set PDF document Properties
	public void addMetaData(Document document)

	{
		document.addTitle("VTPOWER SOLUTIONS");
		document.addSubject("Person Info");
		document.addKeywords("invertor,	solar, cell");
		document.addAuthor("Properator,VTpowerSln");
		document.addCreator("Tamilarasan");
		document.addCreationDate();

	}

	public void addTitlePage(Document document) throws DocumentException {
		// Font Style for Document
		Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
		Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD | Font.UNDERLINE, BaseColor.GRAY);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

		// Start New Paragraph
		Paragraph prHead = new Paragraph(); // Set Font in this Paragraph
		prHead.setFont(titleFont);
		// Add item into Paragraph
		prHead.add("VTPOWER SPLUTIONS\n");
		prHead.setAlignment(Element.ALIGN_LEFT);
		// Create Table into Document with 1 Row
		PdfPTable myTable = new PdfPTable(1);
		// 100.0f mean width of table is same as Document size
		myTable.setWidthPercentage(100.0f);

		// Create New Cell into Table
		PdfPCell myCell = new PdfPCell(new Paragraph(""));
		myCell.setBorder(Rectangle.BOTTOM);

		// Add Cell into Table
		myTable.addCell(myCell);

		prHead.setFont(catFont);
		prHead.add("\nName1 Name2\n");
		prHead.setAlignment(Element.ALIGN_CENTER);

		// Add all above details into Document
		document.add(prHead);
		document.add(myTable);

		document.add(myTable);

		// Now Start another New Paragraph
		Paragraph prPersinalInfo = new Paragraph();
		prPersinalInfo.setFont(smallBold);
		prPersinalInfo.add("Address 1\n");
		prPersinalInfo.add("Address 2\n");
		prPersinalInfo.add("City: SanFran. State: CA\n");
		prPersinalInfo.add("Country: USA Zip Code: 000001\n");
		prPersinalInfo.add("Mobile: 9999999999 Fax: 1111111 Email: john_pit@gmail.com \n");

		prPersinalInfo.setAlignment(Element.ALIGN_CENTER);

		document.add(prPersinalInfo);
		document.add(myTable);

		document.add(myTable);

		Paragraph prProfile = new Paragraph();
		prProfile.setFont(smallBold);
		prProfile.add("\n \n Profile : \n ");
		prProfile.setFont(normal);
		prProfile.add("\nI am Mr. XYZ. I am Android Application Developer at TAG.");

		prProfile.setFont(smallBold);
		document.add(prProfile);

		PdfPTable myTable2 = new PdfPTable(2);

		// Create new Page in PDF
		document.newPage();

		createPDF();

	}

	// private static String FILE = "c:/temp/FirstPdf.pdf";
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	private void createPDF() {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILEROOT + "invoiceeg2.pdf"));
			document.open();
			addMetaData(document);
			addTitle(document);
			addContent(document);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void addTitle(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
		// We add one empty line
		addEmptyLine(preface, 1);
		// Lets write a big header
		preface.add(new Paragraph("Title of the document", catFont));

		addEmptyLine(preface, 1);
		// Will create: Report generated by: _name, _date
		preface.add(new Paragraph("Report generated by: " + System.getProperty("user.name") + ", " + new Date(),
				smallBold));
		addEmptyLine(preface, 3);
		preface.add(new Paragraph("This document describes something which is very important ", smallBold));

		addEmptyLine(preface, 8);

		preface.add(new Paragraph(
				"This document is a preliminary version and not subject to your license agreement or any other agreement with vogella.com ;-).",
				redFont));

		document.add(preface);
		// Start a new page
		document.newPage();
	}

	private static void addContent(Document document) throws DocumentException {
		Anchor anchor = new Anchor("First Chapter", catFont);
		anchor.setName("First Chapter");

		// Second parameter is the number of the chapter
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("Subcategory 1", subFont);
		Section subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("Hello"));

		subPara = new Paragraph("Subcategory 2", subFont);
		subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("Paragraph 1"));
		subCatPart.add(new Paragraph("Paragraph 2"));
		subCatPart.add(new Paragraph("Paragraph 3"));

		// add a list
		createList(subCatPart);
		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 5);
		subCatPart.add(paragraph);
		try {
			// add a table
			createTable(subCatPart);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// now add all this to the document
		document.add(catPart);

		// Next section
		anchor = new Anchor("Second Chapter", catFont);
		anchor.setName("Second Chapter");

		// Second parameter is the number of the chapter
		catPart = new Chapter(new Paragraph(anchor), 1);

		subPara = new Paragraph("Subcategory", subFont);
		subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("This is a very important message"));

		// now add all this to the document
		document.add(catPart);

	}

	private static void createTable(Section subCatPart) throws BadElementException {
		PdfPTable table = new PdfPTable(3);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Table Header 2"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Table Header 3"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		table.addCell("1.0");
		table.addCell("1.1");
		table.addCell("1.2");
		table.addCell("2.1");
		table.addCell("2.2");
		table.addCell("2.3");

		subCatPart.add(table);

	}

	private static void createList(Section subCatPart) {
		List list = new List(true, false, 10);

		ListItem i = new ListItem();
		list.add(new ListItem("First point"));
		list.add(new ListItem("Second point"));
		list.add(new ListItem("Third point"));
		subCatPart.add(list);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

//	

}
