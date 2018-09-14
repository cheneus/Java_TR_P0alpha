package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.beans.GradeStatus;
import com.revature.data.GradeStatusDAO;
import com.revature.data.TRAppDAOFactory;

public class GradeStatusServiceOracle implements GradeStatusService {
	private Logger log = Logger.getLogger(GradeStatusServiceOracle.class);
	private TRAppDAOFactory bf = TRAppDAOFactory.getInstance();
	private GradeStatusDAO dd = bf.getGradeStatusDAO();
	@Override
	public int addGradeStatus(GradeStatus ev) {
		dd.addGradeStatus(ev);
		return 0;
	}
	@Override
	public GradeStatus getGradeStatusById(int id) {
		dd.getGradeStatusById(id);
		return null;
	}
	@Override
	public GradeStatus getGradeStatus(GradeStatus GradeStatus) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateGradeStatus(GradeStatus ev) {
		dd.updateGradeStatus(ev);
		
	}
	@Override
	public void deleteGradeStatus(GradeStatus ev) {
		dd.deleteGradeStatus(ev);
	}
	
}
