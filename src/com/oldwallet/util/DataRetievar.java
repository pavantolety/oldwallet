package com.oldwallet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class DataRetievar {
	
	public static String getStringValue(String columnName, Map<String, Object> map) {
		String value = "";
		if(map.get(columnName)!=null) {
			value = map.get(columnName).toString();
		}		
		return value;		
	}
	
	public static int getIntValue(String columnName, Map<String, Object> map) {
		int value = 0;
		if(map.get(columnName)!=null) {
			value = Integer.parseInt(map.get(columnName).toString());
		}		
		return value;		
	}
	
	public static long getLongValue(String columnName, Map<String, Object> map) {
		long value = 0;
		if(map.get(columnName)!=null) {
			value = Long.parseLong(map.get(columnName).toString());
		}		
		return value;		
	}
	
	public static float getFloatValue(String columnName, Map<String, Object> map) {
		float value = 0.0f;
		if(map.get(columnName)!=null) {
			value = Float.parseFloat(map.get(columnName).toString());
		}		
		return value;		
	}
	
	public static double getDoubleValue(String columnName, Map<String, Object> map) {
		double value = 0.0;
		if(map.get(columnName)!=null) {
			value = Double.parseDouble(map.get(columnName).toString());
		}		
		return value;		
	}
	
	public static boolean getBooleanValue(String columnName, Map<String, Object> map) {
		boolean value = false;
		if(map.get(columnName)!=null) {
			value = Boolean.valueOf(map.get(columnName).toString());
		}		
		return value;		
	}
	
	public static String getDateValueInString(String columnName, Map<String, Object> map) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		try {
			format2.format(format1.parse(map.get(columnName).toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return format2.toString();
	}

}
