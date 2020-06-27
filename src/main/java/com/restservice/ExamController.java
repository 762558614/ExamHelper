package com.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Question;
import com.service.QuestionService;

@RestController
public class ExamController {
	
	@GetMapping("/nextQuestion")
	public Question nextQuestion(
			@RequestParam(value = "id", defaultValue = "-1") String id, 
			@RequestParam(value="remember") String remember,
			@RequestParam(value="subject", defaultValue = QuestionService.SUBJECT_MZD) String subject) {
		
		return QuestionService.getInstance().getOneUserQuestion(1, subject);
	}
}
