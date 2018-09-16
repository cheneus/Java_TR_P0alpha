package com.revature.services;

import java.util.Set;

import com.revature.beans.Status;
import com.revature.beans.TuitionReimbursement;

public interface TuitionReimbursementService {
	// create
	public int addTuitionReimbursement(TuitionReimbursement tr);
	// read
	public TuitionReimbursement getTuitionReimbursementById(int i);
	public Set<TuitionReimbursement> getTuitionReimbursements();
	public Set<TuitionReimbursement> getTuitionReimbursementsByStatus(Status s);
	public Set<TuitionReimbursement> getTuitionReimbursementsByPendingStatus();
	// update
	public void updateTuitionReimbursement(TuitionReimbursement tr);
	// delete
	public void deleteTuitionReimbursement(TuitionReimbursement tr);
}
