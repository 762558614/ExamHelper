package com.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.entity.Question;
import com.service.QuestionService;
import com.utils.redis.AbstractRedisUtil;

public class QuestionCache {
	
	private static QuestionCache INSTANCE ;
	public static QuestionCache getInstance() {
		if(INSTANCE==null) {
			synchronized (QuestionCache.class) {
				if(INSTANCE==null) {
					INSTANCE = new QuestionCache();
				}
			}
		}
		return INSTANCE;
	}
	
	public static final String REDIS_KEY_QUESTION_SCORE_PER = "questions:";
	public static final String REDIS_KEY_USER_QUESTION_PER = "userQuestions:";
	public static final String REDIS_KEY_OLD_QUESTION_PER = "oldQuestions:";
	public static final String REDIS_KEY_QUESTION_CACHE_PER = "questionCaches:";
	
	public static final int QUESTION_TIMEOUT_TIME = 30*60;
	
	private Map<String, Map<Integer, Question>> subjects = new HashMap<>();
	
	public Map<Integer, Question> getQuestions(String subject) {
		return subjects.get(subject);
	}
	
	public Question getQueestion(String subject, int id) {
		return subjects.get(subject).get(id);
	}

	public List<Integer> getUserQuestionCache(int userId, String subject){
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		String key = REDIS_KEY_QUESTION_CACHE_PER+userId+":"+subject;
		List<Integer> ids = redisUtil.lrange(key, 0, -1).stream().collect(
				ArrayList::new, (list, idStr)->{list.add(Integer.valueOf(idStr));},ArrayList::addAll);
		return ids;
	}
	
	public List<Integer> getOldQuestions(int userId, int size, String subject){
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		Set<String> questionIdsTmp = redisUtil.smembers(REDIS_KEY_OLD_QUESTION_PER+userId);
		Set<String> questionIds = redisUtil.smembers(REDIS_KEY_OLD_QUESTION_PER+userId);
		int i=0;
		for(String id : questionIdsTmp) {
			if(i==size-1) {
				break;
			}
			questionIds.add(id);
		}
		return questionIds.stream().collect(ArrayList::new, (list, idStr)->{list.add(Integer.valueOf(idStr));}, ArrayList::addAll);
	}
	
	public void removeOldQuestion(int userId, int questionId) {
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		redisUtil.srem(REDIS_KEY_OLD_QUESTION_PER+userId, Integer.toString((questionId)));
	}
	
	public void addOldQuestion(int questionId) {
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		redisUtil.sadd(REDIS_KEY_OLD_QUESTION_PER+1, Integer.toString(questionId));
	}
	
	public List<Integer> getLongestQuestions(int size, String subject) {
		return getLongestQuestions(0, size, subject);
	}
	
	public List<Integer> getLongestQuestions(int start, int size, String subject) {
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		Set<String> questionIds = redisUtil.zrange(REDIS_KEY_QUESTION_SCORE_PER+"1", start, size);
		List<Integer> res = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		List<Integer> tmp = new ArrayList<Integer>();
		for(String questionId : questionIds) {
			res.add(Integer.valueOf(questionId));
			tmp.add(Integer.valueOf(questionId));
		}
		Collections.sort(tmp);
		for(int i : tmp) {
			builder.append(i+", ");
		}
		System.out.println(builder.toString());
		return res;
	}
	
	public void removeQuestionCache(int userId, String subject, int questionId) {
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		String key = REDIS_KEY_QUESTION_CACHE_PER+userId+":"+subject;
		redisUtil.lpop(key);
	}
	
	public void addQuestionCache(int userId, String subject, List<Integer> questions) {
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		String[] members = new String[questions.size()];
		for(int i=0; i<members.length; i++) {
			members[i] = Integer.toString(questions.get(i));
		}
		String key = REDIS_KEY_QUESTION_CACHE_PER+userId+":"+subject;
		redisUtil.del(key);
		redisUtil.rpush(key, members);
		redisUtil.expire(key, QUESTION_TIMEOUT_TIME);
	}
	
	public void initUserData() {
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		for(Entry<String, Map<Integer, Question>> subjectEntry : subjects.entrySet()) {
			Map<Integer, Question> subjectQuestions = subjectEntry.getValue();
			for(Entry<Integer, Question> questionEntry : subjectQuestions.entrySet()) {
				int questionId = questionEntry.getKey();
				if(!redisUtil.sismember(REDIS_KEY_USER_QUESTION_PER+"1", String.valueOf(questionId))) {
					redisUtil.sadd(REDIS_KEY_USER_QUESTION_PER+"1", String.valueOf(questionId));
					touchQuestion(questionId, 0, QuestionService.SUBJECT_MZD);
				}
			}
		}
	}
	
	/**
	 * 	刷新问题的访问时间
	 * @param questionId
	 * @param time
	 */
	public void touchQuestion(int questionId, long time, String subject) {
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		redisUtil.zadd(REDIS_KEY_QUESTION_SCORE_PER+1, time, String.valueOf(questionId));
	}
	
	public void loadData() {
		Map<String, List<Question>> subjects = new FileDataLoader().loadData();
		for(String subjectName : subjects.keySet()) {
			Map<Integer, Question> questions = new HashMap<>();
			for(Question q : subjects.get(subjectName)) {
				questions.put(q.getId(), q);
			}
			this.subjects.put(subjectName, questions);
		}
	}
}
