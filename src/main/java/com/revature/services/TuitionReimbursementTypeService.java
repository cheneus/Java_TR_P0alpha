package com.revature.services;

import java.util.Set;

import com.revature.beans.TuitionReimbursementType;

public interface TuitionReimbursementTypeService {
	// create
	public int addTuitionReimbursementType(TuitionReimbursementType tr);
	// read
	public TuitionReimbursementType getTuitionReimbursementTypes(TuitionReimbursementType tr);
	// update
	public void updateTuitionReimbursementType(TuitionReimbursementType tr);
	// delete
	public void deleteTuitionReimbursementType(TuitionReimbursementType tr);
	public TuitionReimbursementType getTuitionReimbursementTypeById(int i);
	public Set<TuitionReimbursementType> getTuitionReimbursementTypes();
}
