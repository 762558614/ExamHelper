package com;

import org.junit.Test;

import com.data.QuestionCache;

public class LoadDataTest {

	@Test
	public void loadData() {
		QuestionCache.loadData();
		QuestionCache.initUserData();
	}
	
}
