package com.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.entity.Question;
import com.utils.redis.AbstractRedisUtil;

public class QuestionCache {
	
	public static final int PAGE_SIZE = 15;
	public static final int OLD_PAGE_SIZE = 5;

	public static final String SUBJECT_MZD = "12656";
	public static final String SUBJECT_ENG = "00012";
	
	public static final String REDIS_KEY_QUESTION_SCORE_PER = "questions:";
	public static final String REDIS_KEY_USER_QUESTION_PER = "userQuestions:";
	public static final String REDIS_KEY_OLD_QUESTION_PER = "oldQuestions:";
	
	private static Map<String, Map<Integer, Question>> questions = new HashMap<>();
	
	public static Map<Integer, Question> getQuestions(String subject) {
		if(questions.size() == 0) {
			loadData();
		}
		return questions.get(subject);
	}
	
	public static Question getQueestion(String subject, int id) {
		if(questions.size() == 0) {
			loadData();
		}
		return questions.get(subject).get(id);
	}

	public static List<Integer> getOldQuestions(){
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		Set<String> questionIds = redisUtil.zrange(REDIS_KEY_OLD_QUESTION_PER+"1", 0, OLD_PAGE_SIZE-1);
		List<Integer> res = new ArrayList<>();
		for(String questionId : questionIds) {
			res.add(Integer.valueOf(questionId));
		}
		return res;
	}

	public static List<Integer> getQuestions(){
		List<Integer> ids = getOldQuestions();
		int size = PAGE_SIZE+5-ids.size();
		ids.addAll(getLongestQuestions(size, SUBJECT_MZD));
		return ids;
	}
	
	public static void removeOldQuestion(String questionId) {
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		redisUtil.zrem(REDIS_KEY_OLD_QUESTION_PER+1, questionId);
	}
	
	public static void addOldQuestion(String questionId) {
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		redisUtil.zadd(REDIS_KEY_OLD_QUESTION_PER+1, System.currentTimeMillis(), questionId);
	}
	
	public static List<Integer> getLongestQuestions(int size, String subject) {
		return getLongestQuestions(0, size, subject);
	}
	
	public static List<Integer> getLongestQuestions(int start, int size, String subject) {
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		Set<String> questionIds = redisUtil.zrange(REDIS_KEY_QUESTION_SCORE_PER+"1", start, size);
		List<Integer> res = new ArrayList<>();
		for(String questionId : questionIds) {
			res.add(Integer.valueOf(questionId));
		}
		return res;
	}
	
	public static void initUserData() {
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		for(Entry<String, Map<Integer, Question>> subjectEntry : questions.entrySet()) {
			Map<Integer, Question> subjectQuestions = subjectEntry.getValue();
			for(Entry<Integer, Question> questionEntry : subjectQuestions.entrySet()) {
				int questionId = questionEntry.getKey();
				if(!redisUtil.sismember(REDIS_KEY_USER_QUESTION_PER+"1", String.valueOf(questionId))) {
					redisUtil.sadd(REDIS_KEY_USER_QUESTION_PER+"1", String.valueOf(questionId));
					touchQuestion(questionId, 0);
				}
			}
		}
	}
	
	public static void touchQuestion(int questionId, long time) {
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		redisUtil.zadd(REDIS_KEY_QUESTION_SCORE_PER+1, time, String.valueOf(questionId));
	}
	
	public static void loadData() {
		Map<String, List<Question>> subjects = new FileDataLoader().loadData();
		for(String subjectName : subjects.keySet()) {
			Map<Integer, Question> questions = new HashMap<>();
			for(Question q : subjects.get(subjectName)) {
				questions.put(q.getId(), q);
			}
			QuestionCache.questions.put(subjectName, questions);
		}
	}
}
