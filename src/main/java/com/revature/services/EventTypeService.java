package com.revature.services;

import java.util.Set;

import com.revature.beans.EventType;

public interface EventTypeService {
	/**
	 * Returns the id of a EventType object inserted into the database.
	 * 
	 * @param EventType the EventType object to be inserted
	 * @return the id of the EventType object inserted
	 */
	public int addEventType(EventType EventType);
	/**
	 * returns a login object from the database
	 * 
	 * @param u previously created EventType object for updating with EventType information
	 * @return the EventType from the database that matches the EventTypename and password
	 */
	public EventType getEventType(EventType EventType);
	/**
	 * returns a login object from the database
	 * 
	 * @param u previously created EventType object for updating with EventType information
	 * @return the EventType from the database that matches the EventTypename and password
	 */
	public EventType getEventTypeById(int id);
	public Set<EventType> getEventTypes();
	/**
	 * deletes a EventType from the database
	 * 
	 * @param EventType the EventType to be deleted
	 */
	public void deleteEventType(EventType EventType);
	
	/**
	 * updates a EventType in the database
	 * 
	 * @param EventType the EventType to be updated
	 */
	public void updateEventType(EventType EventType);
}
