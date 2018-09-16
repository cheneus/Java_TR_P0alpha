package com.revature.services;

import java.util.Set;

import com.revature.beans.InfoRequest;

public interface InfoRequestService {
	
	public int addInfoReq(InfoRequest info);
	public InfoRequest getInfoReq(InfoRequest info);
	public Set<InfoRequest> getInfoReq();
	public InfoRequest getInfoReqById(int id);
	public void deleteInfoReq(InfoRequest InfoRequest);
	
	/**
	 * updates a InfoRequest in the database
	 * 
	 * @param InfoRequest the InfoRequest to be updated
	 */
	public void updateInfoReq(InfoRequest InfoRequest);
	
	
}
