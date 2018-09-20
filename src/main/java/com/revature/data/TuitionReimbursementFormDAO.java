package com.revature.data;

import java.util.Set;

import com.revature.beans.Status;
import com.revature.beans.TuitionReimbursementForm;

public interface TuitionReimbursementFormDAO {
	// create
	public int addTuitionReimbursementForm(TuitionReimbursementForm tr);
	// read
	public TuitionReimbursementForm getTuitionReimbursementForm(TuitionReimbursementForm tr);
	public TuitionReimbursementForm getTuitionReimbursementFormById(int i);
	public Set<TuitionReimbursementForm> getTuitionReimbursementForms();
	public Set<TuitionReimbursementForm> getMyTuitionReimbursementForms(int i);
	public Set<TuitionReimbursementForm> getTuitionReimbursementFormsByStatus(Status s);
	// update
	public void updateTuitionReimbursementForm(TuitionReimbursementForm tr);
	public void updateApprove(TuitionReimbursementForm tr);
	// delete
	public void deleteTuitionReimbursementForm(TuitionReimbursementForm tr);
	Set<TuitionReimbursementForm> getTuitionReimbursementFormsByNoAppr(int i);
	Set<TuitionReimbursementForm> getTRFNoApprByManager(int i);
	Set<TuitionReimbursementForm> getTuitionReimbursementFormsOnView();
}
