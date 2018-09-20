package com.revature.beans;

public class InfoRequest {
	private int id;
	private String title;
	private TuitionReimbursementForm formRef;
	private Employee requestorId;
	private Employee requesteeId;
	private String response;
	private int open;
	
	public InfoRequest() {
		super();
	}
	public InfoRequest(int id) {
		super();
		this.id = id;
	}
	
	public InfoRequest(int id, String title, TuitionReimbursementForm formRef, Employee requestorId,
			Employee requesteeId, String response, int open) {
		super();
		this.id = id;
		this.title = title;
		this.formRef = formRef;
		this.requestorId = requestorId;
		this.requesteeId = requesteeId;
		this.response = response;
		this.open = open;
	}
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
	
	public TuitionReimbursementForm getFormRef() {
		return formRef;
	}
	public void setFormRef(TuitionReimbursementForm formRef) {
		this.formRef = formRef;
	}
	public Employee getRequestorId() {
		return requestorId;
	}
	public void setRequestorId(Employee requestorId) {
		this.requestorId = requestorId;
	}
	public Employee getRequesteeId() {
		return requesteeId;
	}
	public void setRequesteeId(Employee requesteeId) {
		this.requesteeId = requesteeId;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
	}
}
