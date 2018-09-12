package com.revature.data;

import java.util.Set;

import com.revature.beans.Status;
import com.revature.beans.TuitionReimbursementForm;

public interface TuitionReimbursementFormDAO {
	// create
	public int addTuitionReimbursementForm(TuitionReimbursementForm b);
	// read
	public TuitionReimbursementForm getTuitionReimbursementForm(int i);
	public TuitionReimbursementForm getTuitionReimbursementFormByIsbn(String isbn);
	public Set<TuitionReimbursementForm> getTuitionReimbursementForms();
	public Set<TuitionReimbursementForm> getTuitionReimbursementFormsByStatus(Status s);
	// update
	public void updateTuitionReimbursementForm(TuitionReimbursementForm b);
	// delete
	public void deleteTuitionReimbursementForm(TuitionReimbursementForm b);
}
