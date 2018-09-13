package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Status;
import com.revature.beans.Status;
import com.revature.utils.LogUtil;

public class StatusOracle implements StatusDAO {
	public int addStatus(Status a) {
		int key =0;
		log.trace("Adding Status to database.");
		log.trace(a);
		Connection conn = cu.getConnection();
		try{
			conn.setAutoCommit(false);
			String sql = "insert into Status (firstname,lastname,aboutblurb) values(?,?,?)";
			String[] keys = {"id"};
			PreparedStatement pstm = conn.prepareStatement(sql, keys);
			pstm.setString(1,a.getFirst());
			pstm.setString(2, a.getLast());
			pstm.setString(3, a.getAbout());
			
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			
			if(rs.next())
			{
				log.trace("Status created.");
				key = rs.getInt(1);
				a.setId(key);
				conn.commit();
			}
			else
			{
				log.trace("Status not created.");
				conn.rollback();
			}
		}
		catch(Exception e)
		{
			LogUtil.rollback(e,conn,StatusOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,StatusOracle.class);
			}
		}
		return key;
	}
	@Override
	public Status getStatus(int id) {
		Status a = null;
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting Status with id: "+id);
			String sql = "Select firstname, lastname, aboutblurb from Status where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if(rs.next())
			{
				log.trace("Status found.");
				a = new Status();
				a.setId(id);
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(rs.getString("firstname"));
				a.setLast(rs.getString("lastname"));

			}
		} catch (Exception e) {
			LogUtil.logException(e,StatusOracle.class);
		}
		return a;
	}
	@Override
	public Status getStatusByName(String firstname, String lastname) {
		Status a = null;
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting Status with firstname ="+firstname+" and lastname="+lastname);
			String sql = "Select id, aboutblurb from Status where firstname=? and lastname=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, firstname);
			pstm.setString(2, lastname);
			ResultSet rs = pstm.executeQuery();
			if(rs.next())
			{
				log.trace("Status found.");
				a = new Status();
				a.setId(rs.getInt("id"));
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(firstname);
				a.setLast(lastname);

			}
		} catch (Exception e) {
			LogUtil.logException(e,StatusOracle.class);
		}
		return a;
	}
	@Override
	public Set<Status> getStatuss() {
		Set<Status> Statuss = new HashSet<Status>();
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting all Statuss");
			String sql = "Select id, firstname, lastname, aboutblurb from Status";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				Status a = new Status();
				a.setId(rs.getInt("id"));
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(rs.getString("firstname"));
				a.setLast(rs.getString("lastname"));
				Statuss.add(a);
			}
		} catch (Exception e) {
			LogUtil.logException(e,StatusOracle.class);
		}
		return Statuss;
	}
	@Override
	public Set<Status> getStatussByBook(Book b) {
		Set<Status> Statuss = new HashSet<Status>();
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting all Statuss by book");
			String sql = "Select id, firstname, lastname, aboutblurb from Status join book_Status"
					+ " on Status.id=book_Status.Status_id where book_id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, b.getId());
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				Status a = new Status();
				a.setId(rs.getInt("id"));
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(rs.getString("firstname"));
				a.setLast(rs.getString("lastname"));
				Statuss.add(a);
			}
		} catch (Exception e) {
			LogUtil.logException(e,StatusOracle.class);
		}
		return Statuss;
	}
	@Override
	public void updateStatus(Status a) {
		Connection conn = cu.getConnection();
		try	{
			conn.setAutoCommit(false);
			String sql = "update Status set firstname=?, lastname=?, aboutblurb=? where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, a.getFirst());
			pstm.setString(2, a.getLast());
			pstm.setString(3, a.getAbout());
			pstm.setInt(4, a.getId());
			
			int result = pstm.executeUpdate();
			
			if(result == 1)
			{
				log.trace("Status updated");
				conn.commit();
			}
			else {
				log.trace("Status update failed");
				conn.rollback();
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, StatusOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,StatusOracle.class);
			}
		}
	}
	@Override
	public void deleteStatus(Status a) {
		log.trace("Deleting Status: "+a);
		Connection conn = cu.getConnection();
		try	{
			conn.setAutoCommit(false);
			String sql = "delete from Status where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, a.getId());
			
			int result = pstm.executeUpdate();
			
			if(result == 1)
			{
				log.trace("Status deleted");
				conn.commit();
			}
			else {
				log.trace("Status delete failed");
				conn.rollback();
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, StatusOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,StatusOracle.class);
			}
		}
	}

}
