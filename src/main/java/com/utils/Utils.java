package com.utils;

import java.util.List;

public class Utils {

	public static final int MINUTE = 60*1000;
	
	public static boolean isNullOrEmpty(List<?> list) {
		return list==null || list.size()==0;
	}
	
}
