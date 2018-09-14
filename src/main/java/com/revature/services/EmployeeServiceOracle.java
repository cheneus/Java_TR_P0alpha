package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.beans.Login;
import com.revature.data.EmployeeDAO;
import com.revature.data.LoginDAO;
import com.revature.data.TRAppDAOFactory;

public class EmployeeServiceOracle implements EmployeeService {
	private Logger log = Logger.getLogger(EmployeeServiceOracle.class);
	private TRAppDAOFactory bf = TRAppDAOFactory.getInstance();
	private LoginDAO lg = bf.getLoginDAO();
	private EmployeeDAO ed = bf.getEmployeeDAO();
	
	
	@Override
	public Employee getEmployee(String username, String password) {
		Employee emp = new Employee();
		Login logInit = new Login();
		logInit = lg.getLogin(username, password);
		emp.setId(logInit.getEmployee_id().getId());
		emp = ed.getEmployee(emp);
		if(emp.getId()==0)
		{
			log.trace("No employee found");
			return null;
		}
		if(emp.getSupervisor()!=null)
		{
			log.trace("Retrieving supervisor");
			emp.setSupervisor(getEmployeeById(emp.getSupervisor().getId()));
		}
		return emp;
	}

	@Override
	public Employee getEmployeeById(int i) {
		log.trace("retrieving employee by id: "+i);
		Employee emp = new Employee(i, null, null, null, null, null, null);
		emp = (Employee) ud.getLoginById(emp);
		emp = ed.getEmployee(emp);
		if(emp.getId()==0)
		{
			log.trace("No employee found");
			return null;
		}
		if(emp.getSupervisor()!=null)
		{
			log.trace("Retrieving supervisor");
			emp.setSupervisor(getEmployeeById(emp.getSupervisor().getId()));
		}
		return emp;
	}

	@Override
	public Set<Employee> getEmployees() {
		Set<Employee> empList = ed.getEmployees();
		for(Employee e : empList)
		{
			ud.getLoginById(e);
			e.setSupervisor(getEmployeeById(e.getSupervisor().getId()));
		}
		return empList;
	}

	@Override
	public void deleteEmployee(Employee emp) {
		ed.deleteEmployee(emp);

	}

	@Override
	public void updateEmployee(Employee emp) {O
		ud.updateLogin(emp);
		if(emp.getSupervisor().getFirst()!=null)
			ed.updateEmployee(emp.getSupervisor());
		ed.updateEmployee(emp);

	}

	@Override
	public void addEmployee(Employee emp) {
		Login u = ud.getLogin(ud.getLogin(emp.getLoginname(), emp.getPassword()));
		if(u==null)
		{
			ud.addLogin(emp);
		}
		Employee supervisor = ed.getEmployee(emp.getSupervisor());
		if(supervisor!=null)
		{
			emp.setSupervisor(supervisor);
		}
	}

}
