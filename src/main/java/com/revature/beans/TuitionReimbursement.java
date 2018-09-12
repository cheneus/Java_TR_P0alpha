package com.revature.beans;

import java.util.Date;

public class TuitionReimbursement {
	private int id;
	private Status status;
	private double amount_reimbursed;
	private Date date_received;
	private String reason;
	private TuitionReimbursementForm form_ref;
	private GradeStatus grade_stat;
	private Employee approved_by;
	private String remarks;
	private TuitionReimbursementType type_id;
	private Submission submission;
	
	public TuitionReimbursement(int id, Status status, double amount_reimbursed, Date date_received, String reason,
			TuitionReimbursementForm form_ref, GradeStatus grade_stat, Employee approved_by, String remarks,
			TuitionReimbursementType type_id, Submission submission) {
		super();
		this.id = id;
		this.status = status;
		this.amount_reimbursed = amount_reimbursed;
		this.date_received = date_received;
		this.reason = reason;
		this.form_ref = form_ref;
		this.grade_stat = grade_stat;
		this.approved_by = approved_by;
		this.remarks = remarks;
		this.type_id = type_id;
		this.submission = submission;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public double getAmount_reimbursed() {
		return amount_reimbursed;
	}
	public void setAmount_reimbursed(double amount_reimbursed) {
		this.amount_reimbursed = amount_reimbursed;
	}
	public Date getTime_submitted() {
		return date_received;
	}
	public void setTime_submitted(Date time_submitted) {
		this.date_received = time_submitted;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public TuitionReimbursementForm getForm_ref() {
		return form_ref;
	}
	public void setForm_ref(TuitionReimbursementForm form_ref) {
		this.form_ref = form_ref;
	}
	public GradeStatus getGrade_stat() {
		return grade_stat;
	}
	public void setGrade_stat(GradeStatus grade_stat) {
		this.grade_stat = grade_stat;
	}
	public Employee getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(Employee approved_by) {
		this.approved_by = approved_by;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public TuitionReimbursementType getType_id() {
		return type_id;
	}
	public void setType_id(TuitionReimbursementType type_id) {
		this.type_id = type_id;
	}
	public Submission getSubmission() {
		return submission;
	}
	public void setSubmission(Submission submission) {
		this.submission = submission;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TuitionReimbursement other = (TuitionReimbursement) obj;
		if (form_ref == null) {
			if (other.form_ref != null)
				return false;
		} else if (!form_ref.equals(other.form_ref))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee []";
	}
}