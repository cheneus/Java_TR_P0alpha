package com.revature.beans;

import java.sql.Date;

public class TuitionReimbursementForm {
	private int id;
	private Date eventDate;
	private int totalDays;
	private Date dateSubmitted;
	private String event_address;
	private String event_city;
	private String event_state;
	private EventType eventId;
	private String title;
	private double cost;
	private GradeFormat gradeFormat;
	private Employee submittedBy;
	private Status status;
	private String addinfo;
	
	public TuitionReimbursementForm() {
		super();
	}
	public TuitionReimbursementForm(int id) {
		super();
		this.id = id;
	}
	
	public TuitionReimbursementForm(int id, Date eventDate, int totalDays, Date dateSubmitted,
			String event_address, String event_city, String event_state, EventType eventId, String Title,
			double cost, GradeFormat gradeFormat, Employee submittedBy, Status status,
			String addinfo) {
		super();
		this.id = id;
		this.eventDate = eventDate;
		this.totalDays = totalDays;
		this.dateSubmitted = dateSubmitted;
		this.event_address = event_address;
		this.event_city = event_city;
		this.event_state = event_state;
		this.eventId = eventId;
		this.title = Title;
		this.cost = cost;
		this.gradeFormat = gradeFormat;
		this.submittedBy = submittedBy;
		this.status = status;
		this.addinfo = addinfo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date geteventDate() {
		return eventDate;
	}
	public void seteventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public int getTotalDays() {
		return totalDays;
	}
	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}
	public Date getdateSubmitted() {
		return dateSubmitted;
	}
	public void setdateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	public String getEvent_address() {
		return event_address;
	}
	public void setEvent_address(String event_address) {
		this.event_address = event_address;
	}
	public String getEvent_city() {
		return event_city;
	}
	public void setEvent_city(String event_city) {
		this.event_city = event_city;
	}
	public String getEvent_state() {
		return event_state;
	}
	public void setEvent_state(String event_state) {
		this.event_state = event_state;
	}
	public EventType getEventId() {
		return eventId;
	}
	public void setEventId(EventType eventId) {
		this.eventId = eventId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String Title) {
		this.title = Title;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public GradeFormat getgradeFormat() {
		return gradeFormat;
	}
	public void setgradeFormat(GradeFormat gradeFormat) {
		this.gradeFormat = gradeFormat;
	}
	public Employee getSubmittedBy() {
		return submittedBy;
	}
	public void setSubmittedBy(Employee submittedBy) {
		this.submittedBy = submittedBy;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getaddinfo() {
		return addinfo;
	}
	public void setaddinfo(String addinfo) {
		this.addinfo = addinfo;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TuitionReimbursementForm other = (TuitionReimbursementForm) obj;
		if (submittedBy == null) {
			if (other.submittedBy != null)
				return false;
		} else if (!submittedBy.equals(other.submittedBy))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TuitionReimbursementForm [id=" + id + ", eventDate=" + eventDate + ", totalDays=" + totalDays
				+ ", dateSubmitted=" + dateSubmitted + ", event_address=" + event_address + ", event_city=" + event_city
				+ ", event_state=" + event_state + ", eventId=" + eventId + ", title=" + title + ", cost=" + cost
				+ ", gradeFormat=" + gradeFormat + ", submittedBy=" + submittedBy + ", status=" + status
				+ ", addinfo=" + addinfo + "]";
	}
}