package com.restservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.data.QuestionCache;

@SpringBootApplication
public class ExamApplication {
	
	public static void main(String[] args) {
		QuestionCache.getInstance().loadData();
		QuestionCache.getInstance().initUserData();
		SpringApplication.run(ExamApplication.class, args);
	}

}
