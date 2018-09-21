package com.revature.services;

import java.util.Set;

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
		return infod.addInfoReq(info);
	}
	@Override
	public InfoRequest getInfoReq(InfoRequest info) {
		return infod.getInfoReq(info);

	}
	@Override
	public InfoRequest getInfoReqById(int id) {
		return infod.getInfoReqById(id);

	}
	@Override
	public void deleteInfoReq(InfoRequest InfoRequest) {
		infod.deleteInfoReq(InfoRequest);
		
	}
	@Override
	public void updateInfoReq(InfoRequest InfoRequest) {
		infod.updateInfoReq(InfoRequest);
		
	}
	@Override
	public Set<InfoRequest> getInfoReq() {
		return infod.getInfoReq();
	}
	@Override
	public Set<InfoRequest> getUrInfoReq(int i) {
		return infod.getUrInfoReq(i);
	}
}
