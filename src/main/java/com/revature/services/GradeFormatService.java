package com.revature.services;

import com.revature.beans.GradeFormat;

public interface GradeFormatService {
	/**
	 * Returns the id of a GradeFormat object inserted into the database.
	 * 
	 * @param GradeFormat the GradeFormat object to be inserted
	 * @return the id of the GradeFormat object inserted
	 */
	public int addGradeFormat(GradeFormat GradeFormat);
	/**
	 * returns a login object from the database
	 * 
	 * @param u previously created GradeFormat object for updating with GradeFormat information
	 * @return the GradeFormat from the database that matches the GradeFormatname and password
	 */
	public GradeFormat getGradeFormat(GradeFormat GradeFormat);
	/**
	 * returns a login object from the database
	 * 
	 * @param u previously created GradeFormat object for updating with GradeFormat information
	 * @return the GradeFormat from the database that matches the GradeFormatname and password
	 */
	public GradeFormat getGradeFormatById(int id);
	/**
	 * deletes a GradeFormat from the database
	 * 
	 * @param GradeFormat the GradeFormat to be deleted
	 */
	public void deleteGradeFormat(GradeFormat GradeFormat);
	
	/**
	 * updates a GradeFormat in the database
	 * 
	 * @param GradeFormat the GradeFormat to be updated
	 */
	public void updateGradeFormat(GradeFormat GradeFormat);
}
