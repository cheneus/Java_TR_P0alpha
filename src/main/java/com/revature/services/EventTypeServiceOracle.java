package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.beans.EventType;
import com.revature.data.EventTypeDAO;
import com.revature.data.TRAppDAOFactory;

public class EventTypeServiceOracle implements EventTypeService {
	private Logger log = Logger.getLogger(EventTypeServiceOracle.class);
	private TRAppDAOFactory bf = TRAppDAOFactory.getInstance();
	private EventTypeDAO dd = bf.getEventTypeDAO();

	@Override
	public int addEventType(EventType ev) {
		dd.addEventType(ev);
		return 0;
	}

	@Override
	public EventType getEventTypeById(int id) {
		dd.getEventTypeById(id);
		return null;
	}

	@Override
	public EventType getEventType(EventType EventType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEventType(EventType ev) {
		dd.updateEventType(ev);

	}

	@Override
	public void deleteEventType(EventType ev) {
		dd.deleteEventType(ev);
	}

}
