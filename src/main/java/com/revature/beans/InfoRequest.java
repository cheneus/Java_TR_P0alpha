package com.revature.beans;

public class InfoRequest {
	private int id;
	private String title;
	private TuitionReimbursementForm form_ref;
	private Employee requestor_id;
	private Employee requestee_id;
	private String response;
	private int open;
	
	public InfoRequest() {
		super();
	}
	public InfoRequest(int id) {
		super();
		this.id = id;
	}
	
	public InfoRequest(int id, String title, TuitionReimbursementForm form_ref, Employee requestor_id,
			Employee requestee_id, String response, int open) {
		super();
		this.id = id;
		this.title = title;
		this.form_ref = form_ref;
		this.requestor_id = requestor_id;
		this.requestee_id = requestee_id;
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
	
	public TuitionReimbursementForm getForm_ref() {
		return form_ref;
	}
	public void setForm_ref(TuitionReimbursementForm form_ref) {
		this.form_ref = form_ref;
	}
	public Employee getRequestor_id() {
		return requestor_id;
	}
	public void setRequestor_id(Employee requestor_id) {
		this.requestor_id = requestor_id;
	}
	public Employee getRequestee_id() {
		return requestee_id;
	}
	public void setRequestee_id(Employee requestee_id) {
		this.requestee_id = requestee_id;
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
