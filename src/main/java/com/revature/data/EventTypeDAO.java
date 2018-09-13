package com.revature.data;

import com.revature.beans.EventType;

public interface EventTypeDAO {
	/**
	 * Returns the id of a EventType object inserted into the database.
	 * 
	 * @param EventType the EventType object to be inserted
	 * @return the id of the EventType object inserted
	 */
	public int addEventType(EventType ev);
	/**
	 * returns a login object from the database
	 * 
	 * @param u previously created EventType object for updating with EventType information
	 * @return the EventType from the database that matches the EventTypename and password
	 */
	public EventType getEventType(EventType ev);
	/**
	 * returns a login object from the database
	 * 
	 * @param u previously created EventType object for updating with EventType information
	 * @return the EventType from the database that matches the EventTypename and password
	 */
	public EventType getEventTypeById(int id);
	/**
	 * deletes a EventType from the database
	 * 
	 * @param EventType the EventType to be deleted
	 */
	public void deleteEventType(EventType ev);
	
	/**
	 * updates a EventType in the database
	 * 
	 * @param EventType the EventType to be updated
	 */
	public void updateEventType(EventType ev);
}
