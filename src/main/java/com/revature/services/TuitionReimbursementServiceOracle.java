package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Status;
import com.revature.beans.TuitionReimbursement;
import com.revature.beans.TuitionReimbursementForm;
import com.revature.data.TRAppDAOFactory;
import com.revature.data.TuitionReimbursementDAO;

public class TuitionReimbursementServiceOracle implements TuitionReimbursementService {
	private Logger log = Logger.getLogger(TuitionReimbursementServiceOracle.class);
	private TRAppDAOFactory bf = TRAppDAOFactory.getInstance();
	private TuitionReimbursementDAO trf = bf.getTuitionReimbursementDAO();

	@Override
	public Set<TuitionReimbursement> getTuitionReimbursements() {
		Set<TuitionReimbursement> trList = trf.getTuitionReimbursements();

//		return empList;
		return trList;
	}

	@Override
	public Set<TuitionReimbursement> getTuitionReimbursementsByStatus(Status s) {
		Set<TuitionReimbursement> trList = trf.getTuitionReimbursementsByStatus(s);
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
		return trf.addTuitionReimbursement(tr);
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
