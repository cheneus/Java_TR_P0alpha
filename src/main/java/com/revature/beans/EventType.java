package com.revature.beans;

public class EventType {
	private int id;
	private String name;
	
	public EventType() {
		super();
	}
	public EventType(int id) {
		super();
		this.id = id;
	}
	public EventType(int id, String name) {
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
