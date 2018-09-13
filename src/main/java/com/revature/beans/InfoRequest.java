package com.revature.beans;

public class InfoRequest {
	private int id;
	private String title;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public InfoRequest() {
		super();
	}
	public InfoRequest(int id) {
		super();
		this.id = id;
	}
}
