package com.dad.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String toIsoLocalDate(Date d) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(d);
	}

	public static String toItDate(Date d) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(d);
	}

}
