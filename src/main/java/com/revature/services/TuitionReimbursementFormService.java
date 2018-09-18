package com.revature.services;

import java.util.Set;

import com.revature.beans.Status;
import com.revature.beans.TuitionReimbursementForm;

public interface TuitionReimbursementFormService {
	// create
	public int addTuitionReimbursementForm(TuitionReimbursementForm tr);
	// read
	public TuitionReimbursementForm getTuitionReimbursementFormById(int i);
	public TuitionReimbursementForm getTuitionReimbursementForm(TuitionReimbursementForm tr);
	public Set<TuitionReimbursementForm> getTuitionReimbursementForms();
	public Set<TuitionReimbursementForm> getTuitionReimbursementFormsByStatus(Status s);
	Set<TuitionReimbursementForm> getTuitionReimbursementFormsByPendingStatus();
	Set<TuitionReimbursementForm> getTuitionReimbursementFormsOnView();
	// update
	public void updateTuitionReimbursementForm(TuitionReimbursementForm tr);
	// delete
	public void deleteTuitionReimbursementForm(TuitionReimbursementForm tr);
}
