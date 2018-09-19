package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Address;
import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.exceptions.NullArgumentException;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class EmployeeOracle implements EmployeeDAO {

	private Logger log = Logger.getLogger(EmployeeOracle.class);
	private ConnectionUtil cu = ConnectionUtil.getInstance();
	
	@Override
	public void addEmployee(Employee employee) {
		Connection conn = cu.getConnection();
		try {
			log.trace("Inserting employee into db");
			conn.setAutoCommit(false);
			String sql = "insert into emp (id, supervisor, title) values (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,employee.getId());
			ps.setInt(2, employee.getSupervisor().getId());
			ps.setString(3, employee.getTitle());
			int result = ps.executeUpdate();
			if(result!=1)
			{
				log.warn("Insertion of employee failed.");
				conn.rollback();
			}
			else {
				log.trace("Successful insertion of employee");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.rollback(e,conn,EmployeeOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,EmployeeOracle.class);
			}
		}
	}

	@Override
	public Employee getEmployee(Employee emp) {
		if(emp==null)
		{
			throw new NullArgumentException("emp can't be null");
		}
		try(Connection conn = cu.getConnection())
		{
			String sql = "select id, lastname,firstname, title, supervisor,birthdate, hiredate, dept_id, address, reimbursement_balance, phone,email from employee where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, emp.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				log.trace("This is a employee");
				if(rs.getObject("supervisor")==null)
				{
					log.trace("null supervisor");
				} else {
					emp.setSupervisor(new Employee(rs.getInt("supervisor")));
				}
				Department dept = new Department();
				dept.setId(rs.getInt("dept_id"));
				Address add = new Address();
				add.setId(rs.getInt("address"));
				emp.setId(rs.getInt("id"));
				emp.setFirstname(rs.getString("firstname"));
				emp.setLastname(rs.getString("lastname"));
				emp.setTitle(rs.getString("title"));
				emp.setBirthDate(rs.getDate("birthdate"));
				emp.setHireDate(rs.getDate("hiredate"));
				emp.setDept_id(dept);
				emp.setAddress(add);
				emp.setReimbursement_balance(rs.getDouble("reimbursement_balance"));
				emp.setPhone(rs.getString("phone"));
				emp.setEmail(rs.getString("email"));
			}
			else
			{
				log.trace("This is not a employee");
				emp.setFirstname(null);
				emp.setLastname(null);
				emp.setId(0);
				emp.setBirthDate(null);
				emp.setTitle(null);
				emp.setHireDate(null);
				emp.setDept_id(null);
				emp.setAddress(0);
				emp.setReimbursement_balance(0);
				emp.setPhone(null);
				emp.setEmail(null);
				
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,EmployeeOracle.class);
		}
		return emp;
	}
	
	public Employee getEmployeeById(int i) {
		Employee emp = new Employee();
		try(Connection conn = cu.getConnection())
		{
			String sql = "select id, lastname,firstname, title, supervisor,birthdate, hiredate, dept_id, address, reimbursement_balance, phone,email from employee where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				log.trace("This is a employee");
				if(rs.getObject("sup_id")==null)
				{
					log.trace("null supervisor");
				} else {
					emp.setSupervisor(new Employee(rs.getInt("supervisor")));
				}
				Department dept = new Department();
				dept.setId(rs.getInt("dept_id"));
				Address add = new Address();
				add.setId(rs.getInt("address"));
				emp.setId(rs.getInt("id"));
				emp.setFirstname(rs.getString("firstname"));
				emp.setLastname(rs.getString("lastname"));
				emp.setTitle(rs.getString("title"));
				emp.setBirthDate(rs.getDate("birthdate"));
				emp.setHireDate(rs.getDate("hiredate"));
				emp.setDept_id(dept);
				emp.setAddress(add);
				emp.setReimbursement_balance(rs.getDouble("reimbursement_balance"));
				emp.setPhone(rs.getString("phone"));
				emp.setEmail(rs.getString("email"));
			}
			else
			{
				log.trace("This is not a employee");
				emp.setFirstname(null);
				emp.setLastname(null);
				emp.setId(0);
				emp.setBirthDate(null);
				emp.setHireDate(null);
				emp.setDept_id(null);
				emp.setAddress(0);
				emp.setReimbursement_balance(0);
				emp.setPhone(null);
				emp.setEmail(null);
				
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,EmployeeOracle.class);
		}
		return emp;
	}

	@Override
	public Set<Employee> getEmployees() {
		Set<Employee> empList = new HashSet<Employee>();
		try(Connection conn = cu.getConnection())
		{
			String sql = "Select id, sup_id, title from emp";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				Employee emp = new Employee();
				if(rs.getObject("sup_id")==null)
				{
					log.trace("null supervisor");
				} else {
					emp.setSupervisor(new Employee(rs.getInt("sup_id")));
				}
				emp.setTitle(rs.getString("title"));
				emp.setId(rs.getInt("id"));
				empList.add(emp);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,EmployeeOracle.class);
		}
		return empList;
	}
	@Override
	public void updateEmployee(Employee employee)
	{
		log.trace("Updating employee from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "update emp set sup_id = ?, title = ? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, employee.getSupervisor().getId());
			pstm.setString(2, employee.getTitle());
			pstm.setInt(3, employee.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("employee not updated.");
				conn.rollback();
			}
			else
			{
				log.trace("employee updated.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,EmployeeOracle.class);
		}
	}

	@Override
	public void deleteEmployee(Employee employee) {
		log.trace("Removing employee from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "delete from emp where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, employee.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("employee not deleted.");
				conn.rollback();
			}
			else
			{
				log.trace("employee deleted.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,EmployeeOracle.class);
		}
	}

}
