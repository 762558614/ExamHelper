package com.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

	private static Map<String, String> hash = new HashMap<>();
	
	public static void load(String path) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		URL url = PropertiesUtil.class.getClassLoader().getResource(path);
        InputStream stream = PropertiesUtil.class.getClassLoader().getResourceAsStream(url.getFile());
		properties.load(stream);
		Enumeration<?> e = properties.propertyNames();
		while(e.hasMoreElements()) {
			String strKey = (String) e.nextElement();
			String strVal = properties.getProperty(strKey);
			hash.put(strKey, strVal);
		}
	}
	
	public static String getVal(String key) {
		return hash.get(key);
	}
	
}
