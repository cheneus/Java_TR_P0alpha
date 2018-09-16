package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Status;
import com.revature.beans.TuitionReimbursement;
import com.revature.beans.TuitionReimbursementForm;
import com.revature.data.LoginDAO;
import com.revature.data.TRAppDAOFactory;
import com.revature.data.TuitionReimbursementDAO;
import com.revature.data.TuitionReimbursementFormDAO;

public class TuitionReimbursementServiceOracle implements TuitionReimbursementService {
	private Logger log = Logger.getLogger(TuitionReimbursementServiceOracle.class);
	private TRAppDAOFactory bf = TRAppDAOFactory.getInstance();
	private TuitionReimbursementDAO tr = bf.getTuitionReimbursementDAO();

	@Override
	public Set<TuitionReimbursement> getTuitionReimbursements() {
		Set<TuitionReimbursement> trList = tr.getTuitionReimbursements();

//		return empList;
		return trList;
	}



	@Override
	public Set<TuitionReimbursement> getTuitionReimbursementsByStatus(Status s) {
		Set<TuitionReimbursement> trList = tr.getTuitionReimbursementsByStatus(s);

//		return empList;
		return trList;
	}

	@Override
	public TuitionReimbursement getTuitionReimbursementById(int i) {

		return null;
	}

	@Override
	public Set<TuitionReimbursement> getTuitionReimbursementsByPendingStatus() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int addTuitionReimbursement(TuitionReimbursement tr) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void updateTuitionReimbursement(TuitionReimbursement tr) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deleteTuitionReimbursement(TuitionReimbursement tr) {
		// TODO Auto-generated method stub
		
	}

	
}
