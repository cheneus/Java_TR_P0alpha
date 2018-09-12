package com.revature.data;

import java.util.Set;

import com.revature.beans.Status;
import com.revature.beans.TuitionReimbursement;

public interface TuitionReimbursementDAO {
	// create
	public int addTuitionReimbursement(TuitionReimbursement b);
	// read
	public TuitionReimbursement getTuitionReimbursement(int i);
	public TuitionReimbursement getTuitionReimbursementByIsbn(String isbn);
	public Set<TuitionReimbursement> getTuitionReimbursements();
	public Set<TuitionReimbursement> getTuitionReimbursementsByStatus(Status s);
	// update
	public void updateTuitionReimbursement(TuitionReimbursement b);
	// delete
	public void deleteTuitionReimbursement(TuitionReimbursement b);
}
