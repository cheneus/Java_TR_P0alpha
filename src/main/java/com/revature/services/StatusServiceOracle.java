package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Status;
import com.revature.data.StatusDAO;
import com.revature.data.TRAppDAOFactory;

public class StatusServiceOracle implements StatusService {
	private Logger log = Logger.getLogger(StatusServiceOracle.class);
	private TRAppDAOFactory bf = TRAppDAOFactory.getInstance();
	private StatusDAO dd = bf.getStatusDAO();
	@Override
	public int addStatus(Status ev) {
		dd.addStatus(ev);
		return 0;
	}
	@Override
	public Status getStatusById(int id) {
		dd.getStatusById(id);
		return null;
	}
	@Override
	public Status getStatus(Status Status) {
		// TODO Auto-generated method stub
		return null;
	}
	public Set<Status> getStatus() {
		return dd.getStatus();
	}
	@Override
	public void updateStatus(Status ev) {
		dd.updateStatus(ev);
		
	}
	@Override
	public void deleteStatus(Status ev) {
		dd.deleteStatus(ev);
	}
	
}
