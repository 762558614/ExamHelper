package com;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.utils.PropertiesUtil;

public class PropertiesTest {

	@Test
	public void getVal() {
		try {
			PropertiesUtil.load("config.properties");
			System.out.println(PropertiesUtil.getVal("redis_ip"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
