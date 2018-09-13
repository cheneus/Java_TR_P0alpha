package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.GradeStatus;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class GradeStatusOracle implements GradeStatusDAO {
	private static Logger log = Logger.getLogger(GradeStatusOracle.class);
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	@Override
	public int addGradeStatus(GradeStatus a) {
		int key =0;
		log.trace("Adding GradeStatus to database.");
		log.trace(a);
		Connection conn = cu.getConnection();
		try{
			conn.setAutoCommit(false);
			String sql = "insert into GradeStatus (firstname,lastname,aboutblurb) values(?,?,?)";
			String[] keys = {"id"};
			PreparedStatement pstm = conn.prepareStatement(sql, keys);
			pstm.setString(1,a.getFirst());
			pstm.setString(2, a.getLast());
			pstm.setString(3, a.getAbout());
			
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			
			if(rs.next())
			{
				log.trace("GradeStatus created.");
				key = rs.getInt(1);
				a.setId(key);
				conn.commit();
			}
			else
			{
				log.trace("GradeStatus not created.");
				conn.rollback();
			}
		}
		catch(Exception e)
		{
			LogUtil.rollback(e,conn,GradeStatusOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,GradeStatusOracle.class);
			}
		}
		return key;
	}
	@Override
	public GradeStatus getGradeStatus(int id) {
		GradeStatus a = null;
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting GradeStatus with id: "+id);
			String sql = "Select firstname, lastname, aboutblurb from GradeStatus where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if(rs.next())
			{
				log.trace("GradeStatus found.");
				a = new GradeStatus();
				a.setId(id);
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(rs.getString("firstname"));
				a.setLast(rs.getString("lastname"));

			}
		} catch (Exception e) {
			LogUtil.logException(e,GradeStatusOracle.class);
		}
		return a;
	}
	@Override
	public GradeStatus getGradeStatusByName(String firstname, String lastname) {
		GradeStatus a = null;
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting GradeStatus with firstname ="+firstname+" and lastname="+lastname);
			String sql = "Select id, aboutblurb from GradeStatus where firstname=? and lastname=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, firstname);
			pstm.setString(2, lastname);
			ResultSet rs = pstm.executeQuery();
			if(rs.next())
			{
				log.trace("GradeStatus found.");
				a = new GradeStatus();
				a.setId(rs.getInt("id"));
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(firstname);
				a.setLast(lastname);

			}
		} catch (Exception e) {
			LogUtil.logException(e,GradeStatusOracle.class);
		}
		return a;
	}
	@Override
	public Set<GradeStatus> getGradeStatuss() {
		Set<GradeStatus> GradeStatuss = new HashSet<GradeStatus>();
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting all GradeStatuss");
			String sql = "Select id, firstname, lastname, aboutblurb from GradeStatus";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				GradeStatus a = new GradeStatus();
				a.setId(rs.getInt("id"));
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(rs.getString("firstname"));
				a.setLast(rs.getString("lastname"));
				GradeStatuss.add(a);
			}
		} catch (Exception e) {
			LogUtil.logException(e,GradeStatusOracle.class);
		}
		return GradeStatuss;
	}
	@Override
	public Set<GradeStatus> getGradeStatussByBook(Book b) {
		Set<GradeStatus> GradeStatuss = new HashSet<GradeStatus>();
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting all GradeStatuss by book");
			String sql = "Select id, firstname, lastname, aboutblurb from GradeStatus join book_GradeStatus"
					+ " on GradeStatus.id=book_GradeStatus.GradeStatus_id where book_id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, b.getId());
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				GradeStatus a = new GradeStatus();
				a.setId(rs.getInt("id"));
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(rs.getString("firstname"));
				a.setLast(rs.getString("lastname"));
				GradeStatuss.add(a);
			}
		} catch (Exception e) {
			LogUtil.logException(e,GradeStatusOracle.class);
		}
		return GradeStatuss;
	}
	@Override
	public void updateGradeStatus(GradeStatus a) {
		Connection conn = cu.getConnection();
		try	{
			conn.setAutoCommit(false);
			String sql = "update GradeStatus set firstname=?, lastname=?, aboutblurb=? where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, a.getFirst());
			pstm.setString(2, a.getLast());
			pstm.setString(3, a.getAbout());
			pstm.setInt(4, a.getId());
			
			int result = pstm.executeUpdate();
			
			if(result == 1)
			{
				log.trace("GradeStatus updated");
				conn.commit();
			}
			else {
				log.trace("GradeStatus update failed");
				conn.rollback();
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, GradeStatusOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,GradeStatusOracle.class);
			}
		}
	}
	@Override
	public void deleteGradeStatus(GradeStatus a) {
		log.trace("Deleting GradeStatus: "+a);
		Connection conn = cu.getConnection();
		try	{
			conn.setAutoCommit(false);
			String sql = "delete from GradeStatus where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, a.getId());
			
			int result = pstm.executeUpdate();
			
			if(result == 1)
			{
				log.trace("GradeStatus deleted");
				conn.commit();
			}
			else {
				log.trace("GradeStatus delete failed");
				conn.rollback();
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, GradeStatusOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,GradeStatusOracle.class);
			}
		}
	}
}
