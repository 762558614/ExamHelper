package com.entity;

import java.io.Serializable;
import java.util.List;

public class Chapter implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String namee;
	private List<Question> questions;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNamee() {
		return namee;
	}
	public void setNamee(String namee) {
		this.namee = namee;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
