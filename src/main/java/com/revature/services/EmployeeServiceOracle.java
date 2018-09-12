package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;
import com.revature.beans.Employee;
import com.revature.beans.Login;
import com.revature.data.TRAppDAOFactory;
import com.revature.data.EmployeeDAO;
import com.revature.data.EmployeeOracle;
import com.revature.data.LoginDAO;

public class EmployeeServiceOracle implements EmployeeService {
	private Logger log = Logger.getLogger(EmployeeServiceOracle.class);
	private TRAppDAOFactory tf = TRAppDAOFactory.getInstance();
	private LoginDAO logind = tf.getLoginDAO();
	private EmployeeDAO empd = tf.getEmployeeDAO();
	
	@Override
	public Employee getEmployee(String username, String password) {
		Employee emp = new Employee(0, username, password, null, null, null, null);
		emp = (Employee) logind.getUser(emp);
		emp = empd.getEmployee(emp);
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
		emp = (Employee) logind.getUserById(emp);
		emp = empd.getEmployee(emp);
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
		Set<Employee> empList = empd.getEmployees();
		for(Employee e : empList)
		{
			logind.getUserById(e);
			e.setSupervisor(getEmployeeById(e.getSupervisor().getId()));
		}
		return empList;
	}

	@Override
	public void deleteEmployee(Employee emp) {
		empd.deleteEmployee(emp);

	}

	@Override
	public void updateEmployee(Employee emp) {
		logind.updateUser(emp);
		if(emp.getSupervisor().getFirst()!=null)
			empd.updateEmployee(emp.getSupervisor());
		empd.updateEmployee(emp);

	}

	@Override
	public void addEmployee(Employee emp) {
		User u = logind.getUser(logind.getUser(emp.getUsername(), emp.getPassword()));
		if(u==null)
		{
			logind.addUser(emp);
		}
		Employee supervisor = empd.getEmployee(emp.getSupervisor());
		if(supervisor!=null)
		{
			emp.setSupervisor(supervisor);
		}
	}

}
