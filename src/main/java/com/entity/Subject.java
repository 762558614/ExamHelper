package com.entity;

import java.io.Serializable;
import java.util.List;

public class Subject implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private List<Chapter> chapters;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}
	
}
