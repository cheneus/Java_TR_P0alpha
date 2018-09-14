package com.revature.data;

import com.revature.beans.Department;

public interface DepartmentDAO {
	// Data Access Object
	// Each bean has an object dedicated to
	// accessing the database on its behalf.
	
	// Usually a DAO is going to have CRUD methods.
	
	// create - Insert
	int addDepartment(Department dept);
	// read - Select
	Department getDepartmentById(int id);
	Department getDepartment(String Department);
	int getDepartmentHead(int id);
	// update - Update
	void updateDepartment(Department dept);
	// delete - Delete
	void deleteDepartment(Department dept);
}
