package com.revature.services;

import com.revature.beans.Submission;

public interface SubmissionService {
	/**
	 * Returns the id of a Submission object inserted into the database.
	 * 
	 * @param Submission the Submission object to be inserted
	 * @return the id of the Submission object inserted
	 */
	public int addSubmission(Submission Submission);
	/**
	 * returns a login object from the database
	 * 
	 * @param u previously created Submission object for updating with Submission information
	 * @return the Submission from the database that matches the Submissionname and password
	 */
	public Submission getSubmission(Submission Submission);
	/**
	 * returns a login object from the database
	 * 
	 * @param u previously created Submission object for updating with Submission information
	 * @return the Submission from the database that matches the Submissionname and password
	 */
	public Submission getSubmissionById(int id);
	/**
	 * deletes a Submission from the database
	 * 
	 * @param Submission the Submission to be deleted
	 */
	public void deleteSubmission(Submission Submission);
	
	/**
	 * updates a Submission in the database
	 * 
	 * @param Submission the Submission to be updated
	 */
	public void updateSubmission(Submission Submission);
}
