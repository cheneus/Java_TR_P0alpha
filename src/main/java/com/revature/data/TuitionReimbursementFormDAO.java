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
	public Set<TuitionReimbursementForm> getTuitionReimbursementFormsByNoAppr(int i);
	public Set<TuitionReimbursementForm> getTRFNoApprByManager(int i);
	public Set<TuitionReimbursementForm> getTuitionReimbursementFormsOnView();
	public Set<TuitionReimbursementForm> getTRFNoApprByManager(int i, int did);
	public Set<TuitionReimbursementForm> getTRFNoApprByHR();
	public Set<TuitionReimbursementForm> getTRFNoApprByManagerSI(int i, int did);
	// update
	public void updateTuitionReimbursementForm(TuitionReimbursementForm tr);
	public void updateApprove(TuitionReimbursementForm tr);
	// delete
	public void deleteTuitionReimbursementForm(TuitionReimbursementForm tr);

}
