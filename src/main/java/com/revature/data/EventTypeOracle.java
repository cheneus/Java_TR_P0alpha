package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Department;
import com.revature.beans.EventType;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class EventTypeOracle implements EventTypeDAO {
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	private static Logger log = Logger.getLogger(EventTypeOracle.class);

	@Override
	public int addEventType(Department g) {
		int key = 0;
		
		log.trace("Inserting a EventType into the database.");
		Connection conn = cu.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "insert into EventType(EventType) values(?)";
			String[] keys = { "id" };
			log.trace(sql);
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, g.getEventType());
			
			int number = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(number!=1) {
				log.warn("We didn't insert only one EventType, or any EventTypes at all.");
				conn.rollback();
			} else {
				log.trace("Inserted EventType successfully");
				if(rs.next()) {
					key = rs.getInt(1);
					g.setId(key);
					conn.commit();
				} else {
					log.trace("EventType not created");
					g.setId(0);
					conn.rollback();
				}
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, EventTypeOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, EventTypeOracle.class);
			}
		}
		
		return key;
	}

	@Override
	public Department getEventType(int id) {
		log.trace("Retrieving EventType with id = " + id);
		Department g = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, EventType from EventType where id =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				g = new Department();
				g.setId(rs.getInt("id"));
				g.setEventType(rs.getString("EventType"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, EventTypeOracle.class);
		}
		log.trace("Method returning: " + g);
		return g;
	}

	@Override
	public Department getEventTypeByName(String EventType) {
		log.trace("Retrieving EventType with EventType= " + EventType);
		Department g = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, EventType from EventType where EventType =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, EventType);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				g = new Department();
				g.setId(rs.getInt("id"));
				g.setEventType(rs.getString("EventType"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, EventTypeOracle.class);
		}
		log.trace("Method returning: " + g);
		return g;
	}

	@Override
	public Set<Department> getEventTypes() {
		log.trace("Retrieving EventTypes");
		Set<Department> EventTypes = new HashSet<Department>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, EventType from EventType";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				Department g = new Department();
				g.setId(rs.getInt("id"));
				g.setEventType(rs.getString("EventType"));
				EventTypes.add(g);
			}
		} catch (SQLException e) {
			LogUtil.logException(e, EventTypeOracle.class);
		}
		log.trace("Method returning: " + EventTypes);
		return EventTypes;
	}

	@Override
	public Set<Department> getEventTypesByBook(Book b) {
		log.trace("Retrieving EventTypes");
		Set<Department> EventTypes = new HashSet<Department>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select g.id, g.EventType from EventType g join book_EventType bg on bg.EventType_id ="
					+ "g.id where bg.book_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, b.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				Department g = new Department();
				g.setId(rs.getInt("id"));
				g.setEventType(rs.getString("EventType"));
				EventTypes.add(g);
			}
		} catch (SQLException e) {
			LogUtil.logException(e, EventTypeOracle.class);
		}
		log.trace("Method returning: " + EventTypes);
		return EventTypes;
	}

	@Override
	public void updateEventType(Department g) {
		log.trace("Updating EventType to "+ g);
		Connection conn = cu.getConnection();
        try {
        	// JDBC automatically commits data. Lets stop it.
        	conn.setAutoCommit(false);
            String sql = "update EventType set EventType = ? where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(2, g.getId());
            stmt.setString(1, g.getEventType());
            int rs = stmt.executeUpdate();
            log.trace("Updated "+rs+" rows.");
            if(rs!=1) {
            	log.warn("EventType update failure. Rolling back.");
            	conn.rollback();
            } else {
            	log.trace("EventType updated successfully. Committing.");
            	conn.commit();
            }
            
        } catch (SQLException e) {
            LogUtil.rollback(e, conn, EventTypeOracle.class);
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, EventTypeOracle.class);
			}
        }
        log.trace("Method returning: " + g);
	}

	@Override
	public void deleteEventType(Department g) {
		log.trace("Deleting EventType: "+ g);
		Connection conn = cu.getConnection();
        try {
        	// JDBC automatically commits data. Lets stop it.
        	conn.setAutoCommit(false);
            String sql = "delete from EventType where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, g.getId());
            int rs = stmt.executeUpdate();
            log.trace("Deleted "+rs+" rows.");
            if(rs!=1) {
            	log.warn("EventType delete failure. Rolling back.");
            	conn.rollback();
            } else {
            	log.trace("EventType deleted successfully. Committing.");
            	conn.commit();
            }
            
        } catch (SQLException e) {
            LogUtil.rollback(e, conn, EventTypeOracle.class);
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, EventTypeOracle.class);
			}
        }
        log.trace("Method returning: " + null);
	}

	@Override
	public int addEventType(EventType EventType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EventType getEventType(EventType EventType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventType getEventTypeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEventType(EventType EventType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEventType(EventType EventType) {
		// TODO Auto-generated method stub
		
	}

}
