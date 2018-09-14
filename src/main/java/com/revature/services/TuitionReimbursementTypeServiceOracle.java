package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.beans.TuitionReimbursementType;
import com.revature.data.TuitionReimbursementTypeDAO;
import com.revature.data.TRAppDAOFactory;

public class TuitionReimbursementTypeServiceOracle implements TuitionReimbursementTypeService {
	private Logger log = Logger.getLogger(TuitionReimbursementTypeServiceOracle.class);
	private TRAppDAOFactory bf = TRAppDAOFactory.getInstance();
	private TuitionReimbursementTypeDAO dd = bf.getTuitionReimbursementTypeDAO();
	@Override
	public int addTuitionReimbursementType(TuitionReimbursementType ev) {
		dd.addTuitionReimbursementType(ev);
		return 0;
	}
	@Override
	public void updateTuitionReimbursementType(TuitionReimbursementType ev) {
		dd.updateTuitionReimbursementType(ev);
		
	}
	@Override
	public void deleteTuitionReimbursementType(TuitionReimbursementType ev) {
		dd.deleteTuitionReimbursementType(ev);
	}
	@Override
	public TuitionReimbursementType getTuitionReimbursementTypes(TuitionReimbursementType tr) {
		dd.getTuitionReimbursementTypes(tr);
		return null;
	}
	
}

