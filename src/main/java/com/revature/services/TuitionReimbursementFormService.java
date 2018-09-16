package com.revature.services;

import java.util.Set;

import com.revature.beans.Status;
import com.revature.beans.TuitionReimbursementForm;

public interface TuitionReimbursementFormService {
	// create
	public int addTuitionReimbursementForm(TuitionReimbursementForm b);
	// read
	public TuitionReimbursementForm getTuitionReimbursementFormById(int i);
	public Set<TuitionReimbursementForm> getTuitionReimbursementForms();
	public Set<TuitionReimbursementForm> getTuitionReimbursementFormsByStatus(Status s);
	// update
	public void updateTuitionReimbursementForm(TuitionReimbursementForm b);
	// delete
	public void deleteTuitionReimbursementForm(TuitionReimbursementForm b);
}
