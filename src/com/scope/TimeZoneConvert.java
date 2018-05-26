package com.scope;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeZoneConvert {
	public Calendar cal=null;
	public Date today=null;
	Date nextYear =null;
	java.text.SimpleDateFormat sdf=null;
	public TimeZoneConvert() {
		getDateCurrent();
	}
	
	public Date getDateCurrent()
	{
		 cal = Calendar.getInstance();
		 today = cal.getTime();
		return today;
	}
	
	public Date getNextYear(int number)
	{
		cal.add(Calendar.YEAR, number); // to get previous year add -1
	  nextYear = cal.getTime();
//		 sdf =new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
//		System.out.print(sdf.format(nextYear)+"  "+nextYear);
	  return nextYear;
	}
	
	public String getGmtTimeZone(Date datezone)
	{
		today = cal.getTime();
		DateFormat df =new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		df.format(today);
		System.out.println(df.format(today));
		return df.toString();
	}
	public static Date getStringToDate(String date)
	{
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date dat = null;
		try {
			dat = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	  return dat;
	}
	public static void main(String[] args) {
		TimeZoneConvert rz=new TimeZoneConvert();
		System.out.println(rz.today+"  "
				+rz.getNextYear(10)+"  "+rz.getGmtTimeZone(null));
	}
}
	
	
	



