package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Department;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class DepartmentOracle implements DepartmentDAO {
	private static Logger log = Logger.getLogger(DepartmentOracle.class);
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	@Override
	public int addDepartment(Department dpt) {
		int key =0;
		log.trace("Adding Department to database.");
		log.trace(dpt);
		Connection conn = cu.getConnection();
		try{
			conn.setAutoCommit(false);
			String sql = "insert into Department (dept_head,name) values(?,?)";
			String[] keys = {"id"};
			PreparedStatement pstm = conn.prepareStatement(sql, keys);
			pstm.setInt(1,dpt.getDept_head());
			pstm.setString(2, dpt.getName());
			
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			
			if(rs.next())
			{
				log.trace("Department created.");
				key = rs.getInt(1);
				dpt.setId(key);
				conn.commit();
			}
			else
			{
				log.trace("Department not created.");
				conn.rollback();
			}
		}
		catch(Exception e)
		{
			LogUtil.rollback(e,conn,DepartmentOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,DepartmentOracle.class);
			}
		}
		return key;
	}
	@Override
	public Department getDepartmentById(int id) {
		Department a = null;
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting Department with id: "+id);
			String sql = "select dept_head, name from Department where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if(rs.next())
			{
				log.trace("Department found.");
				a = new Department();
				a.setId(id);
				a.setDept_head(rs.getInt("dept_head"));
				a.setName(rs.getString("name"));

			}
		} catch (Exception e) {
			LogUtil.logException(e,DepartmentOracle.class);
		}
		return a;
	}
//	@Override
//	public Department getDepartmentByName(String name) {
//		Department a = null;
//		try(Connection conn = cu.getConnection())
//		{
//			log.trace("Getting Department with firstname ="+firstname+" and lastname="+lastname);
//			String sql = "Select id, aboutblurb from Department where firstname=? and lastname=?";
//			PreparedStatement pstm = conn.prepareStatement(sql);
//			pstm.setString(1, firstname);
//			pstm.setString(2, lastname);
//			ResultSet rs = pstm.executeQuery();
//			if(rs.next())
//			{
//				log.trace("Department found.");
//				a = new Department();
//				a.setId(rs.getInt("id"));
//				a.setAbout(rs.getString("aboutblurb"));
//				a.setFirst(firstname);
//				a.setLast(lastname);
//
//			}
//		} catch (Exception e) {
//			LogUtil.logException(e,DepartmentOracle.class);
//		}
//		return a;
//	}
//	
	@Override
	public int getDepartmentHead(int id) {
		Department dpt = null;
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting Department Head with id: "+id);
			String sql = "select dept_head, name from Department where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if(rs.next())
			{
				log.trace("Department found.");
				dpt = new Department();
				dpt.setId(id);
				dpt.setDept_head(rs.getInt("dept_head"));
				dpt.setName(rs.getString("name"));

			}
		} catch (Exception e) {
			LogUtil.logException(e,DepartmentOracle.class);
		}
		return dpt.getDept_head();
	}
	
//	@Override
//	public Set<Department> getDepartments() {
//		Set<Department> Departments = new HashSet<Department>();
//		try(Connection conn = cu.getConnection())
//		{
//			log.trace("Getting all Departments");
//			String sql = "Select id, firstname, lastname, aboutblurb from Department";
//			PreparedStatement pstm = conn.prepareStatement(sql);
//			ResultSet rs = pstm.executeQuery();
//			while(rs.next())
//			{
//				Department a = new Department();
//				a.setId(rs.getInt("id"));
//				a.setAbout(rs.getString("aboutblurb"));
//				a.setFirst(rs.getString("firstname"));
//				Departments.add(a);
//			}
//		} catch (Exception e) {
//			LogUtil.logException(e,DepartmentOracle.class);
//		}
//		return Departments;
//	}
//	@Override
//	public Set<Department> getDepartmentsByBook(Book b) {
//		Set<Department> Departments = new HashSet<Department>();
//		try(Connection conn = cu.getConnection())
//		{
//			log.trace("Getting all Departments by book");
//			String sql = "Select id, firstname, lastname, aboutblurb from Department join book_Department"
//					+ " on Department.id=book_Department.Department_id where book_id=?";
//			PreparedStatement pstm = conn.prepareStatement(sql);
//			pstm.setInt(1, b.getId());
//			ResultSet rs = pstm.executeQuery();
//			while(rs.next())
//			{
//				Department a = new Department();
//				a.setId(rs.getInt("id"));
//				a.setAbout(rs.getString("aboutblurb"));
//				a.setFirst(rs.getString("firstname"));
//				a.setLast(rs.getString("lastname"));
//				Departments.add(a);
//			}
//		} catch (Exception e) {
//			LogUtil.logException(e,DepartmentOracle.class);
//		}
//		return Departments;
//	}
	@Override
	public void updateDepartment(Department a) {
		Connection conn = cu.getConnection();
		try	{
			conn.setAutoCommit(false);
			String sql = "update Department set dept_head=?, name=? where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, a.getDept_head());
			pstm.setString(2, a.getName());
			pstm.setInt(3, a.getId());
			
			int result = pstm.executeUpdate();
			
			if(result == 1)
			{
				log.trace("Department updated");
				conn.commit();
			}
			else {
				log.trace("Department update failed");
				conn.rollback();
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, DepartmentOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,DepartmentOracle.class);
			}
		}
	}
	@Override
	public void deleteDepartment(Department a) {
		log.trace("Deleting Department: "+a);
		Connection conn = cu.getConnection();
		try	{
			conn.setAutoCommit(false);
			String sql = "delete from Department where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, a.getId());
			
			int result = pstm.executeUpdate();
			
			if(result == 1)
			{
				log.trace("Department deleted");
				conn.commit();
			}
			else {
				log.trace("Department delete failed");
				conn.rollback();
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, DepartmentOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,DepartmentOracle.class);
			}
		}
	}
	@Override
	public Department getDepartment(String Department) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
