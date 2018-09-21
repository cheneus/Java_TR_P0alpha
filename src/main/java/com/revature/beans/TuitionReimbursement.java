package com.revature.beans;

import java.util.Date;

public class TuitionReimbursement {
	private int id;
	private Status status;
	private double amountReimbursed;
	private String reason;
	private TuitionReimbursementForm formRef;
	private GradeStatus gradeStat;
	private Employee approvedBy;
	private String remarks;
	private TuitionReimbursementType typeId;
//	private Submission submission;
	
	public TuitionReimbursement() {
		super();
	}
	public TuitionReimbursement(int id) {
		super();
		this.id = id;
	}
	
	public TuitionReimbursement(int id, Status status, double amountReimbursed, Date date_received, String reason,
			TuitionReimbursementForm formRef, GradeStatus gradeStat, Employee approvedBy, String remarks,
			TuitionReimbursementType typeId) {
//			Submission submission) {
		super();
		this.id = id;
		this.status = status;
		this.amountReimbursed = amountReimbursed;
		this.date_received = date_received;
		this.reason = reason;
		this.formRef = formRef;
		this.gradeStat = gradeStat;
		this.approvedBy = approvedBy;
		this.remarks = remarks;
		this.typeId = typeId;
//		this.submission = submission;
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
	public double getAmountReimbursed() {
		return amountReimbursed;
	}
	public void setAmountReimbursed(double amountReimbursed) {
		this.amountReimbursed = amountReimbursed;
	}
	private Date date_received;
	public Date getDate_received() {
		return date_received;
	}
	public void setDate_received(Date date_received) {
		this.date_received = date_received;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public TuitionReimbursementForm getFormRef() {
		return formRef;
	}
	public void setFormRef(TuitionReimbursementForm formRef) {
		this.formRef = formRef;
	}
	public GradeStatus getGrade_stat() {
		return gradeStat;
	}
	public void setGrade_stat(GradeStatus gradeStat) {
		this.gradeStat = gradeStat;
	}
	public Employee getApproved_by() {
		return approvedBy;
	}
	public void setApproved_by(Employee approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public TuitionReimbursementType getType_id() {
		return typeId;
	}
	public void setType_id(TuitionReimbursementType typeId) {
		this.typeId = typeId;
	}
//	public Submission getSubmission() {
//		return submission;
//	}
//	public void setSubmission(Submission submission) {
//		this.submission = submission;
//	}
	@Override
	public String toString() {
		return "TuitionReimbursement [id=" + id + ", status=" + status + ", amountReimbursed=" + amountReimbursed
				+ ", reason=" + reason + ", formRef=" + formRef + ", gradeStat=" + gradeStat + ", approvedBy="
				+ approvedBy + ", remarks=" + remarks + ", typeId=" + typeId + ", date_received=" + date_received + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amountReimbursed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date_received == null) ? 0 : date_received.hashCode());
		result = prime * result + id;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
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
		TuitionReimbursement other = (TuitionReimbursement) obj;
		if (Double.doubleToLongBits(amountReimbursed) != Double.doubleToLongBits(other.amountReimbursed))
			return false;
		if (date_received == null) {
			if (other.date_received != null)
				return false;
		} else if (!date_received.equals(other.date_received))
			return false;
		if (id != other.id)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		return true;
	}
	
}