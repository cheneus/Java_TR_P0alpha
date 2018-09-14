package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.beans.Login;
import com.revature.driver.Main;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class LoginOracle implements LoginDAO {
	private static Logger log = Logger.getLogger(LoginOracle.class);
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	
	@Override
	public int addLogin(Login login) {
		int key =0;
		log.trace("Adding login to database.");
		log.trace(login);
		Connection conn = cu.getConnection();
		try{
			conn.setAutoCommit(false);
			String sql = "insert into login (username, password, employee_id, admin) values(?,?,?,?)";
			String[] keys = {"id"};
			PreparedStatement pstm = conn.prepareStatement(sql, keys);
			pstm.setString(1,login.getUsername());
			pstm.setString(2, login.getPassword());
			pstm.setInt(3, login.getEmployee_id().getId());
			pstm.setInt(4, 0);
			
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			
			if(rs.next())
			{
				log.trace("Login created.");
				key = rs.getInt(1);
				login.setId(key);
				conn.commit();
			}
			else
			{
				log.trace("User not created.");
				conn.rollback();
			}
		}
		catch(Exception e)
		{
			LogUtil.rollback(e,conn, LoginOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, LoginOracle.class);
			}
		}
		return key;
	}

	@Override
	public Login getLogin(String username, String password) {
		Login u = null;

		try (Connection conn = cu.getConnection()) {
			String sql = "select id,username,password,admin, employee_id from login where username =? and password=?";
			log.trace(sql);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				log.trace("User found!");
				u = new Login();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setAdmin(rs.getInt("admin"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, Main.class);
		}
		return u;
	}
	
	public int getLogin(Login login) {
		Login u = null;
		int emp_id = 0;
		try (Connection conn = cu.getConnection()) {
			String sql = "select id,admin, employee_id from login where username =? and password=?";
			log.trace(sql);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, login.getUsername());
			stmt.setString(2, login.getPassword());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				log.trace("User found!");
				u = new Login();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setAdmin(rs.getInt("admin"));
				emp_id = rs.getInt("employee_id");
			}
		} catch (SQLException e) {
			LogUtil.logException(e, Main.class);
		}
		return emp_id;
	}

	@Override
	public Set<Login> getLogins() {
		// TODO Auto-generated method stub
		Set<Login> logList = new HashSet<Login>();
		try(Connection conn = cu.getConnection())
		{
			String sql = "Select id,username, password, customer_id, login_type from login";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				Login log = new Login();
				log.setEmployee_id(new Employee(rs.getInt("id")));
				log.setPassword(rs.getString("password"));
				log.setUsername(rs.getString("username"));
				log.setAdmin(rs.getInt("admin"));
				log.setId(rs.getInt("id"));
				logList.add(log);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,Login.class);
		}
		return logList;
	}

	@Override
	public Login getLoginById(Login l) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLogin(Login login) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLogin(Login login) {
		// TODO Auto-generated method stub

	}

}
