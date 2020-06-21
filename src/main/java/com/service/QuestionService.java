package com.service;

import java.util.List;

public class QuestionService {

	private static QuestionService INSTANCE = null;
	
	public static QuestionService getInstance() {
		if(INSTANCE==null) {
			synchronized (QuestionService.class) {
				if(INSTANCE==null) {
					INSTANCE = new QuestionService();
				}
			}
		}
		return INSTANCE;
	}
	
	public void generateQuestion(long userId, String subject) {
		
	}

	public static List<Integer> getQuestions(){
		List<Integer> ids = getOldQuestions();
		int size = PAGE_SIZE+5-ids.size();
		ids.addAll(getLongestQuestions(size, SUBJECT_MZD));
		return ids;
	}
}
