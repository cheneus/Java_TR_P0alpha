package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Status;
import com.revature.beans.TuitionReimbursementForm;
import com.revature.data.LoginDAO;
import com.revature.data.TRAppDAOFactory;
import com.revature.data.TuitionReimbursementFormDAO;

public class TuitionReimbursementFormServiceOracle implements TuitionReimbursementFormService {
	private Logger log = Logger.getLogger(TuitionReimbursementFormServiceOracle.class);
	private TRAppDAOFactory bf = TRAppDAOFactory.getInstance();
	private LoginDAO lg = bf.getLoginDAO();
	private TuitionReimbursementFormDAO trf = bf.getTuitionReimbursementFormDAO();

	@Override
	public Set<TuitionReimbursementForm> getTuitionReimbursementForms() {
		Set<TuitionReimbursementForm> trList = trf.getTuitionReimbursementForms();
//		for(TuitionReimbursementForm e : empList)
//		return empList;
		return trList;
	}

	@Override
	public void deleteTuitionReimbursementForm(TuitionReimbursementForm tr) {
		trf.deleteTuitionReimbursementForm(tr);

	}

	@Override
	public void updateTuitionReimbursementForm(TuitionReimbursementForm tr) {
		trf.updateTuitionReimbursementForm(tr);

	}

	@Override
	public int addTuitionReimbursementForm(TuitionReimbursementForm tr) {
		trf.addTuitionReimbursementForm(tr);
		return 1;
	}

	@Override
	public TuitionReimbursementForm getTuitionReimbursementFormById(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TuitionReimbursementForm> getTuitionReimbursementFormsByStatus(Status s) {
		Set<TuitionReimbursementForm> trList = trf.getTuitionReimbursementFormsByStatus(s);
//		for(TuitionReimbursementForm e : empList)
//		return empList;
		return trList;
	}

	
}
