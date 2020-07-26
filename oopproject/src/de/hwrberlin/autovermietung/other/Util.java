package de.hwrberlin.autovermietung.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {

	public static Calendar convertStringToCal(String time) {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    	format.format(new Date());
    	Date date = null;
    	try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		return cal;
	}
	
	public static String convertDateLongToString(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
    	
		return (cal.get(Calendar.DAY_OF_MONTH) > 9 ? cal.get(Calendar.DAY_OF_MONTH) : "0" + cal.get(Calendar.DAY_OF_MONTH)) + "." + (cal.get(Calendar.MONTH) > 9 ? (cal.get(Calendar.MONTH) + 1) : "0" + (cal.get(Calendar.MONTH) + 1)) + "." + cal.get(Calendar.YEAR);
	}
}
