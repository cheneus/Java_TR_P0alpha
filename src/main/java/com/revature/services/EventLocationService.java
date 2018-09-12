package com.revature.services;

import com.revature.beans.EventLocation;

public interface EventLocationService {
	/**
	 * Returns the id of a EventLocation object inserted into the database.
	 * 
	 * @param EventLocation the EventLocation object to be inserted
	 * @return the id of the EventLocation object inserted
	 */
	public int addEventLocation(EventLocation EventLocation);
	/**
	 * returns a login object from the database
	 * 
	 * @param u previously created EventLocation object for updating with EventLocation information
	 * @return the EventLocation from the database that matches the EventLocationname and password
	 */
	public EventLocation getEventLocation(EventLocation eventLocation);
	/**
	 * returns a login object from the database
	 * 
	 * @param u previously created EventLocation object for updating with EventLocation information
	 * @return the EventLocation from the database that matches the EventLocationname and password
	 */
	public EventLocation getEventLocationById(int id);
	/**
	 * deletes a EventLocation from the database
	 * 
	 * @param EventLocation the EventLocation to be deleted
	 */
	public void deleteEventLocation(EventLocation EventLocation);
	
	/**
	 * updates a EventLocation in the database
	 * 
	 * @param EventLocation the EventLocation to be updated
	 */
	public void updateEventLocation(EventLocation EventLocation);
}
