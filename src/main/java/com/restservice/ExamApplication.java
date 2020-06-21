package com.restservice;


import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.data.QuestionCache;
import com.utils.PropertiesUtil;

@SpringBootApplication
public class ExamApplication {
	
	public static void main(String[] args) {
		try {
			PropertiesUtil.load("config.properties");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println(PropertiesUtil.getVal("redis_ip"));
		QuestionCache.loadData();
		QuestionCache.initUserData();
		SpringApplication.run(ExamApplication.class, args);
	}

}
