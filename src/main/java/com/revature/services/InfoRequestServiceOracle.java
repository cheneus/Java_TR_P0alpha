package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.beans.InfoRequest;
import com.revature.data.EmployeeDAO;
import com.revature.data.InfoRequestDAO;
import com.revature.data.TRAppDAOFactory;
import com.revature.data.TuitionReimbursementFormDAO;

public class InfoRequestServiceOracle implements InfoRequestService {
	private Logger log = Logger.getLogger(EmployeeServiceOracle.class);
	private TRAppDAOFactory bf =TRAppDAOFactory.getInstance();
	private EmployeeDAO emd = bf.getEmployeeDAO();
	private InfoRequestDAO infod = bf.getInfoRequestDAO();
	private TuitionReimbursementFormDAO trfd= bf.getTuitionReimbursementFormDAO();
	@Override
	public int addInfoReq(InfoRequest info) {
		infod.addInfoReq(info);
		return 0;
	}
	@Override
	public InfoRequest getInfoReq(InfoRequest info) {
		infod.getInfoReq(info);
		return null;
	}
	@Override
	public InfoRequest getInfoReqById(int id) {
		infod.getInfoReqById(id);
		return null;
	}
	@Override
	public void deleteInfoReq(InfoRequest InfoRequest) {
		infod.deleteInfoReq(InfoRequest);
		
	}
	@Override
	public void updateInfoReq(InfoRequest InfoRequest) {
		infod.updateInfoReq(InfoRequest);
		
	}
}
