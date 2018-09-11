package com.revature.beans;

public class Submission {
	private int id;
	private String type;
	private String attachment;
	public Submission(int id, String type, String attachment) {
		super();
		this.id = id;
		this.type = type;
		this.attachment = attachment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
}
