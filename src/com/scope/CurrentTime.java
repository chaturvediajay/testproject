package com.scope;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class CurrentTime {
	public static String getUTCTimeDate(String format){
		String str="";
		ZonedDateTime currentDate = ZonedDateTime.now( ZoneOffset.UTC );
		switch(format){
		case "hour":
			  str+=currentDate.getHour();
			  break;
        case "min":
			 str+=currentDate.getMinute(); 
			 break;	
        case "sec":
        	str+=currentDate.getSecond();
        	break;
        case "date":
        	str+=currentDate.getDayOfMonth();
        	break;
        case "month":
        	str+=currentDate.getMonthValue();
        	break;
        case "year":
        	str+=currentDate.getYear();
        	
        	break;
        
		}
		return str;
		
	}
	
	public static void main(String args[]){
		ZonedDateTime currentDate = ZonedDateTime.now( ZoneOffset.UTC );
		//System.out.println(currentDate.getMonthValue());
		System.out.println(CurrentTime.getUTCTimeDate("date"));
		System.out.println(CurrentTime.getUTCTimeDate("month"));
		System.out.println(CurrentTime.getUTCTimeDate("year"));
		System.out.println(CurrentTime.getUTCTimeDate("hour"));
		System.out.println(CurrentTime.getUTCTimeDate("min"));
	}

}
//ZonedDateTime currentDate = ZonedDateTime.now( ZoneOffset.UTC );
//System.out.println(currentDate);