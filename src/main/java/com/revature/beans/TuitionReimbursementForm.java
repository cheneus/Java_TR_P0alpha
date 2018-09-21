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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addinfo == null) ? 0 : addinfo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dateSubmitted == null) ? 0 : dateSubmitted.hashCode());
		result = prime * result + ((eventDate == null) ? 0 : eventDate.hashCode());
		result = prime * result + ((event_address == null) ? 0 : event_address.hashCode());
		result = prime * result + ((event_city == null) ? 0 : event_city.hashCode());
		result = prime * result + ((event_state == null) ? 0 : event_state.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + totalDays;
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
		TuitionReimbursementForm other = (TuitionReimbursementForm) obj;
		if (addinfo == null) {
			if (other.addinfo != null)
				return false;
		} else if (!addinfo.equals(other.addinfo))
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (dateSubmitted == null) {
			if (other.dateSubmitted != null)
				return false;
		} else if (!dateSubmitted.equals(other.dateSubmitted))
			return false;
		if (eventDate == null) {
			if (other.eventDate != null)
				return false;
		} else if (!eventDate.equals(other.eventDate))
			return false;
		if (event_address == null) {
			if (other.event_address != null)
				return false;
		} else if (!event_address.equals(other.event_address))
			return false;
		if (event_city == null) {
			if (other.event_city != null)
				return false;
		} else if (!event_city.equals(other.event_city))
			return false;
		if (event_state == null) {
			if (other.event_state != null)
				return false;
		} else if (!event_state.equals(other.event_state))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (totalDays != other.totalDays)
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