package com.revature.beans;

import java.util.Date;

public class TuitionReimbursement {
	private int id;
	private Status status;
	private double amount_reimbursed;
	private Date time_submitted;
	private String reason;
	private TuitionReimbursementForm form_ref;
	private GradeStatus grade_stat;
	private Employee approved_by;
	private String remarks;
	private TuitionReimbursementType type_id;
	private Submission submission;
	
	
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