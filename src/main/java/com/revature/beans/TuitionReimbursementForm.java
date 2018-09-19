package com.revature.beans;

import java.util.Date;

public class TuitionReimbursementForm {
	private int id;
	private Date dateOfEvent;
	private int totalDays;
	private Date date_submitted;
	private String event_address;
	private String event_city;
	private String event_state;
	private EventType eventId;
	private String title;
	private double cost;
	private GradeFormat grade_format_id;
	private Employee submitted_by;
	private Status status;
	private String event_related_attachments;
	
	public TuitionReimbursementForm() {
		super();
	}
	public TuitionReimbursementForm(int id) {
		super();
		this.id = id;
	}
	
	public TuitionReimbursementForm(int id, Date dateOfEvent, int totalDays, Date date_submitted,
			String event_address, String event_city, String event_state, EventType eventId, String Title,
			double cost, GradeFormat grade_format_id, Employee submitted_by, Status status,
			String event_related_attachments) {
		super();
		this.id = id;
		this.dateOfEvent = dateOfEvent;
		this.totalDays = totalDays;
		this.date_submitted = date_submitted;
		this.event_address = event_address;
		this.event_city = event_city;
		this.event_state = event_state;
		this.eventId = eventId;
		this.title = Title;
		this.cost = cost;
		this.grade_format_id = grade_format_id;
		this.submitted_by = submitted_by;
		this.status = status;
		this.event_related_attachments = event_related_attachments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateOfEvent() {
		return dateOfEvent;
	}
	public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}
	public int getTotalDays() {
		return totalDays;
	}
	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}
	public Date getDate_submitted() {
		return date_submitted;
	}
	public void setDate_submitted(Date date_submitted) {
		this.date_submitted = date_submitted;
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
	public GradeFormat getGrade_format_id() {
		return grade_format_id;
	}
	public void setGrade_format_id(GradeFormat grade_format_id) {
		this.grade_format_id = grade_format_id;
	}
	public Employee getSubmitted_by() {
		return submitted_by;
	}
	public void setSubmitted_by(Employee submitted_by) {
		this.submitted_by = submitted_by;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getEvent_related_attachments() {
		return event_related_attachments;
	}
	public void setEvent_related_attachments(String event_related_attachments) {
		this.event_related_attachments = event_related_attachments;
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
		if (submitted_by == null) {
			if (other.submitted_by != null)
				return false;
		} else if (!submitted_by.equals(other.submitted_by))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TRF";
	}
}