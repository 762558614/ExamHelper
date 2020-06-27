package com;

import org.junit.Before;
import org.junit.Test;

import com.data.QuestionCache;
import com.service.QuestionService;

public class QuestionCacheTest {

	@Before
	public void loadData() {
		QuestionCache.getInstance().loadData();
	}
	
	@Test
	public void getQuestion() {
		QuestionService.getInstance().getOneUserQuestion(1, "12656");
	}
	
}
