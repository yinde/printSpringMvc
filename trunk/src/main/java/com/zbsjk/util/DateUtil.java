package com.zbsjk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String DateFormat(String DateFormat,Date Date){
		SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);  
	    String dateString = sdf.format(Date);
	    return dateString;
	}
	
}
