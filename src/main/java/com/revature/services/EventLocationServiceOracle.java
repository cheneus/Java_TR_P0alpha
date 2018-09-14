package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.beans.EventLocation;
import com.revature.data.EventLocationDAO;
import com.revature.data.TRAppDAOFactory;

public class EventLocationServiceOracle implements EventLocationService {
	private Logger log = Logger.getLogger(EventLocationServiceOracle.class);
	private TRAppDAOFactory bf = TRAppDAOFactory.getInstance();
	private EventLocationDAO dd = bf.getEventLocationDAO();
	@Override
	public int addEventLocation(EventLocation dept) {
		dd.addEventLocation(dept);
		return 0;
	}
	@Override
	public EventLocation getEventLocationById(int id) {
		dd.getEventLocationById(id);
		return null;
	}
	@Override
	public EventLocation getEventLocation(EventLocation EventLocation) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateEventLocation(EventLocation dept) {
		dd.updateEventLocation(dept);
		
	}
	@Override
	public void deleteEventLocation(EventLocation dept) {
		dd.deleteEventLocation(dept);
	}
	
}