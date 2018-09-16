package com.revature.data;

import java.util.Set;

import com.revature.beans.Status;
import com.revature.beans.TuitionReimbursement;

public interface TuitionReimbursementDAO {
	// create
	public int addTuitionReimbursement(TuitionReimbursement tr);
	// read
	public TuitionReimbursement getTuitionReimbursement(TuitionReimbursement tr);
	public Set<TuitionReimbursement> getTuitionReimbursements();
	public Set<TuitionReimbursement> getTuitionReimbursementsByStatus(Status s);
	public Set<TuitionReimbursement> getTuitionReimbursementsByPendingStatus();
	public TuitionReimbursement getTuitionReimbursementById(int i);
	// update
	public void updateTuitionReimbursement(TuitionReimbursement tr);
	// delete
	public void deleteTuitionReimbursement(TuitionReimbursement tr);

}
