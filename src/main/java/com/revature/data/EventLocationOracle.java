package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.beans.EventLocation;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class EventLocationOracle implements EventLocationDAO {
	private Logger log = Logger.getLogger(EventLocationOracle.class);
	private ConnectionUtil cu = ConnectionUtil.getInstance();
	@Override
	public int addEventLocation(EventLocation EventLocation) {
		int key = 0;
		log.trace("Adding user to database.");
		log.trace(EventLocation);
		Connection conn = cu.getConnection();
		try{
			conn.setAutoCommit(false);
			String sql = "insert into EventLocation (lineone, linetwo, city, state, zip) values(?,?,?,?,?)";
			String[] keys = {"id"};
			PreparedStatement pstm = conn.prepareStatement(sql, keys);
			pstm.setString(1,EventLocation.getLineOne());
			pstm.setString(2, EventLocation.getLineTwo());
			pstm.setString(3, EventLocation.getCity());
			pstm.setString(4, EventLocation.getState());
			pstm.setString(5, EventLocation.getZip());
			
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			
			if(rs.next())
			{
				log.trace("EventLocation created.");
				key = rs.getInt(1);
				EventLocation.setId(key);
				conn.commit();
			}
			else
			{
				log.trace("EventLocation not created.");
				conn.rollback();
			}
		}
		catch(Exception e)
		{
			LogUtil.rollback(e,conn,EventLocationOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,EventLocationOracle.class);
			}
		}
		return key;
	}

	@Override
	public EventLocation getEventLocationById(int id) {
		EventLocation a = null;
		try(Connection conn = cu.getConnection())
		{
			log.trace("retrieving EventLocation information");
			String sql = "select lineone, linetwo, city, state, zip from EventLocation where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				log.trace("EventLocation found");
				a = new EventLocation();
				a.setId(id);
				a.setLineOne(rs.getString("lineone"));
				a.setLineTwo(rs.getString("linetwo"));
				a.setCity(rs.getString("city"));
				a.setState(rs.getString("state"));
				a.setZip(rs.getString("zip"));
			}
		} catch (Exception e) {
			LogUtil.logException(e,EventLocationOracle.class);
		}
		return a;
	}

	@Override
	public void deleteEventLocation(EventLocation EventLocation) {
		log.trace("Removing EventLocation from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "delete from EventLocation where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, EventLocation.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("EventLocation not deleted.");
				conn.rollback();
			}
			else
			{
				log.trace("EventLocation deleted.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,EventLocationOracle.class);
		}
	}

	@Override
	public void updateEventLocation(EventLocation EventLocation) {
		log.trace("Updating EventLocation from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update EventLocation set lineone=?, linetwo=?, city=?, state=?, zip=? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, EventLocation.getLineOne());
			pstm.setString(2, EventLocation.getLineTwo());
			pstm.setString(3, EventLocation.getCity());
			pstm.setString(4, EventLocation.getState());
			pstm.setString(5, EventLocation.getZip());
			pstm.setInt(6, EventLocation.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("Event Location not updated.");
				conn.rollback();
			}
			else
			{
				log.trace("Event Location updated.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,EventLocationOracle.class);
		}
	}

	@Override
	public EventLocation getEventLocation(EventLocation eventLocation) {
		// TODO Auto-generated method stub
		return null;
	}
}