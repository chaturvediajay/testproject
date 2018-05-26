package com.scope;

import java.util.Date;
import java.util.TimeZone;

public class DateConvert {
	public static Date nowTimeGMT() {
		Date dt = new Date();
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			// currentTime = sdf.format(dt)
			dt = sdf.parse(sdf.format(dt));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		// System.out.println(currentTime);
		return dt;
	}

	public static void main(String[] args) {

	}

}
