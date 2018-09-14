package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.beans.Department;
import com.revature.data.DepartmentDAO;
import com.revature.data.TRAppDAOFactory;

public class DepartmentServiceOracle implements DepartmentService {
	private Logger log = Logger.getLogger(DepartmentServiceOracle.class);
	private TRAppDAOFactory bf = TRAppDAOFactory.getInstance();
	private DepartmentDAO dd = bf.getDepartmentDAO();
	@Override
	public int addDepartment(Department dept) {
		dd.addDepartment(dept);
		return 0;
	}
	@Override
	public Department getDepartmentById(int id) {
		dd.getDepartmentById(id);
		return null;
	}
	@Override
	public Department getDepartment(String Department) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getDepartmentHead(int id) {
		
		return dd.getDepartmentHead(id);
	}
	@Override
	public void updateDepartment(Department dept) {
		dd.updateDepartment(dept);
		
	}
	@Override
	public void deleteDepartment(Department dept) {
		dd.deleteDepartment(dept);
	}
	
}