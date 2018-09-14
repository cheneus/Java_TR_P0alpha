package com.revature.data;

import java.util.Set;

import com.revature.beans.TuitionReimbursementType;

public interface TuitionReimbursementTypeDAO {
	// create
	public int addTuitionReimbursementType(TuitionReimbursementType tr);
	// read;
	public TuitionReimbursementType getTuitionReimbursementTypes(TuitionReimbursementType tr);
	// update
	public void updateTuitionReimbursementType(TuitionReimbursementType tr);
	// delete
	public void deleteTuitionReimbursementType(TuitionReimbursementType tr);
	public Set<TuitionReimbursementType> getTuitionReimbursementTypes();
}
