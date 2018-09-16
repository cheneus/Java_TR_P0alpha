package com.revature.services;

import java.util.Set;

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
		return dd.addTuitionReimbursementType(ev);
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
	public Set<TuitionReimbursementType> getTuitionReimbursementTypes() {
		return dd.getTuitionReimbursementTypes();
	}
	@Override
	public TuitionReimbursementType getTuitionReimbursementTypeById(int i) {
		return dd.getTuitionReimbursementTypesById(i);
	}
	
	@Override
	public TuitionReimbursementType getTuitionReimbursementTypes(TuitionReimbursementType tr) {
		return dd.getTuitionReimbursementTypes(tr);
		
	}
	
}

