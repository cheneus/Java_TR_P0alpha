package com.revature.data;

import com.revature.beans.Status;

public interface StatusDAO {
	/**
	 * Returns the id of a Status object inserted into the database.
	 * 
	 * @param Status the Status object to be inserted
	 * @return the id of the Status object inserted
	 */
	public int addStatus(Status Status);
	/**
	 * returns a login object from the database
	 * 
	 * @param u previously created Status object for updating with Status information
	 * @return the Status from the database that matches the Statusname and password
	 */
	public Status getStatus(Status Status);
	/**
	 * returns a login object from the database
	 * 
	 * @param u previously created Status object for updating with Status information
	 * @return the Status from the database that matches the Statusname and password
	 */
	public Status getStatusById(int id);
	/**
	 * deletes a Status from the database
	 * 
	 * @param Status the Status to be deleted
	 */
	public void deleteStatus(Status Status);
	
	/**
	 * updates a Status in the database
	 * 
	 * @param Status the Status to be updated
	 */
	public void updateStatus(Status Status);
}
