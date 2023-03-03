package com.ag.bta.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtils {

	public static String getDateTime() {
		String date= null; String time = null;
		String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		GregorianCalendar gCalender = new GregorianCalendar();
		date = ""+gCalender.get(Calendar.DATE)+"_"+months[gCalender.get(Calendar.MONTH)]+"_"+gCalender.get(Calendar.YEAR);
		time = "_"+gCalender.get(Calendar.HOUR)+"H"+gCalender.get(Calendar.MINUTE)+"M"+gCalender.get(Calendar.SECOND)+"S";

		return (date+time).trim();
	}
	
	public static String getDate() {
		String date= null; 
		String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		GregorianCalendar gCalender = new GregorianCalendar();
		date = ""+gCalender.get(Calendar.DATE)+"-"+months[gCalender.get(Calendar.MONTH)]+"-"+gCalender.get(Calendar.YEAR);
		
		return date.trim();
	}

	public static String getTime() {
		 String time = null;
	    GregorianCalendar gCalender = new GregorianCalendar();
		time = ""+gCalender.get(Calendar.HOUR)+":"+gCalender.get(Calendar.MINUTE)+":"+gCalender.get(Calendar.SECOND);

		return time.trim();
	}
	
	
	public static String getDisplayDateTime() {//for pdf generation
		String date= null; String time = null;
		String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		GregorianCalendar gCalender = new GregorianCalendar();
		date = gCalender.get(Calendar.DATE)+"/"+months[gCalender.get(Calendar.MONTH)]+"/"+gCalender.get(Calendar.YEAR)+"  \n\n";
		time = "Time: "+gCalender.get(Calendar.HOUR)+":"+gCalender.get(Calendar.MINUTE)+":"+gCalender.get(Calendar.SECOND);

		return (date+time).trim();

	}
	public static int getYear() {
		int year= -1; //String time = null;
		//String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		GregorianCalendar gCalender = new GregorianCalendar();
		year = gCalender.get(Calendar.YEAR);

		return (year);
	}
	public static int getMonth() {
		int month= -1; //String time = null;
		//String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		GregorianCalendar gCalender = new GregorianCalendar();
		month =  gCalender.get(Calendar.MONTH) +1;

		return month;
	}

	public static int getDay() {
		int date= -1; //String time = null;
		 	GregorianCalendar gCalender = new GregorianCalendar();
		date = gCalender.get(Calendar.DATE) ;
		return date;
	}
}
