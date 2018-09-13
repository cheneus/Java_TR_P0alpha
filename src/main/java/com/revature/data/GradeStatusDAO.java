package com.revature.data;

import java.util.Set;

import com.revature.beans.GradeStatus;

public interface GradeStatusDAO {
	/**
	 * Returns the id of a GradeStatus object inserted into the database.
	 * 
	 * @param GradeStatus the GradeStatus object to be inserted
	 * @return the id of the GradeStatus object inserted
	 */
	public int addGradeStatus(GradeStatus GradeStatus);
	/**
	 * returns a login object from the database
	 * 
	 * @param u previously created GradeStatus object for updating with GradeStatus information
	 * @return the GradeStatus from the database that matches the GradeStatusname and password
	 */
	public GradeStatus getGradeStatus(GradeStatus GradeStatus);
	/**
	 * returns a login object from the database
	 * 
	 * @param u previously created GradeStatus object for updating with GradeStatus information
	 * @return the GradeStatus from the database that matches the GradeStatusname and password
	 */
	public GradeStatus getGradeStatusById(int id);
	/**
	 * deletes a GradeStatus from the database
	 * 
	 * @param GradeStatus the GradeStatus to be deleted
	 */
	public void deleteGradeStatus(GradeStatus GradeStatus);
	
	/**
	 * updates a GradeStatus in the database
	 * 
	 * @param GradeStatus the GradeStatus to be updated
	 */
	public void updateGradeStatus(GradeStatus GradeStatus);
	Set<GradeStatus> getGradeStatus();
}
