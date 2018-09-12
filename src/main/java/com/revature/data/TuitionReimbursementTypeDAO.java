package com.revature.data;

import com.revature.beans.TuitionReimbursementType;

public interface TuitionReimbursementTypeDAO {
	// create
	public int addTuitionReimbursementType(TuitionReimbursementType tr);
	// read
	public TuitionReimbursementType getTuitionReimbursementTypesById(int i);
	public TuitionReimbursementType getTuitionReimbursementTypes(TuitionReimbursementType tr);
	// update
	public void updateTuitionReimbursementType(TuitionReimbursementType tr);
	// delete
	public void deleteTuitionReimbursementType(TuitionReimbursementType tr);
}
