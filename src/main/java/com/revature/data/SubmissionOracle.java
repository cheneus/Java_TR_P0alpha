package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Submission;
import com.revature.exceptions.NullArgumentException;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class SubmissionOracle implements SubmissionDAO {

	private Logger log = Logger.getLogger(SubmissionOracle.class);
	private ConnectionUtil cu = ConnectionUtil.getInstance();
	
	@Override
	public void addSubmission(Submission Submission) {
		Connection conn = cu.getConnection();
		try {
			log.trace("Inserting Submission into db");
			conn.setAutoCommit(false);
			String sql = "insert into emp (id, sup_id, title) values (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,Submission.getId());
			ps.setInt(2, Submission.getSupervisor().getId());
			ps.setString(3, Submission.getTitle());
			int result = ps.executeUpdate();
			if(result!=1)
			{
				log.warn("Insertion of Submission failed.");
				conn.rollback();
			}
			else {
				log.trace("Successful insertion of Submission");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.rollback(e,conn,SubmissionOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,SubmissionOracle.class);
			}
		}
	}

	@Override
	public Submission getSubmission(Submission emp) {
		if(emp==null)
		{
			throw new NullArgumentException("emp can't be null");
		}
		try(Connection conn = cu.getConnection())
		{
			String sql = "select ID, sup_id, title from emp where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, emp.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				log.trace("This is a Submission");
				if(rs.getObject("sup_id")==null)
				{
					log.trace("null supervisor");
				} else {
					emp.setSupervisor(new Submission(rs.getInt("sup_id")));
				}
				emp.setTitle(rs.getString("title"));
			}
			else
			{
				log.trace("This is not a Submission");
				emp.setFirst(null);
				emp.setLast(null);
				emp.setId(0);
				emp.setPassword(null);
				emp.setUsername(null);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,SubmissionOracle.class);
		}
		return emp;
	}

	@Override
	public Set<Submission> getSubmissions() {
		Set<Submission> empList = new HashSet<Submission>();
		try(Connection conn = cu.getConnection())
		{
			String sql = "Select id, sup_id, title from emp";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				Submission emp = new Submission();
				if(rs.getObject("sup_id")==null)
				{
					log.trace("null supervisor");
				} else {
					emp.setSupervisor(new Submission(rs.getInt("sup_id")));
				}
				emp.setTitle(rs.getString("title"));
				emp.setId(rs.getInt("id"));
				empList.add(emp);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,SubmissionOracle.class);
		}
		return empList;
	}
	@Override
	public void updateSubmission(Submission Submission)
	{
		log.trace("Updating Submission from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "update emp set sup_id = ?, title = ? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, Submission.getSupervisor().getId());
			pstm.setString(2, Submission.getTitle());
			pstm.setInt(3, Submission.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("Submission not updated.");
				conn.rollback();
			}
			else
			{
				log.trace("Submission updated.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,SubmissionOracle.class);
		}
	}

	@Override
	public void deleteSubmission(Submission Submission) {
		log.trace("Removing Submission from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "delete from emp where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, Submission.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("Submission not deleted.");
				conn.rollback();
			}
			else
			{
				log.trace("Submission deleted.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,SubmissionOracle.class);
		}
	}

	@Override
	public int addSubmission(Submission Submission) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Submission getSubmissionById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
