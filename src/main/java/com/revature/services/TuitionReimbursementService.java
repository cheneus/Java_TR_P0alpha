package com.revature.services;

import java.util.Set;

import com.revature.beans.Status;
import com.revature.beans.TuitionReimbursement;

public interface TuitionReimbursementService {
	// create
	public int addTuitionReimbursement(TuitionReimbursement b);
	// read
	public TuitionReimbursement getTuitionReimbursement(int i);
	public Set<TuitionReimbursement> getTuitionReimbursements();
	public Set<TuitionReimbursement> getTuitionReimbursementsByStatus(Status s);
	// update
	public void updateTuitionReimbursement(TuitionReimbursement b);
	// delete
	public void deleteTuitionReimbursement(TuitionReimbursement b);
}
