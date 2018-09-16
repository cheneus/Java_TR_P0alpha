package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.GradeFormat;
import com.revature.data.GradeFormatDAO;
import com.revature.data.TRAppDAOFactory;

public class GradeFormatServiceOracle implements GradeFormatService {
	private Logger log = Logger.getLogger(GradeFormatServiceOracle.class);
	private TRAppDAOFactory bf = TRAppDAOFactory.getInstance();
	private GradeFormatDAO dd = bf.getGradeFormatDAO();
	@Override
	public int addGradeFormat(GradeFormat ev) {
		dd.addGradeFormat(ev);
		return 0;
	}
	@Override
	public GradeFormat getGradeFormatById(int id) {
		dd.getGradeFormatById(id);
		return null;
	}
	@Override
	public GradeFormat getGradeFormat(GradeFormat GradeFormat) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateGradeFormat(GradeFormat ev) {
		dd.updateGradeFormat(ev);
		
	}
	@Override
	public void deleteGradeFormat(GradeFormat ev) {
		dd.deleteGradeFormat(ev);
	}
	@Override
	public Set<GradeFormat> getGradeFormats() {
		// TODO Auto-generated method stub
		return dd.getGradeFormats();
	}
	
}
