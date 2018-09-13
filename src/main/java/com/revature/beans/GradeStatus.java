package com.revature.beans;

public class GradeStatus {
	private int id;
	private String name;
	
	public GradeStatus() {
		super();
	}
	public GradeStatus(int id) {
		super();
		this.id = id;
	}
	
	public GradeStatus(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
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
	
}
