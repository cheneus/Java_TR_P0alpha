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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + open;
		result = prime * result + ((response == null) ? 0 : response.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfoRequest other = (InfoRequest) obj;
		if (id != other.id)
			return false;
		if (open != other.open)
			return false;
		if (response == null) {
			if (other.response != null)
				return false;
		} else if (!response.equals(other.response))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InfoRequest [id=" + id + ", title=" + title + ", formRef=" + formRef + ", requestorId=" + requestorId
				+ ", requesteeId=" + requesteeId + ", response=" + response + ", open=" + open + "]";
	}
}
