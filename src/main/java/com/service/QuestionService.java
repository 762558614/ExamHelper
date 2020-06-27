package com.service;

import java.util.List;
import static com.utils.Utils.*;

import com.data.QuestionCache;
import com.entity.Question;

public class QuestionService {
	
	public static final int PAGE_SIZE = 10;
	public static final int OLD_PAGE_SIZE = 10;

	public static final String SUBJECT_MZD = "12656";
	public static final String SUBJECT_ENG = "00012";

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
	
	public Question getOneUserQuestion(int userId, String subject) {
		List<Integer> questions = QuestionCache.getInstance().getUserQuestionCache(userId, subject);
		if(isNullOrEmpty(questions)) {
			questions = generateQuestion(userId, subject);
			QuestionCache.getInstance().addQuestionCache(userId, subject, questions);
			Question q = new Question(-1, "开始", "");
			return q;
		}else {
			Question question = QuestionCache.getInstance().getQueestion(subject, questions.get(0));
			if(question==null) {
				QuestionCache.getInstance().removeQuestionCache(userId, subject, questions.get(0));
				return getOneUserQuestion(userId, subject);
			}
			QuestionCache.getInstance().removeQuestionCache(userId, subject, question.getId());
			return question;	
		}
	}
	
	public void answerQuestion(int userId, String subject, int questionId, boolean isRemeber) {
		if(questionId<=0) {
			return ;
		}
		if(isRemeber) {
			QuestionCache.getInstance().removeOldQuestion(userId, questionId);;
			QuestionCache.getInstance().touchQuestion(questionId, System.currentTimeMillis(), subject);
		}else {
			QuestionCache.getInstance().addOldQuestion(questionId);
		}
	}
	
	/**
	 * 	重新生成问题
	 * @param userId
	 * @param subject
	 * @return
	 */
	public List<Integer> generateQuestion(int userId, String subject) {
		List<Integer> ids = QuestionCache.getInstance().getOldQuestions(userId, OLD_PAGE_SIZE, subject);
		int newQuestionSize = PAGE_SIZE+5-1-ids.size();
		ids.addAll(QuestionCache.getInstance().getLongestQuestions(newQuestionSize, subject));
		return ids;
	}

}
