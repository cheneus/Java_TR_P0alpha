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
		return trf.addTuitionReimbursementForm(tr);
	}

	@Override
	public TuitionReimbursementForm getTuitionReimbursementFormById(int i) {
		return trf.getTuitionReimbursementFormById(i);
	}

	@Override
	public TuitionReimbursementForm getTuitionReimbursementForm(TuitionReimbursementForm tr) {
		return trf.getTuitionReimbursementForm(tr);

	}
	
	@Override
	public Set<TuitionReimbursementForm> getTuitionReimbursementFormsByStatus(Status s) {
		Set<TuitionReimbursementForm> trList = trf.getTuitionReimbursementFormsByStatus(s);
//		for(TuitionReimbursementForm e : empList)
//		return empList;
		return trList;
	}

	@Override
	public Set<TuitionReimbursementForm> getTuitionReimbursementFormsOnView() {
		return trf.getTuitionReimbursementFormsOnView();
	}

	@Override
	public Set<TuitionReimbursementForm> getTuitionReimbursementFormsByNoAppr(int i) {
		return trf.getTuitionReimbursementFormsByNoAppr(i);
	}

	@Override
	public Set<TuitionReimbursementForm> getMyTuitionReimbursementForms(int i) {
		return trf.getMyTuitionReimbursementForms(i);
	}



	
}
