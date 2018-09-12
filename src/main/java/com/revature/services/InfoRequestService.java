package com.revature.services;

import com.revature.beans.InfoRequest;

public interface InfoRequestService {
	
	public int addInfoReq(InfoRequest info);
	public InfoRequest getInfoReq(InfoRequest info);
	public InfoRequest getInfoReqById(int id);
	public void deleteInfoRequest(InfoRequest InfoRequest);
	
	/**
	 * updates a InfoRequest in the database
	 * 
	 * @param InfoRequest the InfoRequest to be updated
	 */
	public void updateInfoRequest(InfoRequest InfoRequest);
	
	
}
